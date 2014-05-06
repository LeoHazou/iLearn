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
import edu.ben.cmsc398.iLearn.Model.InputLengthValidator;
import edu.ben.cmsc398.iLearn.Model.Md5Encryptor;
import edu.ben.cmsc398.iLearn.Model.User;

/**
 * Servlet implementation class ManageUser
 */
@WebServlet("/ManageUser")
public class ManageUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageUser() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String function = request.getParameter("function");
		UserDAO uDAO = new UserDAO();
		String message = null;
		String successMessage = null;
		
		if ( function.equals("Search Email"))
		{
		
			if (uDAO.existsInTable("user", "Email", request.getParameter("email")))
			{
				User user = uDAO.getUser(request.getParameter("email"));
				ArrayList<User> users = new ArrayList<User>();
				users.add(user);
				if ( user.getType().equals("User"))
				{
					request.getSession().setAttribute("userList",users);
					response.sendRedirect("userList.jsp");
				}
				else
				{
					message = "No Results! Please Try Again.";
					response.sendRedirect("manageUsers.jsp?message="+message);
				}
			}
			else
			{
				message = "No Results! Please Try Again.";
				response.sendRedirect("manageUsers.jsp?message="+message);
			}
		}
		
		else if ( function.equals("Search Last Name"))
		{
			if (uDAO.existsInTable("user", "Last_Name", request.getParameter("lastName")))
			{
				ArrayList<User> users = uDAO.getUserByLastName(request.getParameter("lastName"));
				request.getSession().setAttribute("userList",users);
				response.sendRedirect("userList.jsp");
			}
			else
			{
				message = "No Results! Please Try Again.";
				response.sendRedirect("manageUsers.jsp?message="+message);
			}
		}
		
		if ( function.equals("delete"))
		{
			int ID = Integer.parseInt((String)request.getParameter("ID"));
			boolean flag = uDAO.deleteUser(ID);
			if ( flag)
			{
				successMessage = "User Deleted Successfully!";
				response.sendRedirect("manageUsers.jsp?successMessage="+message);
			}
			else
			{
				message = "Record Could Not Be Deleted! Please Try Again.";
				response.sendRedirect("manageUsers.jsp?message="+message);
			}
		}
		
		// Reset password section
				else if ( function.equals("reset"))
				{
					int ID = Integer.parseInt((String)request.getParameter("ID"));
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

					User currentUser = uDAO.getUser(ID);
					String body = "Hello "+ currentUser.getFirstName() + " " + currentUser.getLastName() +",\nYour new password is(Case Sensitive): "+ password +"\n Please click on the following link to login and change your password: http://tinyurl.com/lzea6tx \n\n This is a post-only mailing from iLearn. Please do not respond or reply to this message.  ";
					Session session = Session.getInstance(props, auth);
					
					EmailUtility.sendEmail(session, toEmail, "Password Reset",
							body);
					
					
					response.sendRedirect("resetPass.jsp");
				}
	}

}
