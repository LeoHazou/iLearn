package edu.ben.cmsc398.iLearn.Model;

import java.util.ArrayList;

public class User {
	private int iD;
	private String type;
	private String email;
	private String firstName;
	private String lastName;
	private String dateOfBirth;
	private String gender;

	private ArrayList<PackageBuilder> packageList = new ArrayList<PackageBuilder>();
	private ArrayList<PackageBuilder> shoppingCartPackageList = new ArrayList<PackageBuilder>();

	public User(int iD, String type, String email, String firstName,
			String lastName, String dateOfBirth, String gender) {
		setiD(iD);
		setType(type);
		setEmail(email);
		setFirstName(firstName);
		setLastName(lastName);
		setDateOfBirth(dateOfBirth);
		setGender(gender);
	}

	public int getiD() {
		return iD;
	}

	public void setiD(int iD) {
		this.iD = iD;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public ArrayList<PackageBuilder> getPackageList() {
		return packageList;
	}

	public void setPackageList(ArrayList<PackageBuilder> packageList) {
		this.packageList = packageList;
	}
	
	public void addPackageToPackageList(PackageBuilder packageBuilder)
	{
		this.packageList.add(packageBuilder);
	}

	public ArrayList<PackageBuilder> getShoppingCartPackageList() {
		return shoppingCartPackageList;
	}

	public void setShoppingCartPackageList(
			ArrayList<PackageBuilder> shoppingCartPackageList) {
		this.shoppingCartPackageList = shoppingCartPackageList;
	}
	
	public void addPackageToShoppingCartPackageList(PackageBuilder packageBuilder)
	{
		this.shoppingCartPackageList.add(packageBuilder);
	}

	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}

		User user = (User) obj;

		return this.getEmail() == user.getEmail()
				&& this.getFirstName() == user.getFirstName()
				&& this.getLastName() == user.getLastName();
	}

	@Override
	public String toString() {
		return "Email: " + this.getEmail() + ", First Name: "
				+ this.getFirstName() + ", Last Name: " + this.getLastName();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((this.getEmail() == null) ? 0 : this.getEmail().hashCode())
				+ ((this.getFirstName() == null) ? 0 : this.getFirstName()
						.hashCode())
				+ ((this.getLastName() == null) ? 0 : this.getLastName()
						.hashCode());
		return result;
	}
}