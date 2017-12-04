package com.air.dao;

import java.util.ArrayList;

import com.air.domain.City;

public interface ICityRegistDao {
	public void add(City city);
	public void delete(String name);
    public City findByName(String name);
    public ArrayList<City> findAll();
}
