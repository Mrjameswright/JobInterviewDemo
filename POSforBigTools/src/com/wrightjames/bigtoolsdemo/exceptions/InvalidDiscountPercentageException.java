package com.wrightjames.bigtoolsdemo.exceptions;

public class InvalidDiscountPercentageException extends Exception {

	public InvalidDiscountPercentageException() {
		System.out.println("The discount percentage has to be between 0 and 100!!!");
	}
	
}
