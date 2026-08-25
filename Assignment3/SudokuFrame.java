import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;

 public class SudokuFrame extends JFrame {
	 private JTextArea input;
	 private JTextArea output;
	 private JButton check;
	 private JCheckBox autoCheck;
	
	public SudokuFrame() {
		super("Sudoku Solver");
		setLayout(new BorderLayout(4, 4));

		input = new JTextArea(15, 20);
		output = new JTextArea(15, 20);
		check = new JButton("Check");
		autoCheck = new JCheckBox("Auto Check");
		autoCheck.setSelected(true);

		input.setBorder(new TitledBorder("Puzzle"));
		add(input, BorderLayout.CENTER);

		output.setBorder(new TitledBorder("Solution"));
		output.setEditable(false);
		add(output, BorderLayout.EAST);

		Box controls = Box.createHorizontalBox();
		add(controls, BorderLayout.SOUTH);
		controls.add(check);
		controls.add(autoCheck);

		setLocationByPlatform(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		check.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tryToSolve();
			}
		});

		input.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				if (autoCheck.isSelected()) tryToSolve();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				if (autoCheck.isSelected()) tryToSolve();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				if (autoCheck.isSelected()) tryToSolve();
			}
		});

		pack();
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		// GUI Look And Feel
		// Do this incantation at the start of main() to tell Swing
		// to use the GUI LookAndFeel of the native platform. It's ok
		// to ignore the exception.
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ignored) { }
		
		SudokuFrame frame = new SudokuFrame();
	}

	private void tryToSolve() {
		try {
			String txt = input.getText();
			int[][] grid = Sudoku.textToGrid(txt);
			Sudoku sudoku = new Sudoku(grid);
			int count = sudoku.solve();

			String solved = "";
			if (count > 0) {
				solved += sudoku.getSolutionText() + "\n";
			}

			solved = solved + "solutions: " + count + "\n";
			solved = solved + "elapsed: " + sudoku.getElapsed() + "ms" + "\n";

			output.setText(solved);
		} catch (Exception e) {
			output.setText("Parsing problem");
		}
	}
}
