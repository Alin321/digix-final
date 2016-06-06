package ro.digix.beans;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Crypter {	

	public static String encryptSHA256(String input) {
		try {
			final MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
			final byte[] result = mDigest.digest(input.getBytes());
			final StringBuffer sb = new StringBuffer();
			for (int i = 0; i < result.length; i++) {
				sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
			}
			return sb.toString();
		} catch (final NoSuchAlgorithmException e) {
			return input;
		}
	}
}
