import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

public class CipherTest {

	@Test
	public void rotationCipherEncryptBy3() {
		String plaintext = "the quick brown fox jumped over the lazy dogs";
		String correctCipherText = "wkh!txlfn!eurzq!irA!mxpshg!ryhu!wkh!odCB!grjv";
		String testCipherText = Cipher.rotationCipherEncrypt(plaintext, 3);
		assertEquals(testCipherText, correctCipherText);
	}

	@Test
	public void rotationCipherDecryptBy3() {
		String plaintext = "the quick brown fox jumped over the lazy dogs";
		String correctCipherText = "wkh!txlfn!eurzq!irA!mxpshg!ryhu!wkh!odCB!grjv";
		String testPlainText = Cipher.rotationCipherDecrypt(correctCipherText, 3);
		assertEquals(testPlainText, plaintext);
	}

	@Test
	public void rotationCipherDecryptBy100() {
		String plaintext = "the quick brown fox jumped over the lazy dogs";
		String correctCipherText = "KyvaHLztBasIFNEawFOaALDGvuaFMvIaKyvaCrQPauFxJ";
		String testPlainText = Cipher.rotationCipherDecrypt(correctCipherText, 100);
		assertEquals(testPlainText, plaintext);
	}

	@Test
	public void rotationCipherEncryptBy100() {
		String plaintext = "the quick brown fox jumped over the lazy dogs";
		String correctCipherText = "KyvaHLztBasIFNEawFOaALDGvuaFMvIaKyvaCrQPauFxJ";
		String testCipherText = Cipher.rotationCipherEncrypt(plaintext, 100);
		assertEquals(testCipherText, correctCipherText);
	}
	
	

	@Test
	public void rotationCipherEncryptBy3CapsWithPunctuation() {
		String plaintext = "\"THE\n\rQUICK\nBROWN FOX. JUMPED OVER THE LAZY DOGS!\"";
		String correctCipherText = "]WKHbcTXLFNbEURZQ!IR0 !MXPSHG!RYHU!WKH!OD21!GRJV/]";
		String testCipherText = Cipher.rotationCipherEncrypt(plaintext, 3);
		assertEquals(testCipherText, correctCipherText);
	}

	@Test
	public void rotationCipherDecryptBy3CapsWithPunctuation() {
		String plaintext = "\"THE\n\rQUICK\nBROWN FOX. JUMPED OVER THE LAZY DOGS!\"";
		String correctCipherText = "]WKHbcTXLFNbEURZQ!IR0 !MXPSHG!RYHU!WKH!OD21!GRJV/]";
		String testPlainText = Cipher.rotationCipherDecrypt(correctCipherText, 3);
		assertEquals(testPlainText, plaintext);
	}

	@Test
	public void rotationCipherDecryptBy100CapsWithPunctuation() {
		String plaintext = "\"THE\n\rQUICK\nBROWN FOX. JUMPED OVER THE LAZY DOGS!\"";
		String correctCipherText = "c,YVpq7.ZT1pS85)4aW5 :a0.36VUa5(V8a,YVa2R\"'aU5X9dc";
		String testPlainText = Cipher.rotationCipherDecrypt(correctCipherText, 100);
		assertEquals(testPlainText, plaintext);
	}

	@Test
	public void rotationCipherEncryptBy100CapsWithPunctuation() {
		String plaintext = "\"THE\n\rQUICK\nBROWN FOX. JUMPED OVER THE LAZY DOGS!\"";
		String correctCipherText = "c,YVpq7.ZT1pS85)4aW5 :a0.36VUa5(V8a,YVa2R\"'aU5X9dc";
		String testCipherText = Cipher.rotationCipherEncrypt(plaintext, 100);
		assertEquals(testCipherText, correctCipherText);
	}

	@Test
	public void vigenereCipherEncrypt() {
		String plaintext = "Pokemon, gotta catch'em all!!!";
		String correctCipherText = "RCnioCq \"urxvo!gcHfl!sp[czo%]b";
		String testCipherText = Cipher.vigenereCipherEncrypt(plaintext, "code");
		assertEquals(testCipherText, correctCipherText);
	}

	@Test
	public void vigenerCipherDecrypt() {
		String plaintext = "Pokemon, gotta catch'em all!!!";
		String correctCipherText = "RCnioCq \"urxvo!gcHfl!sp[czo%]b";
		String testCipherText = Cipher.vigenereCipherDecrypt(correctCipherText, "code");
		assertEquals(testCipherText, plaintext);
	}

	@Test
	public void bruteForceRotateCipherDecrypt() {
		String text = Cipher.readFileAsString("/Volumes/WARREN_LIU/Java/CipherBlankTemplate/cipher1.txt");
		String message = Cipher.crackRotationCipher(text, Cipher.getDefaultAlphabet());
		assertEquals(text, message);
	}
	

	@Test
	public void threeLetterCodeDecrypt(){
		String text = "I repeat. Warren, you suck at picking your vice president... Evan is an awesome person for all that I know."
				+ " He is able to decode almost anything (with the help of Chris of course. Unfortunately he can be a little stubborn in terms of getting"
				+ " what he wants. Whenever he helps me out with anything, he definitely knows how to help a student out. ";
		String encrypted = Cipher.vigenereCipherEncrypt(text, "ass");
		String decrypted = Cipher.vigenereCipherDecrypt3Letter(encrypted, 3);
		System.out.println(decrypted);
		assertEquals(text, decrypted);
	}

}
