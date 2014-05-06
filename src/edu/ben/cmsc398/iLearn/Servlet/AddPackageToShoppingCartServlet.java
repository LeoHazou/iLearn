package edu.ben.cmsc398.iLearn.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ben.cmsc398.iLearn.Model.PackageBuilder;
import edu.ben.cmsc398.iLearn.Model.User;

/**
 * Servlet implementation class AddPackageToShoppingCartServlet
 */
@WebServlet("/AddPackageToShoppingCartServlet")
public class AddPackageToShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddPackageToShoppingCartServlet() {
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
		String message = null;
		
		// Get The Current Session
		HttpSession currentSession = request.getSession(false);

		// If A Session Exists and A User Object Is In The Session, Continue
		// Adding Package To Shopping Cart, Otherwise Redirect To Login Page
		if (currentSession != null
				&& currentSession.getAttribute("user") != null) {
			User user = (User) currentSession.getAttribute("user");
			
			// Build A Temporary Package With The Package Details
			PackageBuilder tempPackage = new PackageBuilder(
					Integer.parseInt(request.getParameter("id")),
					request.getParameter("name"));
			tempPackage.setPkgDescription(request.getParameter("description"));
			tempPackage
					.setPkgPrice(Double.valueOf(request.getParameter("price")));

			boolean packageAlreadyExists = false;
			
			// Loop Through The User's Package List To Check If Package Exists 
			for (int i = 0; i < user.getPackageList().size(); i++) {
				if (user.getPackageList().get(i).getPkgID() == Integer
						.parseInt(request.getParameter("id"))) {
					packageAlreadyExists = true;
				}
			}
			
			// Loop Through The User's Shopping Cart Package List To Check If Package Exists 
			for (int i = 0; i < user.getShoppingCartPackageList().size(); i++) {

				if (user.getShoppingCartPackageList().get(i).getPkgID() == Integer
						.parseInt(request.getParameter("id"))) {
					packageAlreadyExists = true;
				}
			}

			// If The User's Shopping Cart Does Not Contain The Package, Add The
			// Package
			if (!packageAlreadyExists) {
				user.addPackageToShoppingCartPackageList(tempPackage);
				
				message = "Package Successfully Added To Shopping Cart!";
				
				// Redirect The User To The Shopping Cart JSP Page
				response.sendRedirect("shoppingCart.jsp?successMessage=" + message);
			}
			else
			{
				message = "Failed To Add Package To Shopping Cart, You Already Own This Package Or It Is Already In The Shopping Cart!";
				
				// Redirect The User To The Shopping Cart JSP Page
				response.sendRedirect("shoppingCart.jsp?message=" + message);
			}
		} else {
			// Redirect The User To The Login JSP Page
			response.sendRedirect("login.jsp");
		}
	}
}