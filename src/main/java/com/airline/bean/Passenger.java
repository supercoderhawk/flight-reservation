package com.airline.bean;

import java.util.ArrayList;

/**
 * Created by airline on 2017/5/10.
 * 乘客信息对象
 */
public class Passenger {
  private Integer passengerID;
  private String realName;
  private String identityID;
  private String password;
  private String salt;
  private ArrayList<Order> orderList = new ArrayList<>();

  public Passenger(String realName, String identityID, String password) {
    this.realName = realName;
    this.identityID = identityID;
    this.password = password;
  }

  public Passenger(Passenger passenger) {
    this.passengerID = passenger.getPassengerID();
    this.realName = passenger.getRealName();
    this.identityID = passenger.getIdentityID();
    this.password = passenger.getPassword();
    this.salt = passenger.getSalt();
    this.orderList = passenger.getOrderList();
  }

  public Passenger() {
  }

  public Integer getPassengerID() {
    return passengerID;
  }

  public void setPassengerID(Integer passengerID) {
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

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }
}
