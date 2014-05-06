package edu.ben.cmsc398.iLearn.Model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class InputLengthValidator {
	private static final int emailMinLength = 1;
	private static final int emailMaxLength = 255;

	private static final int passwordMinLength = 1;
	private static final int passwordMaxLength = 45;

	private static final int firstNameMinLength = 1;
	private static final int firstNameMaxLength = 45;

	private static final int lastNameMinLength = 1;
	private static final int lastNameMaxLength = 45;

	private static final int dateOfBirthMinLength = 1;
	private static final int dateOfBirthMaxLength = 45;

	private static final int genderMinLength = 1;
	private static final int genderMaxLength = 45;

	private static final int pkgNameMinLength = 1;
	private static final int pkgNameMaxLength = 45;

	private static final int pkgDescriptionMinLength = 1;
	private static final int pkgDescriptionMaxLength = 255;

	private static final int numberOfAnswers = 4;
	
	private static final String randomStr = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static Random rnd = new Random();
	private static final int randomLen = 12;

	public static boolean isRegistrationInformationLengthValid(int emailLength,
			int passwordLength, int firstNameLength, int lastNameLength,
			int dateOfBirthLength, int genderLength) {
		if (isEmailLengthValid(emailLength)
				&& isPasswordLengthValid(passwordLength)
				&& isFirstNameLengthValid(firstNameLength)
				&& isLastNameLengthValid(lastNameLength)
				&& isDateOfBirthLengthValid(dateOfBirthLength)
				&& isGenderLengthValid(genderLength)) {
			return true;
		}
		return false;
	}

	public static boolean isEditProfileInformationLengthValid(
			int firstNameLength, int lastNameLength, int dateOfBirthLength,
			int genderLength) {
		if (isFirstNameLengthValid(firstNameLength)
				&& isLastNameLengthValid(lastNameLength)
				&& isDateOfBirthLengthValid(dateOfBirthLength)
				&& isGenderLengthValid(genderLength)) {
			return true;
		}
		return false;
	}

	public static boolean isEmailLengthValid(int emailLength) {
		if (emailLength >= emailMinLength && emailLength <= emailMaxLength) {
			return true;
		}
		return false;
	}

	public static boolean isPasswordLengthValid(int passwordLength) {
		if (passwordLength >= passwordMinLength
				&& passwordLength <= passwordMaxLength) {
			return true;
		}
		return false;
	}

	public static boolean isFirstNameLengthValid(int firstNameLength) {
		if (firstNameLength >= firstNameMinLength
				&& firstNameLength <= firstNameMaxLength) {
			return true;
		}
		return false;
	}

	public static boolean isLastNameLengthValid(int lastNameLength) {
		if (lastNameLength >= lastNameMinLength
				&& lastNameLength <= lastNameMaxLength) {
			return true;
		}
		return false;
	}

	public static boolean isDateOfBirthLengthValid(int dateOfBirthLength) {
		if (dateOfBirthLength >= dateOfBirthMinLength
				&& dateOfBirthLength <= dateOfBirthMaxLength) {
			return true;
		}
		return false;
	}

	public static boolean isGenderLengthValid(int genderLength) {
		if (genderLength >= genderMinLength && genderLength <= genderMaxLength) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param pkgNameLength
	 * @return true if the string length is within parameters, else returns
	 *         false.
	 */
	public static boolean isPkgNameLengthValid(int pkgNameLength) {
		if (pkgNameLength >= pkgNameMinLength
				&& pkgNameLength <= pkgNameMaxLength) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param pkgDescriptionLength
	 * @return true if the string length is within parameters, else returns
	 *         false.
	 */
	public static boolean isPkgDecriptionLengthValid(int pkgDescriptionLength) {
		if (pkgDescriptionLength >= pkgDescriptionMinLength
				&& pkgDescriptionLength <= pkgDescriptionMaxLength) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param price
	 * @return true if the number is float, otherwise false.
	 */
	public static boolean isDouble(String price) {
		try {
			@SuppressWarnings("unused")
			double priceDouble = Double.parseDouble(price);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

	/**
	 * 
	 * @param ID
	 * @return True if the string represents an integer, otherwise false.
	 */
	public static boolean isInt(String ID) {
		try {
			@SuppressWarnings("unused")
			int newID = Integer.parseInt(ID);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

	/**
	 * 
	 * @param ID
	 * @return The integer value of the string
	 */
	public static int stringToInt(String ID) {
		return Integer.parseInt(ID);
	}

	/**
	 * 
	 * @param price
	 * @return The double value of the string to 2 decimal places.
	 */
	public static double formatPrice(String price) {
		double dPrice = Double.parseDouble(price);

		DecimalFormat f = new DecimalFormat("##.00");
		return Double.parseDouble(f.format(dPrice));
	}

	public static boolean areAnswersUnique(Question question) {
		// Make an array list of answers to make iteration easier
		ArrayList<String> answers = new ArrayList<String>();
		answers.add(question.getCorrectAns());
		answers.add(question.getFirstWrongAns());
		answers.add(question.getSecondWrongAns());
		answers.add(question.getThirdWrongAns());

		for (int i = 0; i < numberOfAnswers; i++) {
			for (int j = 0; j < numberOfAnswers; j++) {
				if (i != j && answers.get(i).equals(answers.get(j))) {
					return false;
				}
			}
		}
		return true;

	}

	/**
	 * 
	 * @param len
	 * @return Random String to be used for password resets.
	 */
	public static String randomString( ) 
	{
	   StringBuilder sb = new StringBuilder( randomLen );
	   for( int i = 0; i < randomLen; i++ ) 
	      sb.append( randomStr.charAt( rnd.nextInt(randomStr.length()) ) );
	   return sb.toString();
	}
}