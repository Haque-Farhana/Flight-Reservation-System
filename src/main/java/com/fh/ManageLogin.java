package com.fh;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ManageLogin {
	private static SessionFactory factory;
	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public ManageLogin() {
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public boolean checkLogin(String username, String password) {
		Session session = factory.openSession();
		System.out.println("Inside ListFlight");
		Transaction tx = null;
		// String pnr = null;
		boolean isValid = false;
		try {
			tx = session.beginTransaction();
			if (session.createQuery("FROM Passenger p WHERE p.username = '" + username + "' AND p.password = '"
					+ password + "'") != null) {
				isValid = true;
				// pnr = randomPNRGenerator(6);
			}
			else
				System.out.println("Result not found!!");
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		// return pnr;
		return isValid;
	}

	public String randomPNRGenerator(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}
}