package edu.ben.cmsc398.iLearn.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoutServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Get The Current Session Or NULL If The Session Does Not Exist
		HttpSession currentSession = request.getSession(false);

		// If A Current Session Exists, Invalidate The Session
		if (currentSession != null) {
			currentSession.invalidate();
		}

		String successMessage = "Log Out Successful!";

		// Redirect The User To The Login JSP Page
		response.sendRedirect("login.jsp?successMessage=" + successMessage);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Redirect The User To The Login JSP Page
		response.sendRedirect("login.jsp");
	}
}