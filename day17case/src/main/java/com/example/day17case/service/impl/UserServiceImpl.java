package com.example.day17case.service.impl;

import com.example.day17case.dao.UserDao;
import com.example.day17case.dao.impl.UserDaoImpl;
import com.example.day17case.domain.User;
import com.example.day17case.service.UserService;
import java.util.List;

public class UserServiceImpl implements UserService {

  private UserDao userDao = new UserDaoImpl();

  @Override
  public List<User> findAll() {
    //调用DAO完成查询

    return userDao.findAll();

  }

  /**
   * 登录
   * @param user
   * @return
   */
  @Override
  public User login(User user) {
   User res = userDao.findUserByUsernameAndPassword(user);
    return res;
  }

  /**
   * 保存用户
   * @param user
   */
  @Override
  public void addUser(User user) {
    userDao.add(user);
  }

  /**
   * 根据ID删除
   * @param id
   */
  @Override
  public void deletUser(String id) {
    userDao.delete(id);
  }

  /**
   * 根据ID查找
   * @param id
   * @return
   */
  @Override
  public User findUserById(String id) {
    return userDao.findById(id);
  }

  @Override
  public void updateUser(User user) {
    userDao.update(user);
  }

  @Override
  public void deletSelectedUser(String[] ids) {
    if(ids==null&&ids.length==0){
      return;
    }
      for(String id:ids){
        userDao.delete(id);
      }
  }
}
