package com.airline.bean;

import java.util.ArrayList;

/**
 * Created by airline on 2017/5/10.
 * 乘客信息对象
 */
public class Passenger {
  private int passengerID;
  private String realName;
  private String identityID;
  private String password;
  private ArrayList<Order> orderList;

  public Passenger(int passengerID, String realName, String identityID, String password) {
    this.passengerID = passengerID;
    this.realName = realName;
    this.identityID = identityID;
    this.password = password;
  }

  public Passenger() {
  }

  public int getPassengerID() {
    return passengerID;
  }

  public void setPassengerID(int passengerID) {
    this.passengerID = passengerID;
  }

  public String getRealName() {
    return realName;
  }

  public void setRealName(String realName) {
    this.realName = realName;
  }

  public String getIdentityID() {
    return identityID;
  }

  public void setIdentityID(String identityID) {
    this.identityID = identityID;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public ArrayList<Order> getOrderList() {
    return orderList;
  }

  public void setOrderList(ArrayList<Order> orderList) {
    this.orderList = orderList;
  }
}
