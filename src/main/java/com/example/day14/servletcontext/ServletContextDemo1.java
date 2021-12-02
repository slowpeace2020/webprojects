package com.example.day14.servletcontext;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/servletContextDemo1")
public class ServletContextDemo1 extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    //ServletContext对象获取
//    1. 通过request对象获取
    ServletContext  test1 = req.getServletContext();

//    2. 通过HttpServlet获取
    ServletContext  test2 = this.getServletContext();
    System.out.println(test1==test2);
  }


  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    this.doPost(req, resp);
  }
}
