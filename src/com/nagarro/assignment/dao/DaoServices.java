package com.nagarro.assignment.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nagarro.assignment.model.Images;
import com.nagarro.assignment.model.User;
import com.nagarro.assignment.util.HibernateUtil;

public class DaoServices {
	/*
	 * add image in database
	 */
	public void saveImage(Images image) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.getTransaction().begin();
			session.save(image);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * add user to database
	 */
	
	public void saveUser(User user) {
				Session session = HibernateUtil.getSessionFactory().openSession();
				Transaction transaction = session.beginTransaction();
				session.save(user);
				transaction.commit();
	}
	/*
	 * set new password 
	 */
	public void setPassword(String username,String password) {
		User user = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.getTransaction().begin();
			user = (User) session.get(User.class, username);
			user.setPassword(password);
			session.update(user);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/*
	 * get image from table
	 */
	public Images getImageObject(String imageId) {
		Images image = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.getTransaction().begin();
			// image = (Image) session.get("Image.class", Long.parseLong(imageId));
			// session.getTransaction().commit();
			// session.close();
			// String queryString = "from Image img where img.Image_Id = :imageId";
			String queryString = "from Images img where img.imageId = :imageId";
			Query query = session.createQuery(queryString).setString("imageId", imageId);

			Object queryResult = query.uniqueResult();
			return (Images) queryResult;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Unable to connect to database");
		}
		return image;
	}

	/*
	 * change image in table on imageid 
	 */
	public void updateImage(Images newImage) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			session.beginTransaction();
			session.update(newImage);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * delete image from database
	 */
	public void delete(String imageid) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();

			String query = "delete from Images where imageId= :imageId";
			session.createQuery(query).setString("imageId", imageid).executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * get user from database
	 */
	public User getUser(String username) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		User user = null;
		try {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object
			user = (User) session.createQuery("FROM User U WHERE U.username = :username")
					.setParameter("username", username).uniqueResult();
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();

		}
		return user;

	}
	
	
	
// get Userdetails 
//	public User getUserDetails(String username) {
//		User user = null;
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		try {
//			session.getTransaction().begin();
//			//user = (User) session.get(User.class, username);
//			user = (User) session.get(User.class,username);
//					//.setParameter("username", username).uniqueResult();
//			if(user==null) {
//				System.out.println("null object ");
//			}
//			System.out.println(user.getEmail()+" "+user.getPassword()+" "+user.getFullname());
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		return user;
//	}
}
