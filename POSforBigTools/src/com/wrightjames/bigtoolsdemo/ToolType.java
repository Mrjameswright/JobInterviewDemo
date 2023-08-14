package com.wrightjames.bigtoolsdemo;

public class ToolType {
	String Code;
	String Type;
	String Brand;
	double Charge;
	boolean WeekendCharge=true;  // Better to over charge and give a refund
	boolean HolidayCharge=true; // Better to over charge and give a refund
	
	
	public void print() {
		System.out.println(this.getCode() + " "+ this.getType()+" "+this.getBrand() +" "+Charge+" "+WeekendCharge+" "+HolidayCharge);
	}
	
	public ToolType(String code, String type, String brand) {
		super();
		Code = code;
		Type = type;
		Brand = brand;
		
		// ignoring Weekday Charge because it is always true. Might have to add code if it is false for new item, but risk seems low. 
		switch (type) {
			case "Ladder":
				Charge=1.99;
				WeekendCharge=true;
				HolidayCharge=false;
				break;
			case "Chainsaw":
				Charge=1.49;
				WeekendCharge=false;
				HolidayCharge=true;
				break;
			case "Jackhammer":
				Charge=2.99;
				WeekendCharge=false;
				HolidayCharge=false;
				break;
			//TODO Should set a default?
				
		}
	}
	public String getCode() {
		return Code;
	}
	public void setCode(String tool) {
		Code = tool;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getBrand() {
		return Brand;
	}
	public void setBrand(String brand) {
		Brand = brand;
	}

	public double getCharge() {
		return Charge;
	}

	public void setCharge(double charge) {
		Charge = charge;
	}

	public boolean isWeekendCharge() {
		return WeekendCharge;
	}

	public void setWeekendCharge(boolean weekendCharge) {
		WeekendCharge = weekendCharge;
	}

	public boolean isHolidayCharge() {
		return HolidayCharge;
	}

	public void setHolidayCharge(boolean holidayCharge) {
		HolidayCharge = holidayCharge;
	} 
	
	
}
