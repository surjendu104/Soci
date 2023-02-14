package com.surjendu.blog.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	String resourceName;
	String fieldName;
	long fieldId;
	String stringFieldId;
	
	public ResourceNotFoundException(String resourceName, String fieldName, long fieldId) {
		super(String.format("%s not found with %s : %s",resourceName,fieldName,fieldId));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldId = fieldId;
	}
	
	public ResourceNotFoundException(String resourceName, String fieldName, String stringFieldId) {
		super(String.format("%s not found with %s : %s",resourceName,fieldName,stringFieldId));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.stringFieldId = stringFieldId;
	}
	
}
