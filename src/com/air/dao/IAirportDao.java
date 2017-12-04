package com.air.dao;

import com.air.domain.Airline;
import com.air.domain.Airport;

public interface IAirportDao {
	public void add(Airline airline, Airport airport);
	public void delete(Airline airline,Airport airport);
	public Airport findById(Airline airline, String id);
	public Airport findByName(Airline airline, String name);
    //public Airport findByID(String airportID);
    
}
