// StringCodeTest
// Some test code is provided for the early HW1 problems,
// and much is left for you to add.

import junit.framework.TestCase;
//(+30)
public class StringCodeTest extends TestCase {
	//
	// blowup (+8)
	//
	public void testBlowup1() { //(+3)
		// basic cases
		assertEquals("cdsssssksss", StringCode.blowup("cds3sk2s"));
		assertEquals("dddddcwww", StringCode.blowup("4dc2w"));
		assertEquals("asddemmm", StringCode.blowup("as1de2m"));
		assertEquals("xxaaaabb", StringCode.blowup("xx3abb"));
		assertEquals("xxxZZZZ", StringCode.blowup("2x3Z"));
	}
	
	public void testBlowup2() { //(+4)
		// strings without digits
		assertEquals("abc", StringCode.blowup("abc"));
		assertEquals("aabd", StringCode.blowup("aabd"));

		// digit at start
		assertEquals("xxxss", StringCode.blowup("2xss"));
		assertEquals("sssssddd", StringCode.blowup("4s2d"));

		// digit at end
		assertEquals("axxx", StringCode.blowup("a2x3"));
		
		// digits next to each other
		assertEquals("a33111", StringCode.blowup("a231"));
		
		// try a 0
		assertEquals("aabb", StringCode.blowup("aa0bb"));
	}
	
	public void testBlowup3() { //(+1)
		// weird chars, empty string
		assertEquals("AB&&,- ab", StringCode.blowup("AB&&,- ab"));
		assertEquals("", StringCode.blowup(""));
		assertEquals("####&*((()", StringCode.blowup("3#&*2()"));
		
		// string with only digits
		assertEquals("", StringCode.blowup("2"));
		assertEquals("33", StringCode.blowup("23"));
	}
	
	
	//
	// maxRun (+6)
	//
	public void testRun1() { //(+3)
		assertEquals(2, StringCode.maxRun("  "));
		assertEquals(1, StringCode.maxRun("h"));
		assertEquals(1, StringCode.maxRun("hp"));

		assertEquals(2, StringCode.maxRun("hoopla"));
		assertEquals(3, StringCode.maxRun("hoopllla"));
	}
	
	public void testRun2() { //(+2)
		assertEquals(4, StringCode.maxRun("aaaabbbb"));
		assertEquals(6, StringCode.maxRun("******&&"));

		assertEquals(5, StringCode.maxRun("aabbsaaaaa"));
		assertEquals(0, StringCode.maxRun(""));
		assertEquals(3, StringCode.maxRun("hhhooppoo"));
	}
	
	public void testRun3() { //(+1)
		// "evolve" technique -- make a series of test cases
		// where each is change from the one above.
		assertEquals(1, StringCode.maxRun("123"));
		assertEquals(2, StringCode.maxRun("1223"));
		assertEquals(2, StringCode.maxRun("112233"));
		assertEquals(3, StringCode.maxRun("1112233"));
		assertEquals(3, StringCode.maxRun("1112233"));

		assertEquals(4, StringCode.maxRun("111223333"));
	}


	//
	// stringIntersect (+16)
	//
	public void testStringIntersect1() { //(+5)
		// false answers
		assertFalse(StringCode.stringIntersect("ashb", "asib", 5));
		assertFalse(StringCode.stringIntersect("", "aa", 1));
		assertFalse(StringCode.stringIntersect("", "", 2));
		assertFalse(StringCode.stringIntersect("ashb", "asibadas", 5));
		assertFalse(StringCode.stringIntersect("asdf", "123", 2));
	}

	public void testStringIntersect2() { //(+5)
		//true answers
		assertTrue(StringCode.stringIntersect("aaabb", "aaaab", 2));
		assertTrue(StringCode.stringIntersect("adsbbbbas", "bbbbqwa", 4));
		assertTrue(StringCode.stringIntersect("98113", "3211", 2));
		assertTrue(StringCode.stringIntersect("we3$", "v$mn", 1));
		assertTrue(StringCode.stringIntersect("aaabvdddd", "dddfaaaaa", 3));
	}

	public void testStringIntersect3() { //(+6)
		//other cases
		assertTrue(StringCode.stringIntersect("  ", " ", 1));
		assertFalse(StringCode.stringIntersect("aabbc", "ccbbaa", 3));
		assertTrue(StringCode.stringIntersect("aaaaaaaaaaaaaa", "bbbaaaaaaaaaaaaaa", 10));
		assertFalse(StringCode.stringIntersect("", "aaa", 2));
		assertFalse(StringCode.stringIntersect("vvvv", "", 4));
		assertFalse(StringCode.stringIntersect("vvvv", "aa", 3));
	}
}
