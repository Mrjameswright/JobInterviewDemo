package com.wrightjames.bigtoolsdemo.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wrightjames.bigtoolsdemo.BigTools;
import com.wrightjames.bigtoolsdemo.RentalAgreement;

public class test2JulyThe4thDiscount {

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

	/* LADW
	 * 7/2/20
	 * 3
	 * Discount 10%
	 */
	
	RentalAgreement ra;
	
	
	@Test
	public void test() throws Exception {
		setValues(); 
		ra.print(); 
		
		assertEquals(true, ra.isHas4thOfJulyDiscount()); 
	
	}
	
	@Test 
	public void test2() throws Exception {
		setValues(); 
		
		// DaysCharged should be equal to the DaysRental minus 1 for the Holiday discount. 
		assertEquals(ra.getDaysRental() -1, ra.getDaysCharged());

	}
	
	private void setValues() throws Exception {
		BigTools bt = new BigTools(); 
		ra = new RentalAgreement(bt.getTool(BigTools.LADW), 3, "7/2/2020", 10);
		
	}

}
