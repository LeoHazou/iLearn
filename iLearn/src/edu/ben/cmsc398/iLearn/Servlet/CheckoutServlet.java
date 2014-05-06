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
import edu.ben.cmsc398.iLearn.Model.User;

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckoutServlet() {
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
		// Adding Packages, Otherwise Redirect To Login Page
		if (currentSession != null
				&& currentSession.getAttribute("user") != null) {
			User user = (User) currentSession.getAttribute("user");

			boolean packageAlreadyExists = false;

			// Loop Through The User's Package List To Check If The Package
			// Already Exists
			for (int i = 0; i < user.getPackageList().size(); i++) {
				for (int j = 0; j < user.getShoppingCartPackageList().size(); j++) {
					if (user.getPackageList().get(i).getPkgID() == user
							.getShoppingCartPackageList().get(j).getPkgID()) {
						packageAlreadyExists = true;
					}
				}
			}

			// If The User's Does Not Own The Packages, Add The
			// Packages
			if (!packageAlreadyExists) {
				DBConnector conn = new DBConnector();
				PackageDAO packageDAO = new PackageDAO();

				// Add The Packages Present In The Shopping Cart To The Database
				for (int i = 0; i < user.getShoppingCartPackageList().size(); i++) {
					packageDAO.addUserPackage(user.getiD(), user
							.getShoppingCartPackageList().get(i).getPkgID(),
							conn);
				}
				
				// Update User Object
				user.setPackageList(packageDAO.getAllUserPackages(conn, user.getEmail()));

				// Clear The Shopping Cart
				user.getShoppingCartPackageList().clear();

				// Add The Updated User Object To The Session
				currentSession.setAttribute("user", user);

				message = "Packages Checked Out Successfully!";

				// Redirect The User To The My Packages JSP Page
				response.sendRedirect("myPackages.jsp?successMessage="
						+ message);
			} else {
				message = "Failed To Check Out Packages!";

				// Redirect The User To The Checkout JSP Page
				response.sendRedirect("checkout.jsp?message=" + message);
			}
		} else {
			// Redirect The User To The Login JSP Page
			response.sendRedirect("login.jsp");
		}
	}
}