package com.fh;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ManageReservation {
	private static SessionFactory factory;

	public ManageReservation() {
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
			} else
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

	// public Integer addReservation(String pnr, String source, String destination,
	// String flight_no) {
	// Session session = factory.openSession();
	// Transaction tx = null;
	// Integer flightID = null;
	// try {
	// tx = session.beginTransaction();
	// Card flight = new Card(source, destination, flight_no);
	// flightID = (Integer) session.save(flight);
	// tx.commit();
	// } catch (HibernateException e) {
	// if (tx != null)
	// tx.rollback();
	// e.printStackTrace();
	// } finally {
	// session.close();
	// }
	// return flightID;
	// }
}