package com.midknight.foodlog.dao;

import com.midknight.foodlog.model.Food;

import java.util.List;

/**
 * Created by Onlyme on 9/27/2017.
 */
public interface FoodDao {
    List<Food> findAll();
    List<Food> findByUserId(long id);
    Food findById(long id);
    void save(Food food);
    void delete(Food food);
}
