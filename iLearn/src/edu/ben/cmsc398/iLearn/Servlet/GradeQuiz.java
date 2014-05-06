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
 * Servlet implementation class GradeQuiz
 */
@WebServlet("/GradeQuiz")
public class GradeQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GradeQuiz() {
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
		// Get The Current Session
		HttpSession currentSession = request.getSession(false);

		// If A Session Exists and A User Object Is In The Session, Continue
		// Grading, Otherwise Redirect To Login Page
		if (currentSession != null
				&& currentSession.getAttribute("user") != null) {
			User user = (User) currentSession.getAttribute("user");

			// Initialize Variables
			int numberOfQuestions;
			int numberOfQuestionCorrect = 0;
			float grade = 0;
			
			// If The Required RequestParams Are Not Null
			if (request.getParameter("packageID") != null
					&& request.getParameter("numberOfQuestions") != null) {
				
				// Get The Number Of Questions
				numberOfQuestions = Integer.parseInt(request
						.getParameter("numberOfQuestions"));
				
				// Calculate How Many Questions Are Correct
				for (int i = 0; i < numberOfQuestions; i++) {
					if (request.getParameter(String.valueOf(i)) != null) {
						if (request.getParameter(String.valueOf(i)).equals(
								"true")) {
							numberOfQuestionCorrect++;
						}
					}
				}
				
				// Calculate The Percentage Grade
				if (numberOfQuestionCorrect > 0 && numberOfQuestions > 0) {
					grade = (float) numberOfQuestionCorrect / numberOfQuestions
							* 100;
				} else {
					grade = 0;
				}
				
				// Add The Grade To The Database
				PackageDAO packageDAO = new PackageDAO();
				DBConnector conn = new DBConnector();
				packageDAO.addUserPackageGrade(user.getiD(),
						Integer.parseInt(request.getParameter("packageID")),
						grade, conn);
				
				String message = "Package Graded!";
				// Redirect The User To The My Packages JSP Page
				response.sendRedirect("myPackages.jsp?successMessage=" + message);
			} else {
				// Redirect The User To The My Packages JSP Page
				response.sendRedirect("myPackages.jsp");
			}
		} else {
			// Redirect The User To The Login JSP Page
			response.sendRedirect("login.jsp");
		}
	}
}
