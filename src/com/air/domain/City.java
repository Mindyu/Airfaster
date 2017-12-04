package com.air.domain;
/**
 * 城市
 * @JavaBean.
 * @author Dua
 */

public class City {
	private String name;
	private String country;
	
	public void setName(String n) { name = n; }
	public void setCountry(String c) { country = c; }
	
	public String getName() { return name; }
	public String getCountry() { return country; }
	
}
