package com.airline.dao;

import com.airline.DataSource;
import com.airline.bean.Order;

/**
 * Created by airline on 2017/5/10.
 * 存取订单数据
 */
public class OrderDao extends BaseDao{
  protected OrderDao(DataSource dataSource) {
    super(dataSource);
  }

  protected Order getOrderByID(long orderID){
    for(Order order:dataSource.getOrders()){
      if(order.getOrderID() == orderID){
        return order;
      }
    }
    return null;
  }

  protected void createOrder(Order order){
    dataSource.getOrders().add(new Order(order));
  }

  protected void updateOrder(Order order){

  }

}
