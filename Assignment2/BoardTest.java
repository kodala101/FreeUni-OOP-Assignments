import junit.framework.TestCase;


public class BoardTest extends TestCase {
	Board b;
	Piece pyr1, pyr2, pyr3, pyr4;
	Piece sqr;
	Piece stick1, stick2;
	Piece s1, s2;
	Piece sr1, sr2;
	Piece l1, l2, l3, l4;
	Piece lr1, lr2, lr3, lr4;

	// This shows how to build things in setUp() to re-use
	// across tests.
	
	// In this case, setUp() makes shapes,
	// and also a 3X6 board, with pyr placed at the bottom,
	// ready to be used by tests.
	
	protected void setUp() throws Exception {
		pyr1 = new Piece(Piece.PYRAMID_STR);
		pyr2 = pyr1.computeNextRotation();
		pyr3 = pyr2.computeNextRotation();
		pyr4 = pyr3.computeNextRotation();

		sqr = new Piece(Piece.SQUARE_STR);

		stick1 = new Piece(Piece.STICK_STR);
		stick2 = stick1.computeNextRotation();

		s1 = new Piece(Piece.S1_STR);
		s2 = s1.computeNextRotation();

		sr1 = new Piece(Piece.S2_STR);
		sr2 = sr1.computeNextRotation();

		l1 = new Piece(Piece.L1_STR);
		l2 = l1.computeNextRotation();
		l3 = l2.computeNextRotation();
		l4 = l3.computeNextRotation();

		lr1 = new Piece(Piece.L2_STR);
		lr2 = lr1.computeNextRotation();
		lr3 = lr2.computeNextRotation();
		lr4 = lr3.computeNextRotation();
	}
	
	// Check the basic width/height/max after the one placement
	public void testSample1() {
		b = new Board(4, 6);
		assertEquals(4, b.getWidth());
		assertEquals(6, b.getHeight());
		assertTrue(b.getGrid(10, 10));

		//Empty board
		assertEquals(0, b.getMaxHeight());
		assertEquals(0, b.getColumnHeight(0));
		assertEquals(0, b.getRowWidth(0));


		int res = b.place(stick2, 0, 0);
		b.commit();
		assertEquals(Board.PLACE_ROW_FILLED, res);

		assertEquals(1, b.getColumnHeight(0));
		assertEquals(1, b.getMaxHeight());
		assertEquals(4, b.getRowWidth(0));

		assertTrue(b.getGrid(0, 0));
		assertFalse(b.getGrid(2, 2));
	}
	
	// Check the basic width/height/max after several placements
	public void testSample2() {
		b = new Board(6, 10);

		int res1 = b.place(pyr1, 0, 0);
		b.commit();
		int res2 = b.place(stick1, 0, 1);
		b.commit();
		int res3 = b.place(sr1, 2, 0);
		b.commit();

		assertEquals(5, b.getColumnHeight(0));
		assertEquals(2, b.getColumnHeight(1));
		assertEquals(2, b.getColumnHeight(2));
		assertEquals(2, b.getColumnHeight(3));
		assertEquals(1, b.getColumnHeight(4));
		assertEquals(0, b.getColumnHeight(5));

		int res4 = b.place(pyr4, 0, 5);
		b.commit();
		assertEquals(5, b.getRowWidth(0));
		assertEquals(4, b.getRowWidth(1));
		assertEquals(1, b.getRowWidth(2));
		assertEquals(1, b.getRowWidth(3));
		assertEquals(1, b.getRowWidth(4));
		assertEquals(0, b.getRowWidth(9));

		assertEquals(8, b.getMaxHeight());
		assertTrue(b.getGrid(1, 1));
		assertFalse(b.getGrid(4, 4));
	}
	
	public void testDrop() {
		b = new Board(6, 10);
		int res1 = b.place(stick2, 0, 0);
		b.commit();
		assertEquals(4, b.getRowWidth(0));
		assertEquals(0, b.getRowWidth(1));
		assertEquals(1, b.getColumnHeight(0));
		assertEquals(0, b.getColumnHeight(5));
		assertEquals(1, b.getMaxHeight());
		int res2 = b.place(s2, 3, 0);
		b.commit();

		assertEquals(1, b.dropHeight(pyr1, 0));
		assertEquals(1, b.dropHeight(pyr2, 0));
		assertEquals(3, b.dropHeight(stick2, 2));
		assertEquals(2, b.dropHeight(stick1, 4));
		assertEquals(0, b.dropHeight(stick1, 5));
		assertEquals(1, b.dropHeight(l1, 0));

		assertEquals(3, b.getMaxHeight());
		assertEquals(2, b.getRowWidth(1));
		assertEquals(5, b.getRowWidth(0));
		assertEquals(3, b.getColumnHeight(3));
		assertEquals(2, b.getColumnHeight(4));
	}

	public void testClear() {
		b = new Board(4, 10);
		int res1 = b.place(stick2, 0, 0);
		b.commit();
		int res2 = b.place(pyr1, 0, 1);
		b.commit();
		int res3 = b.place(stick1, 3, 1);
		b.commit();

		b.clearRows();
		b.commit();
		assertEquals(3, b.getMaxHeight());

		int res4 = b.place(pyr2, 2, 0);
		b.commit();
		int res5 = b.place(stick1, 0, 0);
		b.commit();

		b.clearRows();
		b.commit();
		assertEquals(4, b.getMaxHeight());
		assertEquals(3, b.getColumnHeight(3));
	}

	public void testWithUndo() {
		b = new Board(4, 10);

		b.place(pyr1, 0, 0);
		b.commit();

		assertEquals(2, b.getMaxHeight());
		assertEquals(1, b.getColumnHeight(0));

		b.place(stick1, 3, 0);

		b.clearRows();

		assertEquals(3, b.getMaxHeight());

		b.undo();

		assertEquals(2, b.getMaxHeight());
		assertEquals(1, b.getColumnHeight(0));
		assertFalse(b.getGrid(3, 0));

		b.place(stick1, 0, 2);
		assertEquals(6, b.getColumnHeight(0));
		assertTrue(b.getGrid(0, 2));

		b.undo();
		assertFalse(b.getGrid(0, 2));
	}

	public void testToString() {
		b = new Board(3, 4);

		String expected1 = """
                |   |
                |   |
                |   |
                |   |
                -----""";
		assertEquals(expected1, b.toString());

		b.place(sqr, 0, 0);
		String expected2 = """
                |   |
                |   |
                |++ |
                |++ |
                -----""";
		assertEquals(expected2, b.toString());
	}
}