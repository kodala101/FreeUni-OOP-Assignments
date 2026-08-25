import junit.framework.TestCase;
import java.util.*;

public class TetrisGridTest extends TestCase {
	// (+6)

	// Provided simple clearRows() test
	// width 2, height 3 grid
	public void testClear1() {
		boolean[][] before = {
				{true, true, false},
				{false, true, true}
		};
		
		boolean[][] after = {
				{true, false, false},
				{false, true, false}
		};
		
		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}

	// full i-th and (i+1)-th rows
	public void testClear2() {
		boolean[][] before = {
				{true, true, false},
				{true, true, true}
		};

		boolean[][] after = {
				{false, false, false},
				{true, false, false}
		};

		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}

	// full first and last row
	public void testClear3() {
		boolean[][] before = {
				{true, false, true},
				{true, true, true},
				{true, false, true},
				{true, true, true}
		};

		boolean[][] after = {
				{false, false, false},
				{true, false, false},
				{false, false, false},
				{true, false, false}
		};

		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}

	// none full row
	public void testClear4() {
		boolean[][] before = {
				{true, false, true},
				{false, true, true},
				{true, false, true},
				{true, true, false}
		};

		boolean[][] after = {
				{true, false, true},
				{false, true, true},
				{true, false, true},
				{true, true, false}
		};

		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}

	// full board
	public void testClear5() {
		boolean[][] before = {
				{true, true},
				{true, true}
		};

		boolean[][] after = {
				{false, false},
				{false, false}
		};

		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}

	// only one column
	public void testClear6() {
		boolean[][] before = {
				{true, true, true, true}
		};

		boolean[][] after = {
				{false, false, false, false}
		};

		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}

	// full last row
	public void testClear7() {
		boolean[][] before = {
				{false, false, true},
				{true, false, true},
				{false, false, true},
				{true, false, true}
		};

		boolean[][] after = {
				{false, false, false},
				{true, false, false},
				{false, false, false},
				{true, false, false}
		};

		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}
}
