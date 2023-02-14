package com.surjendu.blog.exceptions;



import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.surjendu.blog.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandaler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandaler(ResourceNotFoundException e) {
		String massege = e.getMessage();
		ApiResponse apiResponse = new ApiResponse(massege,false,404);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> MethodArgumentNotValidExceptionHandaler(MethodArgumentNotValidException e){
		Map<String, String> resposeMap = new HashMap<>();
		e.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName = ((FieldError)error).getField();
			String message = error.getDefaultMessage();
			resposeMap.put(fieldName, message);
		});
		
		return new ResponseEntity<Map<String,String>>(resposeMap,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<ApiResponse> MaxUploadSizeExceededExceptionHandaler(MaxUploadSizeExceededException e){
		ApiResponse apiResponse = new ApiResponse(e.getMessage(),false,400);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(StringIndexOutOfBoundsException.class)
	public ResponseEntity<ApiResponse> StringIndexOutOfBoundsExceptionHandaler(StringIndexOutOfBoundsException e){
		ApiResponse apiResponse = new ApiResponse("Please upload a file",false,400);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ApiResponse> ApiExceptionHandaler(ApiException e){
		String message = e.getMessage();
		ApiResponse apiResponse = new ApiResponse(message,true,400);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
	}

}
