package com.nagarro.assignment.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.nagarro.assignment.model.Images;
import com.nagarro.assignment.model.User;
import com.nagarro.assignment.services.ImageManagerImpl;

public class ImageUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ImageUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  if (request.getSession().getAttribute("user") == null) {
	            response.sendRedirect("Index.jsp");
	        } else {
	            String imageName = null;
	            byte bytes[] = null;
	            double imageSize = 0;
	            //response.setContentType("text/html;charset=UTF-8");
	            if (ServletFileUpload.isMultipartContent(request)) {
	                try {
	                    List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

	                    for (FileItem item : multiparts) {
	                        if (item.isFormField()) {
	                            imageName = item.getString();
	                        } else {
	                            imageSize = item.getSize() / 1024;
	                            bytes = item.get();
	                        }
	                    }
	                    System.out.println("file uploaded successfully");
	                    //File uploaded successfully
	                    request.getSession().setAttribute("message", "File Uploaded Successfully");
	                    User user = (User) request.getSession().getAttribute("user");
	                    Images image = new Images(imageName, imageSize, bytes);
	                    image.setUser(user);

	                    if (user != null) {
	                        if (image.getImageSize() < 1024) {
	                           
	                            if (GetImageSize.getImagesSize(user.getUsername()) + image.getImageSize() < 10240) {
	                                ImageManagerImpl object = new ImageManagerImpl();
	                                object.addImageService(image);
	                                try {
	                   

	                                    User userUpdated = object.getUserDetailsService(((User) request.getSession().getAttribute("user")).getUsername());
	                                    request.getSession().setAttribute("user", userUpdated);
	                                    response.sendRedirect("imageutility.jsp");
	                                } catch (Exception e) {
	                                    e.printStackTrace();
	                                }
	                            } else {
	                                System.out.println("Images size exceeded > 10 MB");
	                                response.sendRedirect("imageutility.jsp");
	                            }
	                        } else {
	                            System.out.println("Image size exceeded");
	                            response.sendRedirect("imageutility.jsp");
	                        }
	                    }
	                } catch (Exception ex) {
	                    request.setAttribute("message", "File Upload Failed due to " + ex);
	                }

	            } else {
	                request.setAttribute("message", "Sorry image could not be uploaded");
	            }
	        }
	        //response.sendRedirect("imageutility.jsp");
	    }

}
