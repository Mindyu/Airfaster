package com.air.domain;

import com.air.client.util.GlobalObject;

/**
 * 航班
 * @JavaBean.
 * @author Dua
 */

public class Flight implements Comparable<Flight>{
	private String id;
	private String name;
	private String startTime;
	private String arrivTime;
	private String startAirportID;
	private String arrivAirportID;
	private String startCity;
	private String arrivCity;
	private String airplaneID;
	private double cost;
	private int leftSeat;
	
	public void setId(String i) { id = i; }
	public void setName(String n) { name = n; }
	public void setStartTime(String s) { startTime = s; }
	public void setArrivTime(String a) { arrivTime = a; }
	public void setStartAirportID(String s) { startAirportID = s; }
	public void setArrivAirportID(String a) { arrivAirportID = a; }
	public void setStartCity(String s) { startCity = s; }
	public void setArrivCity(String a) { arrivCity = a; }
	public void setAirplaneID(String a) { airplaneID = a; }
	public void setCost(double c) { cost = c; }
	public void setLeftSeat(int seat) { leftSeat = seat; }
	
	public String getId() { return id; }
	public String getName() { return name; }
	public String getStartTime() { return startTime; }
	public String getArrivTime() { return arrivTime; }
	public String getStartAirportID() { return startAirportID; }
	public String getArrivAirportID() { return arrivAirportID; }
	public String getStartCity() { return startCity; }
	public String getArrivCity() { return arrivCity; }
	public String getAirplaneID() { return airplaneID; }
	public double getCost() { return cost; }
	public int    getLeftSeat() { return leftSeat; }
	
	@Override
	public int compareTo(Flight arg0) {
		String orderway = GlobalObject.sortWay;
		if ("cost".equals(orderway)) {
			return (int) (this.cost-arg0.cost);
		}else if ("leftSeat".equals(orderway)) {
			return this.leftSeat-arg0.leftSeat;
		}else if ( "id".equals(orderway)){
			return this.id.compareTo(arg0.id);
		}else if ("name".equals(orderway)) {
			return this.name.compareTo(arg0.name);
		}
		return (int) (this.cost-arg0.cost);
	}
}
