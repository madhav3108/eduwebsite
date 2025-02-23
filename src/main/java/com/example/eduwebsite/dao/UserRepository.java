package com.example.eduwebsite.dao;

import com.example.eduwebsite.entity.Course;
import com.example.eduwebsite.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Pageable;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> , PagingAndSortingRepository<User, Long>, JpaSpecificationExecutor<User> {
    Optional<User> findByEmail(String email);
    User findByEmailAndPassword(String email, String password);
    void deleteByEmail(String email);

}
