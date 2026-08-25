//
// TetrisGrid encapsulates a tetris board and has
// a clearRows() capability.

public class TetrisGrid {
	private boolean[][] grid;

	/**
	 * Constructs a new instance with the given grid.
	 * Does not make a copy.
	 * @param grid
	 */
	public TetrisGrid(boolean[][] grid) {
		this.grid = grid;
	}
	
	
	/**
	 * Does row-clearing on the grid (see handout).
	 */
	public void clearRows() {
		for (int i = 0; i < grid[0].length; i++) {
			boolean b = true;
			for (int j = 0; j < grid.length; j++) {
				if (!grid[j][i]) {
					b = false;
					break;
				}
			}
			if (b) {
				if (i == grid[0].length - 1) {
					for (int k = 0; k < grid.length; k++) grid[k][i] = false;
				}
				for (int k = i + 1; k < grid[0].length; k++) {
					for (int l = 0; l < grid.length; l++) {
						grid [l][k - 1] = grid[l][k];
						grid[l][k] = false;
					}
				}
				i--;
			}
		}
	}
	
	/**
	 * Returns the internal 2d grid array.
	 * @return 2d grid array
	 */
	boolean[][] getGrid() {
		return this.grid; // YOUR CODE HERE
	}
}
