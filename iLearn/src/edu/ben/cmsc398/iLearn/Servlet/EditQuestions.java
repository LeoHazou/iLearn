package edu.ben.cmsc398.iLearn.Servlet;

import java.io.IOException;
import java.util.ArrayList;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ben.cmsc398.iLearn.Dao.DBConnector;
import edu.ben.cmsc398.iLearn.Dao.QuestiondDAO;
import edu.ben.cmsc398.iLearn.Model.PackageBuilder;
import edu.ben.cmsc398.iLearn.Model.Question;

/**
 * Servlet implementation class EditQuestions
 */
@WebServlet("/EditQuestions")
public class EditQuestions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditQuestions() {
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
		
		
		String function = request.getParameter("function");
		
		
		if ( function.toLowerCase().equals("delete"))
		{
			int ID = Integer.parseInt(request.getParameter("ID"));
			PackageBuilder pkg = (PackageBuilder) request.getSession().getAttribute("pkg");
			DBConnector conn = new DBConnector();
		   QuestiondDAO delete = new QuestiondDAO();
		   delete.deleteQuestion(ID, conn);
		   // Get updated list of questions.
		   ArrayList<Question> questions = delete.getQuestions(pkg.getPkgID(), conn);
		   pkg.setQuestions(questions);
		   request.getSession().setAttribute("pkg", pkg);
		   response.sendRedirect("editQuestionProperties.jsp");
		   
		}
		
		else if ( function.toLowerCase().equals("edit"))
		{
			int ID = Integer.parseInt(request.getParameter("ID"));
			DBConnector conn = new DBConnector();

			QuestiondDAO qDAO = new QuestiondDAO();
			Question question = qDAO.getQuestion(ID, conn);
			
			conn.closeConnection();
			request.getSession().setAttribute("question", question);
			response.sendRedirect("editQuestion.jsp");
			
		}
		
		else if ( function.toLowerCase().equals("save"))
		{
			// Get new parameters, construct question object and update database
			DBConnector conn = new DBConnector();
			QuestiondDAO qDAO = new QuestiondDAO();
			Question question = (Question) request.getSession().getAttribute("question");
			
			Question newQuestion = new Question();
			newQuestion.setID(question.getID());
			
			newQuestion.setQuestion(request.getParameter("question"));
			newQuestion.setCorrectAns(request.getParameter("correctAns"));
			newQuestion.setFirstWrongAns(request.getParameter("firstWrongAns"));
			newQuestion.setSecondWrongAns(request.getParameter("secondWrongAns"));
			newQuestion.setThirdWrongAns(request.getParameter("thirdWrongAns"));
			newQuestion.setExplanation(request.getParameter("explanation"));
			
			qDAO.updateQuestion(newQuestion, conn);
			
			// Modify the pkg object in session and redirect back to the edit questions page
			PackageBuilder pkg = (PackageBuilder) request.getSession().getAttribute("pkg");
			ArrayList<Question> tempQ = qDAO.getQuestions(pkg.getPkgID(), conn);
			pkg.setQuestions(tempQ);
			conn.closeConnection();
			request.getSession().setAttribute("pkg", pkg);
			   response.sendRedirect("editQuestionProperties.jsp");
			
			
			
		}
	}

}
