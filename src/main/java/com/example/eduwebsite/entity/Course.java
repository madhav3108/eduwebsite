package com.example.eduwebsite.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String duration;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private double fee;

    @Getter
    @Setter
    private int enrolledCount;

    public Course() {}

    public Course(Long id, String name, String duration, String description, double fee, int enrolledCount) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.description = description;
        this.fee = fee;
        this.enrolledCount = enrolledCount;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration='" + duration + '\'' +
                ", description='" + description + '\'' +
                ", fee=" + fee +
                ", enrolledCount=" + enrolledCount +
                '}';
    }


}
