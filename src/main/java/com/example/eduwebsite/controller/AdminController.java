package com.example.eduwebsite.controller;

import com.example.eduwebsite.entity.Course;
import com.example.eduwebsite.entity.User;
import com.example.eduwebsite.service.AdminService;
import com.example.eduwebsite.service.CourseService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private CourseService courseService;

//    @GetMapping("/users")
//    public ResponseEntity<List<User>> getAllUsers() {
//        List<User> users = adminService.getAllUsers();
//        return ResponseEntity.ok(users);
//    }

    @GetMapping("/users")
    public Page<User> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return adminService.getAllUsers(page, size);
    }




    @Transactional
    @DeleteMapping("/users/delete/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long id) {
        try {
            adminService.deletePerson(id);
            return ResponseEntity.ok("User deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // Return user not found message
        }
    }


    @PostMapping("/courses")
    public ResponseEntity<String> addCourse(@RequestBody Course course) {
        adminService.addCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body("Course added successfully.");
    }

    @PutMapping("/updateCourse")
    public ResponseEntity<String> updateCourse(@RequestBody Course course) {
        adminService.updateCourse(course);
        return ResponseEntity.ok("Course updated successfully.");
    }



//    @GetMapping("/totalCourses")
//    public ResponseEntity<List<Course>> getAllCourses() {
//        List<Course> courses = adminService.getAllCourses();
//        return ResponseEntity.ok(courses);
//    }

    @GetMapping("/totalCourses")
    public ResponseEntity<Page<Course>> getAllCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Course> courses = adminService.getAllCourses(page, size);
        return ResponseEntity.ok(courses);
    }


}
