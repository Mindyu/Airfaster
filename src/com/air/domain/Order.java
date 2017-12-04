package com.air.domain;


public class Order {
	private String id;
	private String username;
	private String flightID;
	private int passageNum;
	private double cost;
	private boolean droped;
	
	public void setId(String i) { id = i; }
	public void setUsername(String i) { username = i; }
	public void setFlightID(String i) { flightID = i; }
	public void setPassageNum(int i) { passageNum = i; }
	public void setCost(double i) { cost = i; }
	public void setDroped(boolean i) { droped = i; }
	
	public String getId() { return id; }
	public String getUsername() { return username; }
	public String getFlightID() { return flightID; }
	public int getPassageNum() { return passageNum; }
	public double getCost() { return cost; }
	public boolean getDroped() { return droped; }
	
}
