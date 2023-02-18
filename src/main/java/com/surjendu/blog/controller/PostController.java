package com.surjendu.blog.controller;



import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.surjendu.blog.config.AppConstants;
import com.surjendu.blog.payload.ApiResponse;
import com.surjendu.blog.payload.ImageResponse;
import com.surjendu.blog.payload.PostDto;
import com.surjendu.blog.payload.PostReponse;
import com.surjendu.blog.services.impl.FielServiceImpl;
import com.surjendu.blog.services.impl.postServiceImpl;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/posts")
@SecurityRequirement(name = "bearerAuth")
public class PostController {
	@Autowired
	private postServiceImpl postServiceImpl;
	
	@Autowired
	private FielServiceImpl fileServiceImpl;
	
	@Value("${project.image}")
	private String path;
	
	//CREATE
	@PostMapping("/user/{userId}/category/{categoryId}/post")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto,@PathVariable Integer userId,@PathVariable Integer categoryId) {
		PostDto addedPost = this.postServiceImpl.createpPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(addedPost,HttpStatus.CREATED);
	}
	
	//UPDATE-post
	@PutMapping("/{postId}")
	public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,@PathVariable Integer postId){
		PostDto updatePostDto = this.postServiceImpl.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePostDto,HttpStatus.OK);
	}
	
	//UPDATE-post category
	@PutMapping("/{postId}/category/{categoryId}")
	public ResponseEntity<PostDto> updatePostByCategory(@Valid @PathVariable Integer postId,@PathVariable Integer categoryId){
		PostDto updatePostDto = this.postServiceImpl.updatePostcategory(postId,categoryId);
		return new ResponseEntity<PostDto>(updatePostDto,HttpStatus.OK);
	}
	
	//GET-single post by id
	@GetMapping("/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
		PostDto getPost =  this.postServiceImpl.getPostById(postId);
		return new ResponseEntity<PostDto>(getPost,HttpStatus.OK);
	}
	
	//GET-all posts
	@GetMapping("/posts")
	public ResponseEntity<PostReponse> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
			@RequestParam(value = "sortDirection",defaultValue = AppConstants.SORT_DIR,required = false) String sortDirection){
		PostReponse allPosts = this.postServiceImpl.getAllPost(pageNumber,pageSize,sortBy,sortDirection);
		return new ResponseEntity<PostReponse>(allPosts,HttpStatus.OK);
	}
	
	//GET-all post category id
	@GetMapping("category/{categoryId}/posts")
	public ResponseEntity<PostReponse> getAllPostByCategory(
			@PathVariable Integer categoryId,
			@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue=AppConstants.PAGE_SIZE,required = false) Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
			@RequestParam(value = "sortDirection",defaultValue = AppConstants.SORT_DIR,required = false) String sortDirection) {
		PostReponse allPostsByCategoryId = this.postServiceImpl.getPostByCategory(categoryId,pageNumber,pageSize,sortBy,sortDirection);
		return new ResponseEntity<PostReponse>(allPostsByCategoryId,HttpStatus.OK);
	}
	
	//GET-all post user id
	@GetMapping("user/{userId}/posts")
	public ResponseEntity<PostReponse> getAllPostByUser(
			@PathVariable Integer userId,
			@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue=AppConstants.PAGE_SIZE,required = false) Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
			@RequestParam(value = "sortDirection",defaultValue = AppConstants.SORT_DIR,required = false) String sortDirection) {
		
		PostReponse allPostsByUserId = this.postServiceImpl.getPostByUser(userId,pageNumber,pageSize,sortBy,sortDirection);
		return new ResponseEntity<PostReponse>(allPostsByUserId,HttpStatus.OK);
	}
	
	//DELETE-post
	@DeleteMapping("/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
		this.postServiceImpl.deletePost(postId);
		return ResponseEntity.ok(new ApiResponse("Post deleted succesfully",true,200));
	}
	
	//search post
	@GetMapping("/search/{keyword}")
	public ResponseEntity<PostReponse> searchByTitle(@PathVariable String keyword,
			@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue=AppConstants.PAGE_SIZE,required = false) Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
			@RequestParam(value = "sortDirection",defaultValue = AppConstants.SORT_DIR,required = false) String sortDirection) {
		PostReponse postReponse = this.postServiceImpl.serachPostByTitle(keyword, pageNumber, pageSize,sortBy,sortDirection);
		return new ResponseEntity<PostReponse>(postReponse,HttpStatus.OK);
	}
	
	//POST Upload Image
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<ImageResponse> uploadPostImage(@RequestParam("image") MultipartFile image, @PathVariable Integer postId){
		PostDto post = this.postServiceImpl.getPostById(postId);
		String fileName="";
		try {
			fileName = this.fileServiceImpl.uploadImage(path, image);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		post.setPostImageName(fileName);
		PostDto updatedPost = this.postServiceImpl.updatePost(post, postId);
		ImageResponse imageResponse = new ImageResponse();
		imageResponse.setPostDto(updatedPost);
		imageResponse.setMessage("Image uploaded successfully");
		imageResponse.setSuccess(true);
		imageResponse.setResponseCode(200);
		return new ResponseEntity<ImageResponse>(imageResponse,HttpStatus.OK);
	}
	
	// Download Image
	@GetMapping(value="/post/image/{imageName}",produces=MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable String imageName,HttpServletResponse response) throws IOException{
		InputStream resourse = this.fileServiceImpl.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resourse, response.getOutputStream());
	}
}
