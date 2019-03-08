package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jiangyu on 2019-03-09 00:57.
 */
public class TimeSerializeTest {


  @Test
  public void dateTest() {
    Date date = new Date(119, 0, 1, 1, 1, 1);
    Time time = new Time(date);
    JSONObject json = (JSONObject) JSONObject.toJSON(time);
    System.out.println(json.toJSONString());
    Assert.assertEquals(json.getString("date2"), "2019");
    Assert.assertEquals(json.getString("date3"), "2019-01");
    Assert.assertEquals(json.getString("date4"), "2019-01-01");
    Assert.assertEquals(json.getString("date5"), "2019-01-01 01");
    Assert.assertEquals(json.getString("date6"), "2019-01-01 01:01:01");

    time = JSONObject.parseObject(JSONObject.toJSONString(time), Time.class);
    Assert.assertNotNull(time);
  }

  @Test
  public void jdk8TimeTest() {
    Jdk8Time time = new Jdk8Time(Instant.parse("2019-01-01T01:01:01.000Z"));
    JSONObject json = (JSONObject) JSONObject.toJSON(time);
    System.out.println(json.toJSONString());
    Assert.assertEquals(json.getString("time1"), "2019-01-01");
    Assert.assertEquals(json.getString("time2"), "2019-01-01 09:01:01.000");
    Assert.assertEquals(json.getString("time3"), "2019-01-01");
    Assert.assertEquals(json.getString("time4"), "2019-01-01 09:01:01.000+0800");
    Assert.assertEquals(json.getString("time5"), "2019-01-01");
    Assert.assertEquals(json.getString("time6"), "2019-01-01 09:01:01");
    Assert.assertEquals(json.getString("time7"), "2019-01-01");
    Assert.assertEquals(json.getString("time8"), "2019-01");
  }


  static class Time {

    public Time() {
    }

    public Time(Date date) {
      this.date1 = date;
      this.date5 = date;
      this.date2 = date;
      this.date3 = date;
      this.date4 = date;
      this.date6 = date;
    }

    private Date date1 = new Date();
    @JSONField(format = "yyyy")
    private Date date2 = new Date();
    @JSONField(format = "yyyy-MM")
    private Date date3 = new Date();
    @JSONField(format = "yyyy-MM-dd")
    private Date date4 = new Date();
    @JSONField(format = "yyyy-MM-dd hh")
    private Date date5 = new Date();
    @JSONField(format = "yyyy-MM-dd hh:MM:ss")
    private Date date6 = new Date();

    public Date getDate1() {
      return date1;
    }

    public void setDate1(Date date1) {
      this.date1 = date1;
    }

    public Date getDate2() {
      return date2;
    }

    public void setDate2(Date date2) {
      this.date2 = date2;
    }

    public Date getDate3() {
      return date3;
    }

    public void setDate3(Date date3) {
      this.date3 = date3;
    }

    public Date getDate4() {
      return date4;
    }

    public void setDate4(Date date4) {
      this.date4 = date4;
    }

    public Date getDate5() {
      return date5;
    }

    public void setDate5(Date date5) {
      this.date5 = date5;
    }

    public Date getDate6() {
      return date6;
    }

    public void setDate6(Date date6) {
      this.date6 = date6;
    }
  }

  static class Jdk8Time {

    public Jdk8Time() {
    }

    public Jdk8Time(Instant instant) {
      this.time1 = instant;
      this.time2 = instant;
      this.time3 = ZonedDateTime.ofInstant(instant, JSON.defaultTimeZone.toZoneId());
      this.time4 = ZonedDateTime.ofInstant(instant, JSON.defaultTimeZone.toZoneId());
      this.time5 = LocalDateTime.ofInstant(instant, JSON.defaultTimeZone.toZoneId());
      this.time6 = LocalDateTime.ofInstant(instant, JSON.defaultTimeZone.toZoneId());
      this.time7 = LocalDate.from(time3);
      this.time8 = LocalDate.from(time3);
    }
    @JSONField(format = "yyyy-MM-dd")
    private Instant time1;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss.SSS")
    private Instant time2;
    @JSONField(format = "yyyy-MM-dd")
    private ZonedDateTime time3;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss.SSSZ")
    private ZonedDateTime time4;
    @JSONField(format = "yyyy-MM-dd")
    private LocalDateTime time5;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time6;
    @JSONField(format = "yyyy-MM-dd")
    private LocalDate time7;
    @JSONField(format = "yyyy-MM")
    private LocalDate time8;

    public Instant getTime1() {
      return time1;
    }

    public void setTime1(Instant time1) {
      this.time1 = time1;
    }

    public Instant getTime2() {
      return time2;
    }

    public void setTime2(Instant time2) {
      this.time2 = time2;
    }

    public ZonedDateTime getTime3() {
      return time3;
    }

    public void setTime3(ZonedDateTime time3) {
      this.time3 = time3;
    }

    public ZonedDateTime getTime4() {
      return time4;
    }

    public void setTime4(ZonedDateTime time4) {
      this.time4 = time4;
    }

    public LocalDateTime getTime5() {
      return time5;
    }

    public void setTime5(LocalDateTime time5) {
      this.time5 = time5;
    }

    public LocalDateTime getTime6() {
      return time6;
    }

    public void setTime6(LocalDateTime time6) {
      this.time6 = time6;
    }

    public LocalDate getTime7() {
      return time7;
    }

    public void setTime7(LocalDate time7) {
      this.time7 = time7;
    }

    public LocalDate getTime8() {
      return time8;
    }

    public void setTime8(LocalDate time8) {
      this.time8 = time8;
    }
  }

}
