package com.example.diet.repository;

import com.example.diet.entity.MealRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface MealRecordRepository extends JpaRepository<MealRecord, Long> {
    List<MealRecord> findByDate(LocalDate date);

    // 自定义查询：计算某天的总热量
    @Query("SELECT SUM(m.food.calories * m.weight / 100) FROM MealRecord m WHERE m.date = :date")
    Double sumCaloriesByDate(@Param("date") LocalDate date);
}