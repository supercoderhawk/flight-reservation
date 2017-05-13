package com.airline.bean;

/**
 * Created by airline on 2017/5/10.
 * 业务操作类的返回值
 */
public class OperationResult<T> {
  private boolean status;
  private T data;
  private String msg;
  public OperationResult() {
  }

  public OperationResult(boolean status, T data,String msg) {
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

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}
