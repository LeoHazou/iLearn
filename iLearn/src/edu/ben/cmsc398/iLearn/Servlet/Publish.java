package edu.ben.cmsc398.iLearn.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ben.cmsc398.iLearn.Dao.DBConnector;
import edu.ben.cmsc398.iLearn.Dao.PackageDAO;
import edu.ben.cmsc398.iLearn.Dao.QuestiondDAO;
import edu.ben.cmsc398.iLearn.Model.PackageBuilder;

/**
 * Servlet implementation class Publish
 */
@WebServlet("/Publish")
public class Publish extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Publish() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		
		String message = "";
		// Get package object from session and calculate number of questions.
		String value = request.getParameter("value");
		PackageBuilder pkg = (PackageBuilder) request.getSession().getAttribute("pkg");
		pkg.setNumberOfQuestions();
		
		// This portion is for a NEW package, executed when adding a package
		if ( pkg.getPkgID() == -1)
		{
	
		// Open connection to DB
		DBConnector conn = new DBConnector();
		PackageDAO publishPkg = new PackageDAO();
		QuestiondDAO publishQuestion = new QuestiondDAO();
		
		// If admin want to publish a package, set appropriate parameters to true.
		if ( value.toLowerCase().equals("yes"))
		{
			pkg.setPublished(true);
			message = " Package Added To Database and Published";
		}
		
		//If admin does not want to publish a package, set appropriate parameters to false.
		else
		{
			pkg.setPublished(false);
			message = " Package Added To Database But Was Not Published";
		}
		
		//Add package to database
		pkg.setNumberOfQuestions();
		publishPkg.addPkg(pkg, conn);
		
		// Get the auto generated package id to use as foreign key for questions.
		pkg.setPkgID(publishPkg.getPkgId(pkg, conn));
		
		// Add the questions to the questions table
		publishQuestion.addQuestions(conn, pkg);
		publishPkg.updateQuestionCount(pkg.getPkgID(), pkg.getQuestions().size(), conn);
		conn.closeConnection();
		
		response.sendRedirect("addPackage.jsp?successMessage="+message);
		}
		
		// This portion is for an EDITED package, executed when editing a package
		else
		{
			
			// Open connection to DB
			DBConnector conn = new DBConnector();
			PackageDAO publishPkg = new PackageDAO();
			pkg.setNumberOfQuestions();
			
			// If admin want to publish a package, set appropriate parameters to true.
			if ( value.toLowerCase().equals("yes"))
			{
				
				
				publishPkg.updateLink(pkg.getPkgID(), true, conn, pkg.getStudyMaterialLink());
				publishPkg.updatedPublished(pkg.getPkgID(), true, conn);
				publishPkg.updateQuestionCount(pkg.getPkgID(), pkg.getQuestions().size(), conn);
				
				
				message = " Package Edited Succesfuly and Published";
			}
			
			//If admin does not want to publish a package, set appropriate parameters to false.
			else
			{
				
				pkg.setPublished(false);
				publishPkg.updatedPublished(pkg.getPkgID(), false, conn);
				publishPkg.updateQuestionCount(pkg.getPkgID(), pkg.getQuestions().size(), conn);
				message = " Package Edited Succesfully But Was Not Published";
			}
			
			conn.closeConnection();
			response.sendRedirect("editPackage.jsp?successMessage="+message);
			
		}
		
		
	}
	

}
