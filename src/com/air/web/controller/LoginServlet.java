package com.air.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.air.dao.impl.Singleton;
import com.air.domain.User;
import com.air.service.impl.HomepageServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("login_username_input");
		String password = request.getParameter("login_password_input");
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		boolean res = Singleton.UserDao().check(user);
		
		//显示选择列表
		HomepageServiceImpl homepageServlet = new HomepageServiceImpl();
		homepageServlet.showCities();
		homepageServlet.showAirlines();
		request.setAttribute("homepageServlet", homepageServlet);
		
		HttpSession session = request.getSession();
		String cur_page = "showLogin()";
		session.setAttribute("cur_page", cur_page);
		if (res) {
			session.setAttribute("user", user);
			cur_page = "showLoginComp()";
			session.setAttribute("cur_page", cur_page);
			//request.getRequestDispatcher("/servlet/HomepageUIServlet").forward(request, response);
			request.getRequestDispatcher("/WEB-INF/pages/homepage.jsp").forward(request, response);
		}else {
			//request.getRequestDispatcher("/servlet/HomepageUIServlet").forward(request, response);
			request.getRequestDispatcher("/WEB-INF/pages/homepage.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
