import javax.swing.*;
import java.awt.*;

public class JBrainTetris extends JTetris{
    private JSlider adversary;
    private JLabel adversary_status;
    private JCheckBox brainMode;

    private DefaultBrain brain = new DefaultBrain();
    private int previousCount = 0;
    private Brain.Move bestMove;

    /**
     * Creates a new JTetris where each tetris square
     * is drawn with the given number of pixels.
     *
     * @param pixels
     */
    JBrainTetris(int pixels) {
        super(pixels);
    }

    public static void main(String[] args) {
        // Set GUI Look And Feel Boilerplate.
        // Do this incantation at the start of main() to tell Swing
        // to use the GUI LookAndFeel of the native platform. It's ok
        // to ignore the exception.
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) { }

        JBrainTetris tetris = new JBrainTetris(16);
        JFrame frame = JTetris.createFrame(tetris);
        frame.setVisible(true);
    }

    @Override
    public JComponent createControlPanel() {
        JComponent panel = super.createControlPanel();

        panel.add(new JLabel("Brain:"));
        brainMode = new JCheckBox("Brain active");
        panel.add(brainMode);

        JPanel little = new JPanel();
        little.add(new JLabel("Adversary:"));-

        adversary = new JSlider(0, 100, 0);
        adversary.setPreferredSize(new Dimension(100, 15));
        little.add(adversary);

        adversary_status = new JLabel("ok");
        little.add(adversary_status);

        panel.add(little);

        return panel;
    }

    @Override
    public Piece pickNextPiece() {
        int slider = adversary.getValue();
        int rand = random.nextInt(99) + 1;

        if (rand >= slider) {
            adversary_status.setText("ok");
            return super.pickNextPiece();
        }

        adversary_status.setText("*ok*");

        Piece worstPiece = null;
        double worstScore = 0.0;

        for (int i = 0; i < pieces.length; i++) {
            Piece current = pieces[i];
            Brain.Move move = brain.bestMove(board, current, HEIGHT, null);

            if (move != null) {
                if (worstPiece == null || move.score > worstScore) {
                    worstPiece = current;
                    worstScore = move.score;
                }
            }
        }

        if (worstPiece == null) return super.pickNextPiece();

        return worstPiece;
    }

    @Override
    public void tick(int verb) {
        if (brainMode.isSelected() && verb == DOWN) {
            if (count != previousCount) {
                previousCount = count;
                board.undo();
                bestMove = brain.bestMove(board, currentPiece, HEIGHT, bestMove);
            }

            if (bestMove != null) {
                if (!currentPiece.equals(bestMove.piece)) super.tick(ROTATE);

                if (currentX > bestMove.x) {
                    super.tick(LEFT);
                } else if (currentX < bestMove.x) {
                    super.tick(RIGHT);
                }
            }
        }

        super.tick(verb);
    }
}
