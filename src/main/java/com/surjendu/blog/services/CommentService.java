package com.surjendu.blog.services;

import com.surjendu.blog.payload.CommentDto;

public interface CommentService {
	CommentDto createComment(CommentDto commentDto,Integer postId,Integer userId);
	void deleteComment(Integer commentId);
}
