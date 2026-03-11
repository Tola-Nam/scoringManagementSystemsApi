package com.rupp.tola.dev.scoring_management_system.exception;

import org.springframework.http.HttpStatus;

import java.util.NoSuchElementException;

public class ResourceNotFoundException extends RuntimeException {


	public ResourceNotFoundException(String message) {
		super(message);
	}

//	public ResourceNotFoundException(String resourceName, String message) {
//		super(HttpStatus.NOT_FOUND, String.format("%s not found", resourceName, message));
//	}
//
//	public ResourceNotFoundException(String resourceName, Boolean status) {
//		super(HttpStatus.NOT_FOUND, String.format("%s not found", resourceName, status));
//	}

//	public ResourceNotFoundException(String message) {
//		super(HttpStatus.NOT_FOUND, message);
//	}
}
