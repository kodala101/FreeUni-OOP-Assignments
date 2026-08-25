// Cracker.java
/*
 Generates SHA hashes of short strings in parallel.
*/

import java.security.*;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

public class Cracker {
	// Array of chars used to produce strings
	public static final char[] CHARS = "abcdefghijklmnopqrstuvwxyz0123456789.,-!".toCharArray();

	/*
	 Given a byte[] array, produces a hex String,
	 such as "234a6f". with 2 chars for each byte in the array.
	 (provided code)
	*/
	public static String hexToString(byte[] bytes) {
		StringBuffer buff = new StringBuffer();
		for (int i=0; i<bytes.length; i++) {
			int val = bytes[i];
			val = val & 0xff;  // remove higher bits, sign
			if (val<16) buff.append('0'); // leading 0
			buff.append(Integer.toString(val, 16));
		}
		return buff.toString();
	}
	
	/*
	 Given a string of hex byte values such as "24a26f", creates
	 a byte[] array of those values, one byte value -128..127
	 for each 2 chars.
	 (provided code)
	*/
	public static byte[] hexToArray(String hex) {
		byte[] result = new byte[hex.length()/2];
		for (int i=0; i<hex.length(); i+=2) {
			result[i/2] = (byte) Integer.parseInt(hex.substring(i, i+2), 16);
		}
		return result;
	}

	private static void generate(String pass) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			byte[] hash = md.digest(pass.getBytes());
			System.out.println(hexToString(hash));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void crack(String targ, int maxLen, int numOfWorkers) {
		byte[] targHash = hexToArray(targ);
		CountDownLatch cdl = new CountDownLatch(numOfWorkers);
		int range = CHARS.length/numOfWorkers;

		for (int i = 0; i < numOfWorkers; i++) {
			int start = range * i;
			int end;
			if (i == numOfWorkers - 1) {
				end = CHARS.length - 1;
			} else {
				end = start + range - 1;
			}

			Worker w = new Worker(start, end, maxLen, targHash, cdl);
			w.start();
		}

		try {
			cdl.await();
			System.out.println("all done");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Incorrect number of arguments");
		} else if (args.length == 1) {
			generate(args[0]);
		} else {
			int maxLen = Integer.parseInt(args[1]);
			int numOfWorkers = 1;
			if (args.length > 2) numOfWorkers = Integer.parseInt(args[2]);
			crack(args[0], maxLen, numOfWorkers);
		}
	}

	private static class Worker extends Thread {
		private final int START;
		private final int END;
		private final int MAX_LEN;
		private final byte[] TARG_HASH;
		private final CountDownLatch CDL;

		private MessageDigest md;

		public Worker(int start, int end, int maxLen, byte[] targHash, CountDownLatch cdl) {
			START = start;
			END = end;
			MAX_LEN = maxLen;
			TARG_HASH = targHash;
			CDL = cdl;

			try {
				md = MessageDigest.getInstance("SHA");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			for (int i = START; i <= END; i++) find(String.valueOf(CHARS[i]));
			CDL.countDown();
		}

		private void find(String s) {
			byte[] b = md.digest(s.getBytes());
			if (Arrays.equals(b, TARG_HASH)) {
				System.out.println(s);
			}

			if (s.length() < MAX_LEN) {
				for (int i = 0; i < CHARS.length; i++) find(s + CHARS[i]);
			}
		}
	}
}
