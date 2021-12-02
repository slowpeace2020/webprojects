package com.example.day17case.web.servlet;

import com.example.day17case.service.UserService;
import com.example.day17case.service.impl.UserServiceImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/delSelectedServlet")
public class DelSelectedUserServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    this.doPost(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
      //获取ID
    String[] ids = req.getParameterValues("uid");
    System.out.println(ids);
    //调用service删除
    UserService userService = new UserServiceImpl();
    userService.deletSelectedUser(ids);

    //跳转到list页面
    resp.sendRedirect(req.getContextPath()+"/userListServlet");

  }
}
