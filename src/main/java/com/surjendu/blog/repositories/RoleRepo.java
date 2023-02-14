package com.surjendu.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.surjendu.blog.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{
	
}
