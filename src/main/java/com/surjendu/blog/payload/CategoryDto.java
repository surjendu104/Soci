package com.surjendu.blog.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {
	
	private Integer categoryId;
	
	@NotBlank
	@NotEmpty
	@Size(min = 3,max = 100,message = "Category title must be minimum of 3 character and maximum of 100 character")
	private String categoryTitle;
	
	@NotBlank
	@NotEmpty
	@Size(min = 10,max = 750,message = "Category title must be minimum of 10 character and maximum of 750 character")
	private String categoryDescription;
	
	
}
