package com.wrightjames.bigtoolsdemo;

import java.util.Date;
import java.util.HashMap;

public class BigTools {
	static HashMap<Integer, ToolType> tools = new HashMap();
	final static public int CHNS = 1; 
	final static public int LADW = 2; 
	final static public int JAKD = 3; 
	final static public int JAKR = 4; 
	
	public BigTools () {
		loadTools(); 
	}
	

	public static void main(String[] args) throws Exception {
		loadTools();
		//ToolType t = tools.get(LADW);
		//t.print();
		 
		RentalAgreement ra = new RentalAgreement(tools.get(LADW), 5, "8/14/2023", 10);
		
		ra.print(); 
		
		//RentalAgreement ra = new RentalAgreement("LADW", 5, "8/14/2023", 10);
		//ra.print();
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

	public static HashMap<Integer, ToolType> getTools() {
		return tools;
	}


	public static void setTools(HashMap<Integer, ToolType> tools) {
		BigTools.tools = tools;
	}
	
	

}
