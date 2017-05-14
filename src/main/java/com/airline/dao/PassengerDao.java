package com.airline.dao;

import com.airline.DataSource;
import com.airline.bean.Passenger;

/**
 * Created by airline on 2017/5/10.
 * 存取乘客数据
 */
public class PassengerDao extends BaseDao {
  public PassengerDao(DataSource dataSource) {
    super(dataSource);
  }

  protected void createPassenger(Passenger passenger) {
    dataSource.getPassengers().add(passenger);
  }

  protected Passenger getPassengerByID(Integer passengerID){
    for(Passenger passenger:dataSource.getPassengers()){
      if(passenger.getPassengerID().equals(passengerID)){
        return passenger;
      }
    }
    return null;
  }
}
