package com.airline.utils;

import com.airline.DataSource;
import com.airline.bean.Admin;
import com.airline.bean.OperationResult;
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
  @Test
  public void encryptRawPassword() throws Exception {
    String userName = "root";
    String password = "123456";
    String salt = "Dy7Rd6lQxJnFNG7c";
    String encrypted = "f7ae18e37b3c6778ee780877709874f5e7c4348952acbf20eef3b560e16fc8ad";
    assertEquals(Util.encryptRawPassword(userName,password,salt),encrypted);
    //userName = "测试";
    String id = "12345678";
    password = "123456";
    //salt = RandomStringUtils.randomAlphanumeric(16);
    salt = "UVNQy6jyhgrh77lp";
    //System.out.println(salt);
    //System.out.println(Util.encryptRawPassword(id,password,salt));
  }

  @Test
  public void input2Object() throws Exception {
    String input1 = "userName:xyb,password:123";
    OperationResult<Admin> admin = Util.input2Object(input1, Admin.class);
    assertEquals(admin.isStatus(), true);
    assertEquals(admin.getData().getPassword(), "123");
    assertEquals(admin.getData().getUserName(), "xyb");
    String input2 = "user:xyb,password:123";
    admin = Util.input2Object(input2, Admin.class);
    assertEquals(admin.isStatus(), true);
    assertEquals(admin.getData().getUserName(), null);
    String input3 = "abcdefg";
    admin = Util.input2Object(input3, Admin.class);
    assertEquals(admin.isStatus(), false);
  }

  private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

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
    assertEquals(Util.isDateValidate(date1), true);
    String date2 = "20170514";
    assertEquals(Util.isDateValidate(date2), false);
    String date3 = "2017-13-30";
    assertEquals(Util.isDateValidate(date3), false);
  }

  @Test
  public void generateUserID() throws Exception {
    assertEquals(Util.generateUserID().length(), 8);
  }

  @Test
  public void generateOrderID() throws Exception {
    assertNotEquals(Util.generateOrderID(LocalDateTime.now()), null);
    assertEquals(String.valueOf(Util.generateOrderID(LocalDateTime.now())).length(), 10);
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