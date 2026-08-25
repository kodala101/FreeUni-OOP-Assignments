// Board.java

/**
 CS108 Tetris Board.
 Represents a Tetris board -- essentially a 2-d grid
 of booleans. Supports tetris pieces and row clearing.
 Has an "undo" feature that allows clients to add and remove pieces efficiently.
 Does not do any drawing or have any idea of pixels. Instead,
 just represents the abstract 2-d board.
*/
public class Board	{
	// Some Aivars are stubbed out for you:
	private int width;
	private int height;
	private boolean[][] grid;
	private boolean DEBUG = true;
	boolean committed;

	private int[] widths;
	private int[] heights;
	private int maxHeight;

	private boolean[][] xGrid;
	private int[] xWidths;
	private int[] xHeights;
	private int xMaxHeight;
	
	
	// Here a few trivial methods are provided:
	
	/**
	 Creates an empty board of the given width and height
	 measured in blocks.
	*/
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		grid = new boolean[width][height];
		xGrid = new boolean[width][height];

		committed = true;
		maxHeight = 0;
		xMaxHeight = 0;
		widths = new int[height];
		heights = new int[width];
		xWidths = new int[height];
		xHeights = new int[width];
	}
	
	
	/**
	 Returns the width of the board in blocks.
	*/
	public int getWidth() {
		return width;
	}
	
	
	/**
	 Returns the height of the board in blocks.
	*/
	public int getHeight() {
		return height;
	}
	
	
	/**
	 Returns the max column height present in the board.
	 For an empty board this is 0.
	*/
	public int getMaxHeight() {	 
		return maxHeight;
	}
	
	
	/**
	 Checks the board for internal consistency -- used
	 for debugging.
	*/
	public void sanityCheck() {
		if (DEBUG) {
			int max_h = 0;

			for (int i = 0; i < grid.length; i++) {
				int col_h = 0;
				for (int j = 0; j < grid[0].length; j++) {
					if (grid[i][j]) col_h = j + 1;
				}

				if (heights[i] != col_h) throw new RuntimeException("description");
				if (col_h >= max_h) max_h = col_h;
			}

			if (max_h != maxHeight) throw new RuntimeException("description");


			for (int i = 0; i < grid[0].length; i++) {
				int n_rows = 0;
				for (int j = 0; j < grid.length; j++) {
					if (grid[j][i]) n_rows++;
				}

				if (widths[i] != n_rows) throw new RuntimeException("description");
			}
		}
	}
	
	/**
	 Given a piece and an x, returns the y
	 value where the piece would come to rest
	 if it were dropped straight down at that x.
	 
	 <p>
	 Implementation: use the skirt and the col heights
	 to compute this fast -- O(skirt length).
	*/
	public int dropHeight(Piece piece, int x) {
		int res = 0;	//!!!
		for (int i = 0; i < piece.getWidth(); i++) {
			int dif = heights[x + i] - piece.getSkirt()[i];
			if (dif >= res) res = dif;
		}
		return res;
	}
	
	
	/**
	 Returns the height of the given column --
	 i.e. the y value of the highest block + 1.
	 The height is 0 if the column contains no blocks.
	*/
	public int getColumnHeight(int x) {
		return heights[x]; // YOUR CODE HERE
	}
	
	
	/**
	 Returns the number of filled blocks in
	 the given row.
	*/
	public int getRowWidth(int y) {
		 return widths[y]; // YOUR CODE HERE
	}
	
	
	/**
	 Returns true if the given block is filled in the board.
	 Blocks outside of the valid width/height area
	 always return true.
	*/
	public boolean getGrid(int x, int y) {
		if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) return true;
		return grid[x][y];
	}
	
	
	public static final int PLACE_OK = 0;
	public static final int PLACE_ROW_FILLED = 1;
	public static final int PLACE_OUT_BOUNDS = 2;
	public static final int PLACE_BAD = 3;
	
	/**
	 Attempts to add the body of a piece to the board.
	 Copies the piece blocks into the board grid.
	 Returns PLACE_OK for a regular placement, or PLACE_ROW_FILLED
	 for a regular placement that causes at least one row to be filled.
	 
	 <p>Error cases:
	 A placement may fail in two ways. First, if part of the piece may falls out
	 of bounds of the board, PLACE_OUT_BOUNDS is returned.
	 Or the placement may collide with existing blocks in the grid
	 in which case PLACE_BAD is returned.
	 In both error cases, the board may be left in an invalid
	 state. The client can use undo(), to recover the valid, pre-place state.
	*/
	public int place(Piece piece, int x, int y) {
		if (!committed) throw new RuntimeException("place commit problem");

		committed = false;
		back_up();

		int res = PLACE_OK;

		for (int i = 0; i < piece.getBody().length; i++) {
			int px = piece.getBody()[i].x + x;
			int py = piece.getBody()[i].y + y;

			if (px < 0 || px >= grid.length || py < 0 || py >= grid[0].length) return PLACE_OUT_BOUNDS;
			if (grid[px][py]) {
				return PLACE_BAD;
			} else {
				grid[px][py] = true;
				widths[py]++;
				if (py + 1 > heights[px]) heights[px] = py + 1;
				if (heights[px] > maxHeight) maxHeight = heights[px];

				if (widths[py] == width) res = PLACE_ROW_FILLED;
			}
		}

        sanityCheck();
		return res;
	}



	/**
	 Deletes rows that are filled all the way across, moving
	 things above down. Returns the number of rows cleared.
	*/
	public int clearRows() {
		int rowsCleared = 0;

		if (committed) {
			committed = false;
			back_up();
		}

		int to = 0;
		for (int i = 0; i < maxHeight; i++) {
			if (widths[i] == width) {
				rowsCleared++;
			} else {
				for (int j = 0; j < grid.length; j++) {
					grid[j][to] = grid[j][i];
				}
				widths[to] = widths[i];
				to++;
			}
		}

		for (int i = to; i < maxHeight; i++) {
			widths[i] = 0;
			for (int j = 0; j < grid.length; j++) {
				grid[j][i] = false;
			}
		}

		maxHeight = maxHeight - rowsCleared;

		for (int i = 0; i < grid.length; i++) {
			int col_h = 0;
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j]) col_h = j + 1;
			}
			heights[i] = col_h;
		}

		sanityCheck();
		return rowsCleared;
	}



	/**
	 Reverts the board to its state before up to one place
	 and one clearRows();
	 If the conditions for undo() are not met, such as
	 calling undo() twice in a row, then the second undo() does nothing.
	 See the overview docs.
	*/
	public void undo() {
		if (committed) return;

		int[] temp1 = widths;
		widths = xWidths;
		xWidths = temp1;

		int[] temp2 = heights;
		heights = xHeights;
		xHeights = temp2;

		maxHeight = xMaxHeight;

		boolean[][] temp4 = grid;
		grid = xGrid;
		xGrid = temp4;

		commit();
		sanityCheck();
	}
	
	
	/**
	 Puts the board in the committed state.
	*/
	public void commit() {committed = true;}


	
	/*
	 Renders the board state as a big String, suitable for printing.
	 This is the sort of print-obj-state utility that can help see complex
	 state change over time.
	 (provided debugging utility) 
	 */
	public String toString() {
		StringBuilder buff = new StringBuilder();
		for (int y = height - 1; y >= 0; y--) {
			buff.append('|');
			for (int x=0; x<width; x++) {
				if (getGrid(x,y)) buff.append('+');
				else buff.append(' ');
			}
			buff.append("|\n");
		}
		for (int x = 0; x < width + 2; x++) buff.append('-');
		return(buff.toString());
	}


	private void back_up() {
		for (int i = 0; i < grid.length; i++) {
			System.arraycopy(grid[i], 0, xGrid[i], 0, height);
		}
		System.arraycopy(widths, 0, xWidths, 0, widths.length);
		System.arraycopy(heights, 0, xHeights, 0, heights.length);
		xMaxHeight = maxHeight;
	}
}


