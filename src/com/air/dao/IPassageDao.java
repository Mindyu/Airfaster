package com.air.dao;

import java.util.ArrayList;

import com.air.domain.Passage;


public interface IPassageDao {
	public void add(Passage passage);
	public void delete(Passage passage);
	public ArrayList<Passage> findByOrderID(String orderID);
}
