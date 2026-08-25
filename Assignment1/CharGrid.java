// HW1 2-d array Problems
// CharGrid encapsulates a 2-d grid of chars and supports
// a few operations on the grid.

public class CharGrid {
	private char[][] grid;

	/**
	 * Constructs a new CharGrid with the given grid.
	 * Does not make a copy.
	 * @param grid
	 */
	public CharGrid(char[][] grid) {
		this.grid = grid;
	}
	
	/**
	 * Returns the area for the given char in the grid. (see handout).
	 * @param ch char to look for
	 * @return area for given char
	 */
	public int charArea(char ch) {
		if (grid.length == 0 || grid[0].length == 0) return 0;

		int x1 = Integer.MAX_VALUE;
		int y1 = Integer.MAX_VALUE;
		int x2 = -1;
		int y2 = -1;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == ch) {
					if (i < x1) x1 = i;
					if (i > x2) x2 = i;
					if (j < y1) y1 = j;
					if (j > y2) y2 = j;
				}
			}
		}

		if (x2 == -1) return 0;
		return (x2 - x1 + 1) * (y2 - y1 + 1);
	}
	
	/**
	 * Returns the count of '+' figures in the grid (see handout).
	 * @return number of + in grid
	 */
	public int countPlus() {
		int res = 0;

		for (int i = 1; i < grid.length - 1; i++) {
			for (int j = 1; j < grid[0].length - 1; j++) {
				if (isCenter(i, j)) res++;
			}
		}

		return res;
	}

	private int armLength(int i, int j, int x, int y, int a) {
		int res = 0;
		for (int k = 1; k <= a; k++) {
			if (grid[i + (k * x)][j + (k * y)] == grid[i][j]) {
				res++;
			} else {
				break;
			}
		}
		return res;
	}

	private boolean isCenter(int i, int j) {
		int up = armLength(i, j, -1, 0, i);
		int down = armLength(i, j, 1, 0, grid.length - i - 1);
		int left = armLength(i, j, 0, -1, j);
		int right = armLength(i ,j, 0, 1, grid[0].length - j - 1);

        return up == right && down == right && left == right && up > 0;
    }
}
