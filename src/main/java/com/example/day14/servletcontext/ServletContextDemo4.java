package com.example.day14.servletcontext;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/servletContextDemo4")
public class ServletContextDemo4 extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    //ServletContext对象获取
//    1. 通过HttpServlet获取
    ServletContext  test1 = this.getServletContext();
      //设置数据
    test1.setAttribute("msg","hahahahaha");
    System.out.println("servletContextDemo4.............");

  }


  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    this.doPost(req, resp);
  }
}
