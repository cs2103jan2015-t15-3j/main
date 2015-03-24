package userInterface;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;

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
		frame = new JFrame("HelpGuide");
		frame.setBounds(100, 100, 484, 524);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		KeyListener listener = new KeyListener() {

			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_F1){
					frame.dispose();
			}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}		
		};
	
	    frame.addKeyListener(listener);
		
		JLabel helpLabel = new JLabel("Press 'F1' again to exit Help Guide");
		helpLabel.setForeground(Color.RED);
		helpLabel.setBounds(10, 453, 267, 22);
		frame.getContentPane().add(helpLabel);
		

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setEnabled(false);
		textArea.setForeground(Color.RED);
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

