package com.nagarro.assignment.services;

import com.nagarro.assignment.dao.DaoServices;
import com.nagarro.assignment.model.Images;
import com.nagarro.assignment.model.User;


public class ImageManagerImpl implements ImageManager {
	DaoServices dao = new DaoServices();
	/*
	 * add imageservice
	 */
	@Override
	public void addImageService(Images image) {
		dao.saveImage(image);
	}

	
	 @Override
	    public Images getImageService(String imageId) {
	        Images image = dao.getImageObject(imageId);
	        return image;
	    }
	
	 @Override
	public void editImageService(Images newImage) {
		dao.updateImage(newImage);
	}

	
	 @Override
	public void deleteImageService(String imageid) {
		dao.delete(imageid);
	}
	 @Override
	public Boolean userAuthenticationService(String username, String password) {
		 User user=dao.getUser(username);
			if (user != null && user.getPassword().equals(password)) {
				return true;
			}
		return false;
	}

	
	 
	@Override
	public User getUserDetailsService(String username) {
		User user = dao.getUser(username);
		return user;
	}

	
	//to update password 
	@Override
	public void updatePasswordService(String username, String password) {
		dao.setPassword(username, password);
	}

	
	// to save user
	@Override
	public void addUserService(User user) {
		dao.saveUser(user);
		System.out.println("Your are register");

	}

}
