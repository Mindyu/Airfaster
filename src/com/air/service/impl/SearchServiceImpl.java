package com.air.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.air.dao.impl.Singleton;
import com.air.domain.Airline;
import com.air.domain.Airplane;
import com.air.domain.Airport;
import com.air.domain.Flight;
import com.air.service.ISearchService;
import com.air.web.formbean.SearchBean;

public class SearchServiceImpl implements ISearchService {
	private String res;
	public void setRes(String r) { res = r; }
	public String getRes() { return res; }
	private int cnt = 0;
	
	@Override
	public Flight BeanToFlight(SearchBean s) {
		// TODO Auto-generated method stub
		Flight flight = new Flight();
		flight.setStartCity(s.getStartCity()); flight.setArrivCity(s.getArrivCity());
		String sTime = s.getStartTime() + " 00:00:00";
		String aTime = s.getArrivTime() + " 00:00:00";
		System.out.println(sTime);
		flight.setStartTime(sTime); flight.setArrivTime(aTime);
		
		return flight;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public String showFlight(Flight flight) {
		Airplane airplane = Singleton.AirplaneDao().findByID(flight.getAirplaneID());
		Airline airline = Singleton.AirlineRegistDao().findById(airplane.getAirlineID());
		Airport sAirport = Singleton.AirportDao().findById(airline, flight.getStartAirportID());
		Airport aAirport = Singleton.AirportDao().findById(airline, flight.getArrivAirportID());
		Timestamp startTimestamp = Timestamp.valueOf(flight.getStartTime());
		Timestamp arrivTimestamp = Timestamp.valueOf(flight.getArrivTime());
		String sTime = String.format("%02d", startTimestamp.getHours()) + ":" + String.format("%02d", startTimestamp.getMinutes());
		String aTime = String.format("%02d", arrivTimestamp.getHours()) + ":" + String.format("%02d", arrivTimestamp.getMinutes());
		String discount =String.valueOf(airline.getDiscount()/10.0);
		
		
		String r = "";
		r += "<div class=\"col-md-10 column search_div  search_page\">\n";
		r += "	<form class=\"form-inline\" role=\"form\" id = \"searchForm\" action=\"/AirFast/servlet/BookServlet\" method=\"post\">\n";
		r += "		<label id = \"flight_id\" name = \"flight_id\" value = \""+ flight.getId()+"\" style=\"display:none; \">"+flight.getId()+"</label>";
		r += "		<table class = \"col-xs-12 search_table\">\n";
		r += "			<tbody>\n";
		r += "				<tr class = \"search_table_row\">\n";
		
		r += "					<td class = \"flight_info col-xs-2\">	\n";	
		r += "						<div> \n";
		r += "							<strong class = \"flight_airline\">" + airline.getName() + "</strong>\n";
		r += "							<span class=\"flight_name\"> " + flight.getName() + " </span> \n";
		r += "						</div>\n";
		r += "						<div><span class=\"flight_airplane\">" + airplane.getName() + "</span></div>\n";
		r += "					</td>\n";
		
		r += "					<td class = \"sTime_info col-xs-2\">\n";
		r += "						<div> <strong class = \"sTimeShow\">"+sTime+"</strong> </div>\n";
		r += "						<div>"+sAirport.getName()+"</div>\n";
		r += "					</td>\n";
		
		r += "					<td class = \"center col-xs-1 show_arrow\">\n";
		r += "						<div class = \"arrow\"></div>\n";
		r += "					</td>\n";
		
		r += "					<td class = \"aTime_info col-xs-2\">\n";
		r += "						<div> <strong class = \"sTimeShow\">"+aTime+"</strong> </div>\n";
		r += "						<div>"+aAirport.getName()+"</div>\n";
		r += "					</td>\n";
		
		r += "					<td class = \"discount_info col-xs-1\">\n";
		r += "						<div> <strong class= \"discount_show\">"+discount+"折</strong> </div>\n";
		r += "					</td>\n";
		
		r += "					<td class = \"price_info col-xs-2 price_show\">\n";
		r += "						<span>\n";
		r += "							<span class =\"moneyshow\"> <dfn class = \"money_show\">￥</dfn>"+flight.getCost()+"</span>\n";
		r += "							<i class = \"qi\"> 起 </i> <br>\n";
		r += "							</span>\n";
		r += "					</td>\n";
		
		r += "					<td class = \"book_info col-xs-2\">\n";
		r += "						<button type=\"submit\" class=\"btn btn-primary mySearchButton bookbutton\">订票</button>\n";
		r += "					</td>\n";
		
		r += "				</tr>\n";
		r += "			</tbody>\n";
		r += "		</table>\n";
		r += "	</form>\n";
		r += "</div>\n";
		
		return r;
	}
	
	@Override
	public void showResult(ArrayList<Flight> flights) {
		res = ""; cnt = 0;
		for (Flight flight : flights) {
			res += showFlight(flight); ++cnt;
		}
		System.out.println(res);
	}
	
	public void setCnt(int i) { cnt = i; }
	
	public int getCnt() { return cnt; }
	
}
