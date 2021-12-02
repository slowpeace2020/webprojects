package com.example.day14.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/responseDemo5")
public class ResponseDemo5 extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    this.doPost(req, resp);
  }


  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    //获取流对象之前，设置流的默认编码
//    resp.setCharacterEncoding("utf-8");
    //告诉浏览器，服务器发送的消息使用的编码方式，建议浏览器使用该编码解码
//    resp.setHeader("content-type","text/html;charset=utf-8");
    //简单方式
//    resp.setContentType("text/html;charset=utf-8");

      //获取字节输出流
      ServletOutputStream sos = resp.getOutputStream();
      //输出数据
      sos.write("hello你好".getBytes());


  }
}
