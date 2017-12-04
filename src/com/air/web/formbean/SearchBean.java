package com.air.web.formbean;
/**
 * 将查询时request表单里的信息提取成bean形式
 * @author Dua
 */
public class SearchBean {
	private String startCity;
	private String arrivCity;
	private String startTime;
	private String arrivTime;
	private String airlineName;
	
	public void setStartCity(String s) { startCity = s; }
	public void setArrivCity(String a) { arrivCity = a; }
	public void setStartTime(String s) { startTime = s; }
	public void setArrivTime(String a) { arrivTime = a; }
	public void setAirlineName(String a) { airlineName = a; }
	
	public String getStartCity()   { return startCity; }
	public String getArrivCity()   { return arrivCity; }
	public String getStartTime()   { return startTime; }
	public String getArrivTime()   { return arrivTime; }
	public String getAirlineName() {return airlineName; }
	
}
