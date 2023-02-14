package com.surjendu.blog.payload;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	private String postId;
	@NotEmpty
	private String postTitle;
	@NotEmpty
	private String postContent;
	@NotEmpty
	private String postImageName;

	private Date postDate;

	private CategoryDto category;
	private UserDto user;

	private Set<CommentDto> comments = new HashSet<>();
}
