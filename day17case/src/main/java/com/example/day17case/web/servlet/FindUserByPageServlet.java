package com.example.day17case.web.servlet;

import com.example.day17case.domain.PageBean;
import com.example.day17case.domain.User;
import com.example.day17case.service.UserService;
import com.example.day17case.service.impl.UserServiceImpl;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    this.doPost(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setCharacterEncoding("utf-8");
    //获取参数
    String currentPage = req.getParameter("currentPage");//当前页码
    if (currentPage==null||currentPage.equals("")){
      currentPage = "1";
    }
    String rows = req.getParameter("rows");//每页数量
    if(rows==null||rows.equals("")){
      rows = "5";
    }

    //获取查询条件
    Map<String,String[]> condition = req.getParameterMap();
    //调用service查询
    UserService userService = new UserServiceImpl();
    PageBean<User> pb =userService.findUserByPage(Integer.valueOf(currentPage),Integer.valueOf(rows),condition);

    //将user存入request
    req.setAttribute("pb",pb);
    req.setAttribute("condition",condition);
    System.out.println(pb);
    //转发到list.jsp
    req.getRequestDispatcher("list.jsp").forward(req,resp);


  }
}
