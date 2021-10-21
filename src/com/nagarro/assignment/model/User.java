package com.nagarro.assignment.model;

import java.util.List;

import javax.persistence.CascadeType;
//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
//import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.Query;
import org.hibernate.Session;

import com.nagarro.assignment.util.HibernateUtil;

//import org.hibernate.Query;
//import org.hibernate.Session;

//import com.nagarro.assignment.util.HibernateUtil;

@Entity
@Table(name = "user")
public class User {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userid", updatable = false, nullable = false)
	private int id;
	@Column(name = "fullname")
	private String fullname;
	@Id
	@Column(name = "username")
	private String username;
	@Column(name = "useremail")
	private String email;
	@Column(name = "password")
	private String password;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Images> imageList;

	public List<Images> getImageList() {
		return imageList;
	}

	public User() {
	}

	public User(String username, String fullName, String password, List<Images> images) {
		this.username = username;
		this.fullname = fullName;
		this.password = password;
		//this.imageList = images;
	}

	public User(String username,String email, String fullName, String password) {
		this.username = username;
		this.fullname = fullName;
		this.password = password;
		this.email = email;
	}
	public User(String username, String fullName, String password) {
		this.username = username;
		this.fullname = fullName;
		this.password = password;
	}
	public void setImageList(List<Images> imageList) {
		this.imageList = imageList;
	}

	public static List<Images> getImageList(String name) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from User where userName = :param");
		query.setParameter("param", name);
		User u = (User) query.uniqueResult();
		List<Images> li;
		try {
			li = u.getImageList();
		} catch (Exception e) {
			li = null;
	}
		session.getTransaction().commit();
		session.close();
		return li;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

}
