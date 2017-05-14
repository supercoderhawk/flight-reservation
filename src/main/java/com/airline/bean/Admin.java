package com.airline.bean;

/**
 * Created by airline on 2017/5/10.
 * 管理员信息对象
 */
public class Admin {
  private String userName;
  private String password;
  private String salt;
  public Admin() {
  }

  public Admin(String userName, String password) {
    this.userName = userName;
    this.password = password;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }
}
