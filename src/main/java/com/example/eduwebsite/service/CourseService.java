package com.example.eduwebsite.service;

import com.example.eduwebsite.dao.CourseRepository;
import com.example.eduwebsite.dao.EnrollmentRepository;
import com.example.eduwebsite.dao.UserRepository;
import com.example.eduwebsite.entity.Course;
import com.example.eduwebsite.entity.Enrollment;
import com.example.eduwebsite.entity.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;


import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @PostConstruct
    public void init() {
        if (courseRepository.count() == 0) {
            List<Course> courses = Arrays.asList(
                    new Course(null, "Java", "6 months", "Learn the fundamentals of Java programming.", 30000.0, 0),
                    new Course(null, "C++", "4 months", "Understand object-oriented programming with C++.", 10000.0, 0),
                    new Course(null, "MERN Stack", "6 months", "Full-stack web development with MongoDB, Express, React, and Node.js.", 25000.0, 0),
                    new Course(null, "Machine Learning", "1 months", "Introduction to machine learning concepts and techniques.", 5000.0, 0),
                    new Course(null, "Cybersecurity", "3 months", "Learn about cybersecurity fundamentals and best practices.", 10000.0, 0)

            );

            courseRepository.saveAll(courses);
        }
    }

    public Page<Course> getAllCourses(int page, int size) {
        return courseRepository.findAll(PageRequest.of(page, size));
    }

    public String enrollInCourse(Long courseId, Long userId) {
        // Check if the user exists
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            return "User not found"; // User does not exist
        }
        User user = userOpt.get();

        // Find the course
        Course course = courseRepository.findById(courseId).orElse(null);
        if (course == null) {
            return "Course not found"; // Handle case if the course does not exist
        }

        // Check if the user is already enrolled
        Optional<Enrollment> existingEnrollment = enrollmentRepository.findByCourseAndUser(course, user);
        if (existingEnrollment.isPresent()) {
            return "User is already enrolled in the course"; // User already enrolled
        }

        // Proceed to enroll the user
        course.setEnrolledCount(course.getEnrolledCount() + 1);
        courseRepository.save(course);

        Enrollment enrollment = Enrollment.builder()
                .course(course)
                .user(user)
                .build();
        enrollmentRepository.save(enrollment);

        return "User successfully enrolled in the course"; // Successful enrollment message
    }







    public long getNumberOfCoursesByUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        if (user != null) {
            return enrollmentRepository.countByUserId(user.getId());
        }
        return 0;
    }

    public List<Course> getCoursesByUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Fetch enrollments for the user
        List<Enrollment> enrollments = enrollmentRepository.findByUserId(user.getId());

        // Extract course details from the enrollments
        List<Course> courses = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            courses.add(enrollment.getCourse());
        }

        return courses;
    }

    // search user
    public List<Course> searchCourses(String name, String duration, String description) {
        List<Course> courses = courseRepository.findAll();
        List<Course> filteredCourses = new ArrayList<>();

        for (Course course : courses) {
            boolean matches = true;
            if (name != null && !course.getName().toLowerCase().contains(name.toLowerCase())) {
                matches = false;
            }
            if (duration != null && !course.getDuration().toLowerCase().contains(duration.toLowerCase())) {
                matches = false;
            }
            if (description != null && !course.getDescription().toLowerCase().contains(description.toLowerCase())) {
                matches = false;
            }
            if (matches) {
                filteredCourses.add(course);
            }
        }

        return filteredCourses;
    }
}






