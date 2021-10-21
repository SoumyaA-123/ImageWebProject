package com.nagarro.assignment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nagarro.assignment.model.User;
import com.nagarro.assignment.services.ImageManagerImpl;


public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public Login() {
		super();
		
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ImageManagerImpl login = new ImageManagerImpl();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		if (login.userAuthenticationService(username, password)) {
			User user = login.getUserDetailsService(username);
			if(user==null) {System.out.println("null user");}
			session.setAttribute("authorized", "true");
			session.setAttribute("user", user);
			response.sendRedirect("imageutility.jsp");
		} else {
			session.setAttribute("authorized", "false");
			response.sendRedirect("Index.jsp");
		}
	}

}
