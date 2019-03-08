package com.alibaba.fastjson.serializer.formatter;

/**
 * Created by jiangyu on 2019-03-08 23:45.
 */
public interface TimeFormatter {

  String UNIXTIME_FORMAT = "unixtime";

  Object format(String format, Object time);

  long toUnixTime(Object time);

  boolean supportUnixTime(String format, Object time);

  boolean accept(Class type);
}
