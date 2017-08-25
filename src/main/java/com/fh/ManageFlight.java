package com.fh;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ManageFlight {
	private static SessionFactory factory;

	public ManageFlight() {
		try {
			// multiple db connection will require multiple session. Take it as an arg in
			// configure(). By default shown below:
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	/* Method to READ all the flights */
	public List listFlight(String source, String destination) {
		Session session = factory.openSession();
		System.out.println("Inside ListFlight");
		Transaction tx = null;
		List flightList = null;
		try {
			tx = session.beginTransaction();
			flightList = session
					.createQuery(
							"FROM FlightDetail f WHERE f.source = '" + source + "' AND f.destination = '" + destination
									+ "'")
					.list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return flightList;
	}

	// /* Method to CREATE a flight route in the database */
	// public Integer addFlight(String source, String destination, String flight_no)
	// {
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

	// /* Method to UPDATE flight number of a flight */
	// public void updateFlight(Integer flightID, String flight_no) {
	// Session session = factory.openSession();
	// Transaction tx = null;
	// try {
	// tx = session.beginTransaction();
	// Card flight = (Card) session.get(Card.class, flightID);
	// flight.setFlight_no(flight_no);
	// session.update(flight);
	// tx.commit();
	// } catch (HibernateException e) {
	// if (tx != null)
	// tx.rollback();
	// e.printStackTrace();
	// } finally {
	// session.close();
	// }
	// }
	//
	// /* Method to DELETE an flight from the records */
	// public void deleteFlight(Integer flightID) {
	// Session session = factory.openSession();
	// Transaction tx = null;
	// try {
	// tx = session.beginTransaction();
	// Card flight = (Card) session.get(Card.class, flightID);
	// session.delete(flight);
	// tx.commit();
	// } catch (HibernateException e) {
	// if (tx != null)
	// tx.rollback();
	// e.printStackTrace();
	// } finally {
	// session.close();
	// }
	// }
}