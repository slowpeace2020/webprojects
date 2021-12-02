package com.example.day14.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/rsponseDemo3")
public class ResponseDemo3 extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    this.doPost(req, resp);
  }


  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
  //访问rsponseDemo1，自动跳转到rsponseDemo2

    System.out.println("rsponseDemo3...........");

    //转发
    req.getRequestDispatcher("/rsponseDemo2").forward(req,resp);
  }
}
