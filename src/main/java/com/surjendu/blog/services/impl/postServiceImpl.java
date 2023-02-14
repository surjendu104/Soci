package com.surjendu.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.surjendu.blog.entities.Category;
import com.surjendu.blog.entities.Post;
import com.surjendu.blog.entities.User;
import com.surjendu.blog.exceptions.ResourceNotFoundException;
import com.surjendu.blog.payload.PostDto;
import com.surjendu.blog.payload.PostReponse;
import com.surjendu.blog.repositories.CategoryRepo;
import com.surjendu.blog.repositories.PostRepo;
import com.surjendu.blog.repositories.UserRepo;
import com.surjendu.blog.services.PostService;

@Service
public class postServiceImpl implements PostService {
	@Autowired
	public PostRepo postRepo;

	@Autowired
	public ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createpPost(PostDto postDto, Integer userId, Integer categoryId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User ", "user id", userId));
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", "category id", categoryId));

		Post post = this.modelMapper.map(postDto, Post.class);
		post.setPostImageName("default.png");
		post.setPostDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post createdPost = this.postRepo.save(post);
		return this.modelMapper.map(createdPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post ", "post id", postId));
		post.setPostTitle(postDto.getPostTitle());
		post.setPostContent(postDto.getPostContent());
		post.setPostImageName(postDto.getPostImageName());
		post.setPostDate(new Date());
		Post updatedPost = this.postRepo.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}
	
	@Override
	public PostDto updatePostcategory(Integer postId, Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category ", "category id", categoryId));
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post ", "post id", postId));
		post.setCategory(category);
		Post updatedPost = this.postRepo.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public PostReponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDirection) {
		Sort sort = (sortDirection.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		/*if(sortDirection.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		}else {
			sort = Sort.by(sortBy).descending();
		}*/
		
		Pageable p = PageRequest.of(pageNumber, pageSize,sort);
		
		Page<Post> pagePost = this.postRepo.findAll(p);
		
		List<Post> allPost= pagePost.getContent();
		
		List<PostDto> allPostDtos = allPost.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostReponse postReponse = new PostReponse();
		postReponse.setContent(allPostDtos);
		postReponse.setPageNumber(pagePost.getNumber());
		postReponse.setPageSize(pagePost.getSize());
		postReponse.setTotalElement(pagePost.getNumberOfElements());
		postReponse.setTotalPage(pagePost.getTotalPages());
		postReponse.setLastPage(pagePost.isLast());
		
		return postReponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post =  this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post ","post id",postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post =this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post ","post id",postId));
		this.postRepo.delete(post);
	}

	@Override
	public PostReponse getPostByCategory(Integer categoryId,Integer pageNumber,Integer pageSize,String sortBy,String sortDirection) {
		Sort sort = (sortDirection.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		Category category =  this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category ","category id",categoryId));
		
		Page<Post> pagePost = this.postRepo.findByCategory(category,p);
		
		List<Post> allPostByCategory = pagePost.getContent();
		List<PostDto> allPostByCategoryDto=allPostByCategory.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostReponse postReponse = new PostReponse();
		postReponse.setContent(allPostByCategoryDto);
		postReponse.setPageNumber(pagePost.getNumber());
		postReponse.setPageSize(pagePost.getSize());
		postReponse.setTotalElement(pagePost.getNumberOfElements());
		postReponse.setTotalPage(pagePost.getTotalPages());
		postReponse.setLastPage(pagePost.isLast());
		
		return postReponse;
	}

	@Override
	public PostReponse getPostByUser(Integer userId,Integer pageNumber,Integer pageSize,String sortBy,String sortDirection) {
		Sort sort = (sortDirection.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		User user =  this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User ","user id",userId));
		Pageable p = PageRequest.of(pageNumber, pageSize,sort);
		Page<Post> pagePost = this.postRepo.findByUser(user, p);
		List<Post> allPostByUser = pagePost.getContent();
		List<PostDto> allPostByUserDto = allPostByUser.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		
		PostReponse postReponse = new PostReponse();
		postReponse.setContent(allPostByUserDto);
		postReponse.setPageNumber(pagePost.getNumber());
		postReponse.setPageSize(pagePost.getSize());
		postReponse.setTotalElement(pagePost.getNumberOfElements());
		postReponse.setTotalPage(pagePost.getTotalPages());
		postReponse.setLastPage(pagePost.isLast());
		
		return postReponse;
	}

	@Override
	public PostReponse serachPostByTitle(String keyword,Integer pageNumber,Integer pageSize,String sortBy,String sortDirection) {
		Sort sort = (sortDirection.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		Pageable p = PageRequest.of(pageNumber, pageSize,sort);
		Page<Post> pagePost = this.postRepo.searchByTitle("%"+keyword+"%", p);
		List<Post> allPosts = pagePost.getContent();
		
		List<PostDto> alPostDtos = allPosts.stream().map((allPost)->this.modelMapper.map(allPost, PostDto.class)).collect(Collectors.toList());
		
		PostReponse postReponse = new PostReponse();
		postReponse.setContent(alPostDtos);
		
		postReponse.setPageNumber(pagePost.getNumber());
		postReponse.setPageSize(pagePost.getSize());
		postReponse.setTotalElement(pagePost.getNumberOfElements());
		postReponse.setTotalPage(pagePost.getTotalPages());
		postReponse.setLastPage(pagePost.isLast());
		return postReponse;
	}

	
}
