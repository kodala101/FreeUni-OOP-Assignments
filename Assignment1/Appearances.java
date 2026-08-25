import java.util.*;

public class Appearances {
	
	/**
	 * Returns the number of elements that appear the same number
	 * of times in both collections. Static method. (see handout).
	 * @return number of same-appearance elements
	 */
	public static <T> int sameCount(Collection<T> a, Collection<T> b) {
		Map<T, Integer> a_freq = getFreq(a);
		Map<T, Integer> b_freq = getFreq(b);

		int res = 0;
		for (T elem : a_freq.keySet()) {
			if (b_freq.containsKey(elem) && a_freq.get(elem).equals(b_freq.get(elem))) res++;
		}

		return res;
	}

	private static <T> Map<T, Integer> getFreq(Collection<T> c) {
		Map<T, Integer> freq = new HashMap<>();
		for (T elem : c) {
			if (freq.containsKey(elem)) {
				freq.put(elem, freq.get(elem) + 1);
			} else {
				freq.put(elem, 1);
			}
		}
		return freq;
	}
}
