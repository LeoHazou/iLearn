package edu.ben.cmsc398.iLearn.Model;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

public class CaptchaValidator {
	private static final String privateKey = "6LcYQPASAAAAACVEM0-ImH7o6zGT-Z9Na9ptfBX2";

	public CaptchaValidator() {

	}

	public static boolean isCaptchaValid(String remoteAddress,
			String challenge, String response) {
		ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
		reCaptcha.setPrivateKey(privateKey);
		ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(
				remoteAddress, challenge, response);

		if (reCaptchaResponse.isValid()) {
			return true;
		}
		return false;
	}
}
