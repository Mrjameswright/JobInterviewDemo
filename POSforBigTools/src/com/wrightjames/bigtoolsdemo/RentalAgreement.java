package com.wrightjames.bigtoolsdemo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.time.DayOfWeek;
import java.time.LocalDate;
import static java.time.temporal.TemporalAdjusters.firstInMonth;

public class RentalAgreement {
	ToolType tool;
	int daysRental=0;
	int daysCharged=0; 
	
	String checkOutDtStr;
	Date checkOutDt; 
	Date dueDt;
	SimpleDateFormat df = new SimpleDateFormat("MM/dd/yy");
	
	double prediscountCharge=0;
	int discountPercent=0;
	double discountAmt=0;
	double finalCharge=0;
	
	boolean hasLaborDayDiscount = false;
	boolean has4thOfJulyDiscount = false; 
	
	static HashMap<Integer, ToolType> tools = new HashMap();
	final static public int CHNS = 1; 
	final static public int LADW = 2; 
	final static public int JAKD = 3; 
	final static public int JAKR = 4; 
	
	public void print() {
		
		String output= "Tool Code: "+ tool.getCode() + "\n" +
		"Tool Type: "+tool.getType() + "\n" +
 		"Tool Brand: "+tool.getBrand() + "\n" +
		"Rental Days: "+daysRental + "\n" +
		"Check Out Date: "+df.format(checkOutDt) + "\n" +
		"Due Date: "+df.format(dueDt) + "\n" +
		"Daily Rental Price: $"+tool.getCharge() + "\n" +
		"Charge Days: "+daysCharged + "\n" +
		"Pre-discount Charge: $"+Math.round(prediscountCharge * 100.0) / 100.0 + "\n" +
		"Discount Percent: "+discountPercent + "%\n" +
		"Discount Amount: $"+ (discountAmt == 0 ? "0.00" : Math.round(discountAmt * 100.0) / 100.0) + "\n" +
		"\n"+
		"Final Charge: $"+Math.round(finalCharge * 100.0) / 100.0 +"\n\n\n";
		
		System.out.println(output); 
		
	};
	
	/*
	 * CHNS = 1; 
	 * LADW = 2; 
	 * JAKD = 3; 
	 * JAKR = 4; 
	 */
	public RentalAgreement(String ToolCode, int Days, String CheckOutDtStr, int DiscountPercent ) throws Exception {
		ToolType Tool=null;  
		
		loadTools(); 
		
		switch (ToolCode) {
			case "CHNS":
				Tool = tools.get(CHNS);
				break; 
			case "LADW":
				Tool = tools.get(LADW);
				break; 
			case "JAKD":
				Tool = tools.get(JAKD);
				break;
			case "JAKR":
				Tool = tools.get(JAKR);
				break; 
		}
			
		checkout(Tool, Days, CheckOutDtStr, DiscountPercent); 
			
			
	}

	
	public RentalAgreement(ToolType Tool, int Days, String CheckOutDtStr, int DiscountPercent ) throws Exception {
		checkout(Tool, Days, CheckOutDtStr, DiscountPercent); 
	}
		
