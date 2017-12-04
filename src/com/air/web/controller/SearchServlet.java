package com.air.web.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.air.dao.impl.Singleton;
import com.air.domain.Airline;
import com.air.domain.Flight;
import com.air.service.impl.BookServiceImpl;
import com.air.service.impl.HomepageServiceImpl;
import com.air.service.impl.SearchServiceImpl;
import com.air.util.RequestToBean;
import com.air.web.formbean.SearchBean;

public class SearchServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public SearchServlet() { super(); }

	public void destroy() { super.destroy(); }

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			SearchServiceImpl serviceImpl = new SearchServiceImpl();
			Flight flight = new Flight();
			ArrayList<Flight> flights = new ArrayList<Flight>();
			
			SearchBean formbean = RequestToBean.request2Bean(request, SearchBean.class);
			System.out.println(formbean.getStartCity());
			System.out.println(formbean.getArrivCity());
			System.out.println(formbean.getStartTime());
			System.out.println(formbean.getArrivTime());
			System.out.println(formbean.getAirlineName());
			String airlineName = formbean.getAirlineName();
			
			//发送航班信息
			flight = serviceImpl.BeanToFlight(formbean);	
			if(airlineName.equals("")) {
				flights = Singleton.FlightDao().findAll(flight);
			}
			else {
				Airline airline = Singleton.AirlineRegistDao().findByName(airlineName);
				flights = Singleton.FlightDao().findAirline(airline, flight);
			}
			ArrayList<BookServiceImpl> bookInfo = new ArrayList<BookServiceImpl>();
			System.out.println(flights.size());
			int i = 0;
			for (Flight flight2 : flights) {
				System.out.println(flight2.getName());
				BookServiceImpl bookServiceImpl = new BookServiceImpl();
				bookServiceImpl.genBookInfo(flight2, i); ++i;
				bookInfo.add(bookServiceImpl);
				
			}
			request.setAttribute("formbean", formbean);
			request.setAttribute("bookInfoList", bookInfo);
			
			//显示选择列表
			HomepageServiceImpl homepageServlet = new HomepageServiceImpl();
			homepageServlet.showCities();
			homepageServlet.showAirlines();
			request.setAttribute("homepageServlet", homepageServlet);
			
			
			//serviceImpl.showResult(flights);
			//request.setAttribute("searchRes", serviceImpl);
			
			HttpSession session = request.getSession();
			String cur_page = "showSearch()";
			session.setAttribute("cur_page", cur_page);
			
			request.getRequestDispatcher("/WEB-INF/pages/homepage.jsp").forward(request, response);
			//request.getRequestDispatcher("/servlet/HomepageUIServlet").forward(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request, response);
	}


	public void init() throws ServletException { }

}
