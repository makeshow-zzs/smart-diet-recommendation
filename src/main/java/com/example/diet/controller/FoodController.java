package com.example.diet.controller;

import com.example.diet.entity.Food;
import com.example.diet.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/foods")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @GetMapping
    public List<Food> getAllFoods() {
        return foodService.getAllFoods();
    }

    @PostMapping
    public Food addFood(@RequestBody Food food) {
        return foodService.addFood(food);
    }

    @GetMapping("/search")
    public List<Food> searchFoods(@RequestParam String keyword) {
        return foodService.searchFoods(keyword);
    }
}