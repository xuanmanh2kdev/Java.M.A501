package fa.training.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

	private static final String VALID_PHONE_REGEX = "0\\d{9}";
	private static final String VALID_ORDER_NUMBER_REGEX = "\\d{10}";

	public static boolean isName(String name) {
		if (name == null || name == "") {
			return false;
		}
		return true;
	}

	public static boolean isPhone(String phone) {
		Pattern pattern = Pattern.compile(VALID_PHONE_REGEX);
		Matcher matcher = pattern.matcher(phone);
		return matcher.matches();
	}

	public static boolean isAddress(String address) {
		if (address == "") {
			return false;
		}
		return true;
	}

	public static boolean isNumber(String number) {
		if (number == "") {
			return false;
		}
		try {
			Pattern pattern = Pattern.compile(VALID_ORDER_NUMBER_REGEX);
			Matcher matcher = pattern.matcher(number);
			return matcher.matches();
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
