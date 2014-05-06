package edu.ben.cmsc398.iLearn.Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ben.cmsc398.iLearn.Dao.DBConnector;
import edu.ben.cmsc398.iLearn.Dao.GradeDAO;

/**
 * Servlet implementation class Report
 */
@WebServlet("/Report")
public class Report extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Report() {
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
		int maximum = 6;
		int uid = Integer.parseInt(request.getParameter("uid"));
		int pid = Integer.parseInt(request.getParameter("pid"));
		System.out.println(uid);
		System.out.println(pid);
		GradeDAO gDAO = new GradeDAO();
		DBConnector conn = new DBConnector();
		ArrayList<Float> grades = gDAO.getGrades(uid, pid, conn);
		int size = grades.size();
		System.out.println(size);
		// Grades holds 6 values, whatever does not exist put -1
		for ( int i = size; i < maximum;i++)
		{
			grades.add((float)-2);
		}
			
		request.getSession().setAttribute("grades", grades);
		response.sendRedirect("grades.jsp?size="+size+"&grade1="+grades.get(0)+"&grade2="+grades.get(1)+"&grade3="+grades.get(2)+"&grade4="+grades.get(3)+"&grade5="+grades.get(4)+"&grade6="+grades.get(5));
		
	}

}
