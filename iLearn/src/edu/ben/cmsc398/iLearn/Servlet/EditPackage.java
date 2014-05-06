package edu.ben.cmsc398.iLearn.Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ben.cmsc398.iLearn.Dao.DBConnector;
import edu.ben.cmsc398.iLearn.Dao.PackageDAO;
import edu.ben.cmsc398.iLearn.Dao.QuestiondDAO;
import edu.ben.cmsc398.iLearn.Model.InputLengthValidator;
import edu.ben.cmsc398.iLearn.Model.PackageBuilder;
import edu.ben.cmsc398.iLearn.Model.Question;

/**
 * Servlet implementation class EditPackage
 */
@WebServlet("/EditPackage")
public class EditPackage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditPackage() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int ID = 0;
		String message = "";
		boolean ioError = true;
		boolean isExist = false;
		DBConnector conn = new DBConnector();
		String pkgID = request.getParameter("pkgID");
		PackageDAO packageObj = new PackageDAO();
		QuestiondDAO questionObj = new QuestiondDAO();
		if (!InputLengthValidator.isInt(pkgID))
		{
			ioError = true;
		}
		else
		{
			ioError = false;
			ID = InputLengthValidator.stringToInt(pkgID);
			isExist = packageObj.IDexistsInTable(ID, conn);
		}

		if (ioError)
		{
			message = "Input Error. Please Enter Integer Values For Package ID.";
		} else if (!isExist)
		{
			message = "Package ID Not Found. Please Try Again.";
		}

		if (ioError || !isExist)
		{
			// Redirect The User To The edit package JSP Page And Display The
			// Appropriate Message
			response.sendRedirect("editPackage.jsp?message=" + message);
		}
		// If everything validated, populate the package and questions and
		// redirect to the next page
		else if (isExist && !ioError)
		{
			// Populate the package
			PackageBuilder pkg = packageObj.getPkg(ID, conn);
			
			// Create temp arraylist to populate the questions
			ArrayList<Question> temp = questionObj.getQuestions(ID, conn);
			
			// Add the questions to the package to prevent null pointer
			// exceptions
			for (int j = 0; j < temp.size(); j++) 
			{
				pkg.getQuestions().add(temp.get(j));
			}
			
			request.getSession().setAttribute("pkg", pkg);
			RequestDispatcher rd = request.getRequestDispatcher("/editPackageProperties.jsp");
			rd.forward(request, response);
		}
	}

}
