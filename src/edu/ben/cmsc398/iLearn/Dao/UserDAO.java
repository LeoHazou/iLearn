package edu.ben.cmsc398.iLearn.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.mysql.jdbc.Connection;

import edu.ben.cmsc398.iLearn.Model.Md5Encryptor;
import edu.ben.cmsc398.iLearn.Model.User;

public class UserDAO {

	// Private Variables
	private DBConnector dbcon;
	private Connection con;
	private String tableName = "user";

	// Constructor, Gets Database Connection
	public UserDAO() {
		dbcon = new DBConnector();
		con = (Connection) dbcon.getConnection();
	}

	// Close Connection
	public void closeConnection() {
		try {
			con.close();
			dbcon.closeConnection();
		} catch (SQLException e) {
			System.out.println("Error!");
		}
	}

	public Boolean existsInTable(String tableName, String columnName,
			String value) {
		String sql = null;
		PreparedStatement ps = null;

		try {
			sql = "select * from " + tableName + " where " + columnName
					+ " = ?;";
			ps = con.prepareStatement(sql);
			ps.setString(1, value);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			}
		} catch (Exception exception) {
			System.out
					.println("Error In existsInTable Function When Checking If The "
							+ value
							+ " Value Exists In The "
							+ columnName
							+ " Column Of The " + tableName + " Table!");
		}
		return false;
	}

	public void addUser(String type, String email, String password,
			String firstName, String lastName, String dateOfBirth, String gender) {

		String sql = null;
		PreparedStatement ps = null;

		try {
			sql = "INSERT INTO User "
					+ "(Type, Email, Password, First_Name, Last_Name, Date_Of_Birth, Gender) VALUES (?, ?, ?, ?, ?, ?, ?);";
			ps = con.prepareStatement(sql);
			ps.setString(1, type);
			ps.setString(2, email);
			ps.setString(3, Md5Encryptor.encryptPass(password));
			ps.setString(4, firstName);
			ps.setString(5, lastName);
			ps.setString(6, dateOfBirth);
			ps.setString(7, gender);
			ps.execute();
		} catch (Exception exception) {
			exception.printStackTrace();
			System.out.println("Error In addUser Function!");
		}
	}
	
	

	public void editUserFirstName(String email, String firstName) {
		String sql = null;
		PreparedStatement ps = null;

		try {
			sql = "UPDATE User Set "
					+ "First_Name=? Where Email=?;";
			ps = con.prepareStatement(sql);

			ps.setString(1, firstName);
			ps.setString(2, email);

			ps.execute();
		} catch (Exception exception) {
			exception.printStackTrace();
			System.out.println("Error In editUserFirstName Function!");
		}
	}
	
	public void editUserLastName(String email, String lastName) {
		String sql = null;
		PreparedStatement ps = null;

		try {
			sql = "UPDATE User Set "
					+ "Last_Name=? Where Email=?;";
			ps = con.prepareStatement(sql);

			ps.setString(1, lastName);
			ps.setString(2, email);

			ps.execute();
		} catch (Exception exception) {
			exception.printStackTrace();
			System.out.println("Error In editUserLastName Function!");
		}
	}
	
	public void editUserDateOfBirth(String email, String dateOfBirth) {
		String sql = null;
		PreparedStatement ps = null;

		try {
			sql = "UPDATE User Set "
					+ "Date_Of_Birth=? Where Email=?;";
			ps = con.prepareStatement(sql);

			ps.setString(1, dateOfBirth);
			ps.setString(2, email);

			ps.execute();
		} catch (Exception exception) {
			exception.printStackTrace();
			System.out.println("Error In editUserLastName Function!");
		}
	}
	
	public void editUserGender(String email, String gender) {
		String sql = null;
		PreparedStatement ps = null;

		try {
			sql = "UPDATE User Set "
					+ "Gender=? Where Email=?;";
			ps = con.prepareStatement(sql);

			ps.setString(1, gender);
			ps.setString(2, email);

			ps.execute();
		} catch (Exception exception) {
			exception.printStackTrace();
			System.out.println("Error In editUserLastName Function!");
		}
	}
	
	public void editUserPassword(String email, String password) {
		String sql = null;
		PreparedStatement ps = null;

		try {
			sql = "UPDATE User Set "
					+ "Password=? Where Email=?;";
			ps = con.prepareStatement(sql);

			ps.setString(1, Md5Encryptor.encryptPass(password));
			ps.setString(2, email);

			ps.execute();
		} catch (Exception exception) {
			exception.printStackTrace();
			System.out.println("Error In editUserPassword Function!");
		}
	}
	
	

	public boolean verifyUser(String email, String password) {
		String sql = null;
		PreparedStatement ps = null;

		try {
			sql = "SELECT * FROM User WHERE Email = ? AND Password = ?;";
			ps = con.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, Md5Encryptor.encryptPass(password));
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			}
		} catch (Exception exception) {
			System.out.println("Error In verifyUser Function!");
		}
		return false;
	}

	public User getUser(String email) {
		String sql = null;
		PreparedStatement ps = null;

		User user = null;

		try {
			sql = "SELECT * FROM User WHERE Email = ?;";
			ps = con.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				user = new User(rs.getInt("ID"), rs.getString("Type"),
						rs.getString("Email"), rs.getString("First_Name"),
						rs.getString("Last_Name"),
						rs.getString("Date_Of_Birth"), rs.getString("Gender"));
			}

		} catch (Exception exception) {
			System.out.println("Error In getUser Function!");
		}
		return user;
	}
	
	
	public User getUser(int id) {
		String sql = null;
		PreparedStatement ps = null;

		User user = null;

		try {
			sql = "SELECT * FROM User WHERE id = ?;";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				user = new User(rs.getInt("ID"), rs.getString("Type"),
						rs.getString("Email"), rs.getString("First_Name"),
						rs.getString("Last_Name"),
						rs.getString("Date_Of_Birth"), rs.getString("Gender"));
			}

		} catch (Exception exception) {
			System.out.println("Error In getUser Function!");
		}
		return user;
	}
	public ArrayList<User> getUserByLastName(String lastName)
	{
		String sql = null;
		PreparedStatement ps = null;
		ArrayList<User> users = new ArrayList<User>();
		User user = null;

		try {
			sql = "SELECT * FROM User WHERE Last_Name = ? and Type = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, lastName);
			ps.setString(2, "User");
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				user = new User(rs.getInt("ID"), rs.getString("Type"),
						rs.getString("Email"), rs.getString("First_Name"),
						rs.getString("Last_Name"),
						rs.getString("Date_Of_Birth"), rs.getString("Gender"));
				users.add(user);
			}

		} catch (Exception exception) {
			System.out.println("Error In getUser by email Function!");
		}
		return users;
	}
	
	/**
	 * 
	 * @return ArrayList of all super admins and admins.
	 */
	public ArrayList<User> getAdmins()
	{
		ArrayList<User> users = new ArrayList<User>();
		
		try {
			// First get all super admins
			String sql = "select * from " + tableName + " where Type = 'SuperAdministrator' union select * from " + tableName + " where Type = 'Administrator' order by Last_Name;";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while ( rs.next())
			{
				User user = new User(rs.getInt("ID"), rs.getString("Type"), rs.getString("Email"),rs.getString("First_Name"),rs.getString("Last_Name"),rs.getString("Date_Of_Birth"),rs.getString("Gender"));
				users.add(user);
			}
			
			
		} 
		catch (SQLException e) {
			System.out.println("Error retrieving Administrator group.");
			e.printStackTrace();
		}
		return users;
	}
	
	/**
	 * Deletes the user with the current ID
	 * @param ID
	 */
	public boolean deleteUser(int ID)
	{
		
		try {
			String sql = "delete from "+ tableName+ " where ID = +"+ID+";";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	/**
	 * 
	 * @param ID
	 * @param firstName
	 */
	public void editUserFirstNameByID(int ID, String firstName) {
		String sql = null;
		PreparedStatement ps = null;

		try {
			sql = "UPDATE User Set "
					+ "First_Name=? Where ID=?;";
			ps = con.prepareStatement(sql);

			ps.setString(1, firstName);
			ps.setInt(2, ID);

			ps.execute();
		} catch (Exception exception) {
			exception.printStackTrace();
			System.out.println("Error In editUserFirstNameByID Function!");
		}
	}
	/**
	 * 
	 * @param ID
	 * @param lastName
	 */
	public void editUserLastNameByID(int ID, String lastName) {
		String sql = null;
		PreparedStatement ps = null;

		try {
			sql = "UPDATE User Set "
					+ "Last_Name=? Where ID=?;";
			ps = con.prepareStatement(sql);

			ps.setString(1, lastName);
			ps.setInt(2, ID);

			ps.execute();
		} catch (Exception exception) {
			exception.printStackTrace();
			System.out.println("Error In edit user last name by id Function!");
		}
	}
	/**
	 * 
	 * @param ID
	 * @param dateOfBirth
	 */
	public void editUserDateOfBirthByID(int ID, String dateOfBirth) {
		String sql = null;
		PreparedStatement ps = null;

		try {
			sql = "UPDATE User Set "
					+ "Date_Of_Birth=? Where ID=?;";
			ps = con.prepareStatement(sql);

			ps.setString(1, dateOfBirth);
			ps.setInt(2, ID);

			ps.execute();
		} catch (Exception exception) {
			exception.printStackTrace();
			System.out.println("Error In edit user dob by id Function!");
		}
	}
	/**
	 * 
	 * @param ID
	 * @param gender
	 */
	public void editUserGenderByID(int ID, String gender) {
		String sql = null;
		PreparedStatement ps = null;

		try {
			sql = "UPDATE User Set "
					+ "Gender=? Where ID=?;";
			ps = con.prepareStatement(sql);

			ps.setString(1, gender);
			ps.setInt(2, ID);

			ps.execute();
		} catch (Exception exception) {
			exception.printStackTrace();
			System.out.println("Error In edit user gender by id Function!");
		}
	}
	/**
	 * 
	 * @param ID
	 * @param gender
	 */
	public void editUserTypeByID(int ID, String type) {
		String sql = null;
		PreparedStatement ps = null;

		try {
			sql = "UPDATE User Set "
					+ "Type=? Where ID=?;";
			ps = con.prepareStatement(sql);

			ps.setString(1, type);
			ps.setInt(2, ID);

			ps.execute();
		} catch (Exception exception) {
			exception.printStackTrace();
			System.out.println("Error In edit user type by id Function!");
		}
	}
	/**
	 * 
	 * @param ID
	 * @param gender
	 */
	public void editUserEmailByID(int ID, String email) {
		String sql = null;
		PreparedStatement ps = null;

		try {
			sql = "UPDATE User Set "
					+ "Email=? Where ID=?;";
			ps = con.prepareStatement(sql);

			ps.setString(1, email);
			ps.setInt(2, ID);

			ps.execute();
		} catch (Exception exception) {
			exception.printStackTrace();
			System.out.println("Error In edit user email by id Function!");
		}
	}
	
	/**
	 * Updates the database with a temporary reset password
	 * @param ID
	 * @param password
	 */
	public void resetPass(int ID, String password)
	{
		String sql = null;
		PreparedStatement ps = null;
		

		try {
			sql = "UPDATE User Set "
					+ "Password=? Where ID=?;";
			ps = con.prepareStatement(sql);

			ps.setString(1, password);
			ps.setInt(2, ID);

			ps.execute();
		} catch (Exception exception) {
			exception.printStackTrace();
			System.out.println("Error In reset pass Function!");
		}
	}
	
}