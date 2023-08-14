package com.wrightjames.bigtoolsdemo.exceptions;

public class InvalidRentalDayCount extends Exception {

	public InvalidRentalDayCount() {
		System.out.println("The Rental Day count cannot be less than 1!!!");
	}
	
}
