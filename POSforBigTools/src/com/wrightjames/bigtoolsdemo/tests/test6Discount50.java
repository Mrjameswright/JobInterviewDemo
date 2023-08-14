package com.wrightjames.bigtoolsdemo.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wrightjames.bigtoolsdemo.BigTools;
import com.wrightjames.bigtoolsdemo.RentalAgreement;

public class test6Discount50 {

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
	 * 7/2/20
	 * 4
	 * Discount 50%
	 */
	
	@Test
	public void test() throws Exception {
		BigTools bt = new BigTools(); 
		RentalAgreement ra = new RentalAgreement(bt.getTool(BigTools.JAKR), 4, "7/2/2020", 50);
		
		ra.print(); 
		
		// Apply July 4th Discount for jackhammer tool. 
		assertEquals(true, ra.isHas4thOfJulyDiscount()); 
	}

}
