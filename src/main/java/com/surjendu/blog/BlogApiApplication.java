package com.surjendu.blog;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.surjendu.blog.config.AppConstants;
import com.surjendu.blog.entities.Role;
import com.surjendu.blog.repositories.RoleRepo;

@SpringBootApplication
public class BlogApiApplication implements CommandLineRunner{
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepo roleRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogApiApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("302241"));
		try {
			Role role1 = new Role();
			role1.setRoleId(AppConstants.ADMIN_USER_ROLE);role1.setRoleName("ADMIN_USER");
			
			Role role2 = new Role();
			role2.setRoleId(AppConstants.NORMAL_USER_ROLE);role2.setRoleName("NORMAL_USER");
			
			List<Role> roles = List.of(role1,role2);
			List<Role> result = this.roleRepo.saveAll(roles);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	} 
}
