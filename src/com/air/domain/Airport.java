package com.air.domain;
/**
 * 机场
 * @JavaBean.
 * @author Dua
 */

public class Airport {
	private String id;
	private String name;
	private String city;
	private int connectTime;
	
	public void setId(String i) { id = i; }
	public void setName(String n) { name = n; }
	public void setCity(String c) { city = c; }
	public void setConnectTime(int c) { connectTime = c; }
	
	public String getId() { return id; }
	public String getName() { return name; }
	public String getCity() { return city; }
	public int    getConnectTime() { return connectTime; }
	
}
