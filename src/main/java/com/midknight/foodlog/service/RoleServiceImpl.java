package com.midknight.foodlog.service;

import com.midknight.foodlog.dao.RoleDao;
import com.midknight.foodlog.dao.RoleDaoImpl;
import com.midknight.foodlog.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleDao roleDao;

    @Override
    public Role findById(long id) {
        return roleDao.findById(id);
    }
}
