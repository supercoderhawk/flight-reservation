package com.airline.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

/**
 * Created by airline on 2017/5/13.
 * 工具类单元测试
 */
public class utilTest {

  DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("HH:mm:ss");
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
    assertEquals(util.isStartAndArrivalTimeValidate(start.format(formatter),end.format(formatter)),true);

  }

  @Test
  public void isDateValidate() throws Exception {
  }

  @Test
  public void generateUserID() throws Exception {
  }

  @Test
  public void generateOrderID() throws Exception {
  }

  @Test
  public void encrypt() throws Exception {
  }

  @Test
  public void loadFileToObject() throws Exception {
  }

  @Test
  public void isTimeValidate() throws Exception {
  }

  @Test
  public void loadDataFromFile() throws Exception {
  }

  @Test
  public void isTimeToTerminate() throws Exception {
    String timeString1 = LocalTime.now().plusHours(3).format(formatter);
    String timeString2 = LocalTime.now().plusHours(1).format(formatter);
    assertEquals(util.isTimeToTerminate(timeString1),false);
    assertEquals(util.isTimeToTerminate(timeString2),true);

  }

  @Test
  public void parseTime() throws Exception {
    String timeString1 = "10:10:71";
    LocalTime t = LocalTime.now();
    t = t.minusNanos(t.getNano());
    assertEquals(util.parseTime(timeString1),null);
    assertEquals(util.parseTime(t.format(formatter)),t);
  }

}