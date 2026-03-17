package com.example.diet;

import com.example.diet.entity.Food;
import com.example.diet.repository.FoodRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SmartDietRecommendationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartDietRecommendationApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(FoodRepository foodRepository) {
        return args -> {
            // 定义初始食物列表
            Food apple = new Food();
            apple.setName("苹果");
            apple.setCalories(52.0);
            apple.setProtein(0.3);
            apple.setFat(0.2);
            apple.setCarbohydrate(13.8);

            Food chicken = new Food();
            chicken.setName("鸡胸肉");
            chicken.setCalories(165.0);
            chicken.setProtein(31.0);
            chicken.setFat(3.6);
            chicken.setCarbohydrate(0.0);

            Food rice = new Food();
            rice.setName("米饭");
            rice.setCalories(116.0);
            rice.setProtein(2.6);
            rice.setFat(0.3);
            rice.setCarbohydrate(25.6);

            // 检查并保存
            saveIfNotExists(foodRepository, apple);
            saveIfNotExists(foodRepository, chicken);
            saveIfNotExists(foodRepository, rice);

            System.out.println("示例食物初始化完成");
        };
    }

    private void saveIfNotExists(FoodRepository foodRepository, Food food) {
        List<Food> existing = foodRepository.findByName(food.getName());
        if (existing.isEmpty()) {
            foodRepository.save(food);
        }
    }
}