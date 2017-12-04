package com.air.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.air.dao.impl.Singleton;
import com.air.domain.Flight;
import com.air.domain.User;
import com.air.service.impl.BookServiceImpl;
import com.air.service.impl.HomepageServiceImpl;

@SuppressWarnings("serial")
public class BookServlet extends HttpServlet {

	public BookServlet() {
		super();
	}

	public void destroy() { super.destroy();  }

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			String flightID = (String) request.getParameter("flight_id");
			System.out.println(flightID);
			Flight flight = Singleton.FlightDao().findById(flightID);
			BookServiceImpl bookServiceImpl = new BookServiceImpl();
			bookServiceImpl.genBookInfo(flight, 1);
			request.setAttribute("book_flight", bookServiceImpl);
			
			//显示选择列表
			HomepageServiceImpl homepageServlet = new HomepageServiceImpl();
			homepageServlet.showCities();
			homepageServlet.showAirlines();
			request.setAttribute("homepageServlet", homepageServlet);
			
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			if(user == null || user.getUsername().equals("")) {
				String cur_page = "showLogin()";
				session.setAttribute("cur_page", cur_page);
			}
			else {
				String cur_page = "showBook()";
				session.setAttribute("cur_page", cur_page);
			}
			
			request.getRequestDispatcher("/WEB-INF/pages/homepage.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request, response);
		
	}

	
	public void init() throws ServletException { }

}
