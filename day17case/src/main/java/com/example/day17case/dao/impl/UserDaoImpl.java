package com.example.day17case.dao.impl;

import com.example.day17case.dao.UserDao;
import com.example.day17case.domain.User;
import com.example.day17case.util.JDBCUtils;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {

  private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


  @Override
  public List<User> findAll() {
    //使用JDBC操作数据库
    //定义sql
    String sql = "select * from user";
    List<User> users = template.query(sql,new BeanPropertyRowMapper<>(User.class));
    return users;
  }

  @Override
  public User findUserByUsernameAndPassword(User user) {
    String sql = "select * from user where username= ? and password= ? ";
    User res = template.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),user.getUsername(),user.getPassword());
    return res;
  }

  @Override
  public void add(User user) {
    String sql ="insert into user values(null,?,?,?,?,?,?,null,null)";
    template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail());
  }

  @Override
  public void delete(String id) {
    String sql = "delete from user where id = ?";
    template.update(sql,id);
  }

  @Override
  public User findById(String id) {
    String sql = "select * from user where id = ? ";
    User res = template.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),id);
    return res;
  }

  @Override
  public void update(User user) {
    String sql ="update user set name=?, gender=?, age=?, address=?,qq=?,email=? where id=?";
    template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail(),user.getId());
  }
}
