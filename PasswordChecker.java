import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

/**
 * This program is a simple password checker that prints a string representing the strength
 * of the given password. Strength is based on 3 variables: Length, usage of symbols, and usage of numbers.
 * A very strong password will consist of 14 or more characters and contain at least 1 symbol and 1 number. 
 * 
 * @author Amanda Stofey
 * @version February 11th, 2025
 */
public class PasswordChecker {
	
	/**
	 * This is the main program that asks the user for a password, then
	 * calls the checkPassword method on the given password. It then prints
	 * the result given by the checkPassword method.
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the password to check it's strength:");
		String password = scanner.nextLine();
		System.out.println(checkPassword(password));
		scanner.close();
	}
	
	/**
	 * This method determines the strength of a given password. It decides the strength based on length,
	 * usage of symbols, and usage of numbers. A Very strong password will contain 14 or more characters and
	 * at least 1 symbol and 1 number. Smaller passwords are considered weaker. Any password 7 characters or 
	 * less is considered very weak.
	 * 
	 * This method of checking strength is loosely based on the guidelines found at:
	 * https://bitwarden.com/blog/how-long-should-my-password-be/
	 * 
	 * @param password
	 * @return
	 */
	public static String checkPassword(String password) {
		String[] possibleStrengths = {"Very Weak", "Weak", "Moderate", "Strong", "Very Strong"};
		int passwordScore = 0;
		Pattern symbolPattern = Pattern.compile("/[^\\p{L}\\d\\s@#]/u");
		Pattern numberPattern = Pattern.compile("[0-9]");
		Matcher symbols = symbolPattern.matcher(password);
		Matcher nums = numberPattern.matcher(password);
		
		//if password contains at least 1 symbol and 1 number, increase score by 1
		if(symbols.find()&& nums.find()) { 
			passwordScore++;
		}
		
		//determine score based on length of password. Longer = stronger.
		if(password.length() >= 14) {
			passwordScore += 3;
		}
		else if(password.length() >= 11) {
			passwordScore += 2;
		}
		else if(password.length() >= 8) {
			passwordScore += 1;
		}
		//if password is 7 characters or less, it is very weak.
		else {
			passwordScore = 0;
		}
		
		return possibleStrengths[passwordScore];
	}

}
