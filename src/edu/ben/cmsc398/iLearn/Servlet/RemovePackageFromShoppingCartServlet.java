package edu.ben.cmsc398.iLearn.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ben.cmsc398.iLearn.Model.User;

/**
 * Servlet implementation class RemovePackageFromShoppingCartServlet
 */
@WebServlet("/RemovePackageFromShoppingCartServlet")
public class RemovePackageFromShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RemovePackageFromShoppingCartServlet() {
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
		String message = null;
		
		// Get The Current Session
		HttpSession currentSession = request.getSession(false);

		// If A Session Exists and A User Object Is In The Session, Continue
		// Adding Package To Shopping Cart, Otherwise Redirect To Login Page
		if (currentSession != null
				&& currentSession.getAttribute("user") != null) {
			User user = (User) currentSession.getAttribute("user");

			for (int i = 0; i < user.getShoppingCartPackageList().size(); i++) {

				if (user.getShoppingCartPackageList().get(i).getPkgID() == Integer
						.parseInt(request.getParameter("id"))) {
					user.getShoppingCartPackageList().remove(i);
				}
			}

			message = "Package Successfully Removed From Shopping Cart!";
			
			// Redirect The User To The Shopping Cart JSP Page
			response.sendRedirect("shoppingCart.jsp?successMessage=" + message);
		} else {
			// Redirect The User To The Login JSP Page
			response.sendRedirect("login.jsp");
		}
	}
}
