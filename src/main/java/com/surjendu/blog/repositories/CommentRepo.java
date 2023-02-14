package com.surjendu.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.surjendu.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
