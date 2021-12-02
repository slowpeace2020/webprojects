package com.example.day14.request;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test/requestdemo2")
public class requestDemo2 extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    //获取请求消息体--请求参数

    //获取字符输入流
    BufferedReader reader = req.getReader();

    //读取数据
    String line= null;
    while ((line=reader.readLine())!=null){
      System.out.println(line);
    }

  }
}
