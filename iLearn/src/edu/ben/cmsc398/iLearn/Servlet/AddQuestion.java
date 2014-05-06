package edu.ben.cmsc398.iLearn.Servlet;


// Add check for questions already exists.

import java.io.IOException;

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
 * Servlet implementation class AddQuestion
 */
@WebServlet("/AddQuestion")
public class AddQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddQuestion() {
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
		
		//Extracts value from submitting to determine if the user is done or not.
		String value = request.getParameter("value");
		PackageBuilder pkg = (PackageBuilder) request.getSession().getAttribute("pkg");
		
		// DAO and DB conection for the edit package add question module
		QuestiondDAO qDAO = new QuestiondDAO();
		PackageDAO pDAO = new PackageDAO();
		DBConnector conn = new DBConnector();
		String message = null;
		String successMessage = null;
		
		
		// Get parameters
		String question = request.getParameter("question");
		String correctAns = request.getParameter("correctAns");
		String firstWrongAns = request.getParameter("firstWrongAns");
		String secondWrongAns = request.getParameter("secondWrongAns");
		String thirdWrongAns = request.getParameter("thirdWrongAns");
		String explanation = request.getParameter("explanation");
		
		//Build a question objects and add to the package
		Question questionObj = new Question(question,correctAns,firstWrongAns,secondWrongAns,thirdWrongAns,explanation);
		boolean unique = InputLengthValidator.areAnswersUnique(questionObj);
		
		if ( value.equalsIgnoreCase("Add Another Question") || value.equalsIgnoreCase("Done Adding Questions"))
		{
		// The user wants to add another questions
		if ( value.equals("Add Another Question") && pkg != null && unique)
		{
			pkg.getQuestions().add(questionObj);
			
			request.getSession().setAttribute("package", pkg);
			successMessage = "Question Added!";
			response.sendRedirect("questions.jsp?successMessage="+successMessage);
			
		}
		// The user entered their last questions and will be directed to adding reference material
		else if (value.equals("Done Adding Questions") && pkg != null && unique)
		{
			pkg.getQuestions().add(questionObj);
			pkg.setNumberOfQuestions();
			request.getSession().setAttribute("package", pkg);
			RequestDispatcher rd = request.getRequestDispatcher("/addpdf.jsp");
			rd.forward(request, response);
		}
		
		else if (!unique)
		{
			message = "Dupilcate Answers. Please input unique answers.";
			response.sendRedirect("questions.jsp?message=" + message+"&correctAns="+correctAns+"&question="+question+"&firstWrongAns="+firstWrongAns+"&secondWrongAns="+secondWrongAns+"&thirdWrongAns="+thirdWrongAns+"&explanation="+explanation);
		}
		
		else
		{
			message = "Please create a package before adding questions";
			response.sendRedirect("addPackage.jsp?successMessage=" + message);
		}

	}
		
		
		
		
		
		
		// This section handles the add questions from the edit package module
		else
		{
			// This condition is for the adding questions from the edit package module
			if (value.equals("Save And Add More Questions") && pkg != null && unique)
			{
				pkg.getQuestions().add(questionObj);
				pkg.setNumberOfQuestions();
				request.getSession().setAttribute("package", pkg);
				qDAO.addQuestion(conn, pkg.getPkgID(), questionObj);
				pDAO.updateQuestionCount(pkg.getPkgID(), pkg.getNumberOfQuestions(), conn);
				successMessage = "Question Added!";
				response.sendRedirect("EditPackageAddQuestions.jsp?successMessage="+successMessage);
			}
			
			// This condition is for the adding questions from the edit package module
			else if (value.equals("Save And Return") && pkg != null && unique)
			{
				pkg.getQuestions().add(questionObj);
				pkg.setNumberOfQuestions();
				request.getSession().setAttribute("package", pkg);
				qDAO.addQuestion(conn, pkg.getPkgID(), questionObj);
				pDAO.updateQuestionCount(pkg.getPkgID(), pkg.getNumberOfQuestions(), conn);
				response.sendRedirect("editQuestionProperties.jsp");
			}
			else if (!unique)
			{
				message = "Dupilcate Answers. Please input unique answers.";
				response.sendRedirect("EditPackageAddQuestions.jsp?message=" + message+"&correctAns="+correctAns+"&question="+question+"&firstWrongAns="+firstWrongAns+"&secondWrongAns="+secondWrongAns+"&thirdWrongAns="+thirdWrongAns+"&explanation="+explanation);
			}
			
			else
			{
				message = "Unexpected Error! Please Try Again.";
				response.sendRedirect("EditPackageAddQuestions.jsp?message=" + message);
			}
		}
	}

}


