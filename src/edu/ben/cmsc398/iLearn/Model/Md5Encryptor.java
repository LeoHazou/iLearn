package edu.ben.cmsc398.iLearn.Model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Leo The Will Use MD5 To One-Way Encrypt The Password.
 * 
 */
public class Md5Encryptor {

	/**
	 * Default constructor
	 */
	public Md5Encryptor() {
	}

	/**
	 * 
	 * @param passwordToHash
	 * @return The Hashed Password If Successful Or Empty String If It Failed.
	 */
	public static String encryptPass(String passwordToHash) {
		String generatedPassword = "";
		// Create MessageDigest Instance For MD5 And Add Bytes To Digest
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(passwordToHash.getBytes());
			// Get The Hash's Bytes And Convert To Hexadecimal
			byte[] bytes = messageDigest.digest();
			StringBuilder stringBuilder = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				stringBuilder.append(Integer.toString(
						(bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			// Convert To String
			generatedPassword = stringBuilder.toString();
		} catch (NoSuchAlgorithmException e) {
			// Enable this line for testing and debugging only.
			System.out.println("Error: Could Not Hash Password");
		}
		// Return the encrypted password
		return generatedPassword;
	}
}