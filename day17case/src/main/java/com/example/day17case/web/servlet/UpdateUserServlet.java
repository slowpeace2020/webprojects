package com.example.day17case.web.servlet;

import com.example.day17case.domain.User;
import com.example.day17case.service.UserService;
import com.example.day17case.service.impl.UserServiceImpl;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

@WebServlet("/updateUserServlet")
public class UpdateUserServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    this.doPost(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    //设置编码
    req.setCharacterEncoding("utf-8");
    //获取map
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

    //调用service修改
    UserService userService = new UserServiceImpl();
    userService.updateUser(user);

    //跳转到list页面
    resp.sendRedirect(req.getContextPath()+"/userListServlet");

  }
}
