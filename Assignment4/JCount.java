// JCount.java

/*
 Basic GUI/Threading exercise.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JCount extends JPanel {
	private JLabel lbl;
	private JTextField txt;
	private Thread worker;

	public JCount() {
		// Set the JCount to use Box layout
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		lbl = new JLabel("0");
		txt = new JTextField("100000000");
		JButton start = new JButton("Start");
		JButton stop = new JButton("Stop");

		add(lbl);
		add(txt);
		add(start);
		add(stop);
		add(Box.createRigidArea(new Dimension(0,40)));

		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (worker != null) worker.interrupt();

				int num = Integer.parseInt(txt.getText());
				worker = new Worker(num);
				worker.start();
			}
		});

		stop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (worker != null) {
					worker.interrupt();
					worker = null;
				}
			}
		});
	}
	
	static public void main(String[] args)  {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// Creates a frame with 4 JCounts in it.
				// (provided)
				JFrame frame = new JFrame("The Count");
				frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

				frame.add(new JCount());
				frame.add(new JCount());
				frame.add(new JCount());
				frame.add(new JCount());

				frame.pack();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

	private class Worker extends Thread {
		private int num;

		public Worker(int num) {
			this.num = num;
		}

		@Override
		public void run() {
			for (int i = 0; i <= num; i++) {
				if (isInterrupted()) break;

				if (i % 10000 == 0) {
					try {
						Thread.sleep(100);
					} catch (Exception e) {
						break;
					}

					String curr = String.valueOf(i);
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							lbl.setText(curr);
						}
					});
				}
			}
		}
	}
}

