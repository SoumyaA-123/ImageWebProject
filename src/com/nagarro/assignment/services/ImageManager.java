package com.nagarro.assignment.services;

import com.nagarro.assignment.model.Images;
import com.nagarro.assignment.model.User;

public interface ImageManager {
	public void addImageService(Images image);

    public Images getImageService(String imageid);

    public void editImageService(Images newImage);
    
    public void deleteImageService(String imageid);
    
    public User getUserDetailsService(String username);
    
	public Boolean userAuthenticationService(String username, String password);
	
	public void addUserService(User user);
	
	public void updatePasswordService(String username, String password);
}
