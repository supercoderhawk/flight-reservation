package com.airline.dao;

import com.airline.DataSource;
import com.airline.bean.Admin;

/**
 * Created by airline on 2017/5/13.
 * 存取管理员数据
 */
public class AdminDao extends BaseDao {
  public AdminDao(DataSource dataSource) {
    super(dataSource);
  }

  protected Admin getAdminByName(String userName) {
    for (Admin admin : dataSource.getAdmins()) {
      if (admin.getUserName().equals(userName)) {
        return admin;
      }
    }
    return null;
  }

  protected void createAdmin(Admin admin) {
    dataSource.getAdmins().add(new Admin(admin.getUserName(), admin.getPassword()));
  }
}
