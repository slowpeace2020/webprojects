package com.example.day14.request;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test/requestdemo1")
public class requestDemo1 extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
//    1. 获取请求方式： GET
    String method =req.getMethod();
    System.out.println(method);
//    2. 获取虚拟目录： /day14
    String path =req.getContextPath();
    System.out.println(path);
//    3. 获取Servlet路径 /demo1
    String servletpath =req.getServletPath();
    System.out.println(servletpath);
//    4. 获取get方式请求参数：name=luren
    String data = req.getQueryString();
    System.out.println(data);
//    5. 获取请求URI
    String url = req.getRequestURL().toString();
    System.out.println(url);
    //协议端口
    String version = req.getProtocol();
    System.out.println(version);
    //客户IP地址
    String ip = req.getRemoteAddr();
    System.out.println(ip);
  }
}
