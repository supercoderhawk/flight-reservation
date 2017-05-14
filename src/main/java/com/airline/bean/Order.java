package com.airline.bean;

import com.airline.utils.Constant.OrderStatus;

import java.time.LocalDateTime;

/**
 * Created by airline on 2017/5/10.
 * 订单对象
 */
public class Order {
  private Long orderID;
  private Integer passengerID;
  private String seat;
  private String flightSerial;
  private LocalDateTime createDate;
  private OrderStatus orderStatus;
  private String remark;

  public Order() {
  }

  public Order(Order order){
    this.orderID = order.getOrderID();
    this.passengerID = order.getPassengerID();
    this.seat = order.getSeat();
    this.flightSerial = order.getFlightSerial();
    this.createDate = order.getCreateDate();
    this.orderStatus = order.getOrderStatus();
    this.remark = order.getRemark();
  }

  public Order(Long orderID,Integer passengerID, String seat, String flightSerial,
               LocalDateTime createDate, OrderStatus orderStatus, String remark) {
    this.orderID = orderID;
    this.passengerID = passengerID;
    this.seat = seat;
    this.flightSerial = flightSerial;
    this.createDate = createDate;
    this.orderStatus = orderStatus;
    this.remark = remark;
  }

  public Integer getPassengerID() {
    return passengerID;
  }

  public void setPassengerID(Integer passengerID) {
    this.passengerID = passengerID;
  }

  public String getSeat() {
    return seat;
  }

  public void setSeat(String seat) {
    this.seat = seat;
  }

  public String getFlightSerial() {
    return flightSerial;
  }

  public void setFlightSerial(String flightSerial) {
    this.flightSerial = flightSerial;
  }

  public LocalDateTime getCreateDate() {
    return createDate;
  }

  public void setCreateDate(LocalDateTime createDate) {
    this.createDate = createDate;
  }

  public OrderStatus getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(OrderStatus orderStatus) {
    this.orderStatus = orderStatus;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public Long getOrderID() {
    return orderID;
  }

  public void setOrderID(Long orderID) {
    this.orderID = orderID;
  }

}
