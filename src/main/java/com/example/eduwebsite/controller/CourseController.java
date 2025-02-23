package com.example.eduwebsite.controller;

import com.example.eduwebsite.entity.Course;
import com.example.eduwebsite.entity.User;
import com.example.eduwebsite.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    public Page<Course> getCourses(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return courseService.getAllCourses(page, size);
    }


    @PostMapping("/{courseId}/enroll/{userId}/user")
    public ResponseEntity<String> enrollCourse(@PathVariable Long courseId, @PathVariable Long userId) {
        String responseMessage = courseService.enrollInCourse(courseId, userId);
        return ResponseEntity.ok(responseMessage);
    }


    @GetMapping("/enrollment/count")
    public long getEnrollmentCount(@RequestParam String email) {
        return courseService.getNumberOfCoursesByUser(email);
    }


    @GetMapping("/enrolled")
    public List<Course> getEnrolledCourses(@RequestParam String email) {
        return courseService.getCoursesByUser(email);
    }

    @GetMapping("/search/courses")
    public ResponseEntity<List<Course>> searchCourses(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String duration,
            @RequestParam(required = false) String description) {
        List<Course> courses = courseService.searchCourses(name, duration, description);
        return ResponseEntity.ok(courses);
    }



}