	private void checkout(ToolType Tool, int Days, String CheckOutDtStr, int DiscountPercent ) throws Exception {
		
		if (!(Days >= 1)) {
			throw new com.wrightjames.bigtoolsdemo.exceptions.InvalidRentalDayCount(); 
		}
		if (DiscountPercent <0 || DiscountPercent > 100) {
			throw new com.wrightjames.bigtoolsdemo.exceptions.InvalidDiscountPercentageException();
		}
		
		tool = Tool;
		daysRental = Days;
		daysCharged = Days; 
		checkOutDtStr = CheckOutDtStr; 
		discountPercent = DiscountPercent; 
		
		
		checkOutDt = df.parse(checkOutDtStr);
//		System.out.println("Checkout Date: "+checkOutDt);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(checkOutDt); 
//		try {
//			int mm = Integer.parseInt(checkOutDt.split("/")[0]);
//			int dd = Integer.parseInt(checkOutDt.split("/")[1]); 
//			int yy = Integer.parseInt(checkOutDt.split("/")[2]); 
//			
//			System.out.println("Checkout Date mm/dd/yy: "+mm+"/"+dd+"/"+yy); 
//			cal.set(yy, mm, dd); 
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("Invalid Date: "+ checkOutDt);
//			System.out.println("Correct format is: mm/dd/yy"); 
//		}
	
//		System.out.println("Checkout Date: " + cal.getTime()); 
		cal.add(Calendar.DAY_OF_MONTH, daysRental); 
//		System.out.println("Due Date: "+ cal.getTime()); 
		dueDt = cal.getTime(); 
		
		//TODO Calculate days to charge for weekends
		// If isWeekendCharge is true, there will be a charge for both weekdays and weekends. 
		// If isWeekendCharge is false, there will be no charge for weekends. 
		if (!tool.isWeekendCharge())
			daysCharged = getWorkingDaysBetweenTwoDates(checkOutDt, dueDt);
		//System.out.println("Days Charged before holiday discounts: "+daysCharged); 
		
		
		// If Holiday Charge is false, determine if a holiday exists in the rental time frame. Give discount if it does. 
		// If Holiday Charge is true, treat it as a normal day. No holiday discounts. 
		//TODO Should consider merging the two methods into one since they are so similar. 
		if (!tool.HolidayCharge) {
			if (isJuly4th(checkOutDt, dueDt)) {
				daysCharged = daysCharged - 1; // Getting a discount for July 4th holiday
			}; 
			
			//TODO Calculate discount for Labor Day
			if (isLaborDay(checkOutDt, dueDt)) {
				daysCharged = daysCharged - 1; // Getting a discount for Labor day holiday
			}
		}
		
				
		//TODO All current tools are charged for weekdays. No need to code this unless the business adds new tools that aren't charged for weekdays, which seems unlikely. Confirm with business. 
		
		
		prediscountCharge = tool.Charge * daysCharged; 
		double disPercent = 0; 
		if (discountPercent > 0) disPercent = discountPercent*0.01;  
		//System.out.println(discountPercent*0.01);
		//System.out.println(discountPercent); 
		
		finalCharge = prediscountCharge * (1 - (disPercent)); // Exact amount, but we should round up to the nearest penny. Reminds me of Office Space and Superman 3. 
				
		discountAmt = prediscountCharge * ((disPercent)); // Exact amount, but we should round down to the nearest penny. Reminds me of Office Space and Superman 3. 
		
		//		System.out.println((Math.floor(discountAmt*100)*.01)); 
		discountAmt=(Math.floor(discountAmt*100))*.01; 
		
				
		//System.out.println((Math.round(discountAmt * 100))*.01); 
		//System.out.println("finalCharge: "+finalCharge); 
		//System.out.println("Math.ceil(finalCharge * 100)): "+ Math.ceil(finalCharge * 100));
		//System.out.println("(Math.round(finalCharge * 100))*.01" + (Math.round(finalCharge * 100))*.01);
		finalCharge = (Math.ceil(finalCharge * 100))*.01;
		

		
	}
	
