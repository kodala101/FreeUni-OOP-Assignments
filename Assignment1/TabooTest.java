// TabooTest.java
// Taboo class tests -- nothing provided.

import java.util.*;

import junit.framework.TestCase;

public class TabooTest extends TestCase {
    //
    //Taboo (+14)
    //
    public void testNoFollow1() { //(+3)
        List<Integer> rules = Arrays.asList(1, 2, 1, 4, 2, 6);
        Taboo<Integer> t = new Taboo<>(rules);

        Set<Integer> s1 = t.noFollow(2);
        assertEquals(2, s1.size());
        assertTrue(s1.contains(1));
        assertTrue(s1.contains(6));

        Set<Integer> s2 = t.noFollow(1);
        assertEquals(2, s2.size());
        assertTrue(s2.contains(2));
        assertTrue(s2.contains(4));

        Set<Integer> s3 = t.noFollow(4);
        assertEquals(1, s3.size());
        assertTrue(s3.contains(2));
    }

    public void testNoFollow2() { //(+3)
        List<String> rules = Arrays.asList("a", "b", "c", "a", "f");
        Taboo<String> t = new Taboo<>(rules);

        Set<String> s1 = t.noFollow("a");
        assertEquals(2, s1.size());
        assertTrue(s1.contains("b"));
        assertTrue(s1.contains("f"));

        Set<String> s2 = t.noFollow("x");
        assertEquals(0, s2.size());

        Set<String> s3 = t.noFollow("b");
        assertEquals(1, s3.size());
        assertTrue(s3.contains("c"));
    }

    public void testNoFollow3() { //(+2)
        List<String> rules = Arrays.asList("a", null, "c", null, "f");
        Taboo<String> t = new Taboo<>(rules);

        Set<String> s1 = t.noFollow("a");
        assertEquals(0, s1.size());

        Set<String> s2 = t.noFollow("x");
        assertEquals(0, s2.size());
    }

    public void testReduce1() { //(+3)
        List<String> rules = Arrays.asList("a", "c", "a", "b");
        Taboo<String> t = new Taboo<>(rules);

        List<String> l = new ArrayList<>(Arrays.asList("a", "c", "b", "x", "c", "a"));
        t.reduce(l);

        List<String> expected = Arrays.asList("a", "x", "c");
        assertEquals(expected, l);

        List<String> noChange = new ArrayList<>(Arrays.asList("b", "c", "x"));
        t.reduce(noChange);
        assertEquals(3, noChange.size());

        List<String> oneElem = new ArrayList<>(List.of("a"));
        t.reduce(oneElem);
        assertEquals(1, oneElem.size());
    }

    public void testReduce2() { // (+3)
        List<Integer> ruleList = Arrays.asList(1, 2, 1, 3, 5, 5);
        Taboo<Integer> t = new Taboo<>(ruleList);

        List<Integer> l1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        t.reduce(l1);
        assertEquals(Arrays.asList(1, 4), l1);

        List<Integer> l2 = new ArrayList<>(Arrays.asList(5, 5, 5, 1));
        t.reduce(l2);
        assertEquals(Arrays.asList(5, 1), l2);

        List<Integer> l3 = new ArrayList<>(Arrays.asList(4, 3, 2, 1));
        t.reduce(l3);
        assertEquals(Arrays.asList(4, 3, 2), l3);
    }
}

