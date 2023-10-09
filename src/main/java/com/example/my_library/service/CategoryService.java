package com.example.my_library.service;

import com.example.my_library.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryService extends JpaRepository<Category, Long> {
}
