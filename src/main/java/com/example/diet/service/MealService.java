package com.example.diet.service;

import com.example.diet.entity.MealRecord;
import com.example.diet.repository.MealRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

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

    // 简单的推荐逻辑：根据当日摄入与目标比较给出建议（假设目标为2000千卡）
    public String getRecommendation(LocalDate date) {
        double totalCalories = getDailyTotalCalories(date);
        double target = 2000.0;
        if (totalCalories < target - 200) {
            return "今日摄入偏低，建议适当增加营养丰富的食物。";
        } else if (totalCalories > target + 200) {
            return "今日摄入偏高，建议选择低热量食物，如蔬菜沙拉。";
        } else {
            return "今日摄入合理，继续保持！";
        }
    }
}