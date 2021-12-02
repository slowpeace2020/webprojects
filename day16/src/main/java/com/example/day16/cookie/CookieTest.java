package com.example.day16.cookie;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 在服务器的servlet判断是否有一个名为lastTime的cookie
 1. 有，不是首次访问
 1. 响应数据：欢迎回来，您上次访问时间是xxx
 2. 写回cookie：lastTime=20xx年某月某日
 2. 没有， 是第一次访问
 1. 响应数据：您好，欢迎您首次访问
 2. 写回cookie：lastTime=20xx年某月某日
 */



@WebServlet("/cookieTest")
public class CookieTest extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    this.doPost(req, resp);
  }


  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    System.out.println("cookieTest...........");
    resp.setContentType("text/html;charset=utf-8");
    //获取cookie
    Cookie[] cookies = req.getCookies();

    boolean flag = false;
    //b遍历cookie
    if(cookies!=null){
      for(Cookie cookie:cookies){
        String name = cookie.getName();
        String value = cookie.getValue();


        if(name.equals("lastTime")){
          flag = true;
          value = URLDecoder.decode(value,"utf-8");
          resp.getWriter().write("<h1>欢迎回来，您上次访问时间 "+value+"</h1>");
          break;
        }
      }
    }



    if(!flag){
      resp.getWriter().write("<h1>您好，欢迎您首次访问!");
    }


    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
    String strDate = sdf.format(date);
    Cookie cookie = new Cookie("lastTime", URLEncoder.encode(strDate,"utf-8"));
    cookie.setMaxAge(60*60*24*30);
    resp.addCookie(cookie);
  }
}
