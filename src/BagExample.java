import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BagExample {
	
	LetterBag bag = new LetterBag();
	
	@Before
	public void setUp(){
		bag.clearBag();
		bag.add("a"); // add 3 "a"'s to the bag
		bag.add("a");
		bag.add("a");

		bag.add("e"); // add 4 "e"'s to the bag
		bag.add("e");
		bag.add("e");
		bag.add("e");

		bag.add("x"); // add 5 other letters
		bag.add("x");
		bag.add("z");
		bag.add("w");
		bag.add("p");
	}
	
	@Test
	public void testTotalItems(){
		int correctItems = 12;
		int expectedItems = bag.getTotalWords();
		assertEquals(expectedItems, correctItems);
	}
	
	@Test
	public void testUniqueItems(){
		int correctUnique = 6;
		int expectedUnique = bag.getNumUniqueWords();
		assertEquals(expectedUnique, correctUnique);
	}
	
	@Test
	public void testNumOccurances(){
		int correctNumOccur = 3;
		int expectedOccur = bag.getNumOccurances("a");
		assertEquals(expectedOccur, correctNumOccur);
	}
	
	@Test
	public void testMostFrequent(){
		String correct = "e";
		String expected = bag.getMostFrequent();
		assertEquals(expected, correct);
	}
	
	@Test
	public void testNMostFrequent(){
		assertEquals(bag.getNMostFrequentStrings(2), "[e, a]");
	}
	
	

}
