package com.example.diet.service;

import com.example.diet.entity.Food;
import com.example.diet.entity.MealRecord;
import com.example.diet.repository.MealRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MealService {
    @Autowired
    private MealRecordRepository mealRecordRepository;

    public MealRecord addMealRecord(MealRecord record) {
        return mealRecordRepository.save(record);
    }

    public List<MealRecord> getRecordsByDate(LocalDate date) {
        return mealRecordRepository.findByDate(date);
    }

    public Double getDailyTotalCalories(LocalDate date) {
        Double total = mealRecordRepository.sumCaloriesByDate(date);
        return total == null ? 0.0 : total;
    }

    @Autowired
    private FoodService foodService;  // 确保已注入

    public String getRecommendation(LocalDate date) {
        double totalCalories = getDailyTotalCalories(date);
        double target = 2000.0;

        if (totalCalories < target - 200) {
            // 获取所有食物，按热量降序排序，取前3个
            List<Food> allFoods = foodService.getAllFoods();
            List<Food> highCalorieFoods = allFoods.stream()
                    .sorted((f1, f2) -> Double.compare(f2.getCalories(), f1.getCalories()))
                    .limit(3)
                    .collect(Collectors.toList());

            if (!highCalorieFoods.isEmpty()) {
                String foodNames = highCalorieFoods.stream()
                        .map(Food::getName)
                        .collect(Collectors.joining("、"));
                return "今日摄入偏低，建议增加高热量食物，如：" + foodNames;
            } else {
                return "今日摄入偏低，建议增加高热量食物。";
            }
        } else if (totalCalories > target + 200) {
            return "今日摄入偏高，建议选择低热量食物，如蔬菜沙拉。";
        } else {
            return "今日摄入合理，继续保持！";
        }
    }
}