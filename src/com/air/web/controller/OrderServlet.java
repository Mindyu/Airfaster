package com.air.web.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.air.dao.impl.Singleton;
import com.air.domain.Order;
import com.air.domain.User;
import com.air.service.impl.HomepageServiceImpl;
import com.air.service.impl.OrderServiceImpl;

@SuppressWarnings("serial")
public class OrderServlet extends HttpServlet {

	public OrderServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		//显示选择列表
			HomepageServiceImpl homepageServlet = new HomepageServiceImpl();
			homepageServlet.showCities();
			homepageServlet.showAirlines();
			request.setAttribute("homepageServlet", homepageServlet);
			
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			String cur_page = "showBookView()";
			session.setAttribute("cur_page", cur_page);
			
			
			//显示此用户的订单
			ArrayList<OrderServiceImpl> orderServiceImpls = new ArrayList<OrderServiceImpl>();
			ArrayList<Order> orders = Singleton.OrderDao().findByUser(user.getUsername());
			for (Order order2 : orders) {
				OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
				orderServiceImpl.genOrderInfo(order2);
				orderServiceImpls.add(orderServiceImpl);
			}
			
			request.setAttribute("orders", orderServiceImpls);
			request.getRequestDispatcher("/WEB-INF/pages/homepage.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request, response);
		
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
