package com.example.day14;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet({"/d4","/demo4","/dd4"})
@WebServlet("/user/*")
public class ServletDemo4 extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    System.out.println("ServletDemo4.......");
    System.out.println(req);
  }
}
