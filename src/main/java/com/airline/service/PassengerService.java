package com.airline.service;

import com.airline.DataSource;
import com.airline.bean.OperationResult;
import com.airline.bean.Order;
import com.airline.bean.Passenger;
import com.airline.dao.PassengerDao;
import com.airline.utils.Operation;
import com.airline.utils.Util;
import org.apache.commons.lang.RandomStringUtils;

import static com.airline.utils.Constant.reply;

/**
 * Created by airline on 2017/5/11.
 * 乘客业务处理类
 */
public class PassengerService extends PassengerDao {
  public PassengerService(DataSource dataSource) {
    super(dataSource);
  }

  public OperationResult<Passenger> registerPassenger(Passenger passenger) {
    String salt = RandomStringUtils.randomAlphanumeric(16);
    String pwd = Util.encrypt(passenger.getIdentityID() + passenger.getPassword() + salt);
    passenger.setPassword(pwd);
    passenger.setSalt(salt);
    createPassenger(passenger);
    return Operation.success(passenger);
  }

  public OperationResult<Passenger> login(Passenger passenger){
    OperationResult<Object> res = dataSource.getPassengerCheck();
    if(!res.isStatus()){
      return Operation.fail(res.getMsg());
    }
    Passenger fullPassenger = getPassengerByID(passenger.getIdentityID());
    if(fullPassenger == null){
      return Operation.fail(reply.getPassengerNotExist());
    }
    String pwd = Util.encrypt(fullPassenger.getIdentityID()+passenger.getPassword()+fullPassenger.getSalt());
    if(fullPassenger.getPassword().equals(pwd)){
      return Operation.success(fullPassenger);
    }
    return Operation.fail(reply.getPassengerAuthenticateFailed());
  }

  void addOrderToPassenger(Order order) {
    for (Passenger passenger : dataSource.getPassengers()) {
      if (passenger.getPassengerID().equals(order.getPassengerID())) {
        passenger.getOrderList().add(order);
      }
    }
  }

}
