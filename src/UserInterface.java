import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
//import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JScrollPane;

public class UserInterface implements ActionListener {

	private JFrame frame;
	private JTextField textFieldInput;
	private JTable toDoTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface window = new UserInterface();
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
	public UserInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		proTaskLogo();

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(29, 93, 676, 268);
		frame.getContentPane().add(tabbedPane);

		JPanel panel = new JPanel();
		ImageIcon toDoIcon = new ImageIcon("images/toDoIcon.png");
		// ImageIcon tab1Icon = new
		// ImageIcon(this.getClass().getResource("/images/toDoIcon.png"));
		tabbedPane.addTab("To-Do", toDoIcon, panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 651, 215);
		panel.add(scrollPane);

		toDoTable = new JTable();
		toDoTable.setEnabled(false);
		scrollPane.setViewportView(toDoTable);

		toDoTable.setModel(new DefaultTableModel(new Floating[][] {
				{ "1", "testing", null, null, null },
				{ null, null, null, null, null },
				{ null, null, null, null, null }, }, new String[] { "ID",
				"Description", "Start Time", "End Time", "Remarks" }));

		toDoTable.getTableHeader().setReorderingAllowed(false);
		toDoTable.getTableHeader().setResizingAllowed(false);

		toDoTable.setModel(new DefaultTableModel(new Floating[][] {
				{ "1", "testing", null, null, null },
				{ null, null, null, null, null },
				{ null, null, null, null, null }, }, new String[] { "ID",
				"Description", "Start", "End", "Remarks" }));

		TableColumnModel cResize = toDoTable.getColumnModel();
		cResize.getColumn(0).setPreferredWidth(20); // ID
		cResize.getColumn(1).setPreferredWidth(150); // Description
		cResize.getColumn(2).setPreferredWidth(60); // StartTime
		cResize.getColumn(3).setPreferredWidth(60); // EndTime
		cResize.getColumn(4).setPreferredWidth(300); // Remarks

		JPanel panel_1 = new JPanel();
		ImageIcon completedIcon = new ImageIcon("images/completedIcon.png");
		tabbedPane.addTab("Completed", completedIcon, panel_1);

		textFieldInput = new JTextField();
		textFieldInput.setBounds(29, 62, 536, 20);
		frame.getContentPane().add(textFieldInput);
		textFieldInput.setColumns(10);

		JLabel displayLabel = new JLabel("");
		displayLabel.setForeground(Color.BLACK);
		displayLabel.setBackground(Color.GRAY);
		displayLabel.setBounds(29, 360, 676, 40);
		frame.getContentPane().add(displayLabel);

		JButton enterButton = new JButton("Enter");
		enterButton.addActionListener(new ActionListener() {

			// get user input and print on label (testing)
			public void actionPerformed(ActionEvent e) {

				String input = textFieldInput.getText().toString();
				displayLabel.setText(input);

				// JOptionPane.showMessageDialog(enterButton, "testing");
			}
		});

		enterButton.setBounds(585, 61, 89, 23);
		frame.getContentPane().add(enterButton);
	}

	// OOP
	private void proTaskLogo() {
		frame = new JFrame("ProTask");
		frame.setResizable(false);
		frame.setBounds(100, 100, 740, 455);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel proTaskLabel = new JLabel("ProTask");
		proTaskLabel.setFont(new Font("Stencil Std", Font.BOLD, 30));
		proTaskLabel.setBounds(29, 11, 200, 50);
		frame.getContentPane().add(proTaskLabel);
		ImageIcon proTaskIcon = new ImageIcon("src/Purple-Pear-400px.png");
		proTaskLabel.setIcon(proTaskIcon);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
