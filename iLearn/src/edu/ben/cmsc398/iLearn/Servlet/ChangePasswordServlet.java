package edu.ben.cmsc398.iLearn.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ben.cmsc398.iLearn.Dao.UserDAO;
import edu.ben.cmsc398.iLearn.Model.InputLengthValidator;
import edu.ben.cmsc398.iLearn.Model.User;

/**
 * Servlet implementation class ChangePasswordServlet
 */
@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangePasswordServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Instantiate A New User DAO Object
		UserDAO userDAO = new UserDAO();

		// Instantiate Input Validation Boolean, and Message String
		boolean inputValidated = true;
		String message = null;

		// If Any Parameters Are NULL, Invalidate Input
		if (request.getParameter("password") == null
				|| request.getParameter("newPassword") == null
				|| request.getParameter("confirmNewPassword") == null) {
			inputValidated = false;
			message = "One Or More Input Parameters Were Not Specified!";
		}

		// If Any Of The User's Input Length Is Invalid, Invalidate Input
		else if (!InputLengthValidator.isPasswordLengthValid(request
				.getParameter("password").length())
				|| !InputLengthValidator.isPasswordLengthValid(request
						.getParameter("newPassword").length())
				|| !InputLengthValidator.isPasswordLengthValid(request
						.getParameter("confirmNewPassword").length())) {
			inputValidated = false;
			message = "One Or More Input Lengths Were Invalid!";
		}

		else if (!request.getParameter("newPassword").equals(
				request.getParameter("confirmNewPassword"))) {
			inputValidated = false;
			message = "'New Password' And 'Confirm New Password' Do Not Match!";
		}
		
		else if (request.getParameter("password").equals(
				request.getParameter("newPassword"))) {
			inputValidated = false;
			message = "'Old Password' And 'New Password' Match!";
		}

		// Get The Current Session Or NULL If The Session Does Not Exist
		HttpSession currentSession = request.getSession(false);

		if (currentSession == null) {
			inputValidated = false;
		}

		if (inputValidated == true) {
			// Get The User Object From The Current Session
			User user = (User) currentSession.getAttribute("user");

			if (userDAO.verifyUser(user.getEmail(),
					request.getParameter("password"))) {
				userDAO.editUserPassword(user.getEmail(),
						request.getParameter("newPassword"));
				
				String successMessage = "Password Successfully Changed!";

				// Redirect The User To The My Profile JSP Page
				response.sendRedirect("myProfile.jsp?successMessage="
						+ successMessage);
			} else {
				message = "Password Is Not Valid!";

				// Redirect The User To The My Profile JSP Page
				response.sendRedirect("myProfile.jsp?message=" + message);
			}
		} else {
			// Redirect The User To The My Profile JSP Page
			response.sendRedirect("myProfile.jsp?message=" + message);
		}
	}
}
