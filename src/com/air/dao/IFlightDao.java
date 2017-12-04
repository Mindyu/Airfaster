package com.air.dao;

import java.util.ArrayList;

import com.air.domain.Airline;
import com.air.domain.Flight;

public interface IFlightDao {
	public void add(Airline airline, Flight flight);
	public void delete(Airline airline, Flight flight);
    public ArrayList<Flight> findAirline(Airline airline, Flight flight);
    public ArrayList<Flight> findAll(Flight flight);
    public Flight findById(String flightID);
    public ArrayList<Flight> findAirline(Airline airline);
    public ArrayList<Flight> findAll();
	public void update(Airline airline, Flight flight);
}
