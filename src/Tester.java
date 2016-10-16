import java.util.Arrays;

public class Tester {

	public static void main(String[] args) {
//		String plainText = "Nima's pants are on fire!!!?!!";
//		String cipherText = Cipher.rotationCipherEncrypt(plainText, 3);
//		
//		String[] words = Cipher.getWords(plainText);
//		System.out.println(Arrays.toString(words));
		
		String plainText2 = "Pokemon, gotta catch'em all!!!";
		String cipherText2 = Cipher.vigenereCipherEncrypt(plainText2, "code");
	
		System.out.println("Plaintext: " + plainText2);
		System.out.println("Cipertext: " + cipherText2);
//		
//		System.out.println("Plaintext: " + plainText2);
//		System.out.println("Cipertext: " + cipherText2);
		
//		Dictionary d = Dictionary.buildDictionary("/Volumes/WARREN_LIU/Java/CipherBlankTemplate/words.txt");
//		System.out.println(d.isWord("hi"));
//		System.out.println(d.isWord("pokemon"));
//		
//		String text = Cipher.readFileAsString("/Volumes/WARREN_LIU/Java/CipherBlankTemplate/cipher1.txt");
//		String plaintext = Cipher.crackRotationCipher(cipherText);
//		System.out.println("Plaintext: " + plaintext );
		
//		String test = "I repeat. Warren, you suck at picking your vice president... Evan is an awesome person for all that I know."
//				+ " He is able to decode almost anything (with the help of Chris of course. Unfortunately he can be a little stubborn in terms of getting"
//				+ " what he wants. Whenever he helps me out with anything, he definitely knows how to help a student out. ";
//		String encrypt = Cipher.vigenereCipherEncrypt(test, "ass");
//		System.out.println(encrypt);
//		System.out.println(test.length());
//		System.out.println(encrypt.length());
	}

}