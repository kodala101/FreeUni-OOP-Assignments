import org.junit.Test;
import static org.junit.Assert.*;

public class SudokuTest {
    @Test
    public void TestEasyGrid() {
        Sudoku sudoku = new Sudoku(Sudoku.easyGrid);
        int count = sudoku.solve();
        assertTrue(sudoku.getElapsed() >= 0);
        assertEquals(1, count);
        assertNotNull(sudoku.getSolutionText());
    }

    @Test
    public void TestMediumGrid() {
        Sudoku sudoku = new Sudoku(Sudoku.mediumGrid);
        int count = sudoku.solve();
        assertTrue(sudoku.getElapsed() >= 0);
        assertNotNull(sudoku.getSolutionText());
    }

    @Test
    public void TestHardGrid() {
        Sudoku sudoku = new Sudoku(Sudoku.hardGrid);
        int count = sudoku.solve();
        assertTrue(sudoku.getElapsed() >= 0);
        assertEquals(1, count);
        assertNotNull(sudoku.getSolutionText());
    }

    @Test
    public void TestHardGridChanged() {
        int[][] changedGrid = Sudoku.stringsToGrid(
                "3 0 0 0 0 0 0 8 0",
                "0 0 1 0 9 3 0 0 0",
                "0 4 0 7 8 0 0 0 3",
                "0 9 3 8 0 0 0 1 2",
                "0 0 0 0 4 0 0 0 0",
                "5 2 0 0 0 6 7 9 0",
                "6 0 0 0 2 1 0 4 0",
                "0 0 0 5 3 0 9 0 0",
                "0 3 0 0 0 0 0 5 1");

        Sudoku sudoku = new Sudoku(changedGrid);
        int count = sudoku.solve();
        assertTrue(sudoku.getElapsed() >= 0);
        assertEquals(6, count);
        assertNotNull(sudoku.getSolutionText());
    }

    @Test
    public void TestTextToGrid() {
        String txt = "000000000" +
                "000000000" +
                "000000000" +
                "000000000" +
                "000000000" +
                "000000000" +
                "000000000" +
                "000000000" +
                "000000000";

        int[][] grid = Sudoku.textToGrid(txt);
        Sudoku sudoku = new Sudoku(grid);
        Sudoku.Spot sp = sudoku.new Spot(0, 0);
        assertEquals(0, sp.get());
        int count = sudoku.solve();
        assertTrue(sudoku.getElapsed() >= 0);
        assertEquals(100, count);
        assertNotNull(sudoku.getSolutionText());
    }
}
