package com.example.day14.cookie;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/test/cookieDemo3")
public class CookieDemo3 extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    this.doPost(req, resp);
  }


  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    System.out.println("there");
    //创建cookie对象
    Cookie cookie = new Cookie("msg","hello");
    Cookie cookie2 = new Cookie("name","test");
    //发送cookie
    resp.addCookie(cookie);
    resp.addCookie(cookie2);
  }
}
