package com.surjendu.blog.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {
	private Integer commentId;
	private String content;
//	private UserDto user;
//	private PostDto post;
}
