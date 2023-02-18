package com.surjendu.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.surjendu.blog.payload.ApiResponse;
import com.surjendu.blog.payload.CommentDto;
import com.surjendu.blog.services.impl.CommentServiceImpl;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/comments")
@SecurityRequirement(name = "bearerAuth")
public class CommentController {
	@Autowired
	private CommentServiceImpl commentServiceImpl;
	
	@PostMapping("/post/{postId}/user/{userId}/comments")
	public ResponseEntity<CommentDto> createComment(
			@RequestBody CommentDto comment,
			@PathVariable Integer postId,
			@PathVariable Integer userId
			) {
		CommentDto commentDto = this.commentServiceImpl.createComment(comment, postId, userId);
		return new ResponseEntity<CommentDto>(commentDto,HttpStatus.OK);
	}
	
	@DeleteMapping("/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId) {
		this.commentServiceImpl.deleteComment(commentId);
		return ResponseEntity.ok(new ApiResponse("Comment deleted succesfully",true,200));
	}
}
