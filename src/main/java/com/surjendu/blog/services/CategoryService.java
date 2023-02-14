package com.surjendu.blog.services;

import java.util.List;

import com.surjendu.blog.payload.CategoryDto;

public interface CategoryService {
	//create
	public CategoryDto createCategory(CategoryDto catagoryDto);
	//update
	public CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
	//delete
	public void deleteCategory(Integer categoryId);
	//get
	public CategoryDto getCategory(Integer categoryId);
	//get-all
	public List<CategoryDto> getAllCategory();
}
