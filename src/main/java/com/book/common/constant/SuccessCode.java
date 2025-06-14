package com.book.common.constant;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum SuccessCode {

	SUCCESS_CODE(HttpStatus.OK, "A-001","Success."),

	;
	SuccessCode(HttpStatus httpStatus, String successCode, String message) {
		this.httpStatus = httpStatus;
		this.successCode = successCode;
		this.message = message;
	}

	private final HttpStatus httpStatus;
	private final String successCode;
	private final String message;
}
