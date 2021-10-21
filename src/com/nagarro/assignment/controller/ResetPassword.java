package com.nagarro.assignment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nagarro.assignment.model.User;
import com.nagarro.assignment.services.ImageManagerImpl;


public class ResetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ResetPassword() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = "";
        request.getSession().setAttribute("message", "");
       ImageManagerImpl login = new ImageManagerImpl();
        String username = request.getParameter("username");
        String fullName = request.getParameter("fullname");
        String password = request.getParameter("passwordnew");
        String passwordConfirm = request.getParameter("passwordnewconfirm");
        User user = login.getUserDetailsService(username);
        if (user != null) {
            if (password.equals(passwordConfirm)) {
                if (user.getFullname().equalsIgnoreCase(fullName)) {
                    login.updatePasswordService(username, password);
                    request.getSession().setAttribute("passwordmessage", "Password Updated Successfully");
                    message = "Success";
                } else {
                    request.getSession().setAttribute("passwordmessage", "One of the given information is incorrect... (Full Name)");
                    message = "One of the given information is incorrect... (Full Name)";
                }
            } else {
                request.getSession().setAttribute("passwordmessage", "Password does not match");
                message = "Password does not match";
            }
        } else {
            request.getSession().setAttribute("passwordmessage", "No user exists with such username");
            message = "No user exists with such username";
        }
        System.out.println(message);

        response.sendRedirect("Index.jsp");
    }

}


