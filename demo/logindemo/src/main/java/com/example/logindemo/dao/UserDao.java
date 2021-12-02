package com.example.logindemo.dao;

import com.example.logindemo.domain.User;
import com.example.logindemo.utils.JDBCUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDao {

  //声明JDBCTemplate对象来共用
  private JdbcTemplate template = new JdbcTemplate(JDBCUtil.getDatasource());


  public User login(User loginUser){
    String sql = "select * from user where username=? and password=?";
    User user = template.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),loginUser.getUsername(),loginUser.getPassword());

    return user;
  }

}
