package com.example.day17case.dao;

import com.example.day17case.domain.User;
import java.util.List;
import java.util.Map;

/**
 * 用户操作的dao
 */
public interface UserDao {
    public List<User> findAll();
    public User findUserByUsernameAndPassword(User user);

    void add(User user);

    void delete(String id);

    User findById(String id);

    void update(User user);

  int findTotalCount(Map<String, String[]> condition);

    List<User> findByPage(int start, Integer rows,
        Map<String, String[]> condition);
}
