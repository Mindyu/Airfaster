package com.air.dao;

import java.util.ArrayList;

import com.air.domain.Airplane;
import com.air.domain.Airline;

public interface IAirplaneDao {
	public void add(Airline airline, Airplane airplane);
	public void delete(Airline airline, Airplane airplane);
	public Airplane findByID(String airplaneID);
    public Airplane findByName(String name);
    public void update(Airline airline, Airplane airplane);
    public ArrayList<Airplane> findAll(Airline airline);
    public void deleteAll(Airline airline);
    
}
