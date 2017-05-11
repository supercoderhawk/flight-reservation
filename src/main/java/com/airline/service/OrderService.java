package com.airline.service;

import com.airline.DataSource;
import com.airline.dao.OrderDao;

/**
 * Created by airline on 2017/5/11.
 * 订单业务处理类
 */
public class OrderService extends OrderDao{
  public OrderService(DataSource dataSource) {
    super(dataSource);
  }
}
