package com.alibaba.fastjson.serializer.formatter;

import com.alibaba.fastjson.JSON;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * Created by jiangyu on 2019-03-08 23:47.
 */
public class DateFormatter implements TimeFormatter {

  public final static DateFormatter FORMATTER = new DateFormatter();

  private DateFormatter() {
  }

  @Override
  public String format(String format, Object time) {
    if (format == null || format.equals("")) {
      format = JSON.DEFFAULT_DATE_FORMAT;
    }
    SimpleDateFormat dateFormat = new SimpleDateFormat(format, JSON.defaultLocale);
    dateFormat.setTimeZone(JSON.defaultTimeZone);
    return dateFormat.format(time);
  }

  @Override
  public long toUnixTime(Object time) {
    if (time instanceof Date) {
      return Date.class.cast(time).getTime();
    } else {
      return Calendar.class.cast(time).getTimeInMillis();
    }
  }

  @Override
  public boolean supportUnixTime(String format, Object time) {
    return Objects.equals(UNIXTIME_FORMAT, format);
  }

  @Override
  public boolean accept(Class type) {
    return type != null && (Date.class == type || Calendar.class == type);
  }
}
