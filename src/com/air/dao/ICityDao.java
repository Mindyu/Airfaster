package com.air.dao;

import java.util.ArrayList;

import com.air.domain.Airline;
import com.air.domain.City;

public interface ICityDao {
	public void add(Airline airline, City city);
	public void delete(Airline airline, String name);
    public City findByName(Airline airline, String name);
    public ArrayList<City> findAll(Airline airline);
}
