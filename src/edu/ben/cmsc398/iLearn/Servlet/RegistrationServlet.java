package edu.ben.cmsc398.iLearn.Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ben.cmsc398.iLearn.Dao.*;
import edu.ben.cmsc398.iLearn.Model.*;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrationServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Redirect The User To The Registration JSP Page
		response.sendRedirect("registration.jsp");
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
		if (request.getParameter("email") == null
				|| request.getParameter("password") == null
				|| request.getParameter("confirmPassword") == null
				|| request.getParameter("firstName") == null
				|| request.getParameter("lastName") == null
				|| request.getParameter("dateOfBirth") == null
				|| request.getParameter("gender") == null
				|| request.getParameter("recaptcha_challenge_field") == null
				|| request.getParameter("recaptcha_response_field") == null) {
			inputValidated = false;
			message = "One Or More Input Parameters Were Not Specified!";
		}
		// If Any Of The User's Input Length Is Invalid, Invalidate Input
		else if (!InputLengthValidator.isRegistrationInformationLengthValid(
				request.getParameter("email").length(),
				request.getParameter("password").length(), request
						.getParameter("firstName").length(), request
						.getParameter("lastName").length(), request
						.getParameter("dateOfBirth").length(), request
						.getParameter("gender").length())) {
			inputValidated = false;
			message = "One Or More Input Lengths Were Invalid!";
		}
		// If Any Of The User's Input Format Is Invalid, Invalidate Input
		else if (!InputFormatValidator.isRegistrationInformationFormatValid(
				request.getParameter("email"),
				request.getParameter("password"),
				request.getParameter("firstName"),
				request.getParameter("lastName"),
				request.getParameter("dateOfBirth"),
				request.getParameter("gender"))) {
			inputValidated = false;
			message = "One Or More Input Formats Were Invalid!";
		}
		// If The User's Email Already Exists In The Database, Invalidate Input
		else if (userDAO.existsInTable("User", "Email",
				request.getParameter("email"))) {
			inputValidated = false;
			message = "'Email' Already Exists!";
		}
		// If The User's Input For "Password" and "Confirm Password" Are Not
		// Equal, Invalidate Input
		else if (!request.getParameter("password").equals(
				request.getParameter("confirmPassword"))) {
			inputValidated = false;
			message = "'Password' And 'Confirm Password' Didn't Match";
		}
		// If The User's Input For "Gender" Is Not Valid, Invalidate Input
		else if (!request.getParameter("gender").equals("Male")
				&& !request.getParameter("gender").equals("Female")
				&& !request.getParameter("gender").equals("Other")) {
			inputValidated = false;
			message = "Invalid 'Gender'";
		}
		// If The User's Input For "Captcha" Is Not Valid, Invalidate Input
		else if (!CaptchaValidator.isCaptchaValid(request.getRemoteAddr(),
				request.getParameter("recaptcha_challenge_field"),
				request.getParameter("recaptcha_response_field"))) {
			inputValidated = false;
			message = "Invalid 'Captcha'";
		}

		// If The Input Is Valid, Add The User To The Database And Redirect The
		// User To The Login Page, Otherwise Redirect The User Back To The
		// Registration Page
		if (inputValidated) {
			// Add The User To The Database
			userDAO.addUser("User", request.getParameter("email"),
					request.getParameter("password"),
					request.getParameter("firstName"),
					request.getParameter("lastName"),
					request.getParameter("dateOfBirth"),
					request.getParameter("gender"));
			
			// Forward The User To The Email Servlet
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("EmailServlet");
			requestDispatcher.forward(request, response);

		} else {
			// Redirect The User To The Registration JSP Page And Display The
			// Appropriate Message
			response.sendRedirect("registration.jsp?message=" + message);
		}
	}
}