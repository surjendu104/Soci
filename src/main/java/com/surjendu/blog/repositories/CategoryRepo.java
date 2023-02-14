package com.surjendu.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.surjendu.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