	//TODO Code not designed for leases over a year. Ask business if this should be addressed. 
	boolean isJuly4th(Date start, Date end) {
		
		//boolean hasJuly4thDiscount = false; 
		
//		int sy = start.getYear(); 	
//		int ey = end.getYear(); 
//		
//		System.out.println("start and end year: "+sy+" "+ey); 
		//Date july4th = new Date(); 
		
		Calendar calStart = Calendar.getInstance();
		calStart.setTime(start);
		
		Calendar calEnd = Calendar.getInstance(); 
		calEnd.setTime(end); 
		
//		System.out.println("calStart.compareTo(calEnd)"+ calStart.compareTo(calEnd)); 
//		System.out.println("calStart.compareTo(calStart)"+ calStart.compareTo(calStart)); 
//		System.out.println("calEnd.compareTo(calStart)"+ calEnd.compareTo(calStart));
		
//		System.out.println("Start year: "+calStart.get(Calendar.YEAR)); 
//		System.out.println("End year: "+calEnd.get(Calendar.YEAR));
		
		/*
		 * Calendar calJuly4thA = Calendar.getInstance();
		 * calJuly4thA.set(calStart.get(Calendar.YEAR), Calendar.JULY, 4);
		 * 
		 * Calendar calJuly4thB = Calendar.getInstance();
		 * calJuly4thB.set(calEnd.get(Calendar.YEAR), Calendar.JULY, 4);
		 * 
		 * System.out.println("calJuly4thA: "+ df.format(calJuly4thA.getTime()));
		 * System.out.println("calJuly4thB: "+ df.format(calJuly4thB.getTime()));
		 */

		if (calStart.get(Calendar.MONTH) <= Calendar.JULY && calEnd.get(Calendar.MONTH) >= Calendar.JULY) {
			if (calStart.get(Calendar.MONTH) == Calendar.JULY) {
				//System.out.println("Calendar.MONTH: "+calStart.get(Calendar.MONTH)); 
				//System.out.println("START MONTH IS IN JULY!");
			    if (calStart.get(Calendar.DAY_OF_MONTH) > 4) {
			    	//System.out.println("Start date is after July 4th."); 
			    	return false; // Start day is after July 4th.
			    }
			}
			if (calEnd.get(Calendar.MONTH) == Calendar.JULY) {
				//System.out.println("Calendar.MONTH: "+calEnd.get(Calendar.MONTH)); 
				//System.out.println("END MONTH IS IN JULY!");
			    if (calEnd.get(Calendar.DAY_OF_MONTH) <= 4) {
			    	//System.out.println("End date is before July 4th."); 
			    	return false; // End date is before July 4th.
			    }
			}
			
//			System.out.println("We have a July 4th holiday discount!!!");
			has4thOfJulyDiscount = true; 
			return has4thOfJulyDiscount; 
		};
		
		
		return false; 
	}

