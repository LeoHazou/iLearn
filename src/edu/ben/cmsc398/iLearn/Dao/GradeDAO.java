/**
 * 
 */
package edu.ben.cmsc398.iLearn.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Leo
 *
 */
public class GradeDAO {

	/**
	 * 
	 */
	public GradeDAO() {}
		
	private  String tableName = "user_grade";
	
	public ArrayList<Float> getGrades(int uid, int pid, DBConnector conn)
	{
		ArrayList<Float> grades = new ArrayList<Float>();
		try {
			System.out.println(uid+pid);
			String sql = "select Grade from "+ tableName+" where User_ID ="+ uid +" and Package_ID = "+pid+"; ";
			PreparedStatement ps = conn.SetprepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
			 	grades.add(rs.getFloat("Grade"));
			}
		} 
		catch (SQLException e) {
			System.out.println("Error in getGrades function");
			e.printStackTrace();
		}
		return grades;
	}
	

}
