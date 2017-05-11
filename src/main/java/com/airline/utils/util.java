package com.airline.utils;

import com.airline.DataSource;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by airline on 2017/5/11.
 */
public class util {
  public static DataSource loadDataFromFile(String path){
    DataSource dataSource = new DataSource();
    Gson gson = new Gson();
    String data = null;
    try {
      String fullPath = util.class.getClassLoader().getResource(path).getFile();
      data = new Scanner(new File(fullPath)).useDelimiter("\\Z").next();
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
    return gson.fromJson(data,DataSource.class);
  }
}
