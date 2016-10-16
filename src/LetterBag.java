import java.util.Arrays;

public class LetterBag {
	
	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789,.() '\"![]/%-_;?=:"
	+ '\n' + '\r';
	private static int[] letterFrequencies;

	public LetterBag() {
		letterFrequencies = new int[ALPHABET.length()];
	}

	/****
	 * adds a letter to the bag
	 * 
	 * @param letter
	 *            the letter to be added
	 */
	public void add(String letter) {
		int index = getIndexForLetter(letter);
		letterFrequencies[index]++;
	}

	/***
	 * get the index of the lowercase letter
	 * 
	 * @param letter
	 *            the lowercase letter
	 * @return
	 */
	private int getIndexForLetter(String letter) {
		return ALPHABET.indexOf(letter);
	}

	/***
	 * finds the number of items in the bag based on the frequency of each
	 * letter
	 * 
	 * @return the total number of items
	 */
	public int getTotalWords() {
		int items = 0;
		for (int i = 0; i < letterFrequencies.length; i++) {
			items += letterFrequencies[i];
		}
		return items;
	}

	/***
	 * counts how many unique letters there are in the bag by checking if the
	 * frequency is > 0
	 * 
	 * @return the number of different letters
	 */
	public int getNumUniqueWords() {
		int numUniqueLetters = 0;
		for (int i = 0; i < letterFrequencies.length; i++) {
			if (letterFrequencies[i] > 0) {
				numUniqueLetters++;
			}
		}
		return numUniqueLetters;

	}

	/***
	 * 
	 * @param letter
	 *            the letter the array is looking for
	 * @return the frequency of the letter based on the index of
	 */
	public int getNumOccurances(String letter) {
		int index = ALPHABET.indexOf(letter);
		return letterFrequencies[index];
	}

	/***
	 * checks the frequency of each letter and saves the highest value
	 * 
	 * @return the most frequent later
	 */
	public String getMostFrequent() {
		int maxFrequency = letterFrequencies[0];
		int index = 0;
		for (int i = 0; i < letterFrequencies.length; i++) {
			if (letterFrequencies[i] > maxFrequency) {
				maxFrequency = letterFrequencies[i];
				index = i;
			}
		}
		if (maxFrequency == 0) {
			return "There is no frequent letter";
		}
		return ALPHABET.substring(index, index + 1);
	}

	/***
	 * 
	 * @param i
	 *            the number of most frequent letters
	 * @return an array displaying the most frequent letters
	 */
	public String getNMostFrequentStrings(int i) {
		String[] frequentLetters = new String[i];
		String letter = "";
		for (int a = 0; a < i; a++) {
			letter = getMostFrequent();
			frequentLetters[a] = letter;
			removeLetter(letter);
		}
		return Arrays.toString(frequentLetters);
	}

	/***
	 * 
	 * @param letter
	 *            letter in which to remove from letterFrequencies to get rid of
	 *            the old max to find the next max
	 */
	public void removeLetter(String letter) {
		for (int i = 0; i < letterFrequencies.length; i++) {
			if (i == ALPHABET.indexOf(letter)) {
				letterFrequencies[i] = 0;
			}
		}
	}
	
	public void clearBag(){
		for (int i = 0; i < letterFrequencies.length; i++){
			letterFrequencies[i] = 0;
		}
	}

}
