package edu.ben.cmsc398.iLearn.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.ben.cmsc398.iLearn.Model.PackageBuilder;

/**
 * @author Leo
 * 
 */
public class PackageDAO {
	// Private variable for the table name. Used to make it easier when changing
	// the table name
	private final String tableName = "package";

	public PackageDAO() {
	}

	/**
	 * 
	 * @param pkgName
	 * @return true if the package name exists in the database, otherwise
	 *         returns false.
	 */
	public Boolean NameexistsInTable(String pkgName, DBConnector conn) {
		String sql = "select * from " + tableName + " where Name =" + pkgName
				+ ";";
		PreparedStatement ps;
		try {
			ps = conn.SetprepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		}

		catch (SQLException e) {
			return false;
		}

	}

	/**
	 * 
	 * @param ID
	 * @param conn
	 * @return True if the package ID exists, false if it does not exist.
	 */
	public Boolean IDexistsInTable(int ID, DBConnector conn) {
		String sql = "select * from " + tableName + " where ID =" + ID + ";";
		PreparedStatement ps;
		try {
			ps = conn.SetprepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		}

		catch (SQLException e) {
			return false;
		}

	}

	/**
	 * 
	 * @param userID
	 * @param packageID
	 * @param conn
	 * @return True if the package ID exists, false if it does not exist.
	 */
	public Boolean userHasPackage(int userID, int packageID, DBConnector conn) {
		String sql = "select * from User_Has_Package"
				+ " where User_ID = ? AND Package_ID = ?;";
		PreparedStatement ps;
		try {
			ps = conn.SetprepareStatement(sql);
			ps.setInt(1, userID);
			ps.setInt(2, packageID);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		}

		catch (SQLException e) {
			return false;
		}
	}

