package com.example.day17case.service.impl;

import com.example.day17case.dao.UserDao;
import com.example.day17case.dao.impl.UserDaoImpl;
import com.example.day17case.domain.PageBean;
import com.example.day17case.domain.User;
import com.example.day17case.service.UserService;
import java.util.List;
import java.util.Map;

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

  /**
   * 分页条件查询
   * @param currentPage
   * @param rows
   * @param condition
   * @return
   */
  @Override
  public PageBean<User> findUserByPage(Integer currentPage, Integer rows,
      Map<String, String[]> condition) {
    //创建PageBean对象
    PageBean<User> pageBean =new PageBean<User>();
    //设置参数
    if(currentPage<=0){
      currentPage = 1;
    }
    pageBean.setCurrentPage(currentPage);
    pageBean.setRows(rows);

    //查出总记录数
    int totalCount = userDao.findTotalCount(condition);
    System.out.println(totalCount);
    pageBean.setTotalCount(totalCount);



    //计算总页数
    int totalPage = (totalCount+rows-1)/rows;
    pageBean.setTotalPage(totalPage);

    if(currentPage>totalPage){
      currentPage = totalPage;
    }

    //获取当前页面数据
    //计算开始的记录索引
    int start =(currentPage-1)*rows;

    List<User> list =  userDao.findByPage(start,rows,condition);
    pageBean.setList(list);

    return pageBean;
  }
}
