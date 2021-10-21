package com.nagarro.assignment.model;

import javax.persistence.Basic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="userImage")
public class Images {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Image_Id")
    private Long imageId;

    @Column(name = "Image_Name")
    private String imageName;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "Image", columnDefinition = "mediumblob")
    private byte[] photo;

    @Column(name = "Image_Size")
    private double imageSize;

    @ManyToOne
    @JoinColumn(name = "user_username", nullable = false)
    private User user;

    public Images() {
    }

    public Images(Long imageId, String imageName, byte[] photo, double imageSize, User user) {
        this.imageId = imageId;
        this.imageName = imageName;
        this.photo = photo;
        this.imageSize = imageSize;
        this.user = user;
    }

    public Images(String imageName, Double imageSize, byte[] photo) {
        this.imageName = imageName;
        this.imageSize = imageSize;
        this.photo = photo;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public double getImageSize() {
        return imageSize;
    }

    public void setImageSize(double imageSize) {
        this.imageSize = imageSize;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
