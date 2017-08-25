package com.fh;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class LoginIdGenerator implements IdentifierGenerator {
	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
		String generatedId = "";
		Connection connection = session.connection();

		try {
			Statement statement = connection.createStatement();

			ResultSet rs = statement.executeQuery("select username from springweb.login");

			if (rs.next()) {
				generatedId = rs.getString(1);
				return generatedId;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
