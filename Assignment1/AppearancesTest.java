import junit.framework.TestCase;

import java.util.*;
// (+11)
public class AppearancesTest extends TestCase {
	// utility -- converts a string to a list with one
	// elem for each char.
	private List<String> stringToList(String s) {
		List<String> list = new ArrayList<String>();
		for (int i=0; i<s.length(); i++) {
			list.add(String.valueOf(s.charAt(i)));
			// note: String.valueOf() converts lots of things to string form
		}
		return list;
	}
	
	public void testSameCount1() { // (+4)
		List<String> a = stringToList("abbccc");
		List<String> b = stringToList("cccbba");
		assertEquals(3, Appearances.sameCount(a, b));

		assertEquals(2, Appearances.sameCount(a, stringToList("xsbvbbccac")));
		assertEquals(1, Appearances.sameCount(a, stringToList("xsvbbcaca")));
		assertEquals(0, Appearances.sameCount(b, stringToList("12345")));
		assertEquals(0, Appearances.sameCount(b, stringToList("")));
	}
	
	public void testSameCount2() { // (+ 2)
		// basic List<Integer> cases
		List<Integer> a = Arrays.asList(1, 2, 3, 1, 2, 3, 5);
		assertEquals(1, Appearances.sameCount(a, Arrays.asList(1, 9, 9, 1)));
		assertEquals(2, Appearances.sameCount(a, Arrays.asList(1, 3, 3, 1)));
		assertEquals(1, Appearances.sameCount(a, Arrays.asList(1, 3, 3, 1, 1)));

		assertEquals(1, Appearances.sameCount(a, List.of(5)));
		assertEquals(3, Appearances.sameCount(a, Arrays.asList(1, 1, 5, 2, 2)));
	}

	public void testSameCount3() { // (+ 5)
		List<String> a = stringToList("");
		List<String> b = stringToList("abbadffo");

		assertEquals(3, Appearances.sameCount(b, stringToList("babdo")));
		assertEquals(2, Appearances.sameCount(b, stringToList("bfbf")));
		assertEquals(1, Appearances.sameCount(b, stringToList("bb")));
		assertEquals(0, Appearances.sameCount(a, stringToList("12345")));
		assertEquals(0, Appearances.sameCount(a, stringToList("")));
	}
}
