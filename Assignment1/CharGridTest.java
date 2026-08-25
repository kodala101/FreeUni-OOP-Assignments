
// Test cases for CharGrid -- a few basic tests are provided.

import junit.framework.TestCase;

public class CharGridTest extends TestCase {
	//
 	// charArea (+25)
	//
	public void testCharArea1() { //(+3)
		char[][] grid = new char[][] {
				{'a', 'y', ' '},
				{'x', 'a', 'z'},
			};
		
		
		CharGrid cg = new CharGrid(grid);
				
		assertEquals(4, cg.charArea('a'));
		assertEquals(1, cg.charArea('z'));

		assertEquals(1, cg.charArea(' '));
		assertEquals(1, cg.charArea('y'));
		assertEquals(0, cg.charArea('w'));
	}
	
	
	public void testCharArea2() { //(+2)
		char[][] grid = new char[][] {
				{'c', 'a', ' '},
				{'b', ' ', 'b'},
				{' ', ' ', 'a'}
			};
		
		CharGrid cg = new CharGrid(grid);
		
		assertEquals(6, cg.charArea('a'));
		assertEquals(3, cg.charArea('b'));
		assertEquals(1, cg.charArea('c'));
		assertEquals(9, cg.charArea(' '));
		assertEquals(0, cg.charArea('d'));
	}

	public void testCharArea3() { //(+5)
		//empty grid cases

		char[][] grid1 = new char[][] {{}, {}, {}};
		char[][] grid2 = new char[][] {};

		CharGrid cg1 = new CharGrid(grid1);
		CharGrid cg2 = new CharGrid(grid2);

		assertEquals(0, cg1.charArea('a'));
		assertEquals(0, cg1.charArea(' '));
		assertEquals(0, cg1.charArea('c'));
		assertEquals(0, cg2.charArea('f'));
		assertEquals(0, cg2.charArea('9'));
	}

	public void testCharArea4() { //(+5)
		// single row and 1x1 grid

		char[][] grid1 = new char[][] {{'a', 'b', 'a'}};
		char[][] grid2 = new char[][] {{'a'}};

		CharGrid cg1 = new CharGrid(grid1);
		CharGrid cg2 = new CharGrid(grid2);

		assertEquals(3, cg1.charArea('a'));
		assertEquals(1, cg1.charArea('b'));
		assertEquals(0, cg1.charArea('c'));
		assertEquals(1, cg2.charArea('a'));
		assertEquals(0, cg2.charArea('y'));
	}


	//
 	// countPlus (+10)
	//
	public void testCountPlus1() { //(+5)
		char[][] grid1 = new char[][] {
				{' ', 'y', ' ', 'x'},
				{'y', 'y', 'y', ' '},
				{'x', 'y', 'z', 'a'},
				{'x', 'z', 'z', 'z'},
				{'x', 'a', 'z', ' '},
				{'a', 'a', 'a', ' '},
				{'x', 'a', 'z', 'y'},
		};

		char[][] grid2 = new char[][] {
				{' ', 'y', ' '},
				{'y', 'y', 'y'},
				{'y', 'y', 'y'},
				{'x', 'y', 'z'},
		};

		char[][] grid3 = new char[][] {
				{' ', 'y', 'x'},
				{'y', 'x', 'x'},
				{'x', 'x', 'x'},
				{'x', 'x', 'x'},
				{'x', 'y', 'x'}
		};

		char[][] grid4 = new char[][] {
				{' ', 'x', 'x'},
		};

		char[][] grid5 = new char[][] {
				{'x'},
		};


		CharGrid cg1 = new CharGrid(grid1);
		CharGrid cg2 = new CharGrid(grid2);
		CharGrid cg3 = new CharGrid(grid3);
		CharGrid cg4 = new CharGrid(grid4);
		CharGrid cg5 = new CharGrid(grid5);

		assertEquals(3, cg1.countPlus());
		assertEquals(0, cg2.countPlus());
		assertEquals(1, cg3.countPlus());
		assertEquals(0, cg4.countPlus());
		assertEquals(0, cg5.countPlus());
	}

	public void testCountPlus2() { //(+5)
		char[][] grid1 = new char[][] {
				{' ', 'y', ' ', 'x'},
				{'y', 'y', 'y', ' '},
				{'x', 'y', 'a', 'a'},
				{'x', 'y', 'z', 'z'},
				{'x', 'z', 'z', 'z'},
				{'a', 'a', 'z', ' '},
				{'x', 'a', 'z', 'y'},
		};

		char[][] grid2 = new char[][] {
				{' ', 'a', 'b'},
				{'e', 'd', 'c'},
				{'f', 'g', 'h'},
				{'k', 'j', 'i'},
		};

		char[][] grid3 = new char[][] {
				{' ', 'x', '9'},
				{'y', 'y', 'x'},
				{'y', 'y', 'y'},
				{'x', 'y', 'x'},
				{'x', ' ', '-'}
		};

		char[][] grid4 = new char[][] {};

		char[][] grid5 = new char[][] {{}};


		CharGrid cg1 = new CharGrid(grid1);
		CharGrid cg2 = new CharGrid(grid2);
		CharGrid cg3 = new CharGrid(grid3);
		CharGrid cg4 = new CharGrid(grid4);
		CharGrid cg5 = new CharGrid(grid5);

		assertEquals(0, cg1.countPlus());
		assertEquals(0, cg2.countPlus());
		assertEquals(1, cg3.countPlus());
		assertEquals(0, cg4.countPlus());
		assertEquals(0, cg5.countPlus());
	}
}
