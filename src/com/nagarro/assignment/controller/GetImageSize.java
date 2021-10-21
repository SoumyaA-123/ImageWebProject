package com.nagarro.assignment.controller;

import java.util.Collection;

import com.nagarro.assignment.model.Images;
import com.nagarro.assignment.model.User;
import com.nagarro.assignment.services.ImageManagerImpl;

public class GetImageSize {
	public static double getImagesSize(String username) {
        double totalSize = 0;
        ImageManagerImpl login = new ImageManagerImpl();
        User user = login.getUserDetailsService(username);
        Collection<Images> images = user.getImageList();
        for (Images image : images) {
            totalSize += image.getImageSize();
        }
        return totalSize;
    }

}
