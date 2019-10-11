package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import models.User;

public class UserDao {
	public UserDao() {
	}

	public User findUser(User u) {
		String query = "select * from Reader.Reader where  username = ? and password = ?";
		User user = null;
		try {
			Context initCtx = new InitialContext();   
            Context envCtx = (Context) initCtx.lookup("java:comp/env");   
            DataSource ds = (DataSource)envCtx.lookup("jdbc/Reader");   
            Connection conn = ds.getConnection();
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, u.getUsername());
			statement.setString(2, u.getPassword());
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
			}
			rs.close();

		} catch (SQLException | NamingException  e) {
			System.err.println(e);
		}
		return user;
	}
	
	public User findUser(String name) {
		String query = "select * from Reader where username=?;";
		User user = null;
		try {
			Context initCtx = new InitialContext();   
            Context envCtx = (Context) initCtx.lookup("java:comp/env");   
            DataSource ds = (DataSource)envCtx.lookup("jdbc/Reader");   
            Connection conn = ds.getConnection();
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, name);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
			}
			rs.close();

		} catch (SQLException | NamingException  e) {
			System.err.println(e);
		}
		return user;
	}
	public void insertUser(User u) {
		String query = "insert into Reader.Reader value  (?,?);";
		try {
			Context initCtx = new InitialContext();   
            Context envCtx = (Context) initCtx.lookup("java:comp/env");   
            DataSource ds = (DataSource)envCtx.lookup("jdbc/Reader");   
            Connection conn = ds.getConnection();
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, u.getUsername());
			statement.setString(2, u.getPassword());
			statement.executeUpdate();
		} catch (SQLException | NamingException  e) {
			System.err.println(e);
		}
	}
}
