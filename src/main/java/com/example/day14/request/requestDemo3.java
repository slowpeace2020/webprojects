package com.example.day14.request;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/requestdemo3")
public class requestDemo3 extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    System.out.println("requestdemo3被访问类");
    req.setAttribute("greet","hello");
    req.getRequestDispatcher("/requestdemo4").forward(req,resp);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
   this.doPost(req,resp);
  }
}
