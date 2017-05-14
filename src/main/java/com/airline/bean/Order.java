package com.airline.bean;

import com.airline.utils.Constant.OrderStatus;

import java.util.Date;

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
  private String remark;

  public Order() {
  }

  public Order(int passengerID, String seat, Flight flight, Date createDate, OrderStatus orderStatus, String remark) {
    this.passengerID = passengerID;
    this.seat = seat;
    this.flight = flight;
    this.createDate = createDate;
    this.orderStatus = orderStatus;
    this.remark = remark;
  }

  public int getPassengerID() {
    return passengerID;
  }

  public void setPassengerID(int passengerID) {
    this.passengerID = passengerID;
  }

  public String getSeat() {
    return seat;
  }

  public void setSeat(String seat) {
    this.seat = seat;
  }

  public Flight getFlight() {
    return flight;
  }

  public void setFlight(Flight flight) {
    this.flight = flight;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
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
}
