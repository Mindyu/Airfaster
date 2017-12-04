package com.air.domain;
/**
 * 飞机座位
 * @JavaBean.
 * @author Dua
 */

public class Airseat {
	private int row;
	private int col;
	private boolean state;
	private String airplaneID;
	
	public void setRow(int r) { row = r; }
	public void setCol(int i) { col = i; }
	public void setState(boolean b) { state = b; }
	public void setAirplaneID(String aid) { airplaneID = aid; }
	
	public int  getRow() { return row; }
	public int  getCol() { return col; }
	public String  getAirplaneID() { return airplaneID; }
	public boolean getState() { return state; }
	
}
