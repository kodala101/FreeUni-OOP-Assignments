import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class CrackerTest {
    @Test
    public void testHashAndHax() {
        String hex = "4181eecbd7a755d19fdf73887c54837cbecf63fd";
        byte[] b = Cracker.hexToArray(hex);
        String s = Cracker.hexToString(b);

        assertEquals(hex, s);
    }

    @Test
    public void testEverything() {
        ByteArrayOutputStream catcher = new ByteArrayOutputStream();
        System.setOut(new PrintStream(catcher));

        String targ = "4181eecbd7a755d19fdf73887c54837cbecf63fd";
        String[] input = {targ, "5", "8"};
        Cracker.main(input);

        String output = catcher.toString();

        assertTrue(output.contains("molly"));
        assertTrue(output.contains("all done"));

        System.setOut(System.out);
    }

    @Test
    public void testRanges() {
        ByteArrayOutputStream catcher = new ByteArrayOutputStream();
        System.setOut(new PrintStream(catcher));

        String targ = "86f7e437faa5a7fce15d1ddcb9eaeaea377667b8";
        String[] input = {targ, "1", "40"};
        Cracker.main(input);

        String output = catcher.toString();

        assertTrue(output.contains("a"));
        System.setOut(System.out);
    }

    @Test
    public void testGenerate() {
        ByteArrayOutputStream catcher = new ByteArrayOutputStream();
        System.setOut(new PrintStream(catcher));

        String[] input = {"molly"};
        Cracker.main(input);

        String output = catcher.toString();

        assertTrue(output.contains("4181eecbd7a755d19fdf73887c54837cbecf63fd"));
    }
}
