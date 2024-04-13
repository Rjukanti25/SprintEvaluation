package com.example.insurance.exception;

public class PaymentsNotFoundException extends RuntimeException{
	public PaymentsNotFoundException(String message) {
		super(message);
	}
}
