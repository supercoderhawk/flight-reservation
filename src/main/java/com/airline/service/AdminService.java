package com.airline.service;

import com.airline.DataSource;
import com.airline.bean.Admin;
import com.airline.bean.OperationResult;
import com.airline.dao.AdminDao;
import com.airline.utils.Operation;
import com.airline.utils.Util;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

import static com.airline.utils.Constant.reply;

/**
 * Created by airline on 2017/5/13.
 * 管理员业务处理类
 */

public class AdminService extends AdminDao {
  public AdminService(DataSource dataSource) {
    super(dataSource);
  }

  public OperationResult<Admin> addAdmin(Admin admin) {
    String userName = admin.getUserName();
    if (StringUtils.isEmpty(userName)) {
      return Operation.fail(reply.getAdminUserNameEmpty());
    }else if(StringUtils.isEmpty(admin.getPassword())){
      return Operation.fail(reply.getAdminPasswordEmpty());
    }
    Admin oldAdmin = getAdminByName(admin.getUserName());
    if (oldAdmin != null) {
      return Operation.fail(reply.getAdminUserNameExisted());
    }
    String salt = RandomStringUtils.randomAlphanumeric(16);
    admin.setPassword(Util.encrypt(admin.getUserName()+admin.getPassword()+salt));
    admin.setSalt(salt);
    createAdmin(admin);
    return Operation.success(new Admin(admin));
  }

  public OperationResult<Admin> login(Admin admin) {
    String userName = admin.getUserName();
    if (StringUtils.isEmpty(userName)) {
      return Operation.fail(reply.getAdminUserNameEmpty());
    }else if(StringUtils.isEmpty(admin.getPassword())){
      return Operation.fail(reply.getAdminPasswordEmpty());
    }
    Admin trueAdmin = getAdminByName(admin.getUserName());
    if (trueAdmin == null) {
      return Operation.fail(reply.getAdminUserNameNoExist());
    }
    String pwd = Util.encrypt(userName + admin.getPassword() + trueAdmin.getSalt());
    if (trueAdmin.getPassword().equals(pwd)) {
      return Operation.success(trueAdmin);
    }
    return Operation.fail(reply.getAdminAuthenticateFail());
  }
}
