package com.rupp.tola.dev.scoring_management_system.exception;

import java.util.UUID;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends ApiException{

	public ResourceNotFoundException(String resourceName, UUID id) {
		super(HttpStatus.NOT_FOUND, String.format("%s With id = %d not found", resourceName, id));
	}

}
