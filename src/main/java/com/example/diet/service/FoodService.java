package com.example.diet.service;

import com.example.diet.entity.Food;
import com.example.diet.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FoodService {
    @Autowired
    private FoodRepository foodRepository;

    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    public Food addFood(Food food) {
        // 检查名称是否已存在
        List<Food> existing = foodRepository.findByName(food.getName());
        if (!existing.isEmpty()) {
            throw new RuntimeException("食物名称已存在，请勿重复添加！");
        }
        return foodRepository.save(food);
    }

    public List<Food> searchFoods(String keyword) {
        return foodRepository.findByNameContaining(keyword);
    }
}
