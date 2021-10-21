package com.nagarro.assignment.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.nagarro.assignment.model.Images;
import com.nagarro.assignment.model.User;
import com.nagarro.assignment.services.ImageManagerImpl;


public class ImagesEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ImagesEdit() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			User user = (User) request.getSession().getAttribute("user");
	        if (request.getSession().getAttribute("user") == null) {
	            response.sendRedirect("index.jsp");
	        } else {
	            ImageManagerImpl manager = new ImageManagerImpl();
	            String imageId = request.getParameter("imageId");
	            Images img = manager.getImageService(imageId);
	            float currentImageSize = (float) img.getImageSize();
	            double imageSize = 0;
	            byte[] bytes = null;
	            String imageName = null;

	            response.setContentType("text/html;charset=UTF-8");
	            try {
	                if (!ServletFileUpload.isMultipartContent(request)) {
	                    System.out.println("sorry. No file uploaded");
	                    return;
	                }

	                // Apache Commons-Fileupload library classes
	                DiskFileItemFactory factory = new DiskFileItemFactory();
	                ServletFileUpload servletFileUpload = new ServletFileUpload(factory);

	                // parse request
	                List<FileItem> items = servletFileUpload.parseRequest(request);
	                System.out.println(items.size());

	                for (FileItem file : items) {
	                    if (file.isFormField()) {
	                        imageName = file.getString();
	                        if (!imageName.isEmpty()) {
	                            img.setImageName(imageName);
	                        }
	                    } else {
	                        imageSize = file.getSize() / 1024;
	                        if (imageSize != 0) {
	                            bytes = file.get();
	                            img.setImageSize(imageSize);
	                            img.setPhoto(bytes);
	                        }
	                    }
	                }
	            } catch (Exception e) {
	                System.err.println("Error occurred ");
	            }

	            if (img.getImageSize() > 1024 ||
	                    (GetImageSize.getImagesSize(user.getUsername()) + imageSize - currentImageSize) > 10240) {
	                System.out.println("Image size exceeded");
	            } else {
	               manager.editImageService(img);
	            }
	            User userUpdated = manager.getUserDetailsService(((User) request.getSession().getAttribute("user")).getUsername());
	            request.getSession().setAttribute("user", userUpdated);
	            response.sendRedirect("imageutility.jsp");
	        }
	    }
}
