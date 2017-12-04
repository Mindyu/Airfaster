package com.air.dao;

import java.util.ArrayList;

import com.air.domain.Airline;
import com.air.domain.Airseat;
import com.air.domain.Airplane;
import com.air.domain.Flight;

public interface IAirseatDao {
	public void add(Airline airline, Airplane airplane, Airseat airseat);
    public Airseat findByPos(Airline airline, Airplane airplane, int row, int col);
    public void deleteAll(Airline airline, Airplane airplane);
    public ArrayList<ArrayList<Airseat> > findAll(Airline airline, Flight flight);
    public Airseat findByPos(Airline airline, Flight flight, int row, int col);
    public void updateByPos(Airline airline, Flight flight, int row, int col, boolean state);
}
