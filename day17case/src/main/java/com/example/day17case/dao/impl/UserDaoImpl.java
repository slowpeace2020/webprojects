package com.example.day17case.dao.impl;

import com.example.day17case.dao.UserDao;
import com.example.day17case.domain.User;
import com.example.day17case.util.JDBCUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

  @Override
  public int findTotalCount(Map<String, String[]> condition) {
    //定义模板sql
    String sql = "select count(*) from user where 1=1";
    //遍历map
    StringBuilder sb = new StringBuilder(sql);
    List<Object> params = new ArrayList<>();
    for(String key:condition.keySet()){
      //排除分页条件
      if("currentPage".equals(key)||"rows".equals(key)){
        continue;
      }

      String value = condition.get(key)[0];
      if(value!=null&& !"".equals(value)){
        sb.append(" and "+key+" like ? ");
        params.add("%"+value+"%");//加条件值
      }
    }
    return template.queryForObject(sb.toString(),Integer.class,params.toArray());
  }

  @Override
  public List<User> findByPage(int start, Integer rows,
      Map<String, String[]> condition) {
    String sql = "select * from user where 1=1 ";
    //遍历map
    StringBuilder sb = new StringBuilder(sql);
    List<Object> params = new ArrayList<>();
    for(String key:condition.keySet()){
      //排除分页条件
      if("currentPage".equals(key)||"rows".equals(key)){
        continue;
      }

      String value = condition.get(key)[0];
      if(value!=null&& !"".equals(value)){
        sb.append(" and "+key+" like ? ");
        params.add("%"+value+"%");//加条件值
      }
    }
    sb.append(" limit ?,?");
    params.add(start);
    params.add(rows);
    return template.query(sb.toString(),new BeanPropertyRowMapper<>(User.class),params.toArray());
  }
}
