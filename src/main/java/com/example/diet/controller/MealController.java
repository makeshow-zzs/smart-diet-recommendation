package com.example.diet.controller;

import com.example.diet.entity.MealRecord;
import com.example.diet.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/meals")
public class MealController {
    @Autowired
    private MealService mealService;

    @PostMapping
    public MealRecord addMeal(@RequestBody MealRecord record) {
        return mealService.addMealRecord(record);
    }

    @GetMapping("/daily/{date}")
    public List<MealRecord> getMealsByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return mealService.getRecordsByDate(date);
    }

    @GetMapping("/daily/{date}/calories")
    public Double getDailyCalories(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return mealService.getDailyTotalCalories(date);
    }

    @GetMapping("/daily/{date}/recommendation")
    public String getRecommendation(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return mealService.getRecommendation(date);
    }
}