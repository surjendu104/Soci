package com.surjendu.blog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.surjendu.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	// Fetch the user email
	Optional<User> findByEmail(String email);
}
