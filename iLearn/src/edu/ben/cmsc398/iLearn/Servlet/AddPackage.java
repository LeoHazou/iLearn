package edu.ben.cmsc398.iLearn.Servlet;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.ben.cmsc398.iLearn.Model.InputLengthValidator;
import edu.ben.cmsc398.iLearn.Model.PackageBuilder;

/**
 * Servlet implementation class AddPackage
 */


@WebServlet("/AddPackage")

public class AddPackage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPackage() {
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get parameters
		String pkgName = request.getParameter("pkgName");
		String pkgDescription = request.getParameter("pkgDescription");
		String pkgPrice = request.getParameter("pkgPrice");
		
		// Instantiate Input Validation Boolean, and Message String
				boolean inputValidated = true;
				String message = null;
				
				// check input validity and set appropriate error message if needed
				if (!InputLengthValidator.isPkgNameLengthValid(pkgName.length()))
				{
					inputValidated = false;
					message = "Package name should be between 1 and 45 characters long";
				}
				else if (!InputLengthValidator.isPkgDecriptionLengthValid(pkgDescription.length()))
				{
					inputValidated = false;
					message = "Package description should be between 1 and 255 characters long";
				}
				else if (!InputLengthValidator.isDouble(pkgPrice))
				{
					inputValidated = false;
					message = "Price should be in X.XX format i.e 4.50 or 13.99";
				}
		
				// All inputs are valid
				if (inputValidated)
				{
					//convert price to float
					double price = InputLengthValidator.formatPrice(pkgPrice);
					//Build a package
					PackageBuilder pkg = new PackageBuilder(pkgName,pkgDescription,price);
					request.getSession().setAttribute("pkg", pkg);
					RequestDispatcher rd = request.getRequestDispatcher("/questions.jsp");
					rd.forward(request, response);
					
				}
				else
				{
					// Redirect The User To The add package JSP Page And Display The
					// Appropriate Message
					
					response.sendRedirect("addPackage.jsp?message=" + message+"&pkgName="+pkgName+"&pkgDescription="+pkgDescription+"&pkgPrice="+pkgPrice);
				}
	}
}
