package userInterface;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import logic.LogicMain;
import logic.Memory;
import logic.Task;

public class UserInterfaceMain extends JPanel{

	private JFrame frame;
	private static String userInput = new String();
	public static JTextField textFieldInput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Memory mem = new Memory();
				try {
					UserInterfaceMain window = new UserInterfaceMain(mem);
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
	public UserInterfaceMain(Memory mem) {
		initialize(mem);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Memory mem) {
		proTasklogo();
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(54, 74, 600, 265);
		frame.getContentPane().add(tabbedPane);

		JPanel completedPanel = new JPanel();
		ImageIcon completedIcon = new ImageIcon("images/completedIcon.png");

		JPanel toDoPanel = new JPanel();
		ImageIcon toDoIcon = new ImageIcon("images/toDoIcon.png");
		
		//add table (Creator) here
		Creator create = new Creator();
		toDoPanel.add(create);
		
		//Initial loading of table
		ArrayList<Task> printList = mem.getBuffer();
		create.updateTable(printList);
		
		
		tabbedPane.addTab("To-Do", toDoIcon, toDoPanel);
		//toDoPanel.setLayout(null);

		//completed table tab
		tabbedPane.addTab("Completed", completedIcon, completedPanel);

		JTextArea displayTextArea = new JTextArea();
		displayTextArea.setForeground(Color.WHITE);
		displayTextArea.setBackground(Color.GRAY);
		displayTextArea.setBounds(54, 350, 600, 27);
		frame.getContentPane().add(displayTextArea);

		textFieldInput = new JTextField();
		textFieldInput.setBounds(54, 395, 600, 27);
		frame.getContentPane().add(textFieldInput);
		textFieldInput.setColumns(10);


		KeyListener listener = new KeyListener() {

			public void keyPressed(KeyEvent e) {
				Memory mem = new Memory();
				
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					userInput = textFieldInput.getText().toString();

					mem = LogicMain.executeCommand(userInput, mem);

					ArrayList<Task> printList = mem.getBuffer();

					//testing if output is correct
					System.out.println(printList);

					//pass Arraylist to Creator
					create.updateTable(printList);

					InputHistory.getInput(userInput);

					//logging records
					Logging.getInputLog(userInput);

					//displayTextArea.setText(mem.getFeedback());
					displayTextArea.setText("command accepted successfully!");

					textFieldInput.setText(null);
				}
				else if(e.getKeyCode() == KeyEvent.VK_H){		
					HelpGuide.main(null);				
				}
			}

			public void keyReleased(KeyEvent e) {
				InputHistory.retrieveInputText(e);
			}

			public void keyTyped(KeyEvent e) {
				
				/*
				 * if(e.getKeyCode() == KeyEvent.VK_F1){ HelpGuide.main(null); }
				 */
			}
		};
		textFieldInput.addKeyListener(listener);
	}


	private void proTasklogo() {

		frame = new JFrame("ProTask");
		frame.setBounds(100, 100, 753, 485);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel proTaskLabel = new JLabel("ProTask");
		proTaskLabel.setFont(new Font("Tekton Pro", Font.PLAIN, 30));
		proTaskLabel.setBounds(54, 26, 175, 57);
		frame.getContentPane().add(proTaskLabel);
		ImageIcon proTaskIcon = new ImageIcon("images/Purple-Pear-400px.png");
		proTaskLabel.setIcon(proTaskIcon);
	}
}
