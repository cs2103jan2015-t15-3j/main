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
import logic.Repository;
import logic.Task;

@SuppressWarnings("serial")
public class UserInterfaceMain extends JPanel{

	private JFrame frame;
	private static String userInput = new String();
	public static JTextField textFieldInput;
	Repository mem = new Repository();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				//Repository mem = new Repository();
				try {
					UserInterfaceMain window = new UserInterfaceMain();
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
	public UserInterfaceMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		proTasklogo();
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(54, 74, 600, 265);
		frame.getContentPane().add(tabbedPane);

		JPanel completedPanel = new JPanel();
		ImageIcon completedIcon = new ImageIcon("images/completedIcon.png");

		JPanel toDoPanel = new JPanel();
		ImageIcon toDoIcon = new ImageIcon("images/toDoIcon.png");
		
		//add table (Creator) into toDoPanel
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
		
		JLabel helpLabel = new JLabel("Press 'F1' in text field for Help Guide");
		helpLabel.setForeground(Color.RED);
		helpLabel.setBounds(54, 422, 329, 14);
		frame.getContentPane().add(helpLabel);

		KeyListener listener = new KeyListener() {

			public void keyPressed(KeyEvent e) {
				
				
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					userInput = textFieldInput.getText().toString();
				
					String firstWord = getFirstWord(userInput);
					
					//testing purpose
					System.out.println(firstWord);

					mem = LogicMain.executeCommand(userInput, mem);

					ArrayList<Task> printList = mem.getBuffer();
					ArrayList<Task> searchList = mem.getTempBuffer();
					
					//testing if output is correct
					System.out.println(printList);

					if(firstWord.toLowerCase().equals("search"))
					{
						create.updateTable(searchList);
						
						//testing prints
						System.out.println("searchList in");
						System.out.println(mem.getTempBuffer().size());
						System.out.println(mem.getTempBuffer());
						System.out.println(mem.getBuffer());
					}
					else 
					{
					create.updateTable(printList);
					System.out.println("printList in");
					}
					
					InputHistory.getInput(userInput);

					//logging testing
					Logging.getInputLog(userInput);

					displayTextArea.setText(mem.getFeedback());

					textFieldInput.setText(null);
				}
				else if(e.getKeyCode() == KeyEvent.VK_F1){		
					HelpGuide.main(null);		
					
				}
			}

			public void keyReleased(KeyEvent e) {
				InputHistory.retrieveInputText(e);
				
			}

			public void keyTyped(KeyEvent e) {
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
		
	// This method will return the command user enters.
		public static String getFirstWord(String userCommand) {
			String commandTypeString = userCommand.trim().split("\\s+")[0];
			return commandTypeString;
}
}


