package userInterface;

import java.awt.EventQueue;
import java.awt.TextField;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.DefaultCellEditor;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.util.ArrayList;

import logic.LogicMain; //import LogicMain
import logic.Memory;

public class UserInterfaceMain implements KeyListener {

	private JFrame frame;
	public static JTextField textFieldInput;
	private JTable toDoTable;
	private static String userInput = new String();
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
		toDoPanel.add(scrollPane);

		TableMain.setupTable();

		toDoTable = new JTable();
		toDoTable.setEnabled(false);
		toDoTable.setModel(new DefaultTableModel(new Object[][] {},
						new String[] { "ID", "Description", "Start", "End",
								"Remarks" }));
		toDoTable.getColumnModel().getColumn(0).setPreferredWidth(25);
		toDoTable.getColumnModel().getColumn(1).setPreferredWidth(228);
		toDoTable.getColumnModel().getColumn(2).setPreferredWidth(100);
		toDoTable.getColumnModel().getColumn(3).setPreferredWidth(100);
		toDoTable.getColumnModel().getColumn(4).setPreferredWidth(139);
		scrollPane.setViewportView(toDoTable);

		toDoTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		tabbedPane.addTab("Completed", completedIcon, completedPanel);

		textFieldInput = new JTextField();
		textFieldInput.setBounds(94, 377, 600, 28);
		frame.getContentPane().add(textFieldInput);
		textFieldInput.setColumns(10);

		JTextArea displayTextArea = new JTextArea();
		displayTextArea.setForeground(Color.WHITE);
		displayTextArea.setBackground(Color.DARK_GRAY);
		displayTextArea.setBounds(94, 332, 600, 28);
		frame.getContentPane().add(displayTextArea);

		// table = TableMain.setupTable(shell);
		// load data into the table in the beginning
		// Loader.populateTable(table, mem);



		KeyListener listener = new KeyListener() {

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					userInput = textFieldInput.getText().toString();
					
					 mem = LogicMain.executeCommand(userInput, mem);
					
					InputHistory.getInput(userInput);
					//pass to logging
					Logging.getInputLog(userInput);

					displayTextArea.setText("command accepted!");
					textFieldInput.setText(null);
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

	/*
	 * // static printing on Jtable /*toDoTable.setValueAt(1, 0, 0);
	 * 
	 * String delimiter = " "; String[] temp = input.split(delimiter);
	 * 
	 * toDoTable.setValueAt(temp[1], 0, 1); toDoTable.setValueAt(temp[2], 0, 2);
	 * toDoTable.setValueAt(temp[3], 0, 3); toDoTable.setValueAt(temp[4], 0, 4);
	 * toDoTable.setValueAt(temp[5], 0, 5); // remarks
	 */

	private void proTasklogo() {

		// TODO Auto-generated method stub
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
