package com.midknight.foodlog.dao;

import com.midknight.foodlog.model.Role;
import com.midknight.foodlog.model.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Repository
public class UserDaoImpl implements UserDao{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User findByUsername(String username) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(User.class);
        User user = (User) criteria.add(Restrictions.eq("username",username)).uniqueResult();
        session.close();
        return user;
    }

    @Override
    public void save(User user){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(user);
        session.getTransaction().commit();
        session.close();
    }
}
