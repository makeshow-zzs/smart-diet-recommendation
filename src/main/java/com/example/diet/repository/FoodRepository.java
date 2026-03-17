package com.example.diet.repository;

import com.example.diet.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findByNameContaining(String keyword);
    List<Food> findByName(String name);
}