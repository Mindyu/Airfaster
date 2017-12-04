package com.air.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.air.dao.impl.Singleton;
import com.air.domain.Order;
import com.air.domain.Passage;
import com.air.domain.User;
import com.air.service.impl.HomepageServiceImpl;
import com.air.service.impl.OrderServiceImpl;
import com.air.util.GetUUID;

@SuppressWarnings("serial")
public class BookInfoServlet extends HttpServlet {

	
	public BookInfoServlet() { super(); }

	public void destroy() { super.destroy(); }

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//获得Order订单信息
		Order order = new Order();
		String flightID = request.getParameter("book_flight_id");
		System.out.println(flightID);
		String cost = request.getParameter("book_tot_cost");
		System.out.println(cost);
		order.setId(GetUUID.getID());
		order.setFlightID(flightID);
		order.setCost(Double.valueOf(cost));
		order.setDroped(false);
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		order.setUsername(user.getUsername());
		System.out.println(order.getUsername());
		
		String name[] = request.getParameterValues("passage_name_input");
		String id[] = request.getParameterValues("passage_id_input");
		String phone[] = request.getParameterValues("passage_phone_input");
		
		int tot = name.length; order.setPassageNum(tot);
		Singleton.OrderDao().add(order);
		
		for(int i = 0; i < tot; ++i) {
			Passage passage = new Passage();
			passage.setOrderID(order.getId());
			passage.setName(name[i]);
			passage.setPid(id[i]);
			passage.setPhone(phone[i]);
			passage.setSeatRow(0);
			passage.setSeatCol(0);
			passage.setSeated(false);
			Singleton.PassageDao().add(passage);
		}
		
		System.out.println("hehe");
		request.getRequestDispatcher("/servlet/OrderServlet").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request, response);
	}

	
	public void init() throws ServletException { }

}
