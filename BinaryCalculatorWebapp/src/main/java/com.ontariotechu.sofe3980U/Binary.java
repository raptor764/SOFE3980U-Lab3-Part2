package com.ontariotechu.sofe3980U;

/**
 * Unsigned integer Binary variable
 * This class represents a binary number and provides methods for binary arithmetic operations.
 */
public class Binary
{
	private String number = "0";  // string containing the binary value '0' or '1'
	
	/**
	 * Constructor that initializes the binary number with a given string.
	 * If the input string contains invalid characters, the number is set to "0".
	 * Leading zeros are removed unless the number is zero itself.
	 *
	 * @param number The string representation of the binary number.
	 */
	public Binary(String number) {
		// Handle null or empty string input by setting the number to "0"
		if (number == null || number.isEmpty()) {
			this.number = "0"; // Default to "0" for null or empty input
			return;
		}
	
		// Check if the string contains only '0' or '1' characters
		for (int i = 0; i < number.length(); i++) {
			char ch = number.charAt(i);
			if (ch != '0' && ch != '1') {
				this.number = "0"; // Invalid binary, set number to "0"
				return;
			}
		}
	
		// Remove leading zeros
		int beg;
		for (beg = 0; beg < number.length(); beg++) {
			if (number.charAt(beg) != '0') {
				break;
			}
		}
		this.number = (beg == number.length()) ? "0" : number.substring(beg);
	
		// If the resulting number is empty, set it to "0"
		if (this.number.isEmpty()) {
			this.number = "0";
		}
  	}
	
	/**
	 * Returns the string representation of the binary number.
	 *
	 * @return The binary number as a string.
	 */
	public String getValue()
	{
		return this.number;
	}
	
	/**
	 * Utility function to equalize the lengths of two binary numbers by padding the shorter one with leading zeros.
	 *
	 * @param binary The binary string to be padded.
	 * @param length The target length for the binary string.
	 * @return A new string representing the binary number with leading zeros added.
	 */
	private static String equalize(String binary, int length) {
		StringBuilder padded = new StringBuilder(binary);
		// Add leading zeros until the binary number reaches the target length
		while (padded.length() < length) {
			padded.insert(0, '0');
		}
		return padded.toString();
	}

	/**
	 * Adds two Binary numbers and returns the result as a new Binary object.
	 * Performs binary addition with carry.
	 *
	 * @param num1 The first binary number.
	 * @param num2 The second binary number.
	 * @return The sum of the two binary numbers.
	 */
	public static Binary add(Binary num1, Binary num2)
	{
		int ind1 = num1.number.length() - 1;
		int ind2 = num2.number.length() - 1;
		int carry = 0;
		String num3 = "";
		
		// Perform addition digit by digit from the rightmost bit
		while (ind1 >= 0 || ind2 >= 0 || carry != 0)
		{
			int sum = carry;
			// Add the corresponding bits from num1 if available
			if (ind1 >= 0) {
				sum += (num1.number.charAt(ind1) == '1') ? 1 : 0;
				ind1--;
			}
			// Add the corresponding bits from num2 if available
			if (ind2 >= 0) {
				sum += (num2.number.charAt(ind2) == '1') ? 1 : 0;
				ind2--;
			}
			// Calculate the carry for the next iteration
			carry = sum / 2;
			// Store the result for the current digit
			sum = sum % 2;
			num3 = (sum == 0 ? "0" : "1") + num3;
		}
		
		// Return the result as a new Binary object
		Binary result = new Binary(num3);
		return result;
	}

	/**
	 * Performs a binary OR operation on two Binary numbers and returns the result.
	 * The OR operation results in a '1' if at least one bit is '1'.
	 *
	 * @param a The first binary number.
	 * @param b The second binary number.
	 * @return The result of the OR operation as a Binary object.
	 */
	public static Binary or(Binary a, Binary b) {
		String result = "";
		// Equalize the lengths of the two binary numbers
		int maxLength = Math.max(a.getValue().length(), b.getValue().length());
		a = new Binary(equalize(a.getValue(), maxLength));
		b = new Binary(equalize(b.getValue(), maxLength));
		
		// Perform the OR operation for each bit
		for (int i = 0; i < maxLength; i++) {
			char bitA = a.getValue().charAt(i);
			char bitB = b.getValue().charAt(i);
			result += (bitA == '1' || bitB == '1') ? '1' : '0';
		}
		// Return the result as a new Binary object
		return new Binary(result);
	}

	/**
	 * Performs a binary AND operation on two Binary numbers and returns the result.
	 * The AND operation results in a '1' only if both bits are '1'.
	 *
	 * @param a The first binary number.
	 * @param b The second binary number.
	 * @return The result of the AND operation as a Binary object.
	 */
	public static Binary and(Binary a, Binary b) {
		String result = "";
		// Equalize the lengths of the two binary numbers
		int maxLength = Math.max(a.getValue().length(), b.getValue().length());
		a = new Binary(equalize(a.getValue(), maxLength));
		b = new Binary(equalize(b.getValue(), maxLength));
		
		// Perform the AND operation for each bit
		for (int i = 0; i < maxLength; i++) {
			char bitA = a.getValue().charAt(i);
			char bitB = b.getValue().charAt(i);
			result += (bitA == '1' && bitB == '1') ? '1' : '0';
		}
		// Return the result as a new Binary object
		return new Binary(result);
	}

	/**
	 * Performs a binary multiplication on two Binary numbers and returns the result.
	 * The multiplication is performed using the standard algorithm, where each digit 
	 * of the second binary number multiplies the first binary number.
	 *
	 * @param a The first binary number.
	 * @param b The second binary number.
	 * @return The result of the multiplication as a Binary object.
	 */
	public static Binary multiply(Binary a, Binary b) {
		Binary result = new Binary("0");
		// Perform multiplication by shifting and adding
		for (int i = b.getValue().length() - 1; i >= 0; i--) {
			if (b.getValue().charAt(i) == '1') {
				// Add the shifted binary number to the result if the bit is '1'
				result = add(result, new Binary(a.getValue() + "0".repeat(b.getValue().length() - 1 - i)));
			}
		}
		// Return the result as a new Binary object
		return result;
	}

}
