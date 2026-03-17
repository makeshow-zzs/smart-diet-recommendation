package com.example.diet.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class MealRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;        // 记录日期
    private String mealType;       // 餐别（早餐、午餐、晚餐、加餐）

    @ManyToOne
    private Food food;             // 关联的食物
    private Double weight;         // 实际食用重量（克）
}