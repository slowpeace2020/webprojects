package com.example.day17case.web.servlet;

import com.example.day17case.domain.User;
import com.example.day17case.service.UserService;
import com.example.day17case.service.impl.UserServiceImpl;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

@WebServlet("/addUserServlet")
public class AddUserServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    //设置编码
    req.setCharacterEncoding("utf-8");
    //获取参数
    Map<String,String[]> map = req.getParameterMap();
    //封装对象
    User user = new User();
    try {
      BeanUtils.populate(user,map);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }

    //调用service保存
    UserService userService = new UserServiceImpl();
    userService.addUser(user);

    //跳转到userListServlet
    resp.sendRedirect(req.getContextPath()+"/userListServlet");

  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    this.doPost(req, resp);
  }
}
