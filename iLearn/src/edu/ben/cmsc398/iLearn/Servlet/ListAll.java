package edu.ben.cmsc398.iLearn.Servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.ben.cmsc398.iLearn.Dao.DBConnector;
import edu.ben.cmsc398.iLearn.Dao.PackageDAO;
import java.util.ArrayList;
import edu.ben.cmsc398.iLearn.Model.PackageBuilder;

/**
 * Servlet implementation class ListAll
 */
@WebServlet("/ListAll")
public class ListAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListAll() {
        super();
        // TODO Auto-generated constructor stub
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
		
		ArrayList<PackageBuilder> packageList = new ArrayList<PackageBuilder>();
		DBConnector conn = new DBConnector();
		PackageDAO packages = new PackageDAO();
		
		// Get the result set which contains all id's and package names
		ResultSet rs = packages.listAllIDs(conn);
		
		
		//Populate the Array while the result set has more records.
		try {
			while ( rs.next())
			{
				PackageBuilder tempPkg = new PackageBuilder(rs.getInt("ID"), rs.getString("Name"));
				packageList.add(tempPkg);
			}
		} catch (SQLException e) {
			// Do Nothing
		}
		
		request.setAttribute("packageList", packageList);
		RequestDispatcher rd = request.getRequestDispatcher("/listID.jsp");
		rd.forward(request, response);
		
	}

}
