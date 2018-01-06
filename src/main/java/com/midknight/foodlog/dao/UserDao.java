package com.midknight.foodlog.dao;

import com.midknight.foodlog.model.User;

public interface UserDao {
    User findByUsername(String username);
}
