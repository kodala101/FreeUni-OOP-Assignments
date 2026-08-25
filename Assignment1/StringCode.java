import java.util.HashSet;
import java.util.Set;

// CS108 HW1 -- String static methods

public class StringCode {

	/**
	 * Given a string, returns the length of the largest run.
	 * A a run is a series of adajcent chars that are the same.
	 *
	 * @param str
	 * @return max run length
	 */
	public static int maxRun(String str) {
		if (str.isEmpty()) return 0;

		int ans = 1;
		int curr = 1;
		for (int i = 1; i < str.length(); i++) {
			if (str.charAt(i) == str.charAt(i - 1)) {
				curr++;
				if (curr >= ans) ans = curr;
			} else {
				curr = 1;
			}
		}
		return ans;
	}


	/**
	 * Given a string, for each digit in the original string,
	 * replaces the digit with that many occurrences of the character
	 * following. So the string "a3tx2z" yields "attttxzzz".
	 *
	 * @param str
	 * @return blown up string
	 */
	public static String blowup(String str) {
		if (str.isEmpty()) return "";

		StringBuilder res = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			if (Character.isDigit(str.charAt(i))) {
				if (i != (str.length() - 1)) {
					for (int j = 0; j < str.charAt(i) - '0'; j++) {
						res.append(str.charAt(i + 1));
					}
				}
			} else {
				res.append(str.charAt(i));
			}
		}

		return res.toString();
	}

	/**
	 * Given 2 strings, consider all the substrings within them
	 * of length len. Returns true if there are any such substrings
	 * which appear in both strings.
	 * Compute this in linear time using a HashSet. Len will be 1 or more.
	 */
	public static boolean stringIntersect(String a, String b, int len) {
		if (a.isEmpty() || b.isEmpty()) return false;
		if (len > a.length() || len > b.length()) return false;

		HashSet<String> hs = new HashSet<>();
		for (int i = 0; i <= a.length() - len; i++) {
			hs.add(a.substring(i, i + len));
		}

		for (int i = 0; i <= b.length() - len; i++) {
			if (hs.contains(b.substring(i, i + len))) return true;
		}

		return false;
	}
}
