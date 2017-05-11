package com.airline.bean.Result;

/**
 * Created by airline on 2017/5/10.
 * 业务操作类的返回值
 */
public class OperationResult {
  private boolean status;
  private Object data;
  private String msg;
  public OperationResult() {
  }

  public OperationResult(boolean status, Object data,String msg) {
    this.status = status;
    this.data = data;
    this.msg = msg;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}
