package com.surjendu.blog.payload;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostReponse {
	private List<PostDto> content;
	private int pageNumber;
	private int pageSize;
	private int totalElement;
	private int totalPage;
	private boolean lastPage;
}
