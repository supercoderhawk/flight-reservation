package com.airline.service;

import com.airline.DataSource;
import com.airline.bean.Admin;
import com.airline.bean.OperationResult;
import com.airline.utils.Util;
import org.junit.Before;
import org.junit.Test;

import static com.airline.utils.Constant.reply;
import static org.junit.Assert.assertEquals;

/**
 * Created by airline on 2017/5/14.
 * 管理员信息单元测试类
 */
public class AdminServiceTest {
  @Test
  public void updateAdmin() throws Exception {
    Admin admin = new Admin("root",Util.encrypt("1234"));
    Admin admin1 = new Admin("xyb","123");
    adminRes = adminService.updateAdmin(admin);
    assertEquals(adminRes.isStatus(),true);
    adminRes =adminService.updateAdmin(admin1);
    assertEquals(adminRes.isStatus(),false);
    admin1.setPassword("");
    adminRes = adminService.updateAdmin(admin1);
    assertEquals(adminRes.isStatus(),false);
  }

  private AdminService adminService;
  private OperationResult<Admin> adminRes;

  @Before
  public void setUp() throws Exception {
    DataSource dataSource = Util.loadFileToObject("init.json", DataSource.class);
    adminService = new AdminService(dataSource);
  }

  @Test
  public void addAdmin() throws Exception {
    Admin admin1 = new Admin("", "");
    adminRes = adminService.addAdmin(admin1);
    assertEquals(adminRes.isStatus(), false);
    assertEquals(adminRes.getMsg(), reply.getAdminUserNameEmpty());
    Admin admin2 = new Admin("root", "1234");
    adminRes = adminService.addAdmin(admin2);
    assertEquals(adminRes.isStatus(), false);
    assertEquals(adminRes.getMsg(), reply.getAdminUserNameExisted());
    Admin admin3 = new Admin("xyb", "");
    adminRes = adminService.addAdmin(admin3);
    assertEquals(adminRes.isStatus(), false);
    assertEquals(adminRes.getMsg(), reply.getAdminPasswordEmpty());
    Admin admin = new Admin("xyb", Util.encrypt("1234"));
    adminRes = adminService.addAdmin(admin);
    assertEquals(adminRes.isStatus(), true);
    Admin adminCreated = adminRes.getData();
    assertEquals(adminRes.getData().getPassword(),Util.encryptRawPassword(admin.getUserName(),"1234",
                                                                          adminCreated.getSalt()));
  }

  @Test
  public void login() throws Exception {

    Admin admin1 = new Admin("", "");
    adminRes = adminService.login(admin1);
    assertEquals(adminRes.isStatus(), false);
    assertEquals(adminRes.getMsg(), reply.getAdminUserNameEmpty());
    Admin admin2 = new Admin("xyb", "");
    adminRes = adminService.login(admin2);
    assertEquals(adminRes.isStatus(), false);
    assertEquals(adminRes.getMsg(), reply.getAdminPasswordEmpty());
    Admin admin3 = new Admin("xyb", Util.encrypt("1234"));
    adminRes = adminService.login(admin3);
    assertEquals(adminRes.isStatus(), false);
    assertEquals(adminRes.getMsg(),reply.getAdminUserNameNoExist());
    Admin admin4 = new Admin("root","1234");
    adminRes = adminService.login(admin4);
    assertEquals(adminRes.isStatus(),false);
    assertEquals(adminRes.getMsg(),reply.getAdminAuthenticateFail());
    Admin admin = new Admin("root",Util.encrypt("123456"));
    adminRes = adminService.login(admin);
    assertEquals(adminRes.isStatus(),true);
  }

}