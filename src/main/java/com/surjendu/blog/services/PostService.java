package com.surjendu.blog.services;





import com.surjendu.blog.payload.PostDto;
import com.surjendu.blog.payload.PostReponse;

public interface PostService {
	public PostDto createpPost(PostDto postDto, Integer userId, Integer postId);

	public PostDto updatePost(PostDto postDto, Integer postId);

	public PostDto updatePostcategory(Integer postId, Integer categoryId);

	public PostReponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDirection);

	public PostDto getPostById(Integer postId);

	public void deletePost(Integer postId);

	public PostReponse getPostByCategory(Integer categoryId,Integer pageNumber,Integer pageSize,String sortBy,String sortDirection);

	public PostReponse getPostByUser(Integer userId,Integer pageNumber,Integer pageSize,String sortBy,String sortDirection);
	
	public PostReponse serachPostByTitle(String keyword,Integer pageNumber,Integer pageSize,String sortBy,String sortDirection);
//	public List<PostDto> serachPostByTitle(String keyword);
}
