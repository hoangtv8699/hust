package JNDI;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.annotation.Resource;

@WebServlet("/listProducts")
public class ResourceInjectionJNDI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name = "jdbc/ClassManagement")
	private DataSource dataSource;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		try {
			Connection conn = dataSource.getConnection();
			Statement statement = conn.createStatement();
			String sql = "select * from student";
			PreparedStatement preStatement = conn.prepareStatement(sql);
			ResultSet result = preStatement.executeQuery();
			
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			
			out.print("<html><body><h2>List of students</h2>");
			out.print("<table border=\"1\" cellspacing=10 cellpadding=5>");
            out.print("<th>StudentId</th>");
            out.print("<th>FirstName</th>");
            out.print("<th>LastName</th>");
            out.print("<th>Address</th>");
            out.print("<th>Telephone</th>");
            out.print("<th>Email</th>");
            out.print("<th>Password</th>");
            
            while(result.next())
            {
                out.print("<tr>");
                out.print("<td>" + result.getLong("StudentID") + "</td>");
                out.print("<td>" + result.getString("FirstName") + "</td>");
                out.print("<td>" + result.getString("LastName") + "</td>");
                out.print("<td>" + result.getString("Address") + "</td>");
                out.print("<td>" + result.getString("Telephone") + "</td>");
                out.print("<td>" + result.getString("Email") + "</td>");
                out.print("<td>" + result.getString("Password") + "</td>");
                out.print("</tr>");
            }
            out.print("</table></body><br/>");
		} catch (SQLException ex) {
			System.err.println(ex);
		}
	}
}