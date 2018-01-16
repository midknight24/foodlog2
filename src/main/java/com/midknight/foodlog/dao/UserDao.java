package com.midknight.foodlog.dao;

import com.midknight.foodlog.model.Role;
import com.midknight.foodlog.model.User;

public interface UserDao {
    User findByUsername(String username);
    void save(User user);
}
