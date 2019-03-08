package com.alibaba.fastjson.serializer.formatter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangyu on 2019-03-08 23:45.
 */
public class TimeFormatterChain implements TimeFormatter {

  public final static TimeFormatter TIME_FORMATTER = new TimeFormatterChain();

  private List<TimeFormatter> formatterList;
  private TimeFormatter EMPTY = new EmptyFormatter();

  private TimeFormatterChain() {
    this.formatterList = new ArrayList<TimeFormatter>(3);
    formatterList.add(DateFormatter.FORMATTER);
    formatterList.add(Jdk8TimeFormatter.FORMATTER);
    formatterList.add(JodaFormatter.FORMATTER);
    formatterList.add(EMPTY);
  }

  private TimeFormatter getFormatter(String format, Object timeObject) {
    if (format == null || timeObject == null) {
      return EMPTY;
    }
    for (TimeFormatter formatter : formatterList) {
      if (formatter.accept(timeObject.getClass())) {
        return formatter;
      }
    }
    return EMPTY;
  }

  @Override
  public Object format(String format, Object time) {
    TimeFormatter formatter = getFormatter(format, time);
    return formatter.format(format, time);
  }

  @Override
  public long toUnixTime(Object time) {
    for (TimeFormatter formatter : formatterList) {
      if (formatter.supportUnixTime(UNIXTIME_FORMAT, time)) {
        return toUnixTime(time);
      }
    }
    return 0;
  }

  @Override
  public boolean supportUnixTime(String format, Object time) {
    for (TimeFormatter formatter : formatterList) {
      if (formatter.supportUnixTime(format, time)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean accept(Class type) {
    return true;
  }
}
