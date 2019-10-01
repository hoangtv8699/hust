package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import models.Student;

public class StudentDAO {
	private int noOfRecords;

	public StudentDAO() {
	}

	public List<Student> viewAllProducts(int offset, int noOfRecords) {
		List<Student> list = new ArrayList<Student>();
		Student student = null;
		try {
			Context initCtx = new InitialContext();   
            Context envCtx = (Context) initCtx.lookup("java:comp/env");   
            DataSource ds = (DataSource)envCtx.lookup("jdbc/ClassManagement");   
            Connection conn = ds.getConnection();
			Statement statement = conn.createStatement();
			String sql = "select SQL_CALC_FOUND_ROWS * from ClassManagement.student limit " + offset + "," + noOfRecords;
			PreparedStatement preStatement = conn.prepareStatement(sql);
			ResultSet rs = preStatement.executeQuery();
			while (rs.next()) {
				student = new Student();
				student.setStudentID(rs.getString("StudentID"));
				student.setFirstname(rs.getString("FirstName"));
				student.setLastname(rs.getString("LastName"));
				student.setTelephone(rs.getString("Telephone"));
				student.setEmail(rs.getString("Email"));
				student.setAddress(rs.getString("Address"));
				student.setPassword(rs.getString("Password"));
				list.add(student);
			}
			rs.close();
			rs = statement.executeQuery("select FOUND_ROWS()");
			if (rs.next()) {
				this.noOfRecords = rs.getInt(1);
			}

		} catch (SQLException | NamingException e ) {
			System.err.println(e);
		}
		return list;
	}

	public int getNoOfRecords() {
		return noOfRecords;
	}
}