	/**
	 * 
	 * @param pkg
	 * @param conn
	 *            Adds a package to the database.
	 */
	public void addPkg(PackageBuilder pkg, DBConnector conn) {
		try {
			String sql = "INSERT INTO "
					+ tableName
					+ "(Name, Description, Price, Study_Material, Study_Material_Link, Number_Of_Questions, Published) VALUES (?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement ps = conn.SetprepareStatement(sql);
			ps.setString(1, pkg.getPkgName());
			ps.setString(2, pkg.getPkgDescription());
			ps.setDouble(3, pkg.getPkgPrice());
			ps.setBoolean(4, pkg.isStudyMaterial());
			ps.setString(5, pkg.getStudyMaterialLink());
			ps.setInt(6, pkg.getNumberOfQuestions());
			ps.setBoolean(7, pkg.isPublished());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param conn
	 *            Adds a User Grade To The Database
	 */
	public void addUserPackageGrade(int userID, int packageID, float grade,
			DBConnector conn) {
		try {
			String sql = "INSERT INTO User_Grade "
					+ "(User_ID, Package_ID, Grade) VALUES (?, ?, ?);";
			PreparedStatement ps = conn.SetprepareStatement(sql);
			ps.setInt(1, userID);
			ps.setInt(2, packageID);
			ps.setFloat(3, grade);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param pkg
	 * @param conn
	 * @return the Grades of a User in a specific package
	 */
	public ArrayList<Float> getUserPackageGrades(int userID, int packageID, DBConnector conn) {
		ArrayList<Float> gradeList = new ArrayList<Float>();
		try {
			String sql = "Select Grade from User_Grade where User_ID = ? AND Package_ID = ?;";
			PreparedStatement ps;

			ps = conn.SetprepareStatement(sql);
			ps.setInt(1, userID);
			ps.setInt(2, packageID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				gradeList.add(rs.getFloat("Grade"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return gradeList;
		}
		return gradeList;
	}

	/**
	 * 
	 * @param pkg
	 * @param conn
	 *            Adds a package to the database.
	 */
	public void addUserPackage(int userID, int packageID, DBConnector conn) {
		try {
			String sql = "INSERT INTO User_Has_Package"
					+ " (User_ID, Package_ID) VALUES (?, ?);";
			PreparedStatement ps = conn.SetprepareStatement(sql);
			ps.setInt(1, userID);
			ps.setInt(2, packageID);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param pkg
	 * @param conn
	 * @return the ID of the package from the database
	 */
	public int getPkgId(PackageBuilder pkg, DBConnector conn) {
		try {
			String sql = "select ID from " + tableName + " where Name =\""
					+ pkg.getPkgName() + "\"";
			PreparedStatement ps;

			ps = conn.SetprepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt("ID");
			} else
				return 0;

		} catch (SQLException e) {

			return -1;
		}

	}

	/**
	 * 
	 * @param conn
	 * @return the ResultSet from the query.
	 */
	public ResultSet listAllIDs(DBConnector conn) {
		String sql = "SELECT ID,Name from " + tableName + ";";
		PreparedStatement ps;

		try {
			ps = conn.SetprepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			return rs;

		} catch (SQLException e) {
			return null;
		}

	}

	/**
	 * 
	 * @param conn
	 * @return the ResultSet from the query.
	 */
	public ResultSet listAll(DBConnector conn) {
		String sql = "SELECT * from " + tableName + ";";
		PreparedStatement ps;

		try {
			ps = conn.SetprepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			return rs;

		} catch (SQLException e) {
			return null;
		}

	}

	/**
	 * 
	 * @param conn
	 * @param email
	 * @return ArrayList of packages
	 */
	public ArrayList<PackageBuilder> getAllUserPackages(DBConnector conn,
			String email) {
		ArrayList<PackageBuilder> packageList = new ArrayList<PackageBuilder>();

		String sql = "SELECT Package.ID, Package.Name, Package.Description, Package.Price, Package.Study_Material, Package.Study_Material_Link"
				+ " from Package, User, User_Has_Package"
				+ " where User.Email = ? AND User_Has_Package.User_ID = User.ID AND User_Has_Package.Package_ID = Package.ID;";
		PreparedStatement ps;

		try {
			ps = conn.SetprepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				PackageBuilder tempPkg = new PackageBuilder(rs.getInt("ID"),
						rs.getString("Name"));

				tempPkg.setPkgDescription(rs.getString("Description"));
				tempPkg.setPkgPrice(rs.getDouble("Price"));
				tempPkg.setStudyMaterial(rs.getBoolean("Study_Material"));
				tempPkg.setStudyMaterialLink(rs.getString("Study_Material_Link"));

				packageList.add(tempPkg);
			}
		} catch (SQLException e) {
			System.out.println("Error in getAllUserPackages function!");
		}

		return packageList;
	}

	/**
	 * 
	 * @param ID
	 * @param conn
	 * @return PackageBuilder object populated with the given package ID
	 *         elements.
	 */
	public PackageBuilder getPkg(int ID, DBConnector conn) {
		PackageBuilder pkg = new PackageBuilder(ID);
		String sql = "select * from " + tableName + " where ID =" + ID + ";";
		PreparedStatement ps;
		try {
			ps = conn.SetprepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pkg.setPkgName(rs.getString("Name"));
				pkg.setPkgDescription(rs.getString("Description"));
				pkg.setPkgPrice(rs.getDouble("Price"));
				pkg.setStudyMaterial(rs.getBoolean("Study_Material"));
				pkg.setStudyMaterialLink(rs.getString("Study_Material_Link"));
				pkg.setPublished(rs.getBoolean("Published"));
			}

		} catch (SQLException e) {

		}

		return pkg;
	}

	/**
	 * Updates the package properties
	 * 
	 * @param pkg
	 * @param conn
	 */
	public void updatePkgProperties(PackageBuilder pkg, DBConnector conn) {
		try {
			String sql = "UPDATE " + tableName + " SET Name=\""
					+ pkg.getPkgName() + "\" , Description=\""
					+ pkg.getPkgDescription() + "\" , Price="
					+ pkg.getPkgPrice() + " WHERE ID=" + pkg.getPkgID() + ";";
			PreparedStatement ps;
			ps = conn.SetprepareStatement(sql);
			ps.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param num
	 * @param ID
	 * @param conn
	 */
	public void updateNumberOfQuestions(int num, int ID, DBConnector conn) {
		try {
			String sql = "UPDATE " + tableName + " SET Number_Of_Questions=\""
					+ num + "\"  WHERE ID=" + ID + ";";
			PreparedStatement ps;
			ps = conn.SetprepareStatement(sql);
			ps.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param ID
	 * @param status
	 * @param conn
	 */
	public void updatedPublished(int ID, boolean status, DBConnector conn) {
		String sql = "UPDATE " + tableName + " SET Published=" + status
				+ " WHERE ID=" + ID + ";";

		try {
			PreparedStatement ps = conn.SetprepareStatement(sql);
			ps.executeUpdate();
		}

		catch (SQLException e) {

			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param ID
	 * @param status
	 * @param conn
	 * @param path
	 */
	public void updateLink(int ID, boolean status, DBConnector conn, String path) {
		path = path.replace("\\", "\\\\");
		String sql = "UPDATE " + tableName + " SET Study_Material= " + status
				+ " , Study_Material_Link='" + path + "' WHERE ID=" + ID + ";";
		
		try {
			PreparedStatement ps = conn.SetprepareStatement(sql);
			ps.executeUpdate();
		}

		catch (SQLException e) {

			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param ID
	 * @param num
	 * @param conn
	 */
	public void updateQuestionCount(int ID, int num, DBConnector conn) {
		String sql = "UPDATE " + tableName + " SET Number_Of_Questions= " + num
				+ "  WHERE ID=" + ID + ";";

		try {
			PreparedStatement ps = conn.SetprepareStatement(sql);
			ps.executeUpdate();
		}

		catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
