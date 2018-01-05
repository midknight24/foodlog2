package com.midknight.foodlog.service;

import com.midknight.foodlog.dao.FoodDao;
import com.midknight.foodlog.model.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by Onlyme on 9/27/2017.
 */
@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    private FoodDao foodDao;

    @Override
    public List<Food> findAll() { return foodDao.findAll(); }

    @Override
    public Food findById(long id){ return foodDao.findById(id); }

    @Override
    public void save(Food food, MultipartFile file) {
        try {
            food.setBytes(file.getBytes());
            foodDao.save(food);
        } catch (IOException e) {
            System.err.println("Unable to get byte from uploaded file");
        }
    }

    @Override
    public void delete(Food food) { foodDao.delete(food); }

    @Override
    public void toggleLike(Food food){
        food.setLiked(!food.isLiked());
        foodDao.save(food);
    }
}
