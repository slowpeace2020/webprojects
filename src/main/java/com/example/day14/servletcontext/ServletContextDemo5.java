package com.example.day14.servletcontext;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/servletContextDemo5")
public class ServletContextDemo5 extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    //ServletContext对象获取
//    1. 通过HttpServlet获取
    ServletContext  test1 = this.getServletContext();
      //获取文件路径
    String realPath = test1.getRealPath("/b.txt");
    String path = test1.getRealPath("/WEB-INF/a.txt");
    System.out.println(realPath);
    System.out.println(path);
    File file = new File(path);


  }


  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    this.doPost(req, resp);
  }
}
