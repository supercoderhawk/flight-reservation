package com.airline.utils;

import com.airline.bean.OperationResult;

/**
 * Created by airline on 2017/5/10.
 * 业务操作返回结果的封装类
 */
public class Operation {
  public static OperationResult success(){
    return new OperationResult(true,null,null);
  }
  public static OperationResult success(Object obj){
    return new OperationResult(true,obj,null);
  }
  public static OperationResult fail(String msg){
    return new OperationResult(false,null,msg);
  }
}
