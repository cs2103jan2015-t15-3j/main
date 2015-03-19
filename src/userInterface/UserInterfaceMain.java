package userInterface;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


import java.util.Iterator;
import java.util.ListIterator;

import logic.LogicMain; //import LogicMain
import logic.Memory;
import logic.Task;

public class UserInterfaceMain{

	private JFrame frame;
	private JTable toDoTable;
	private static String userInput = new String();
	public static JTextField textFieldInput;
	
	Memory mem = new Memory();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
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
		tabbedPane.setBounds(94, 84, 600, 237);
		frame.getContentPane().add(tabbedPane);
		ImageIcon toDoIcon = new ImageIcon("images/toDoIcon.png");

		JPanel completedPanel = new JPanel();
		ImageIcon completedIcon = new ImageIcon("images/completedIcon.png");

		JPanel toDoPanel = new JPanel();
		tabbedPane.addTab("To-Do", toDoIcon, toDoPanel);
		toDoPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 595, 209);
		toDoPanel.add(scrollPane);;

		toDoTable = new JTable();
		
		toDoTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"ID", "Description", "Start", "End", "Remarks"
			}
		));
		toDoTable.getColumnModel().getColumn(0).setPreferredWidth(25);
		toDoTable.getColumnModel().getColumn(1).setPreferredWidth(228);
		toDoTable.getColumnModel().getColumn(2).setPreferredWidth(100);
		toDoTable.getColumnModel().getColumn(3).setPreferredWidth(100);
		toDoTable.getColumnModel().getColumn(4).setPreferredWidth(139);
		scrollPane.setViewportView(toDoTable);
		
		toDoTable.setEnabled(false);
		toDoTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		//completed table tab
		tabbedPane.addTab("Completed", completedIcon, completedPanel);

		JTextArea displayTextArea = new JTextArea();
		displayTextArea.setForeground(Color.WHITE);
		displayTextArea.setBackground(Color.DARK_GRAY);
		displayTextArea.setBounds(94, 332, 600, 28);
		frame.getContentPane().add(displayTextArea);
		
		textFieldInput = new JTextField();
		textFieldInput.setBounds(94, 377, 600, 28);
		frame.getContentPane().add(textFieldInput);
		textFieldInput.setColumns(10);

		
		KeyListener listener = new KeyListener() {

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					
					userInput = textFieldInput.getText().toString();
					
<<<<<<< HEAD
					mem = LogicMain.executeCommand(userInput, mem);
					ArrayList<Task> printList = mem.getBuffer();
					
					//testing if output is correct
					System.out.println(printList);
=======
					 mem = LogicMain.executeCommand(userInput, mem);
					 
					 ArrayList<Task> test = mem.getBuffer();
					 System.out.println(test);
>>>>>>> origin/master
					
					updateTable(printList);
					

					InputHistory.getInput(userInput);
					
					//logging records
					Logging.getInputLog(userInput);
					
					//displayTextArea.setText(mem.getFeedback());
					displayTextArea.setText("command accepted!");
					
					textFieldInput.setText(null);
					
				}
			}

			
			Task task = new Task();
			public void updateTable(ArrayList<Task> printList){
				
				Iterator<Task> ltr = printList.iterator();
				
				int i = 0;
				int j = 0;
				
				if(ltr.hasNext()){
					toDoTable.setValueAt(task.getTaskId(), 0, j);
					toDoTable.setValueAt(task.getTaskName().toString(), 0, j+1);
					toDoTable.setValueAt(task.getType(), 0, j+2);
					System.out.println(task.getTaskName());
					
					
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
		frame.setBounds(100, 100, 792, 478);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel proTaskLabel = new JLabel("ProTask");
		proTaskLabel.setFont(new Font("Tekton Pro", Font.PLAIN, 30));
		proTaskLabel.setBounds(94, 30, 175, 57);
		frame.getContentPane().add(proTaskLabel);
		ImageIcon proTaskIcon = new ImageIcon("images/Purple-Pear-400px.png");
		proTaskLabel.setIcon(proTaskIcon);
	}
}
