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
		boolean isValid = false;
		try {
			tx = session.beginTransaction();
			if (session.createQuery("FROM Passenger p WHERE p.username = '" + username + "' AND p.password = '"
					+ password + "'") != null) {
				isValid = true;
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
		return isValid;
	}

	public Integer addReservation(Reservation book) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer bookID = null;
		try {
			System.out.println("Inside add reservation");
			tx = session.beginTransaction();
			int id = 0;
			Reservation reserve = new Reservation(book.getPnr(), book.getUsername(), book.getFlight_no(),
					book.getNo_of_tickets(), book.getPrice());
			// Reservation c = (Reservation) session.get(Reservation.class, book.getId());
			// // TODO: Why are we
			// // getting the card
			// c.setUsername(book.getUsername());
			// c.setPnr(book.getPnr());
			// c.setFlight_no(book.getFlight_no());
			// c.setPrice(book.getPrice());
			// c.setNo_of_tickets(book.getNo_of_tickets());
			// c.setId(book.getId());
			// // c.setCard_no(card.getCard_no());
			// // c.setCcv(card.getCard_no());
			// // c.setAmount(card.getAmount());
			// // id = c.getId();
			bookID = (Integer) session.save(reserve);

			// bookID = (Integer) session.save(book);
			tx.commit();
			System.out.println("After commit");
			System.out.println(bookID);
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return bookID;
	}
}