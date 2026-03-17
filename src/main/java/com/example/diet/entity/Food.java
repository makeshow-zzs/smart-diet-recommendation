package com.example.diet.entity;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;          // 食物名称(唯一)
    private Double calories;       // 热量（千卡/100克）
    private Double protein;        // 蛋白质（克/100克）
    private Double fat;            // 脂肪（克/100克）
    private Double carbohydrate;   // 碳水化合物（克/100克）
}