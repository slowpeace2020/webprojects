package com.example.day17case.service;

import com.example.day17case.domain.PageBean;
import com.example.day17case.domain.User;
import java.util.List;
import java.util.Map;

/**
 * 用户管理的业务接口
 */
public interface UserService {

  /**
   * 查询所有用户信息
   * @return
   */
  public List<User> findAll();
  public User login(User user);

  void addUser(User user);

  void deletUser(String id);

  User findUserById(String id);

  void updateUser(User user);

  void deletSelectedUser(String[] ids);

  PageBean<User> findUserByPage(Integer currentPage, Integer rows,
      Map<String, String[]> condition);
}
