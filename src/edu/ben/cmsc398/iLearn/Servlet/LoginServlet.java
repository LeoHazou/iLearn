package edu.ben.cmsc398.iLearn.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ben.cmsc398.iLearn.Dao.*;
import edu.ben.cmsc398.iLearn.Model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Redirect The User To The Login JSP Page
		response.sendRedirect("login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Instantiate A New User DAO Object
		UserDAO userDAO = new UserDAO();

		// Instantiate Message String
		String message = null;

		// If "RememberMe" Parameter Is Not Null If The Remember Me Check Box Is
		// Set To True Add A Cookie Holding The Users Email Else: Add A Cookie
		// With A Null Value And A Instant Expiration Date
		if (request.getParameter("rememberMe") != null
				&& request.getParameter("rememberMe").equals("True")) {
			Cookie cookie = new Cookie("iLearnUserID",
					request.getParameter("email"));

			// Set Cookie Age To One Week
			cookie.setMaxAge(604800);
			response.addCookie(cookie);

		} else {
			Cookie cookie = new Cookie("iLearnUserID", null);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}

		// If The User Is Verified, Create A New Session, Store A User Object In
		// The Session And Redirect The User To The Home Page If The User Is Not
		// Verified, Redirect The User To The Login Page And Display Appropriate
		// Message
		if (userDAO.verifyUser(request.getParameter("email"),
				request.getParameter("password"))) {
			// Generate A New Session
			HttpSession session = request.getSession(true);

			// Get The User Information And Store It In A User Object, Then
			// Store The User Object In The Session
			User user = (User) userDAO.getUser(request.getParameter("email"));

			// Add All Owned Packages To User
			DBConnector conn = new DBConnector();
			PackageDAO packageDAO = new PackageDAO();
			user.setPackageList(packageDAO.getAllUserPackages(conn, request.getParameter("email")));
			
			// Put The User Object In The Session
			session.setAttribute("user", user);
			
			conn.closeConnection();

			// Redirect The User To The Home Page
			response.sendRedirect("index.jsp");
		} else {
			message = "Log In Failed, Invalid 'Email' And/Or 'Password'!";
			// Redirect The User To The Login Page
			response.sendRedirect("login.jsp?message=" + message);
		}
	}
}