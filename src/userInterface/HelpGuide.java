package userInterface;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

//@author A0112961L -reused

public class HelpGuide {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	private static void createAndShowGUI() {
		JFrame frame = new JFrame("HelpGuide");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(
				HelpGuide.class
						.getResource("/userInterface/ImageIcon/helpIcon.png")));

		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F1) {
					frame.dispose();
				}
			}
		});

		frame.setResizable(false);
		frame.setBounds(100, 100, 447, 470);
		frame.getContentPane().add(HelpDesign.createTabbedPane(),
				BorderLayout.NORTH);
		frame.setSize(734, 536);
		frame.setVisible(true);
		frame.setFocusable(true);
		frame.getContentPane().setLayout(null);
	}
}
