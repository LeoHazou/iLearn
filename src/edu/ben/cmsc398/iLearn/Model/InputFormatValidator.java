package edu.ben.cmsc398.iLearn.Model;

public class InputFormatValidator {
	public static boolean isRegistrationInformationFormatValid(String email,
			String password, String firstName, String lastName,
			String dateOfBirth, String gender) {
		return true;
	}

	public static boolean isEditProfileInformationFormatValid(
			String firstName, String lastName, String dateOfBirth, String gender) {
		return true;
	}
}
