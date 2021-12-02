package com.example.logindemo.test;

import com.example.logindemo.dao.UserDao;
import com.example.logindemo.domain.User;

public class UserDaoTest {

  public static void main(String[] args) {
    User user =  new User();
    user.setUsername("zhangsan");
    user.setPassword("123456");
    UserDao dao = new UserDao();
    User test = dao.login(user);
    System.out.println(test);
  }
  public void testLogin(){
    User user =  new User();
    user.setUsername("zhangsan");
    user.setPassword("123456");
    UserDao dao = new UserDao();
    User test = dao.login(user);
    System.out.println(test);

  }

}
