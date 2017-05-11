package com.airline.service;

import com.airline.DataSource;
import com.airline.dao.PassengerDao;

/**
 * Created by airline on 2017/5/11.
 * 乘客业务处理类
 */
public class PassengerService extends PassengerDao {
  public PassengerService(DataSource dataSource) {
    super(dataSource);
  }
}
