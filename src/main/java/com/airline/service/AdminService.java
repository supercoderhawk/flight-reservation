package com.airline.service;

import com.airline.DataSource;
import com.airline.bean.Admin;
import com.airline.bean.OperationResult;
import com.airline.dao.AdminDao;
import com.airline.utils.Operation;
import static com.airline.utils.Constant.reply;

/**
 * Created by airline on 2017/5/13.
 * 管理员业务处理类
 */

public class AdminService extends AdminDao{
  public AdminService(DataSource dataSource) {
    super(dataSource);
  }

  public OperationResult<Admin> addAdmin(Admin admin){
    String userName = admin.getUserName();
    if(userName == null || userName.length() == 0){
      return Operation.fail(reply.getAdminUserNameEmpty());
    }
    Admin oldAdmin = getAdminByName(admin.getUserName());
    if(oldAdmin != null){
      return Operation.fail(reply.getAdminUserNameExisted());
    }
    return Operation.success(admin);
  }
}
