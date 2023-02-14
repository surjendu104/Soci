package com.surjendu.blog.payload;


import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	private int id;
	
	@NotEmpty
	@Size(min=4,message = "User's name must be minimum of 4 characters")
	private String name;
	
	@NotEmpty
	@Email(message = "Your Email is Not valid")
	private String email;
	
	@NotEmpty
	@Size(min = 3,max=36,message = "Password must be greater than 4 characters")
	private String password;
	
	@NotEmpty
	@Size(message = "About can not be empty")
	private String about;


	private Set<CommentDto> comments = new HashSet<>();
	
	private Set<RoleDto> roles = new HashSet<>();
}
