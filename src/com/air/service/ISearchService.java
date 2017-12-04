package com.air.service;

import java.util.ArrayList;

import com.air.domain.Flight;
import com.air.web.formbean.SearchBean;

public interface ISearchService {
	public Flight BeanToFlight(SearchBean s);
	public String showFlight(Flight flight);
	public void showResult(ArrayList<Flight> flights);
}
