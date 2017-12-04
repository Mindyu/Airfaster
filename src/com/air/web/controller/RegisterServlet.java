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
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String username = request.getParameter("regist_username_input");
		String password = request.getParameter("regist_passname_input");
		String usershow = request.getParameter("regist_showname_input");
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setUsershow(usershow);
		Singleton.UserDao().add(user);
		System.out.println("注册成功！");
		
		//打开主页前的初始化操作
		HomepageServiceImpl homepageServlet = new HomepageServiceImpl();
		homepageServlet.showCities();
		homepageServlet.showAirlines();
		request.setAttribute("homepageServlet", homepageServlet);
		
		HttpSession session = request.getSession();
		String cur_page = "showLogin()";
		session.setAttribute ("cur_page", cur_page);
		
		request.getRequestDispatcher("/WEB-INF/pages/homepage.jsp").forward(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
