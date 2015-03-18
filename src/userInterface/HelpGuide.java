package userInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.Color;

public class HelpGuide {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HelpGuide window = new HelpGuide();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HelpGuide() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 484, 524);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setEnabled(false);
		textArea.setForeground(Color.BLACK);
		textArea.setBackground(Color.DARK_GRAY);
		textArea.setBounds(0, 0, 468, 486);
		frame.getContentPane().add(textArea);

		textArea.setText("ProTask ReadMe \n"
				+ "=================================\n"
				+ "1. List Of Commands\n"
				+ "Add	\n"
				+ "- add <description> \n"
				+ "- add <description> <dueDate> <startTime> <endTime> <remarks>\n"
				+ "\n"
				+ "Edit\n"
				+ "- edit <ID> <dueDate> <startTime> <endTime> <remarks>\n"
				+ "- edit <description> <dueDate> <startTime> <endTime> <remarks>\n"
				+ "\n" + "Delete\n" + "- del <ID>  \n" + "\n" + "Undo\n"
				+ "- undo\n" + "\n" + "Complete\n" + "- complete <ID> \n"
				+ "- complete <description>\n" + "\n"
				+ "Search / PowerSearch\n" + "- search <description>  \n"
				+ "- psearch <keywords>\n");
	}
}
