package com.airline.dao;

import com.airline.DataSource;

/**
 * Created by airline on 2017/5/10.
 * 数据存取对象的基类
 */
public class BaseDao {
  protected DataSource dataSource;

  protected BaseDao(DataSource dataSource) {
    this.dataSource = dataSource;
  }
}
