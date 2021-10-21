package com.nagarro.assignment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nagarro.assignment.model.User;
import com.nagarro.assignment.services.ImageManagerImpl;

public class Register extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ImageManagerImpl impl = new ImageManagerImpl();
		User object = new User(
				request.getParameter("username"),
				request.getParameter("email"),
				request.getParameter("fullname"),
				request.getParameter("password")
				
				);
		impl.addUserService(object);
		
		
		
	}

	

}
	
