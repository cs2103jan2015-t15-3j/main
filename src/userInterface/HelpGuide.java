package userInterface;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;

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
		// Create and set up the window.
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

		// call the method to change the appearance of the frame
		// UserInterfaceHelpMenu.changeAppearance();

		// Create and set up the content pane.
		frame.setResizable(false);
		frame.setBounds(100, 100, 468, 511);

		frame.getContentPane().add(HelpDesign.createTabbedPane(),
				BorderLayout.NORTH);
		// frame.getContentPane().add(UserInterfaceHelpMenu.createMenuTab(),
		// BorderLayout.NORTH);

		// Display the window.
		// frame.setSize(350, 378);
		frame.setVisible(true);

		// Set the location of the Help Menu on the top right corner
		// frame.setLocation(Xcoordinate, Ycoordinate);

		// Set the focus to the main frame
		frame.setFocusable(true);
		frame.getContentPane().setLayout(null);

		frame.getContentPane().add(HelpDesign.createExitLabel());

	}
}