	//TODO Code not designed for leases over a year. Ask business if this should be addressed. 
	// Labor day is first Monday in September.
	boolean isLaborDay(Date start, Date end) { 
		
		Calendar calStart = Calendar.getInstance();
		calStart.setTime(start);
		
		Calendar calEnd = Calendar.getInstance(); 
		calEnd.setTime(end); 

		if (calStart.get(Calendar.MONTH) <= Calendar.SEPTEMBER && calEnd.get(Calendar.MONTH) >= Calendar.SEPTEMBER) {
			

//			System.out.println("firstMonday: "+ firstMonday.atStartOfDay());
//			System.out.println("firstMonday: "+ firstMonday.atStartOfDay().getDayOfMonth());
//			System.out.println("firstMonday: "+ firstMonday.atStartOfDay().getDayOfWeek());
//			System.out.println("firstMonday: "+ firstMonday.atStartOfDay().getDayOfYear());
//			System.out.println("firstMonday: "+ firstMonday.atStartOfDay().getMonthValue());			
			
			
			
			if (calStart.get(Calendar.MONTH) == Calendar.SEPTEMBER) {
				// Determine when is Labor Day (first Monday of September)
				LocalDate now = LocalDate.of(calStart.get(Calendar.YEAR), Calendar.SEPTEMBER +1, 1); // calStart.get(Calendar.DATE)); 
				//System.out.println("now: "+ now);
				LocalDate firstMonday = now.with(firstInMonth(DayOfWeek.MONDAY)); 
				int dayOfMonthForLaborDay = firstMonday.atStartOfDay().getDayOfMonth(); 
				
				//System.out.println("Calendar.MONTH: "+calStart.get(Calendar.MONTH)); 
//				System.out.println("START MONTH IS IN SEPTEMBER!");
			    if (calStart.get(Calendar.DAY_OF_MONTH) > dayOfMonthForLaborDay) {
//			    	System.out.println("Start date is on or after Labor Day."); 
			    	hasLaborDayDiscount=false; 
			    	return hasLaborDayDiscount; // Start day is after Labor Day.
			    }
			}
			if (calEnd.get(Calendar.MONTH) == Calendar.SEPTEMBER) {
				// Determine when is Labor Day (first Monday of September)
				LocalDate now = LocalDate.of(calEnd.get(Calendar.YEAR), Calendar.SEPTEMBER +1, 1); // calEnd.get(Calendar.DATE)); 
				//System.out.println("now: "+ now);
				LocalDate firstMonday = now.with(firstInMonth(DayOfWeek.MONDAY)); 
				int dayOfMonthForLaborDay = firstMonday.atStartOfDay().getDayOfMonth(); 
				
				//System.out.println("Calendar.MONTH: "+calEnd.get(Calendar.MONTH)); 
//				System.out.println("END MONTH IS IN SEPTEMBER!!");
			    if (calEnd.get(Calendar.DAY_OF_MONTH) <= dayOfMonthForLaborDay) {
//			    	System.out.println("End date is on or before Labor Day.");
			    	hasLaborDayDiscount = false; 
			    	return hasLaborDayDiscount; // End date is before Labor Day.
			    }
			}
			
//			System.out.println("We have a Labor Day holiday discount!!!");
			hasLaborDayDiscount=true; 
			return hasLaborDayDiscount; 
		};
		
		hasLaborDayDiscount=false; 
		return hasLaborDayDiscount; 
	}

	
	public static int getWorkingDaysBetweenTwoDates(Date startDate, Date endDate) {
	    Calendar startCal = Calendar.getInstance();
	    startCal.setTime(startDate);        

	    Calendar endCal = Calendar.getInstance();
	    endCal.setTime(endDate);

	    int workDays = 0;

	    //Return 0 if start and end are the same
	    if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
	        return 0;
	    }

	    if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
	        startCal.setTime(endDate);
	        endCal.setTime(startDate);
	    }

	    do {
	       //excluding start date
	        startCal.add(Calendar.DAY_OF_MONTH, 1);
	        if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
	            ++workDays;
	        }
	    } while (startCal.getTimeInMillis() < endCal.getTimeInMillis()); //excluding end date

	    return workDays;
	}
	
	private static void loadTools() {
		try {
			tools.put(CHNS, new ToolType("CHNS", "Chainsaw", "Stihl"));
			tools.put(LADW, new ToolType("LADW", "Ladder", "Werner"));
			tools.put(JAKD, new ToolType("JAKD", "Jackhammer", "Dewalt"));
			tools.put(JAKR, new ToolType("JAKR", "Jackhammer", "Ridgid"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ToolType getTool(int i) {
		loadTools(); 		
		return tools.get(i); 
	}
	
    public static boolean sameYear(Calendar one, Calendar two) {
        return one.get(Calendar.YEAR) == two.get(Calendar.YEAR);
    }

	public boolean isHasLaborDayDiscount() {
		return hasLaborDayDiscount;
	}

	public void setHasLaborDayDiscount(boolean hasLaborDayDiscount) {
		this.hasLaborDayDiscount = hasLaborDayDiscount;
	}

	public boolean isHas4thOfJulyDiscount() {
		return has4thOfJulyDiscount;
	}

	public void setHas4thOfJulyDiscount(boolean has4thOfJulyDiscount) {
		this.has4thOfJulyDiscount = has4thOfJulyDiscount;
	}

	public int getDaysRental() {
		return daysRental;
	}

	public void setDaysRental(int daysRental) {
		this.daysRental = daysRental;
	}

	public int getDaysCharged() {
		return daysCharged;
	}

	public void setDaysCharged(int daysCharged) {
		this.daysCharged = daysCharged;
	}

	public double getDiscountAmt() {
		return discountAmt;
	}

	public void setDiscountAmt(double discountAmt) {
		this.discountAmt = discountAmt;
	}

	
	
}
