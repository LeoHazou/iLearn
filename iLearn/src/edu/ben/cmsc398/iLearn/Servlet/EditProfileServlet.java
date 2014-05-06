package edu.ben.cmsc398.iLearn.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ben.cmsc398.iLearn.Dao.DBConnector;
import edu.ben.cmsc398.iLearn.Dao.PackageDAO;
import edu.ben.cmsc398.iLearn.Dao.UserDAO;
import edu.ben.cmsc398.iLearn.Model.InputFormatValidator;
import edu.ben.cmsc398.iLearn.Model.InputLengthValidator;
import edu.ben.cmsc398.iLearn.Model.User;

/**
 * Servlet implementation class EditProfileServlet
 */
@WebServlet("/EditProfileServlet")
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditProfileServlet() {
		super();
		// TODO Auto-generated constructor stub
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

		// Instantiate Input Validation Boolean, and Message String
		boolean inputValidated = true;
		String message = null;

		// If Any Parameters Are NULL, Invalidate Input
		if (request.getParameter("firstName") == null
				|| request.getParameter("lastName") == null
				|| request.getParameter("dateOfBirth") == null
				|| request.getParameter("gender") == null) {
			inputValidated = false;
			message = "One Or More Input Parameters Were Not Specified!";
		}

		// If Any Of The User's Input Length Is Invalid, Invalidate Input
		else if (!InputLengthValidator.isEditProfileInformationLengthValid(

		request.getParameter("firstName").length(),
				request.getParameter("lastName").length(), request
						.getParameter("dateOfBirth").length(), request
						.getParameter("gender").length())) {
			inputValidated = false;
			message = "One Or More Input Lengths Were Invalid!";
		}

		// If Any Of The User's Input Format Is Invalid, Invalidate Input
		else if (!InputFormatValidator.isEditProfileInformationFormatValid(
				request.getParameter("firstName"),
				request.getParameter("lastName"),
				request.getParameter("dateOfBirth"),
				request.getParameter("gender"))) {
			inputValidated = false;
			message = "One Or More Input Formats Were Invalid!";
		}

		// If The User's Input For "Gender" Is Not Valid, Invalidate Input
		if (!request.getParameter("gender").equals("Male")
				&& !request.getParameter("gender").equals("Female")
				&& !request.getParameter("gender").equals("Other")) {
			inputValidated = false;
			message = "Invalid 'Gender'";
		}

		// Get The Current Session Or NULL If The Session Does Not Exist
		HttpSession currentSession = request.getSession(false);

		if (currentSession == null) {
			inputValidated = false;
		}

		if (inputValidated == true) {
			// Get The User Object From The Current Session
			User user = (User) currentSession.getAttribute("user");

			// Update All User Values
			userDAO.editUserFirstName(user.getEmail(),
					request.getParameter("firstName"));
			userDAO.editUserLastName(user.getEmail(),
					request.getParameter("lastName"));
			userDAO.editUserDateOfBirth(user.getEmail(),
					request.getParameter("dateOfBirth"));
			userDAO.editUserGender(user.getEmail(),
					request.getParameter("gender"));

			// Get The New User Information And Store It In A User Object
			user = userDAO.getUser(request.getParameter("email"));

			// Add All Owned Packages To The User Object
			DBConnector conn = new DBConnector();
			PackageDAO packageDAO = new PackageDAO();
			user.setPackageList(packageDAO.getAllUserPackages(conn,
					request.getParameter("email")));

			// Store The New User Object In The Current Session
			currentSession.setAttribute("user", user);

			String successMessage = "Edit Profile Successful!";

			// Redirect The User To The My Profile JSP Page
			response.sendRedirect("myProfile.jsp?successMessage="
					+ successMessage);
		} else {
			// Redirect The User To The My Profile JSP Page
			response.sendRedirect("myProfile.jsp?message=" + message);
		}
	}
}
