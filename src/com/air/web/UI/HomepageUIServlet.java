package com.air.web.UI;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.air.domain.User;
import com.air.service.impl.HomepageServiceImpl;

public class HomepageUIServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public HomepageUIServlet() { super(); }

	public void destroy() { super.destroy(); }

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			//打开主页前的初始化操作
			HomepageServiceImpl homepageServlet = new HomepageServiceImpl();
			homepageServlet.showCities();
			homepageServlet.showAirlines();
			request.setAttribute("homepageServlet", homepageServlet);
			
			HttpSession session = request.getSession();
			String cur_page = "showSearch()";
			session.setAttribute("cur_page", cur_page);
			User user = new User(); user.setUsername("");
			session.setAttribute("user", user);
			request.getRequestDispatcher("/WEB-INF/pages/homepage.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request, response);
	}

	public void init() throws ServletException { }

}
