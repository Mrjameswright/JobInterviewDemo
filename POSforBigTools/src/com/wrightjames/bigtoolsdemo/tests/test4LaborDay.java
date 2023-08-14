package com.wrightjames.bigtoolsdemo.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wrightjames.bigtoolsdemo.BigTools;
import com.wrightjames.bigtoolsdemo.RentalAgreement;

public class test4LaborDay {

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

	/* JAKD
	 * 9/3/15
	 * 6
	 * Discount 0%
	 */
	
	RentalAgreement ra;
	
	@Test
	public void test() throws Exception {
		setValues(); 		
		ra.print(); 
		
		assertEquals(true, ra.isHasLaborDayDiscount()); 
	}
	
	@Test
	public void test2() throws Exception {
		setValues(); 
		
		// DiscountAmt should be 0.0 since the Discount Percentage is zero. 
		assertEquals(ra.getDiscountAmt(), 0.0, 0); 
	}
	
	private void setValues() throws Exception {
		BigTools bt = new BigTools(); 
		ra = new RentalAgreement(bt.getTool(BigTools.JAKD), 6, "9/3/2015", 0);
	}

}
