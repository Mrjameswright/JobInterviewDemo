package com.wrightjames.bigtoolsdemo.tests;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.wrightjames.bigtoolsdemo.BigTools;
import com.wrightjames.bigtoolsdemo.RentalAgreement;
import com.wrightjames.bigtoolsdemo.exceptions.*; 

public class test1Discount101 {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/* JAKR
	 * 9/3/15
	 * 5
	 * Discount 101%
	 */
	
	@SuppressWarnings("deprecation")
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	
	@Test //(expected = InvalidDiscountPercentageException.class)
	public void test() throws Exception {
		BigTools bt = new BigTools(); 
		
		exception.expect(InvalidDiscountPercentageException.class);
		RentalAgreement ra = new RentalAgreement(bt.getTool(BigTools.JAKR), 10, "9/3/2015", 101);
		
	}

}
