package com.example.eduwebsite.service;

import com.example.eduwebsite.dao.CourseRepository;
import com.example.eduwebsite.dao.EnrollmentRepository;
import com.example.eduwebsite.dao.UserRepository;
import com.example.eduwebsite.entity.Course;
import com.example.eduwebsite.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }

    public Page<User> getAllUsers(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size));
    }



    @Transactional
    public void deletePerson(Long id) {
        // Check if the user exists first
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found for ID: " + id);
        }
        enrollmentRepository.deleteByUserId(id);
        userRepository.deleteById(id);
    }




    public void addCourse(Course course) {
        courseRepository.save(course);
    }

    public void updateCourse(Course course) {
        if (courseRepository.existsById(course.getId())) {
            courseRepository.save(course);
        } else {
            throw new RuntimeException("Course not found for ID: " + course.getId());
        }
    }


    public boolean deleteCourse(Long courseId) {
        if (courseRepository.existsById(courseId)) {
            courseRepository.deleteById(courseId);
            return true; // Course was deleted
        }
        return false; // Course not found
    }

//    public List<Course> getAllCourses() {
//        return courseRepository.findAll();
//    }

    public Page<Course> getAllCourses(int page, int size) {
        return courseRepository.findAll(PageRequest.of(page, size));
    }

}
