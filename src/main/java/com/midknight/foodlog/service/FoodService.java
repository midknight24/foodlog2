package com.midknight.foodlog.service;

import com.midknight.foodlog.model.Food;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Onlyme on 9/27/2017.
 */
public interface FoodService {
    List<Food> findAll();
    Food findById(long id);
    void save(Food food, MultipartFile file);
    void delete(Food food);
    void toggleLike(Food food);
}
