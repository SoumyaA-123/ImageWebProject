package com.nagarro.assignment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nagarro.assignment.model.User;
import com.nagarro.assignment.services.ImageManagerImpl;

public class ImageDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public ImageDelete() {
		super();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("user") == null) {
			response.sendRedirect("Index.jsp");
		} else {
			ImageManagerImpl Manager = new ImageManagerImpl();
			String imageid = request.getParameter("imageid").toString();
			Manager.deleteImageService(imageid);
			System.out.println(imageid + "deleted");
			User userUpdated = Manager.getUserDetailsService(((User) request.getSession().getAttribute("user")).getUsername());
			request.getSession().setAttribute("user", userUpdated);
			response.sendRedirect("imageutility.jsp");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
