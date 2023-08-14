package com.wrightjames.bigtoolsdemo.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wrightjames.bigtoolsdemo.BigTools;
import com.wrightjames.bigtoolsdemo.RentalAgreement;

public class test3Discount25 {

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

	/* CHNS
	 * 7/2/15
	 * 5
	 * Discount 25%
	 * 
	 * Holiday Charge is true for chainsaw tools. Ignore July 4th Holiday Discount. 
	 */
	
	RentalAgreement ra;
	
	@Test
	public void test() throws Exception {
		setValues(); 
		ra.print(); 
		
		// No July 4th Holiday Discount for chainsaw tools. 
		assertEquals(ra.getDaysCharged(), 3 ); 
	}
	
	@Test
	public void test2() throws Exception {
		setValues(); 
		
		// No July 4th Holiday Discount for chainsaw tools. 
		assertEquals(false, ra.isHas4thOfJulyDiscount()); 
	}
	
	
	private void setValues() throws Exception {
		BigTools bt = new BigTools(); 
		ra = new RentalAgreement(bt.getTool(BigTools.CHNS), 5, "7/2/2015", 25);
		
	}

}
