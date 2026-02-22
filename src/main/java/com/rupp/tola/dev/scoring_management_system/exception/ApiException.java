package com.rupp.tola.dev.scoring_management_system.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public class ApiException extends RuntimeException{
	private final HttpStatus httpStatus;
	private final String message;
}
