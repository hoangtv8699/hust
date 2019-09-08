package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Hello
 */
@WebServlet("/Hello")
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = response.getWriter();) {
			out.println("<!DOCTYPE html>");
			 out.println("<html>");
			 out.println("<head>");
			 out.println("<title>Servlet HelloServlet</title>");
			 out.println("</head>");
			 out.println("<body>");

			 String username = (String) request.getParameter("username");
			 String useremail = (String) request.getParameter("user-email");
			 out.println("<h1>Hello!</h1>");
			 out.println("<h3>your username: " + username + "</h3>");
			 out.println("<h3>your user-email: " + useremail + "</h3>");

			 out.println("</body>");
			 out.println("</html>");
		}
	}

}
