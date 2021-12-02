package com.example.day14;

import java.io.IOException;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/demo2")
public class ServletDemo2 extends GenericServlet {

  @Override
  public void service(ServletRequest servletRequest, ServletResponse servletResponse)
      throws ServletException, IOException {
    System.out.println("GenericServlet test............");
  }
}
