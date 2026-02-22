package com.rupp.tola.dev.scoring_management_system.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ApiException.class)
	public ResponseEntity<?> handleApiException(ApiException e) {
		ErrorResponse errorRespose = new ErrorResponse(e.getHttpStatus(), e.getMessage());
		return ResponseEntity
				.status(e.getHttpStatus())
				.body(errorRespose);
	}
}
