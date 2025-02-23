package com.example.eduwebsite.dao;

import com.example.eduwebsite.entity.Course;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Pageable;

public interface CourseRepository extends JpaRepository<Course, Long> ,PagingAndSortingRepository<Course , Long> , JpaSpecificationExecutor<Course> {
    //Page<Course> findAllBy(Pageable pageable);
//

}
