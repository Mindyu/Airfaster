package com.air.domain;
/**
 * 飞机
 * @JavaBean.
 * @author Dua
 */

public class Airplane {
	private String id;
	private String name;
	private String airlineID;
	private int row;
	private int leftSeat;	//剩余座位(初始为row*6)
	
	public void setId(String i) { id = i; }
	public void setName(String n) { name = n; }
	public void setAirlineID(String aid) { airlineID = aid; }
	public void setRow(int r) { row = r; }
	public void setLeftSeat(int i) { leftSeat = i; }
	
	public String getId() { return id; }
	public String getName() { return name; }
	public String getAirlineID() { return airlineID; }
	public int    getRow() { return row; }
	public int    getLeftSeat() { return leftSeat; }
	
}
