package com.airline.bean;

import java.util.Date;
import com.airline.utils.Constant.OrderStatus;

/**
 * Created by airline on 2017/5/10.
 * 订单对象
 */
public class Order {
  private int passengerID;
  private String seat;
  private Flight flight;
  private Date createDate;
  private OrderStatus orderStatus;
}
