package com.air.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Set;

import com.air.dao.impl.AirlineDaoImpl;
import com.air.dao.impl.AirlineRegistDaoImpl;
import com.air.dao.impl.AirplaneDaoImpl;
import com.air.dao.impl.AirseatDaoImpl;
import com.air.dao.impl.Singleton;
import com.air.domain.Airline;
import com.air.domain.Airplane;
import com.air.domain.Airport;
import com.air.domain.Airseat;
import com.air.domain.City;
import com.air.domain.Flight;
import com.air.domain.User;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Airline airline = new Airline();
		City city = new City();
		
		//某个航空公司配置内部信息
/*		airline.setUrl("jdbc:mysql://localhost:3306/changchengairline?useUnicode=true&characterEncoding=utf8");
		airline.setUsername("root"); airline.setPassword("dua811");
		airline.setId(GetUUID.getID()); airline.setName("长城航空"); airline.setDiscount(80);
		Singleton.AirlineDao().setInfo(airline);
*/		
		//某个航空公司添加城市信息	
/*		airline.setUrl("jdbc:mysql://localhost:3306/changchengairline?useUnicode=true&characterEncoding=utf8");
		airline.setUsername("root"); airline.setPassword("dua811");
		airline = Singleton.AirlineDao().getInfo(airline);
		
		city.setName("武汉"); city.setCountry("中国");
		Singleton.CityDao().add(airline, city);
		city.setName("北京"); city.setCountry("中国");
		Singleton.CityDao().add(airline, city);
*/	
		/*//某个航空公司注册到本地
		airline.setUrl("jdbc:mysql://118.89.109.23:3306/changchengairline?useUnicode=true&characterEncoding=utf8");
		airline.setUsername("root"); airline.setPassword("123456");
		airline = Singleton.AirlineDao().getInfo(airline);
		
		Singleton.AirlineRegistDao().add(airline);
		*/
		//某个航空公司增加机场信息
/*		airline.setUrl("jdbc:mysql://localhost:3306/changchengairline?useUnicode=true&characterEncoding=utf8");
		airline.setUsername("root"); airline.setPassword("dua811");
		airline = Singleton.AirlineDao().getInfo(airline);
		
		Airport airport = new Airport();
		airport.setId(GetUUID.getID()); airport.setName("天河机场");
		airport.setCity("武汉"); airport.setConnectTime(60);
		Singleton.AirportDao().add(airline, airport);
*/
		
		//某个航空公司增加飞机信息
/*		airline.setUrl("jdbc:mysql://localhost:3306/changchengairline?useUnicode=true&characterEncoding=utf8");
		airline.setUsername("root"); airline.setPassword("dua811");
		airline = Singleton.AirlineDao().getInfo(airline);
		
		Airplane airplane = new Airplane();
		airplane.setId(GetUUID.getID());
		airplane.setName("长城666");
		airplane.setAirlineID(airline.getId());
		airplane.setRow(6);
		airplane.setLeftSeat(6*6);
		Singleton.AirplaneDao().add(airline, airplane);
*/	
		
		//某个航空公司增加航班信息
		/*airline.setUrl("jdbc:mysql://localhost:3306/changchengairline?useUnicode=true&characterEncoding=utf8");
		airline.setUsername("root"); airline.setPassword("123456");
		airline = Singleton.AirlineDao().getInfo(airline);
		
		Flight flight = new Flight();
		flight.setId(GetUUID.getID()); flight.setName("HH1521");
		flight.setAirplaneID(Singleton.AirplaneDao().findByName("波音767").getId());
		flight.setStartAirportID(Singleton.AirportDao().findByName(airline, "天河机场").getId());
		flight.setArrivAirportID(Singleton.AirportDao().findByName(airline, "首都机场").getId());
		flight.setStartCity(Singleton.AirportDao().findByName(airline, "首都机场").getCity());
		flight.setArrivCity(Singleton.AirportDao().findByName(airline, "天河机场").getCity());
		flight.setStartTime("2017-11-16 09:00:00");
		flight.setArrivTime("2017-11-16 12:30:00");
		flight.setCost(300);
		Singleton.FlightDao().add(airline, flight);*/

		//某个航空公司删除航班信息
/*		airline.setUrl("jdbc:mysql://localhost:3306/hainanairline?useUnicode=true&characterEncoding=utf8");
		airline.setUsername("root"); airline.setPassword("dua811");
		airline = Singleton.AirlineDao().getInfo(airline);
		Flight flight = new Flight(); flight.setId("58d87e47-31f8-49ad-8fe4-df4afa877fd3");
		Singleton.FlightDao().delete(airline, flight);
*/		
		/*//查询某个航空公司的航班信息
		airline.setUrl("jdbc:mysql://localhost:3306/changchengairline?useUnicode=true&characterEncoding=utf8");
		airline.setUsername("root"); airline.setPassword("123456");
		airline = Singleton.AirlineDao().getInfo(airline);
		
		Flight flight = new Flight();
		flight.setStartCity("南京");
		flight.setArrivCity("北京");
		flight.setStartTime("2017-11-15 00:00:00");
		flight.setArrivTime("2017-11-17 00:00:00");
		
		ArrayList<Flight> flights = Singleton.FlightDao().findAll(flight);
		for (Flight flight2 : flights) {
			System.out.println(flight2.getName());
		}*/
		
		
		//用户注册
/*		User user = new User();
		user.setUsername("1510300603");
		user.setPassword("1234567");
		user.setUsershow("ffqe");
		Singleton.UserDao().add(user);
*/
		//删除用户
/*		User user = new User();
		user.setUsername("1510300603");
		Singleton.UserDao().delete(user);
*/		
		//查询用户
/*		User user = new User();
		user.setUsername("1510300602");
		user.setPassword("123456");
		boolean res = Singleton.UserDao().check(user);
		System.out.println(res);
*/
	}

}
