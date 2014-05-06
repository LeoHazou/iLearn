package edu.ben.cmsc398.iLearn.Servlet;

import java.io.IOException;
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
 * Servlet implementation class ResetPasswordServlet
 */
@WebServlet("/ResetPasswordServlet")
public class ResetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ResetPasswordServlet() {
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
		String message;

		if (request.getParameter("email") != null) {

			// From Email
			final String fromEmail = "iLearnMailerDaemon@gmail.com";
			// Email From Password
			final String password = "iLearn123";

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
					return new PasswordAuthentication(fromEmail, password);
				}
			};

			Session session = Session.getInstance(props, auth);

			UserDAO userDAO = new UserDAO();

			if (userDAO.existsInTable("User", "Email",
					request.getParameter("email"))) {
				String newPassword = InputLengthValidator.randomString();
				String hashed = Md5Encryptor.encryptPass(newPassword);
				User user = (User) userDAO.getUser(request
						.getParameter("email"));
				userDAO.resetPass(user.getiD(), hashed);

				EmailUtility
						.sendEmail(
								session,
								toEmail,
								"Password Reset!",
								"Your New Password Is (Case Sensitive): "
										+ newPassword
										+ "\nPlease Click On The Following Link To Login And Change Your Password: "
										+ "http://tinyurl.com/lzea6tx");

				message = "Password Reset Successful!";
				// Redirect The User To The Login JSP Page
				response.sendRedirect("login.jsp?successMessage=" + message);
			} else {
				message = "Password Reset Failed! User Does Not Exist!";
				// Redirect The User To The Login JSP Page
				response.sendRedirect("resetPassword.jsp?message=" + message);
			}
		} else {
			message = "Password Reset Failed!";
			// Redirect The User To The Login JSP Page
			response.sendRedirect("resetPassword.jsp?message=" + message);
		}
	}
}
