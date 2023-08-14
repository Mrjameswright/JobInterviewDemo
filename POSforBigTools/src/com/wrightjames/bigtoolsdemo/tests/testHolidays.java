package com.wrightjames.bigtoolsdemo.tests;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wrightjames.bigtoolsdemo.BigTools;
import com.wrightjames.bigtoolsdemo.RentalAgreement;

public class testHolidays {

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

	@Test
	public void test() throws Exception {
		BigTools bt = new BigTools(); 
		RentalAgreement ra = new RentalAgreement(bt.getTool(BigTools.LADW), 5, "9/4/2023", 10);
				
		assertEquals(true, ra.isHasLaborDayDiscount()); 
			
	}
	
	@Test
	public void test2() throws Exception {
		BigTools bt = new BigTools(); 
		RentalAgreement ra = new RentalAgreement(bt.getTool(BigTools.LADW), 5, "9/5/2023", 10);
				
		assertEquals(false, ra.isHasLaborDayDiscount()); 
	}
	
	@Test
	public void test3() throws Exception {
		BigTools bt = new BigTools(); 
		RentalAgreement ra = new RentalAgreement(bt.getTool(BigTools.LADW), 50, "8/4/2023", 10);
				
		assertEquals(true, ra.isHasLaborDayDiscount()); 
	}
	
	@Test
	public void test4() throws Exception {
		BigTools bt = new BigTools(); 
		RentalAgreement ra = new RentalAgreement(bt.getTool(BigTools.LADW), 50, "7/1/2023", 10);
				
		assertEquals(false, ra.isHasLaborDayDiscount()); 
	}
		
	@Test
	public void test5() throws Exception {
		BigTools bt = new BigTools(); 
		RentalAgreement ra = new RentalAgreement(bt.getTool(BigTools.LADW), 50, "7/1/2023", 10);
				
		assertEquals(true, ra.isHas4thOfJulyDiscount()); 
	}
	
	@Test
	public void test6() throws Exception {
		BigTools bt = new BigTools(); 
		RentalAgreement ra = new RentalAgreement(bt.getTool(BigTools.LADW), 70, "6/25/2023", 10);
				
		assertEquals(true, ra.isHas4thOfJulyDiscount()); 
	}
	
	@Test
	public void test7() throws Exception {
		BigTools bt = new BigTools(); 
		RentalAgreement ra = new RentalAgreement(bt.getTool(BigTools.LADW), 10, "7/5/2023", 10);
				
		assertEquals(false, ra.isHas4thOfJulyDiscount()); 
	}

}
