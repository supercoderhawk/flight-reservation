package com.airline;

import com.airline.utils.Constant.Identity;
import com.airline.bean.Admin;
import com.airline.bean.Flight;
import com.airline.bean.Order;
import com.airline.bean.Passenger;

import java.util.ArrayList;

/**
 * Created by airline on 2017/5/10.
 * 数据源，保存程序的所有数据状态
 */
public class DataSource {
  private Identity identity;
  private ArrayList<Flight> flights = new ArrayList<Flight>();
  private ArrayList<Passenger> passengers = new ArrayList<Passenger>();
  private ArrayList<Admin> admins = new ArrayList<Admin>();
  private ArrayList<Order> orders = new ArrayList<Order>();

  public Identity getIdentity() {
    return identity;
  }

  public void setIdentity(Identity identity) {
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
}
