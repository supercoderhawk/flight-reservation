package com.airline.utils;

import com.airline.bean.OperationResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang.StringUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.airline.utils.Constant.reply;

/**
 * Created by airline on 2017/5/11.
 * 工具类，封装一些常用的函数
 */
public class Util {
  private static Gson gson = new GsonBuilder().
      registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();

  public static <T> T loadFileToObject(String path, Class<T> c) {
    if (StringUtils.isEmpty(path)) {
      return null;
    } else if (!path.startsWith("/")) {
      path = "/" + path;
    }
    String data = loadResourceFile(path);
    return gson.fromJson(data, c);
  }

  /**
   * 读取当前类加载器目录的resources目录下的文件，输出为字符串
   *
   * @param path: 文件在resources文件夹下的相对路径
   * @return: 文件字符串
   */
  private static String loadResourceFile(String path) {
    String data = null;
    try {
      InputStream in = Util.class.getResourceAsStream(path);
      data = new Scanner(in, StandardCharsets.UTF_8.name()).useDelimiter("\\Z").next();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return data;
  }

  /**
   * 将形如`userName:root,password:12345`形式的输入字符串转换为对应的类对象
   *
   * @param input: 输入字符串
   * @param t:     目标类类型
   * @param <T>:   目标类
   * @return: 目标类的对象
   */
  public static <T> OperationResult<T> input2Object(String input, Class<T> t) {
    String json = "{" + input + "}";
    T c = null;
    try {
      c = gson.fromJson(json, t);
    } catch (Exception e) {
      return Operation.fail(reply.getAppInputInvalidate());
    }
    return Operation.success(c);
  }

  public static LocalTime parseTime(String timeString) {
    LocalTime time = null;
    try {
      time = LocalTime.parse(timeString);
    } catch (DateTimeParseException e) {
      System.out.println(e.getCause().getLocalizedMessage());
      return null;
    }
    return time;
  }

  public static boolean isTimeValidate(String timeString) {
    String pattern = "\\d{2}:\\d{2}:\\d{2}";
    Pattern r = Pattern.compile(pattern);
    Matcher m = r.matcher(timeString);
    if (m.matches()) {
      try {
        LocalTime time = LocalTime.parse(timeString);
      } catch (DateTimeParseException e) {
        System.out.println(e.getCause().getLocalizedMessage());
        return false;
      }
      return true;
    }
    return false;
  }

  private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddHH:mm:ss");

  public static boolean isStartTimeValidate(String timeString, String dateString) {
    LocalDateTime time = LocalDateTime.from(formatter.parse(dateString + timeString));
    return time != null && Duration.between(LocalDateTime.now(), time).toHours() >= 2;
  }

  public static boolean isStartAndArrivalTimeValidate(String startTime, String startDate,
                                                      String arrivalTime, String arrivalDate) {
    LocalDateTime start = LocalDateTime.from(formatter.parse(startDate + startTime));
    LocalDateTime arrival = LocalDateTime.from(formatter.parse(arrivalDate + arrivalTime));
    return start != null && arrival != null && !Duration.between(start, arrival).isNegative();
  }

  public static boolean isDateValidate(String dateString) {
    String pattern = "\\d{4}-\\d{2}-\\d{2}";
    Pattern r = Pattern.compile(pattern);
    Matcher m = r.matcher(dateString);
    if (m.matches()) {
      try {
        LocalDate date = LocalDate.parse(dateString);
      } catch (DateTimeParseException e) {
        System.out.println(e.getCause().getLocalizedMessage());
        return false;
      }
      return true;
    }
    return false;
  }

  private static String[] chars = new String[]{"a", "b", "c", "d", "e", "f",
      "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
      "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
      "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
      "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
      "W", "X", "Y", "Z"};

  public static String generateUserID() {
    int length = 8;
    StringBuilder shortBuffer = new StringBuilder();
    String uuid = UUID.randomUUID().toString().replace("-", "");
    for (int i = 0; i < length; i++) {
      String str = uuid.substring(i * 4, i * 4 + 4);
      int x = Integer.parseInt(str, 16);
      shortBuffer.append(chars[x % 0x3E]);
    }
    return shortBuffer.toString();
  }

  public static Long generateOrderID(LocalDateTime time) {
    return time.toInstant(OffsetDateTime.now().getOffset()).getEpochSecond();
  }

  public static String encrypt(String str) {
    StringBuilder sb = new StringBuilder();
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      md.update(str.getBytes());
      byte byteData[] = md.digest();
      for (byte b : byteData) {
        sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
      }
    } catch (NoSuchAlgorithmException e) {
      return null;
    }
    return sb.toString();
  }

  public static String encryptRawPassword(String userName, String password, String salt) {
    return encrypt(userName + encrypt(password) + salt);
  }

}
