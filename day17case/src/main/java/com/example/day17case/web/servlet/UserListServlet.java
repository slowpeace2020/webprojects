package com.example.day17case.web.servlet;

import com.example.day17case.domain.User;
import com.example.day17case.service.UserService;
import com.example.day17case.service.impl.UserServiceImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/userListServlet")
public class UserListServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    //调用userService完成查询
    UserService userService = new UserServiceImpl();
    List<User> users = userService.findAll();
    System.out.println(users);
    //将list存入request域
    req.setAttribute("users",users);

    //转发到list.jsp页面
    req.getRequestDispatcher("list.jsp").forward(req,resp);


  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    this.doPost(req, resp);
  }
}
