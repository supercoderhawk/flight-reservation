package com.airline.utils;

import com.airline.bean.Flight;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by airline on 2017/5/10.
 * 一些枚举和常量
 */
public class Constant {
  public enum Identity {ADMIN,USER}

  public enum OrderStatus {UNPAID, PAID, CANCEL}

  public enum FlightStatus {UNPUBLISHED, AVAILABLE, FULL, TERMINATE}

  public static Map<OrderStatus,String> orderStatusMap = new HashMap<>();

  public static Map<FlightStatus,String> flightStatusMap = new HashMap<>();

  static {
    orderStatusMap.put(OrderStatus.PAID,"已支付");
    orderStatusMap.put(OrderStatus.UNPAID,"未支付");
    orderStatusMap.put(OrderStatus.CANCEL,"已取消");
    flightStatusMap.put(FlightStatus.AVAILABLE,"可预订");
    flightStatusMap.put(FlightStatus.FULL,"订满");
    flightStatusMap.put(FlightStatus.UNPUBLISHED,"未发布");
    flightStatusMap.put(FlightStatus.TERMINATE,"终止");
  }

}
