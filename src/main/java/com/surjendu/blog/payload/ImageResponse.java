package com.surjendu.blog.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ImageResponse {
	private PostDto postDto;
	private String message;
	private String ImageName;
	private boolean success;
	private Integer responseCode;
}
