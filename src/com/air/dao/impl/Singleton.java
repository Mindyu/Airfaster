package com.air.dao.impl;

import java.util.ArrayList;

import com.air.domain.Flight;

/**
 * 所有Dao的单例
 * @author Dua
 */
public class Singleton {
	private Singleton() {}
	
	private static AirlineRegistDaoImpl airlineRegistDao 	= new AirlineRegistDaoImpl();
	private static AirlineDaoImpl 		airlineDao 			= new AirlineDaoImpl();
	private static CityRegistDaoImpl 	cityRegistDao 		= new CityRegistDaoImpl();
	private static CityDaoImpl 			cityDao 			= new CityDaoImpl();
	private static AirplaneDaoImpl 		airplaneDao 		= new AirplaneDaoImpl();
	private static AirseatDaoImpl 		airseatDao 			= new AirseatDaoImpl();
	private static AirportDaoImpl		airportDao			= new AirportDaoImpl();
	private static FlightDaoImpl		flightDao			= new FlightDaoImpl();
	private static UserDaoImpl			userDao				= new UserDaoImpl();
	private static OrderDaoImpl			orderDao			= new OrderDaoImpl();
	private static PassageDaoImpl		passageDao			= new PassageDaoImpl();
	
	public static AirlineRegistDaoImpl 	AirlineRegistDao() 	{ return airlineRegistDao; }
	public static AirlineDaoImpl 		AirlineDao() 		{ return airlineDao; }
	public static CityRegistDaoImpl 	CityRegistDao() 	{ return cityRegistDao; }
	public static CityDaoImpl 			CityDao() 			{ return cityDao; }
	public static AirplaneDaoImpl 		AirplaneDao() 		{ return airplaneDao; }
	public static AirseatDaoImpl 		AirseatDao() 		{ return airseatDao; }
	public static AirportDaoImpl		AirportDao() 		{ return airportDao; }
	public static FlightDaoImpl			FlightDao() 		{ return flightDao; }
	public static UserDaoImpl			UserDao()			{ return userDao; }
	public static OrderDaoImpl			OrderDao()			{ return orderDao; }
	public static PassageDaoImpl		PassageDao()		{ return passageDao; }
	private static ArrayList<Flight>    flights 			= new ArrayList<Flight>();
	public static ArrayList<Flight>     Flight()			{ return flights; }
	public static void     setFlight(ArrayList<Flight> flight)			{ flights = flight; }
}
