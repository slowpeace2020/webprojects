package com.example.day14.servletcontext;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/servletContextDemo3")
public class ServletContextDemo3 extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    //ServletContext对象获取
//    1. 通过request对象获取
    ServletContext  test1 = req.getServletContext();
    System.out.println("ServletContextDemo3.............");
    System.out.println(test1.getAttribute("msg"));
//     获取MIME类型(在互联网通信过程中定义的一种文件数据类型)
//    格式： 大类型/小类型  text/html。image/jpeg

    //获取数据

  }


  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    this.doPost(req, resp);
  }
}
