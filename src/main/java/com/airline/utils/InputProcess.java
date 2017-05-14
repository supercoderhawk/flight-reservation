package com.airline.utils;

import com.airline.bean.OperationResult;
import com.google.gson.Gson;

import static com.airline.utils.Constant.reply;

/**
 * Created by airline on 2017/5/14.
 * 对命令行输入的处理
 */
public class InputProcess {
  private static Gson gson = new Gson();
  public static <T> OperationResult<T> input2Object(String input, Class<T> t){
    String json = "{"+input+"}";
    T c = null;
    try {
      c = gson.fromJson(json,t);
    }catch (Exception e){
      return Operation.fail(reply.getAppInputInvalidate());
    }
    return Operation.success(c);
  }
}
