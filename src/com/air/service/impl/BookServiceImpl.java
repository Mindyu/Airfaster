package com.air.service.impl;

import java.sql.Timestamp;

import com.air.dao.impl.Singleton;
import com.air.domain.Airline;
import com.air.domain.Airplane;
import com.air.domain.Airport;
import com.air.domain.Flight;
import com.air.service.IBookService;

public class BookServiceImpl implements IBookService {
	private Flight flight;
	private Airline airline;
	private Airplane airplane;
	private Airport sAirport;
	private Airport aAirport;
	private String sTime;
	private String aTime;
	int id;
	String discount;
	String fcost;
	
	@SuppressWarnings("deprecation")
	@Override
	public void genBookInfo(Flight flight, int id) {
		// TODO Auto-generated method stub
		Airplane airplane = Singleton.AirplaneDao().findByID(flight.getAirplaneID());
		Airline airline = Singleton.AirlineRegistDao().findById(airplane.getAirlineID());
		Airport sAirport = Singleton.AirportDao().findById(airline, flight.getStartAirportID());
		Airport aAirport = Singleton.AirportDao().findById(airline, flight.getArrivAirportID());
		Timestamp startTimestamp = Timestamp.valueOf(flight.getStartTime());
		Timestamp arrivTimestamp = Timestamp.valueOf(flight.getArrivTime());
		sTime = String.format("%02d", startTimestamp.getHours()) + ":" + String.format("%02d", startTimestamp.getMinutes());
		aTime = String.format("%02d", arrivTimestamp.getHours()) + ":" + String.format("%02d", arrivTimestamp.getMinutes());
		discount =String.valueOf(airline.getDiscount()/10.0);
		double cost = flight.getCost()*airline.getDiscount()/100.0;
		fcost = String.format("%.1f", cost);
		
		this.flight = flight;
		this.airline = airline;
		this.airplane = airplane;
		this.sAirport = sAirport;
		this.aAirport = aAirport;
		this.id = id;
		
	}
	
	public void setFlight(Flight f) { flight = f; }
	public void setAirline(Airline f) { airline = f; }
	public void setAirplane(Airplane f) { airplane = f; }
	public void setsAirport(Airport f) { sAirport = f; }
	public void setaAirport(Airport f) { aAirport = f; }
	public void setsTime(String f) { sTime = f; }
	public void setaTime(String f) { aTime = f; }
	public void setId(int i) { id = i; }
	public void setDiscount(String i) { discount = i; }
	public void setFcost(String f) { fcost = f; }
	
	public Flight getFlight() { return flight; }
	public Airline getAirline() { return airline; }
	public Airplane getAirplane() { return airplane; }
	public Airport getsAirport() { return sAirport; }
	public Airport getaAirport() { return aAirport; }
	public String getsTime() { return sTime; }
	public String getaTime() { return aTime; }
	public int getId() { return id; }
	public String getDiscount() { return discount; }
	public String getFcost() { return fcost; }
}
