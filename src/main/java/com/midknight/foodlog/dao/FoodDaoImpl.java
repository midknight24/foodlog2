package com.midknight.foodlog.dao;

import com.midknight.foodlog.model.Food;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Onlyme on 9/27/2017.
 */
@Repository
public class FoodDaoImpl implements FoodDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<Food> findAll() {
        Session session = sessionFactory.openSession();
        List<Food> foods = session.createCriteria(Food.class).addOrder(Order.asc("dateUpLoaded")).list();
        session.close();
        return foods;
    }

    @Override
    public Food findById(long id){
        Session session = sessionFactory.openSession();
        Food food = session.get(Food.class,id);
        session.close();
        return food;
    }

    @Override
    public void save(Food food){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(food);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Food food){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(food);
        session.getTransaction().commit();
        session.close();
    }


}
