package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UserDao;
import models.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDao ud = new UserDao();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("link.html").include(request, response);
		String name = request.getParameter("user");
		String password = request.getParameter("pwd");
		String rePassword = request.getParameter("repwd");
		if(!password.equals(rePassword)){
			RequestDispatcher again = request.getRequestDispatcher("Register.jsp");
			again.forward(request, response);
		}
		User u = new User();
		u.setUsername(name);
		u.setPassword(password);
		if (ud.findUser(u.getUsername()) == null) {
			out.print("<font color=blue>You are successfully register!</font>");
			out.print("<br>Welcome, " + name);
			ud.insertUser(u);
			Cookie ck = new Cookie("name", name);
			response.addCookie(ck);
		}else {
			out.print("<font color=red>sorry, can't register because password incorect or username have used!");
			request.getRequestDispatcher("Register.jsp").include(request, response);
		}
		out.close();
	}

}
