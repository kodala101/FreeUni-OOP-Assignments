import junit.framework.TestCase;

import java.util.*;

/*
  Unit test for Piece class -- starter shell.
 */
public class PieceTest extends TestCase {
	// You can create data to be used in the
	// test cases like this. For each run of a test method,
	// a new PieceTest object is created and setUp() is called
	// automatically by JUnit.
	// For example, the code below sets up some
	// pyramid and s pieces in instance variables
	// that can be used in tests.
	private Piece pyr1, pyr2, pyr3, pyr4;
	private Piece l1, l2, l3, l4;
	private Piece lr1, lr2, lr3, lr4;
	private Piece s1, s2, s3;
	private Piece sr1, sr2, sr3;
	private Piece sqr1, sqr2;
	private Piece stick1, stick2, stick3;
	private Piece s, sRotated;

	protected void setUp() throws Exception {
		super.setUp();
		
		pyr1 = new Piece(Piece.PYRAMID_STR);
		pyr2 = pyr1.computeNextRotation();
		pyr3 = pyr2.computeNextRotation();
		pyr4 = pyr3.computeNextRotation();

		l1 = new Piece(Piece.L1_STR);
		l2 = l1.computeNextRotation();
		l3 = l2.computeNextRotation();
		l4 = l3.computeNextRotation();

		lr1 = new Piece(Piece.L2_STR);
		lr2 = lr1.computeNextRotation();
		lr3 = lr2.computeNextRotation();
		lr4 = lr3.computeNextRotation();

		s1 = new Piece(Piece.S1_STR);
		s2 = s1.computeNextRotation();
		s3 = s2.computeNextRotation();

		sr1 = new Piece(Piece.S2_STR);
		sr2 = sr1.computeNextRotation();
		sr3 = sr2.computeNextRotation();

		sqr1 = new Piece(Piece.SQUARE_STR);
		sqr2 = sqr1.computeNextRotation();

		stick1 = new Piece(Piece.STICK_STR);
		stick2 = stick1.computeNextRotation();
		stick3 = stick2.computeNextRotation();
		
		s = new Piece(Piece.S1_STR);
		sRotated = s.computeNextRotation();
	}
	
	// Here are some sample tests to get you started
	
	public void testSampleSize() {
		// Check size of pyr piece
		assertEquals(3, pyr1.getWidth());
		assertEquals(2, pyr1.getHeight());

		// Check size of S1 piece
		assertEquals(3, s1.getWidth());
		assertEquals(2, s1.getHeight());

		// Check size of S2 piece
		assertEquals(3, sr1.getWidth());
		assertEquals(2, sr1.getHeight());

		// Check size of L1 piece
		assertEquals(2, l1.getWidth());
		assertEquals(3, l1.getHeight());

		// Check size of L2 piece
		assertEquals(2, lr1.getWidth());
		assertEquals(3, lr1.getHeight());

		// Check size of pyr piece
		assertEquals(2, sqr1.getWidth());
		assertEquals(2, sqr1.getHeight());

		// Check size of pyr piece
		assertEquals(1, stick1.getWidth());
		assertEquals(4, stick1.getHeight());
		
		// Now try after rotation
		// Effectively we're testing size and rotation code here
		assertEquals(2, pyr2.getWidth());
		assertEquals(3, pyr2.getHeight());

		assertEquals(2, s2.getWidth());
		assertEquals(3, s2.getHeight());

		assertEquals(2, sr2.getWidth());
		assertEquals(3, sr2.getHeight());

		assertEquals(3, l2.getWidth());
		assertEquals(2, l2.getHeight());

		assertEquals(3, lr2.getWidth());
		assertEquals(2, lr2.getHeight());

		assertEquals(2, sqr2.getWidth());
		assertEquals(2, sqr2.getHeight());

		assertEquals(4, stick2.getWidth());
		assertEquals(1, stick2.getHeight());
		
		// Now try with some other piece, made a different way
		Piece l = new Piece(Piece.STICK_STR);
		assertEquals(1, l.getWidth());
		assertEquals(4, l.getHeight());

		// Test getBody()
		assertEquals(4, pyr1.getBody().length);
	}
	
	
	// Test the skirt returned by a few pieces
	public void testSampleSkirt() {
		// Note must use assertTrue(Arrays.equals(... as plain .equals does not work
		// right for arrays.
		assertTrue(Arrays.equals(new int[] {0, 0, 0}, pyr1.getSkirt()));
		assertTrue(Arrays.equals(new int[] {1, 0, 1}, pyr3.getSkirt()));
		
		assertTrue(Arrays.equals(new int[] {0, 0, 1}, s.getSkirt()));
		assertTrue(Arrays.equals(new int[] {1, 0}, sRotated.getSkirt()));

		assertTrue(Arrays.equals(new int[] {1, 0, 0}, sr1.getSkirt()));
		assertTrue(Arrays.equals(new int[] {0, 1}, sr2.getSkirt()));

		assertTrue(Arrays.equals(new int[] {0, 0}, l1.getSkirt()));
		assertTrue(Arrays.equals(new int[] {2, 0}, l3.getSkirt()));

		assertTrue(Arrays.equals(new int[] {0, 0}, lr1.getSkirt()));
		assertTrue(Arrays.equals(new int[] {1, 1, 0}, lr2.getSkirt()));

		assertTrue(Arrays.equals(new int[] {0, 0}, sqr1.getSkirt()));
		assertTrue(Arrays.equals(new int[] {0, 0}, sqr2.getSkirt()));

		assertTrue(Arrays.equals(new int[] {0}, stick1.getSkirt()));
		assertTrue(Arrays.equals(new int[] {0, 0, 0, 0}, stick2.getSkirt()));
	}
	
	// Test equals method
	public void testEquals() {
		assertTrue(pyr1.equals(pyr4.computeNextRotation()));
		assertTrue(s1.equals(s3));
		assertTrue(sr1.equals(sr3));
		assertTrue(l1.equals(l4.computeNextRotation()));
		assertTrue(lr1.equals(lr4.computeNextRotation()));
		assertTrue(sqr1.equals(sqr2));
		assertTrue(stick1.equals(stick3));
	}

	//Test makeFastRotation method
	public void testFastRotation() {
		Piece[] rotations = Piece.getPieces();

		Piece stick = rotations[0];
		Piece l = rotations[1];
		Piece lr = rotations[2];
		Piece s = rotations[3];
		Piece sr = rotations[4];
		Piece square = rotations[5];
		Piece pyramid = rotations[6];

		assertTrue(pyr2.equals(pyramid.fastRotation()));
		assertTrue(stick2.equals(stick.fastRotation()));
		assertTrue(l3.equals(l.fastRotation().fastRotation()));
		assertTrue(lr4.equals(lr.fastRotation().fastRotation().fastRotation()));
		assertTrue(s2.equals(s.fastRotation()));
		assertTrue(sr1.equals(sr.fastRotation().fastRotation().fastRotation().fastRotation()));
		assertTrue(sqr2.equals(square.fastRotation()));
	}
}
