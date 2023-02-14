package com.surjendu.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surjendu.blog.entities.Comment;
import com.surjendu.blog.entities.Post;
import com.surjendu.blog.entities.User;
import com.surjendu.blog.exceptions.ResourceNotFoundException;
import com.surjendu.blog.payload.CommentDto;
import com.surjendu.blog.repositories.CommentRepo;
import com.surjendu.blog.repositories.PostRepo;
import com.surjendu.blog.repositories.UserRepo;
import com.surjendu.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post ", "post id", postId));
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User ", "user id", userId));
		
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		comment.setUser(user);
		
		Comment savedComment = this.commentRepo.save(comment);
		
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment com = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment ", "comment id", commentId));
		this.commentRepo.delete(com);
		
	}

}
