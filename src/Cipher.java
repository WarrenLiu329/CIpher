import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class Cipher {
	private final static String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789,.() '\"![]/%-_;?=:"
			+ '\n' + '\r';
	private final String SIMPLE_ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	private final String LOWERCASE_ALPHABET = "abcdefghijklmnopqrstuvwxyz";

	// Set this variable to the default alphabet you wish to use
	private final static String DEFAULT_ALPHABET = ALPHABET;

	private final static double[] frequenciesOfLettersInEnglish = new double[] { 8.17, 1.49, 2.78, 4.25, 12.70, 2.23, 2.01,
			6.09, 6.97, 0.15, 0.77, 4.03, 2.40, 6.75, 7.50, 1.93, 0.09, 5.99, 6.33, 9.1, 2.8, 0.98, 1.98, 0.07 };

	private static double[] scores = new double[frequenciesOfLettersInEnglish.length];

	private static double threshold = 4.00;
	private Dictionary dictionary = Dictionary
			.buildDictionary("/Volumes/WARREN_LIU/Java/CipherBlankTemplate/words.txt");

	/**
	 * Returns plaintext encrypted by the rotation cipher with a shift of
	 * movement.
	 * 
	 * @param alphabet
	 *            the alphabet to be used for the encryption
	 * @param plainText
	 *            the plain text to be encrypted.
	 * @param shiftAmount
	 *            the number of characters in ALPHABET to shift by.
	 * @return returns the encrypted plainText.
	 */
	// Help from Evan. :]
	public static String rotationCipherEncrypt(String plain, int shift, String alphabet) {
		String text = "";
		for (int i = 0; i < plain.length(); i++) {
			String letter = plain.substring(i, i + 1);
			text += ShiftLetter(alphabet, shift, letter);
		}
		return text;
	}

	public static String rotationCipherEncrypt(String plainText, int shiftAmount) {
		return rotationCipherEncrypt(plainText, shiftAmount, DEFAULT_ALPHABET);
	}

	/****
	 * Shifts a plaintext letter by the shift amount
	 * 
	 * @param alphabet
	 * @param shiftAmount
	 * @param letter
	 * @return the shifted letter
	 */
	public static String ShiftLetter(String alphabet, int shiftAmount, String letter) {
		int index = alphabet.indexOf(letter, 0);
		while (shiftAmount < 0) {
			shiftAmount += alphabet.length();
		}
		index += shiftAmount;
		index %= alphabet.length();
		String shiftedLetter = alphabet.substring(index, index + 1);
		return shiftedLetter;
	}

	/**
	 * Returns a the result of decrypting cipherText by shiftAmount using the
	 * rotation cipher.
	 * 
	 * @param alphabet
	 *            the alphabet to be used for the encryption
	 * @param cipherText
	 *            the encrypted text.
	 * @param shiftAmount
	 *            the key to decrypt the cipher.
	 * @return returns the decrypted cipherText.
	 */
	public static String rotationCipherDecrypt(String cipher, int shift, String alphabet) {
		String text = rotationCipherEncrypt(cipher, -shift, alphabet);
		return text;
	}

	/***
	 * goes through each letter until it finds the right shofted letter
	 * 
	 * @param cipherText
	 * @param alphabet
	 * @return decrypted text
	 */
	public static String rotationCipherCrack(String cipher, String alphabet){
		String text = "";
		for (int i = 0; i < alphabet.length(); i++) {
			text = rotationCipherDecrypt(cipher, i, alphabet);
			text = stripInvalidChars(text, alphabet);
			if (isEnglish(text)) {
				return text;
			}
		}
		return null;
	}

	public String rotationCipherDecrypt(String cipherText, int shiftAmount) {
		return rotationCipherDecrypt(cipherText, shiftAmount, DEFAULT_ALPHABET);
	}

	/****
	 * 
	 * @param plaintext
	 *            plaintext is the original message that is inputed
	 * @param permutation
	 *            is an int array that holds indexes of the alphabet
	 * @param alphabet
	 *            is the DEFAULT_ALPHABET
	 * @return text which is the encrypted message
	 */
	public String substitutionCipher(String plaintext, int[] permutation, String alphabet) {
		String text = "";
		for (int i = 0; i < plaintext.length(); i++) {
			int index = permutation[i];
			String letter = alphabet.substring(index, index + 1);
			text += letter;
		}
		return text;
	}

	/****
	 * 
	 * @param permutation
	 *            is the input array of randomly generated indexes.
	 * @return false is there are duplicate indexes
	 */
	public boolean isValidPermutation(int[] permutation) {
		for (int i = 0; i < permutation.length; i++) {
			for (int j = i; j < permutation.length; j++) {
				if (permutation[i] == permutation[j]) {
					return false;
				}
			}
		}
		return true;
	}

	/****
	 * 
	 * @param length
	 *            the length of the alphabet
	 * @return the randomly generated permutations
	 */
	public int[] randomPermutation(int length) {
		int[] permutation = new int[length];
		boolean isValid = false;
		while (isValid == false) {
			for (int i = 0; i < permutation.length; i++) {
				permutation[i] = -1;
				if (permutation[i] == -1) {
					permutation[i] = (int) Math.random() * length;
				}
				if (isValidPermutation(permutation) == true) {
					isValid = true;
				}
			}
		}
		return permutation;
	}

	/**
	 * Returns plaintext encrypted by the vigenere cipher encoded with the
	 * String code
	 * 
	 * @param alphabet
	 *            the alphabet to be used for the encryption
	 * @param plainText
	 *            the plain text to be encrypted.
	 * @param code
	 *            the code to use as the encryption key.
	 * @return returns the encrypted plainText.
	 */
	public String vigenereCipherEncrypt(String plainText, String password, String alphabet) {
		String text = "";
		for (int i = 0; i < plainText.length(); i++) {
			int plainIndex = getIndex(alphabet, plainText, i);
			int codeIndex = getIndex(alphabet, password, (i % password.length()));
			String shiftedLetter = ShiftLetter(alphabet, codeIndex, alphabet.substring(plainIndex, plainIndex + 1));
			text += shiftedLetter;
		}
		return text;
	}

	/***
	 * gets the index of a letter basewd on its alphabet
	 * 
	 * @param alphabet
	 * @param text
	 * @param position
	 * @return
	 */
	public int getIndex(String alphabet, String text, int position) {
		String letter = text.substring(position, position + 1);
		int index = alphabet.indexOf(letter);
		return index;
	}

	public String vigenereCipherEncrypt(String plainText, String code) {
		return vigenereCipherEncrypt(plainText, code, DEFAULT_ALPHABET);
	}

	/**
	 * Returns the result of decrypting cipherText with the key code.
	 * 
	 * @param alphabet
	 *            the alphabet to be used for the encryption
	 * @param cipherText
	 *            the encrypted text.
	 * @param code
	 *            the decryption key
	 * @return returns the decrypted cipherText.
	 */
	public String vigenereCipherDecrypt(String cipher, String password, String alphabet) {
		String text = "";
		for (int i = 0; i < cipher.length(); i++) {
			int cipherIndex = getIndex(alphabet, cipher, i);
			int codeIndex = getIndex(alphabet, password, (i % password.length()));
			String shiftedLetter = ShiftLetter(alphabet, -codeIndex, alphabet.substring(cipherIndex, cipherIndex + 1));
			text += shiftedLetter;
		}
		return text;
	}

	public String vigenereCipherDecrypt(String cipherText, String code) {
		return vigenereCipherDecrypt(cipherText, code, DEFAULT_ALPHABET);
	}

	/**
	 * returns a copy of the input plaintext String with invalid characters
	 * stripped out.
	 * 
	 * @param plaintext
	 *            The plaintext string you wish to remove illegal characters
	 *            from
	 * @param alphabet
	 *            A string of all legal characters.
	 * @return String A copy of plain with all characters not in alphabet
	 *         removed.
	 */
	private static String stripInvalidChars(String plaintext, String alphabet) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < plaintext.length(); i++) { // loop through plaintext
			if (alphabet.indexOf(plaintext.charAt(i)) >= 0) // get index of char
				b.append(plaintext.charAt(i)); // if it exists, keep it
			else
				// otherwise skip it &
				System.out.println("Stripping letter: \"" + plaintext.charAt(i) // display
																				// a
																				// message
						+ "\"");
		}
		return b.toString();
	}

	/**
	 * returns true if plaintext is valid English.
	 * 
	 * @param plaintext
	 *            the text you wish to test for whether it's valid English
	 * @return boolean returns true if plaintext is valid English.
	 */
	private static boolean isEnglish(String plaintext) {

		double min = Double.MAX_VALUE;
		for (int j = 0; j < plaintext.length(); j++) {
			fillInAllScores(plaintext);
			double totalScore = addScores(scores);
			if (totalScore < min) {
				min = totalScore;
			}
		}
		if (min <= threshold) {
			return true;
		}
		return false;
	}

	/****
	 * gets all of the words in a plaintext and stores them in an array
	 * 
	 * @param plaintext
	 * @return
	 */
	public String[] getWords(String plaintext) {
		String[] words = new String[countWordsIn(plaintext)];
		plaintext += " ";
		int letterIndex = 0;
		int spaceIndex = 0;
		int nextSpace = 0;
		for (int i = 0; i < plaintext.length(); i++) {
			String letter = plaintext.substring(i, i + 1);
			if (!letter.equals(" ")) {
				letterIndex = plaintext.indexOf(letter, i);
			}
			spaceIndex = plaintext.indexOf(" ", letterIndex);

			if (nextSpace < words.length) {
				words[nextSpace] = plaintext.substring(letterIndex, spaceIndex);
				nextSpace++;
			}
		}
		return words;
	}

	/***
	 * counts the number of words in a plaintext
	 * 
	 * @param plaintext
	 * @return
	 */
	private int countWordsIn(String plaintext) {
		int numWords = 0;
		for (int i = 0; i < plaintext.length() - 1; i++) {
			String letter = plaintext.substring(i, i + 1);
			if (letter.equals(" ")) {
				if (plaintext.substring(i + 1, i + 2) != " ") {
					numWords++;
				}
			}
		}
		return numWords;
	}

	/***
	 * reads text document
	 * 
	 * @param filePath
	 * @return
	 */
	public String readFileAsString(String filePath) {
		StringBuilder output = new StringBuilder();

		try (Scanner scanner = new Scanner(new File(filePath))) {
			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				output.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return output.toString();
	}

	/****
	 * decrypts a cipher by a 3 letter password
	 * 
	 * @param cipher
	 * @param alphabet
	 * @return
	 */
	public String vigenereCipherCrackThreeLetter(String cipher, String alphabet) {
		String password = "";
		for (int i = 0; i < 3; i++) {
			String group = splitIntoGroups(cipher, i, 3);
			password += findLetterCorrespondingTo(group, alphabet);
		}
		System.out.println(password);
		return (vigenereCipherDecrypt(cipher, password, alphabet));
	}

	/***
	 * decrypts using an unknown lengthed password
	 * 
	 * @param cipher
	 * @param alphabet
	 * @param passSize
	 * @return
	 */
	public String vigenereCipherCrack(String cipher, int passwordLength, String alphabet) {
		String password = "";
		for (int i = 0; i < passwordLength; i++) {
			String group = splitIntoGroups(cipher, i, passwordLength);
			password += findLetterCorrespondingTo(group, alphabet);
		}
		System.out.println(password);
		return (vigenereCipherDecrypt(cipher, password, alphabet));

	}

	public String vigenereCipherDecrypt3Letter(String cipher, int passSize) {
		return vigenereCipherCrack(cipher, passSize, DEFAULT_ALPHABET);
	}

	/****
	 * finds the letter corresponding to the letters of the cipher texts
	 * compared to the plaintext
	 * 
	 * @param group
	 * @param alphabet
	 * @return
	 */
	public String findLetterCorrespondingTo(String group, String alphabet) {
		for (int i = 0; i < alphabet.length(); i++) {
			String decoded = rotationCipherDecrypt(group, i, alphabet);
			LetterBag bag = new LetterBag();
			for (int x = 0; x < decoded.length(); x++) {
				bag.add(decoded.substring(x, x + 1));
			}

			if (bag.getMostFrequent().equals(" ")) {
				return alphabet.substring(i, i + 1);
			}
		}
		return null;
	}

	/***
	 * divides the cipher by the letter corresponding with one letter of the
	 * password
	 * 
	 * @param encrypted
	 * @param index
	 * @param length
	 * @return
	 */
	public String splitIntoGroups(String encrypted, int index, int length) {
		String group = "";
		for (int i = index; i < encrypted.length(); i += length) {
			group += encrypted.substring(i, i + 1);
		}

		return group;
	}

	public String getDefaultAlphabet() {
		return DEFAULT_ALPHABET;
	}

	/****
	 * Gets the frequency of a single letter in a plaintext
	 * 
	 * @param letter
	 * @param plaintext
	 * @param alphabet
	 * @return the frequency of the letter in a plaintext
	 */
	private static double getFrequency(String letter, String plaintext, String alphabet) {
		int count = 0;
		for (int i = 0; i < alphabet.length(); i++) {
			if (alphabet.substring(i, i + 1).equals(letter)) {
				count++;
			}
		}

		return (double) count / (double) plaintext.length();
	}

	/***
	 * Gets an array of scores from dividing the frequency given and the
	 * original english frequency
	 * 
	 * @param frequency
	 * @return
	 */
	public static double getScores(double frequency) {
		double score = 0;
		for (int i = 0; i < frequenciesOfLettersInEnglish.length; i++) {
			score = (double) frequency / (double) frequenciesOfLettersInEnglish[i];
		}

		return score;
	}

	private static void fillInScores(int index, double score) {
		scores[index] = score;
	}

	/****
	 * adds the total scores of all of the letters
	 * 
	 * @param scores
	 * @return
	 */
	private static double addScores(double[] scores) {
		double total = 0;
		for (int i = 0; i < scores.length; i++) {
			total += scores[i];
		}

		return total;
	}

	/****
	 * Gets all of the scores for each leter in the plaintext in to an array
	 * 
	 * @param plaintext
	 */
	private static void fillInAllScores(String plaintext) {
		clearAllScores(scores);
		for (int i = 0; i < plaintext.length(); i++) {
			String currentLetter = plaintext.substring(i, i + 1);
			double frequency = getFrequency(currentLetter, plaintext, DEFAULT_ALPHABET);

			double score = getScores(frequency);

			fillInScores(DEFAULT_ALPHABET.indexOf(currentLetter), score);
		}
	}

	/***
	 * Resets the array so have no scores
	 * 
	 * @param scores
	 */
	private static void clearAllScores(double[] scores) {
		for (int i = 0; i < scores.length; i++) {
			scores[i] = 0;
		}
	}
}