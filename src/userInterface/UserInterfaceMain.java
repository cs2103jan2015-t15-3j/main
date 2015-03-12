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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
//import java.awt.event.KeyListener;
import java.util.ArrayList;

import logic.MainFunc; //import mainfunc

public class UserInterfaceMain implements ActionListener {

	private JFrame frame;
	private JTable toDoTable;
	private JTextField textFieldInput;

	// mainfunc import
	private MainFunc func;

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
		// func = new MainFunc();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		proTasklogo();

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(94, 118, 600, 237);
		frame.getContentPane().add(tabbedPane);
		ImageIcon toDoIcon = new ImageIcon("images/toDoIcon.png");

		JPanel completedPanel = new JPanel();
		ImageIcon completedIcon = new ImageIcon("images/completedIcon.png");

		JPanel toDoPanel = new JPanel();
		tabbedPane.addTab("To-Do", toDoIcon, toDoPanel);
		toDoPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 575, 187);
		toDoPanel.add(scrollPane);

		toDoTable = new JTable();
		toDoTable.setEnabled(false);
		toDoTable.setModel(new DefaultTableModel(new Object[][] {
				{ null, null, null, null, null },
				{ null, null, null, null, null },
				{ null, null, null, null, null }, },
				new String[] { "ID", "Description", "Due Date", "Start Time",
						"End Time", "Remarks" }));
		scrollPane.setViewportView(toDoTable);
		toDoTable.getTableHeader().setReorderingAllowed(false);
		toDoTable.getTableHeader().setResizingAllowed(false);

		TableColumnModel cResize = toDoTable.getColumnModel();
		cResize.getColumn(0).setPreferredWidth(20); // ID
		cResize.getColumn(1).setPreferredWidth(250); // Description
		cResize.getColumn(2).setPreferredWidth(80); // Date
		cResize.getColumn(3).setPreferredWidth(70); // StartTime
		cResize.getColumn(4).setPreferredWidth(70); // EndTime
		cResize.getColumn(5).setPreferredWidth(150); // Remarks

		tabbedPane.addTab("Completed", completedIcon, completedPanel);

		textFieldInput = new JTextField();
		textFieldInput.setBounds(94, 87, 488, 20);
		frame.getContentPane().add(textFieldInput);
		textFieldInput.setColumns(10);

		JButton enterButton = new JButton("Enter");
		enterButton.setBounds(592, 86, 89, 23);
		frame.getContentPane().add(enterButton);

		JTextArea displayTextArea = new JTextArea();
		displayTextArea.setForeground(Color.WHITE);
		displayTextArea.setBackground(Color.GRAY);
		displayTextArea.setBounds(94, 366, 600, 46);
		frame.getContentPane().add(displayTextArea);

		enterButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String input = textFieldInput.getText().toString();

				MainFunc.userInput(input);

				// static printing on Jtable
				toDoTable.setValueAt("1", 0, 0);

				String delimiter = " ";
				String[] temp = input.split(delimiter);

				toDoTable.setValueAt(temp[1], 0, 1);
				toDoTable.setValueAt(temp[2], 0, 2);
				toDoTable.setValueAt(temp[3], 0, 3);
				toDoTable.setValueAt(temp[4], 0, 4);
				toDoTable.setValueAt(temp[5], 0, 5); // remarks

				displayTextArea.setText(" added successfully !");

				textFieldInput.setText(null);

			}
		});

		textFieldInput.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String input = textFieldInput.getText().toString();

				MainFunc.userInput(input);

				// static printing on Jtable
				toDoTable.setValueAt(1, 0, 0);

				String delimiter = " ";
				String[] temp = input.split(delimiter);

				toDoTable.setValueAt(temp[1], 0, 1);
				toDoTable.setValueAt(temp[2], 0, 2);
				toDoTable.setValueAt(temp[3], 0, 3);
				toDoTable.setValueAt(temp[4], 0, 4);
				toDoTable.setValueAt(temp[5], 0, 5); // remarks

				displayTextArea.setText(" added successfully !");

				textFieldInput.setText(null);
			}
		});
		
	}

	// print list
	/*
	 * ArrayList<MainFunc> list = new ArrayList<MainFunc>();
	 * 
	 * for(int i = 0; i < list.size(); i++){
	 * 
	 * displayTextArea.setText(list.toString()); }
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
