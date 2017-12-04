package com.air.domain;
/**
 * 航空公司
 * @JavaBean.
 * @author Dua
 */

public class Airline {
	private String id;
	private String name;
	private int discount;
	private String url;
	private String username;
	private String password;
	
	public void setId(String i) { id = i; }
	public void setName(String n) { name = n; }
	public void setDiscount(int d) { discount = d; }
	public void setUrl(String u) { url = u; }
	public void setUsername(String u) { username = u; }
	public void setPassword(String p) { password = p; }
	
	public String getId() { return id; }
	public String getName() { return name; }
	public int	  getDiscount() { return discount; }
	public String getUrl() { return url; }
	public String getUsername() { return username; }
	public String getPassword() { return password; }
}
