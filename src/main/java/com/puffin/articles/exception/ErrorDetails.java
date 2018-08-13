package com.puffin.articles.exception;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorDetails {
	private LocalDateTime timestamp;
	private String message;
	private String details;
	private String statusCode;

	public ErrorDetails(LocalDateTime timestamp, String message, String details, String statusCode) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
		this.statusCode = statusCode;
	}
}