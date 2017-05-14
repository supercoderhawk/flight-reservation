package com.airline.utils;

import com.airline.DataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by airline on 2017/5/13.
 * 工具类单元测试
 */
public class utilTest {

  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void isStartTimeValidate() throws Exception {

  }

  @Test
  public void isStartAndArrivalTimeValidate() throws Exception {
    LocalTime start = LocalTime.now();
    LocalTime end = LocalTime.now().plusHours(1);
    //assertEquals(Util.isStartAndArrivalTimeValidate(start.format(formatter), end.format(formatter)), true);

  }

  @Test
  public void isDateValidate() throws Exception {
    String date1 = "2017-05-14";
    assertEquals(Util.isDateValidate(date1),true);
    String date2 = "20170514";
    assertEquals(Util.isDateValidate(date2),false);
    String date3 = "2017-13-30";
    assertEquals(Util.isDateValidate(date3),false);
  }

  @Test
  public void generateUserID() throws Exception {
    assertEquals(Util.generateUserID().length(), 8);
  }

  @Test
  public void generateOrderID() throws Exception {
    assertNotEquals(Util.generateOrderID(LocalDateTime.now()),null);
    assertEquals(String.valueOf(Util.generateOrderID(LocalDateTime.now())).length(),10);
  }

  @Test
  public void encrypt() throws Exception {
    String source = "123456";
    String target = "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92";
    assertEquals(Util.encrypt(source), target);
  }

  @Test
  public void loadFileToObject() throws Exception {
    DataSource dataSource = Util.loadFileToObject("init.json", DataSource.class);
    assertNotEquals(dataSource, null);
  }

  @Test
  public void isTimeValidate() throws Exception {
    String str1 = "12:00:11";
    assertEquals(Util.isTimeValidate(str1), true);
    String str2 = "12:72:10";
    assertEquals(Util.isTimeValidate(str2), false);
    String str3 = "12:777:22";
    assertEquals(Util.isTimeValidate(str2), false);
  }


  @Test
  public void isTimeToTerminate() throws Exception {
    String timeString1 = LocalTime.now().plusHours(3).format(formatter);
    String timeString2 = LocalTime.now().plusHours(1).format(formatter);
    //assertEquals(Util.isTimeToTerminate(timeString1),false);
    //assertEquals(Util.isTimeToTerminate(timeString2),true);

  }

  @Test
  public void parseTime() throws Exception {
    String timeString1 = "10:10:71";
    LocalTime t = LocalTime.now();
    t = t.minusNanos(t.getNano());
    assertEquals(Util.parseTime(timeString1), null);
    assertEquals(Util.parseTime(t.format(formatter)), t);
  }

}