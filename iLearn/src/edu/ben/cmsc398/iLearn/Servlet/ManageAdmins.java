package edu.ben.cmsc398.iLearn.Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ben.cmsc398.iLearn.Dao.UserDAO;
import edu.ben.cmsc398.iLearn.Model.User;

/**
 * Servlet implementation class ManageAdmins
 */
@WebServlet("/ManageAdmins")
public class ManageAdmins extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageAdmins() {
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
		
		
		String message = null;

		// Get The Current Session
		HttpSession currentSession = request.getSession(false);

		// If A Session Exists and A User Object Is In The Session, Continue
		// Otherwise Redirect To Login Page
		if (currentSession != null
				&& currentSession.getAttribute("user") != null) 
		{
			User user = (User) currentSession.getAttribute("user");
			//Verify access level
			if ( !user.getType().equals("SuperAdministrator"))
			{
				message = "Access Denied due To Insuffiecient Privilages";
				response.sendRedirect("index.jsp?message=" + message);
			}
			
			// Collect admin info from database and put in a user array.
			//The redirect to the appropriate jsp page
			else
			{
				UserDAO uDAO = new UserDAO();
			ArrayList<User> users = uDAO.getAdmins();
			request.getSession().setAttribute("adminList", users);
			response.sendRedirect("manageAdministrators.jsp");
			
			
			}
			
		}
		
		else
		{
			// Redirect to login page
			message = " Session timeout or Invalid Session.";
			response.sendRedirect("login.jsp?message=" + message);
		}
		
		
		
	}

}
