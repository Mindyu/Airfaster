package com.air.service.impl;

import java.util.ArrayList;

import com.air.dao.impl.Singleton;
import com.air.domain.Airline;
import com.air.domain.City;
import com.air.service.IHomepageService;

public class HomepageServiceImpl implements IHomepageService {
	
	private String cityOptions;
	private String airlineOptions;
	
	@Override
	public void showCities() {
		// TODO Auto-generated method stub
		ArrayList<City> cities = Singleton.CityRegistDao().findAll();
		cityOptions = "";
		for (City city : cities) {
			cityOptions += String.format("<li role=\"presentation\"> <a role=\"menuitem\" tabindex=\"-1\" href=javascript:void(0)>%s</a> </li>\n", city.getName(), city.getName());
		}
		System.out.println(cityOptions);
	}
	
	@Override
	public void showAirlines() {
		// TODO Auto-generated method stub
		ArrayList<Airline> airlines = Singleton.AirlineRegistDao().findAll();
		airlineOptions = "";
		for (Airline airline : airlines) {
			airlineOptions += String.format("<li role=\"presentation\"> <a role=\"menuitem\" tabindex=\"-1\" href=javascript:void(0)>%s</a> </li>\n", airline.getName());

		}
		System.out.println(airlineOptions);
	}
	
	public String getCityOptions() { return cityOptions; }
	public void setCityOptions(String o) { cityOptions = o; }
	public String getAirlineOptions() { return airlineOptions; }
	public void setAirlineOptions(String a) { airlineOptions = a; }

}
