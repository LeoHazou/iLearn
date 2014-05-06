package edu.ben.cmsc398.iLearn.Servlet;

import java.io.IOException;
import java.util.ArrayList;


import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ben.cmsc398.iLearn.Dao.UserDAO;
import edu.ben.cmsc398.iLearn.Model.EmailUtility;
import edu.ben.cmsc398.iLearn.Model.InputFormatValidator;
import edu.ben.cmsc398.iLearn.Model.InputLengthValidator;
import edu.ben.cmsc398.iLearn.Model.Md5Encryptor;
import edu.ben.cmsc398.iLearn.Model.User;

/**
 * Servlet implementation class EditAdmin
 */
@WebServlet("/EditAdmin")
public class EditAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditAdmin() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int ID = 0;
		if ( request.getParameter("ID") != null)
		{
		ID = Integer.parseInt(request.getParameter("ID"));
		}
		String function = request.getParameter("function");
		UserDAO uDAO = new UserDAO();
		String message = null;
		String successMessage = null;
		boolean inputValidated = true;
		
		// Delete an admin
		if (function.equals("delete"))
		{
			// Make sure the user is in the database
			boolean flag = uDAO.deleteUser(ID);
			if (flag)
			{
				@SuppressWarnings("unchecked")
				ArrayList<User> users = (ArrayList<User>) request.getSession().getAttribute("adminList");
				for ( int i = 0; i < users.size(); i++)
				{
					if (users.get(i).getiD() == ID)
					{
						users.remove(i);
					}
				}
				request.getSession().setAttribute("adminList", users);
				successMessage = "Delete Successful!";
				response.sendRedirect("manageAdministrators.jsp?successMessage="+successMessage);
			}
			else
			{
				message = "Could Not Delete User. Please Re-Login And Try Again";
				response.sendRedirect("manageAdministrators.jsp?message="+message);
			}
		}
		
		// To save new admin information
		else if (function.equals("Save Info"))
		{
			
			// Instantiate Input Validation Boolean, and Message String
			
			 message = null;

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
			else if (!request.getParameter("gender").equals("Male")
					&& !request.getParameter("gender").equals("Female")
					&& !request.getParameter("gender").equals("Other")) 
			{
				inputValidated = false;
				message = "Invalid 'Gender'";
			}
			
			// If input is valid, update the info
			if ( !inputValidated)
			{
				response.sendRedirect("editAdminInfo.jsp?message="+message+"&ID="+request.getParameter("ID")+"&firstName="+request.getParameter("firstName")+"&lastName="+request.getParameter("lastName")+"&gender="+request.getParameter("gender")+"&dateOfBirth="+request.getParameter("dateOfBirth")+"&email="+request.getParameter("email")+"&type="+request.getParameter("type"));
			}
			
			// If input is invalid
			else
			{
				//update data, then update the admin list in the session.
				successMessage = " Information Edited Successfuly!";
				uDAO.editUserDateOfBirthByID(ID, request.getParameter("dateOfBirth"));
				uDAO.editUserFirstNameByID(ID, request.getParameter("firstName"));
				uDAO.editUserLastNameByID(ID, request.getParameter("lastName"));
				uDAO.editUserGenderByID(ID, request.getParameter("gender"));
				uDAO.editUserTypeByID(ID,request.getParameter("type"));
				uDAO.editUserEmailByID(ID,request.getParameter("email"));
				
				ArrayList<User> users = uDAO.getAdmins();
				
				request.getSession().setAttribute("adminList", users);
				
				
				response.sendRedirect("manageAdministrators.jsp?successMessage="+successMessage);
			}
		}
		
		// Reset password section
		else if ( function.equals("reset"))
		{
			String fname = request.getParameter("fname");
			String lname = request.getParameter("lname");
			String password = InputLengthValidator.randomString();
			String hashed = Md5Encryptor.encryptPass(password);
			uDAO.resetPass(ID, hashed);
			message = "WARNING: PLEASE DO NOT PRINT OR SAVE THIS PAGE.\nIF YOU COULD NOT GIVE THE CLIENT THE PASSWORD IN TIME, PERFORM ANOTHER RESET";
			request.getSession().setAttribute("word", password);
			request.getSession().setAttribute("message", message);
			
			
			successMessage = "Password Reset Successful!";

			final String fromEmail = "iLearnMailerDaemon@gmail.com"; // Email From
			final String emailPassword = "iLearn123"; // Email From Password
			
			String toEmail = request.getParameter("email");

			
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
			props.put("mail.smtp.socketFactory.port", "465"); // SSL Port
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory"); // SSL Factory Class
			props.put("mail.smtp.auth", "true"); // Enabling SMTP Authentication
			props.put("mail.smtp.port", "465"); // SMTP Port

			Authenticator auth = new Authenticator() {
				// Override the getPasswordAuthentication method
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromEmail, emailPassword);
				}
			};

			String body = "Hello "+ fname + " " + lname +",\nYour new password is(Case Sensitive): "+ password +"\n Please click on the following link to login and change your password: http://tinyurl.com/lzea6tx \n\n This is a post-only mailing from iLearn. Please do not respond or reply to this message.  ";
			Session session = Session.getInstance(props, auth);
			
			EmailUtility.sendEmail(session, toEmail, "Password Reset",
					body);
			
			response.sendRedirect("resetPass.jsp");
		}
		
		//Add new admin section
		else if ( function.equals("Add Administrator"))
		{
			// If Any Parameters Are NULL, Invalidate Input
			if (request.getParameter("email") == null
					|| request.getParameter("password") == null
					|| request.getParameter("confirmPassword") == null
					|| request.getParameter("firstName") == null
					|| request.getParameter("lastName") == null
					|| request.getParameter("dateOfBirth") == null
					|| request.getParameter("gender") == null)
					 {
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
			else if (uDAO.existsInTable("User", "Email",
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
			

			// If The Input Is Valid, Add The User To The Database And Redirect The
			// User To The Login Page, Otherwise Redirect The User Back To The
			// Registration Page
			if (inputValidated) {
				// Add The User To The Database and refresh the adminlist
				uDAO.addUser(request.getParameter("type"), request.getParameter("email"),
						request.getParameter("password"),
						request.getParameter("firstName"),
						request.getParameter("lastName"),
						request.getParameter("dateOfBirth"),
						request.getParameter("gender"));
				successMessage="New Personnel Added!";
				
				ArrayList<User> users = uDAO.getAdmins();
				request.getSession().setAttribute("adminList", users);
				
				
				// Forward The User To The Email Servlet
			response.sendRedirect("manageAdministrators.jsp?successMessage="+successMessage);

			} else {
			
				// Redirect The Admin To The add Admin JSP Page And Display The
				// Appropriate Message
				response.sendRedirect("addAdmin.jsp?message="+message+"&firstName="+request.getParameter("firstName")+"&lastName="+request.getParameter("lastName")+"&gender="+request.getParameter("gender")+"&dateOfBirth="+request.getParameter("dateOfBirth")+"&email="+request.getParameter("email")+"&type="+request.getParameter("type"));
			}
		}
			
	}

}
