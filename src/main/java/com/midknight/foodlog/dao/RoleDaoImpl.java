package com.midknight.foodlog.dao;

import com.midknight.foodlog.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Role findById(Long id) {
        Session session = sessionFactory.openSession();
        Role role = session.get(Role.class,id);
        session.close();
        return role;
    }
}