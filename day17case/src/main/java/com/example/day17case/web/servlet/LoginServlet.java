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
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    //设置编码
    req.setCharacterEncoding("utf-8");

    //获取数据
    //验证码校验操作
    String verifycode = req.getParameter("verifycode");
    Map<String,String[]> map = req.getParameterMap();

    HttpSession session = req.getSession();
    String checkcodeServer = (String) session.getAttribute("CHECKCODE_SERVER");
    //确保验证码一次性
    session.removeAttribute("CHECKCODE_SERVER");
    if(checkcodeServer==null||!checkcodeServer.equalsIgnoreCase(verifycode)){
      //验证码错误
      //提示信息
      req.setAttribute("login_msg","验证码错误！");
      // 跳转登录页面
      req.getRequestDispatcher("/login.jsp").forward(req,resp);
      return;
    }


    //封装user对象
    User user = new User();
    try {
      BeanUtils.populate(user,map);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }

//    System.out.println(user);

    //调用Service查询
    UserService userService = new UserServiceImpl();
    User loginUser = userService.login(user);

    //判断是否登录成功
    if(loginUser!=null){
      //登录成功
      //将用户存入session
      session.setAttribute("user",loginUser);
      //跳转页面
      resp.sendRedirect(req.getContextPath()+"/index.jsp");


    }else {
      //登录失败
      //提示信息
      req.setAttribute("login_msg","用户名或密码错误！");
      // 跳转登录页面
      req.getRequestDispatcher("/login.jsp").forward(req,resp);
      return;

    }


  }
}
