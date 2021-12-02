package com.example.day17case.web.servlet;

import com.example.day17case.domain.User;
import com.example.day17case.service.UserService;
import com.example.day17case.service.impl.UserServiceImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/findUserServlet")
public class FindUserServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    this.doPost(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    //获取ID
    String id = req.getParameter("id");
    //调用service查询
    UserService userService = new UserServiceImpl();
    User user =userService.findUserById(id);

    //将user存入request
    req.setAttribute("user",user);

    //转发到update.jsp
    req.getRequestDispatcher("update.jsp").forward(req,resp);


  }
}
