package com.test.testproject;

import jakarta.persistence.*;

@Entity
@Table(name = "draghi")
public class Dragon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String color;
    private int age;

    public Dragon() {}

    public Dragon(String name, String color, int age) {
        this.name = name;
        this.color = color;
        this.age = age;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getColor() { return color; }
    public int getAge() { return age; }
}