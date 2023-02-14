package com.surjendu.blog.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.surjendu.blog.entities.Category;
import com.surjendu.blog.entities.Post;
import com.surjendu.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

	Page<Post> findByUser(User user, Pageable p);

	Page<Post> findByCategory(Category category, Pageable p);

	@Query("select p from Post p where p.postTitle like :key")
	Page<Post> searchByTitle(@Param("key") String title, Pageable p);
//	@Query("select p from Post p where p.postTitle like :key")
//	List<Post> searchByTitle(@Param("key") String title);
}
