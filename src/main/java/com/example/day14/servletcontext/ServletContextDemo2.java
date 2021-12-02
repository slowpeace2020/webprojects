package com.example.day14.servletcontext;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/servletContextDemo2")
public class ServletContextDemo2 extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    //ServletContext对象获取
//    1. 通过request对象获取
    ServletContext  test1 = req.getServletContext();
//     获取MIME类型(在互联网通信过程中定义的一种文件数据类型)
//    格式： 大类型/小类型  text/html。image/jpeg

    //定义文件名称
    String filename= "a.jpg";
    //获取MIME类型(
    String mimeType =  test1.getMimeType(filename);
    System.out.println(mimeType);
  }


  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    this.doPost(req, resp);
  }
}
