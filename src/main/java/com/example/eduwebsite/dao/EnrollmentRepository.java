package com.example.eduwebsite.dao;

import com.example.eduwebsite.entity.Course;
import com.example.eduwebsite.entity.Enrollment;
import com.example.eduwebsite.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    long countByUserId(Long userId);
    void deleteByUserId(Long userId);

    // New method to find enrollments by user ID
    List<Enrollment> findByUserId(Long userId);
    Optional<Enrollment> findByCourseAndUser(Course course, User user);
}
