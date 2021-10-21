package com.nagarro.assignment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nagarro.assignment.model.Images;
import com.nagarro.assignment.services.ImageManagerImpl;


public class ImageRetriever extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ImageRetriever() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getSession().getAttribute("user") == null) {
            response.sendRedirect("Index.jsp");
        }
        else {
          ImageManagerImpl imageManagement = new ImageManagerImpl();
            String imageId = request.getParameter("imageId");
            Images image = imageManagement.getImageService(imageId);
            if (image != null) {
                response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
                try {
                    response.getOutputStream().flush();
                    response.getOutputStream().write(image.getPhoto());
                    response.getOutputStream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
