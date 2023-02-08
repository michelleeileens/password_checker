/*
 * Class: CMSC204
 * Instructor: Prof. Khandan Monshi
	 * Description: Password Checker
	 * Due: 2/7/2023
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming 
 * assignment independently. I have not copied the code 
 * from a student or any source. I have not given my code 
 * to any student.
   Print your Name here: Michelle Eileen
*/
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class PasswordCheckerUtility {

	
	public PasswordCheckerUtility() {
	}
	
	public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException {
		if (!comparePasswordsWithReturn(password, passwordConfirm)){
			throw new UnmatchedException();
		}
	}
	
	public static boolean comparePasswordsWithReturn(String password, String passwordConfirm) {
		if (password.equals(passwordConfirm)) {
			return true;
			}
			return false;
	}
	
	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) {
		ArrayList<String> pass = new ArrayList<String>();
		String invalidpass = null;
		for (int i = 0; i < passwords.size() ; i++) {	
			try {
				invalidpass = passwords.get(i);
				if (!isValidPassword(invalidpass)) {
					invalidpass = invalidpass + "";
				}
			}
			catch (Exception e) {
				pass.add(invalidpass + " " + e.getMessage());
			}
		}
		return pass;	
	}
	
	public static boolean hasBetweenSixAndNineChars(String password) {
		return false;
	}
	
	public static boolean hasDigit(String password) throws NoDigitException {
		char[] pass = password.toCharArray();
		int count = 0;
		for (int i = 0; i < pass.length; i++) {
			if (Character.isDigit(pass[i])) {
				count++;
			}
		}
		if (count == 0) {
			throw new NoDigitException();
		}
		else {
			return true;
		}
	}
	public static boolean hasLowerAlpha(String password) throws NoLowerAlphaException {

		if (password.equals(password.toUpperCase())) 
			throw new NoLowerAlphaException();
		else
			return true;
	}
	
	public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException{
		String reg = "[a-zA-Z0-9]*";
		Pattern pat = Pattern.compile(reg);
		Matcher mat = pat.matcher(password);
		
		if(mat.matches()) {
			throw new NoSpecialCharacterException();
		}
		else {
			return true;
		}
	}
	
	public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException {
		if (password.equals(password.toLowerCase())) 
			throw new NoUpperAlphaException();
		else
			return true;
	}
	
	public static boolean isValidLength(String password) throws LengthException {
		if (password.length() < 6) 
			throw new LengthException();
		else
			return true;
	}
	
	public static boolean isValidPassword(String password) throws LengthException, NoUpperAlphaException, 
	NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException {
		boolean length = false , upper = false, lower = false, digit = false, sym = false, seq = false, 
				val = true;	
		try {
			length = isValidLength(password);
			upper = hasUpperAlpha(password);
			lower = hasLowerAlpha(password);
			digit = hasDigit(password);
			sym = hasSpecialChar(password);
			seq = noSameCharInSequence(password);
		}
		finally {
			if (length == true && upper == true && lower == true && digit == true && sym == true && 
					seq == true) {
				val = true;
			}
			else {
				val = false;
			}
		}
		return val;
	}
	
	public static boolean isWeakPassword(String password) throws WeakPasswordException {		
		boolean isWeak = false;
		if (password.length() >= 6 && password.length() <= 9) {
			isWeak = true;
			throw new WeakPasswordException();
		}
		
		return isWeak;
	}
	
	public static boolean noSameCharInSequence(String password) throws InvalidSequenceException {
		boolean isValid=true;
		for (int i = 0 ; i <= password.length() - 2; i++) { 
			if (password.charAt(i) == password.charAt(i+1)) {
				if (password.charAt(i) == password.charAt(i+2)) {
					isValid = false;
					throw new InvalidSequenceException();
				}
				
			}
		}
		return isValid;
	}


	
	
	
	
}
