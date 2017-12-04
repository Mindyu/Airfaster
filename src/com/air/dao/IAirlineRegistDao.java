package com.air.dao;

import java.util.ArrayList;

import com.air.domain.Airline;
/**
 * 注册航空公司 到 本地数据库
 * @author Dua
 */

public interface IAirlineRegistDao {
	public void add(Airline airline);
	public void deleteById(String airlineID);
	public void deleteByName(String name);
    public Airline findById(String airlineID);
    public Airline findByName(String name);
    public ArrayList<Airline> findAll();
    
}
