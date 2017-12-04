package com.air.domain;

public class Passage {
	private String orderID;
	private String name;
	private String pid;
	private String phone;
	private int seatRow;
	private int seatCol;
	private boolean seated;
	
	public void setOrderID(String i) { orderID = i; }
	public void setName(String i) { name = i; }
	public void setPid(String i) { pid = i; }
	public void setPhone(String i) { phone = i; }
	public void setSeatRow(int i) { seatRow = i; }
	public void setSeatCol(int i) { seatCol = i; }
	public void setSeated(boolean i) { seated = i; }
	
	public String  getOrderID() { return orderID; }
	public String  getName() { return name; }
	public String  getPid() { return pid; }
	public String  getPhone() { return phone; }
	public int 	   getSeatRow() { return seatRow; }
	public int 	   getSeatCol() { return seatCol; }
	public boolean getSeated() { return seated; }
}
