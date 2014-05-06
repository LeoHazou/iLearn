/**
 * 
 */
package edu.ben.cmsc398.iLearn.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.ben.cmsc398.iLearn.Model.PackageBuilder;
import edu.ben.cmsc398.iLearn.Model.Question;

/**
 * @author Leo
 *
 */
public class QuestiondDAO
{

	/**
	 * 
	 */
	public QuestiondDAO() {}
	
	// Private variable for the table name. Used to make it easier when changing the table name
	private final String tableName = "question";

	
	/**
	 * Add questions to the database for the add package module
	 * @param conn
	 * @param pkg
	 */
	public void addQuestions(DBConnector conn, PackageBuilder pkg)
	{
		try 
		{
			// Go through the arraylist of questions objects and add each parameter
			for (int i = 0; i < pkg.getQuestions().size(); i++)
			{
			String sql = "INSERT INTO " + tableName + "(Pkg_ID, Question, Correct_Answer, Wrong_Answer1, Wrong_Answer2, Wrong_Answer3, Explanation) VALUES (?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement ps = conn.SetprepareStatement(sql);
	
			ps.setInt(1, pkg.getPkgID());
			ps.setString(2, pkg.getQuestions().get(i).getQuestion());
			ps.setString(3,pkg.getQuestions().get(i).getCorrectAns());
			ps.setString(4, pkg.getQuestions().get(i).getFirstWrongAns());
			ps.setString(5, pkg.getQuestions().get(i).getSecondWrongAns());
			ps.setString(6, pkg.getQuestions().get(i).getThirdWrongAns());
			ps.setString(7, pkg.getQuestions().get(i).getExplanation());
			ps.execute();
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *  Adds one question from the edit package module for adding questions.
	 * @param conn
	 * @param pkgID
	 * @param question
	 */
	public void addQuestion(DBConnector conn, int pkgID, Question question)
	{
		try 
		{
			
			String sql = "INSERT INTO " + tableName + "(Pkg_ID, Question, Correct_Answer, Wrong_Answer1, Wrong_Answer2, Wrong_Answer3, Explanation) VALUES (?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement ps = conn.SetprepareStatement(sql);
	
			ps.setInt(1, pkgID);
			ps.setString(2, question.getQuestion());
			ps.setString(3 ,question.getCorrectAns());
			ps.setString(4, question.getFirstWrongAns());
			ps.setString(5, question.getSecondWrongAns());
			ps.setString(6, question.getThirdWrongAns());
			ps.setString(7, question.getExplanation());
			ps.execute();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param Pkg_ID
	 * @param conn
	 * @return ArrayList of type Questions for the given package id; not the question id!
	 */
	public ArrayList<Question> getQuestions(int Pkg_ID, DBConnector conn)
	{
		ArrayList<Question> questions = new ArrayList<Question>();
		String sql = "select * from " + tableName + " where Pkg_ID =" + Pkg_ID + ";";
		PreparedStatement ps;	
		try {
			ps = conn.SetprepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				Question temp = new Question();
				temp.setID(rs.getInt("ID"));
				temp.setQuestion(rs.getString("Question"));
				temp.setCorrectAns(rs.getString("Correct_Answer"));
				temp.setFirstWrongAns(rs.getString("Wrong_Answer1"));
				temp.setSecondWrongAns(rs.getString("Wrong_Answer2"));
				temp.setThirdWrongAns(rs.getString("Wrong_Answer3"));
				temp.setExplanation(rs.getString("Explanation"));
				questions.add(temp);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return questions;
	}
	
	/**
	 * 
	 * @param ID
	 * @param conn
	 */
	public void deleteQuestion(int ID, DBConnector conn)
	{
		String sql = "DELETE FROM " + tableName + " WHERE ID=" +ID+";";
		PreparedStatement ps;
		try {
			ps = conn.SetprepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 
	 * @param ID
	 * @param conn
	 * @return A question object
	 */
	public Question getQuestion(int ID, DBConnector conn)
	{
		Question temp = new Question();
		String sql = "select * from " + tableName + " where ID="+ID+";";
		PreparedStatement ps;
		try {
			ps = conn.SetprepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				temp.setID(rs.getInt("ID"));
				temp.setQuestion(rs.getString("Question"));
				temp.setCorrectAns(rs.getString("Correct_Answer"));
				temp.setFirstWrongAns(rs.getString("Wrong_Answer1"));
				temp.setSecondWrongAns(rs.getString("Wrong_Answer2"));
				temp.setThirdWrongAns(rs.getString("Wrong_Answer3"));
				temp.setExplanation(rs.getString("Explanation"));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return temp;
		
		
	}
	
	/**
	 * 
	 * @param question
	 * @param conn
	 */
	public void updateQuestion(Question question, DBConnector conn)
	{
		
		try {
			String sql = "UPDATE "+tableName+ " SET `Question`='"+question.getQuestion()+"', `Correct_Answer`='"+question.getCorrectAns()+"', `Wrong_Answer1`='"+question.getFirstWrongAns()+"', `Wrong_Answer2`='"+question.getSecondWrongAns()+"', `Wrong_Answer3`='"+question.getThirdWrongAns()+"', `Explanation`='"+question.getExplanation()+"' WHERE `ID`="+question.getID()+";";
			PreparedStatement ps;
			ps=conn.SetprepareStatement(sql);
			ps.executeUpdate();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	}

	}
