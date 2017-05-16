package com.airline;

import com.airline.bean.*;
import com.airline.utils.Constant;

import java.util.ArrayList;

/**
 * Created by airline on 2017/5/10.
 * 数据源，保存程序的所有数据状态
 */
public class DataSource {
  private Constant.Identity identity;
  private ArrayList<Flight> flights = new ArrayList<Flight>();
  private ArrayList<Passenger> passengers = new ArrayList<Passenger>();
  private ArrayList<Admin> admins = new ArrayList<Admin>();
  private ArrayList<Order> orders = new ArrayList<Order>();
  private ArrayList<String> cities = new ArrayList<>();
  private OperationResult<Object> modifyFlight;
  private OperationResult<Flight> orderCheck;
  private OperationResult<Object> passengerCheck;
  private boolean isLogin;

  public DataSource() {

  }

  public OperationResult<Object> getModifyFlight() {
    return modifyFlight;
  }

  public void setModifyFlight(OperationResult<Object> modifyFlight) {
    this.modifyFlight = modifyFlight;
  }

  public Constant.Identity getIdentity() {
    return identity;
  }

  public void setIdentity(Constant.Identity identity) {
    this.identity = identity;
  }

  public ArrayList<Flight> getFlights() {
    return flights;
  }

  public void setFlights(ArrayList<Flight> flights) {
    this.flights = flights;
  }

  public ArrayList<Passenger> getPassengers() {
    return passengers;
  }

  public void setPassengers(ArrayList<Passenger> passengers) {
    this.passengers = passengers;
  }

  public ArrayList<Admin> getAdmins() {
    return admins;
  }

  public void setAdmins(ArrayList<Admin> admins) {
    this.admins = admins;
  }

  public ArrayList<Order> getOrders() {
    return orders;
  }

  public void setOrders(ArrayList<Order> orders) {
    this.orders = orders;
  }

  public ArrayList<String> getCities() {
    return cities;
  }

  public void setCities(ArrayList<String> cities) {
    this.cities = cities;
  }

  public boolean isLogin() {
    return isLogin;
  }

  public void setLogin(boolean login) {
    isLogin = login;
  }

  public OperationResult<Flight> getOrderCheck() {
    return orderCheck;
  }

  public void setOrderCheck(OperationResult<Flight> orderCheck) {
    this.orderCheck = orderCheck;
  }

  public OperationResult<Object> getPassengerCheck() {
    return passengerCheck;
  }

  public void setPassengerCheck(OperationResult<Object> passengerCheck) {
    this.passengerCheck = passengerCheck;
  }
}
