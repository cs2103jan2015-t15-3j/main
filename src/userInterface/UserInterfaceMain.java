package userInterface;

import java.util.ArrayList;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;

import logic.Appointment;
import logic.Deadline;
import logic.LogicMain;
import logic.Repository;
import logic.Task;

@SuppressWarnings("serial")
public class UserInterfaceMain extends JPanel {

	private JFrame frame;
	private static String userInput = new String();
	public static JTextField inputTextField;
	Repository mem = new Repository();
	protected static ArrayList<String> finalCloneCopy;
	private static JTextArea feedbackTextArea;
	public static ArrayList<String> finalClone = new ArrayList<String>();
	protected JPanel completedPanel;
	protected JPanel toDoPanel;

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
		tabbedPane.setBounds(43, 74, 541, 515);
		frame.getContentPane().add(tabbedPane);

		completedPanel = new JPanel();
		ImageIcon completedIcon = new ImageIcon("images/completedIcon.png");

		toDoPanel = new JPanel();
		toDoPanel.setForeground(Color.DARK_GRAY);
		toDoPanel.setBackground(Color.WHITE);
		ImageIcon toDoIcon = new ImageIcon("images/toDoIcon.png");

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVisible(true);

		tabbedPane.addTab("To-Do", toDoIcon, scrollPane, null);
		scrollPane.setViewportView(toDoPanel);
		toDoPanel.setLayout(new BoxLayout(toDoPanel, BoxLayout.Y_AXIS));

		// adding Jtable
		Create c = new Create();
		completedPanel.add(c);

		// completed table tab
		tabbedPane.addTab("Completed", completedIcon, completedPanel);

		feedbackTextArea = new JTextArea();
		feedbackTextArea.setForeground(Color.WHITE);
		feedbackTextArea.setBackground(Color.GRAY);
		feedbackTextArea.setBounds(43, 605, 541, 27);
		frame.getContentPane().add(feedbackTextArea);

		inputTextField = new JTextField();
		inputTextField.setBounds(43, 649, 541, 27);
		frame.getContentPane().add(inputTextField);
		inputTextField.setColumns(10);

		JLabel helpLabel = new JLabel("Press 'F1' in text field for Help Guide");
		helpLabel.setForeground(new Color(139, 0, 0));
		helpLabel.setBounds(43, 698, 581, 27);
		frame.getContentPane().add(helpLabel);

		KeyListener listener = new KeyListener() {

			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					userInput = inputTextField.getText().toString();

					// getFirstWord
					String firstWord = getFirstWord(userInput);

					// testing getFirstWord
					System.out.println(firstWord);

					mem = LogicMain.executeCommand(userInput, mem);

					// pass to printTempList/printList
					if (firstWord.toLowerCase().equals("search")) {

						toDoPanel.revalidate();
						toDoPanel.repaint();
						toDoPanel.removeAll();
						printTempLabel(mem);

					}

					else if (firstWord.toLowerCase().equals("sort")) {

						toDoPanel.revalidate();
						toDoPanel.repaint();
						toDoPanel.removeAll();
						printTempLabel(mem);

					}

					else {

						toDoPanel.revalidate();
						toDoPanel.repaint();
						toDoPanel.removeAll();
						printLabel(mem);

					}

					// commandHistory/InputHistory
					InputHistory.getInput(userInput);

					// logging testing
					Logging.getInputLog(userInput);

					feedbackTextArea.setText(mem.getFeedback());

					inputTextField.setText(null);

				} else if (e.getKeyCode() == KeyEvent.VK_F1) {
					HelpGuide.main(null);
				}
			}

			public void keyReleased(KeyEvent e) {
				InputHistory.retrieveInputText(e);
			}

			public void keyTyped(KeyEvent e) {
			}
		};
		inputTextField.addKeyListener(listener);
	}

	public void printLabel(Repository list) {

		int id;
		String name, start, end, remarks;

		for (Task task : list.getBuffer()) {

			String type = task.getType().toString();

			if (type.equals("APPOINTMENT")) {

				Appointment appt = (Appointment) task;

				id = task.getTaskID();
				name = task.getTaskName();
				start = appt.getStartDateString();
				end = appt.getDueDateString();
				remarks = task.getRemarks();
			}

			else if (type.equals("DEADLINE")) {
				Deadline dl = (Deadline) task;

				id = task.getTaskID();
				name = task.getTaskName();
				start = " - ";
				end = dl.getDueDateString();
				remarks = task.getRemarks();

			} else {

				id = task.getTaskID();
				name = task.getTaskName();
				start = " - ";
				end = " - ";
				remarks = task.getRemarks();
			}

			String str = "<html>" + "<br>" + id + ": " + " " + name + "<br>"
					+ "Start: " + start + " " + "Due: " + end + "<br>"
					+ "Remarks: " + remarks + "<br>";

			str += "____________________________________________________________________________"
					+ "</html>";

			JLabel JL = new JLabel(str);
			toDoPanel.add(JL);
		}
	}

	public void printTempLabel(Repository list) {

		int id;
		String name, start, end, remarks;

		for (Task task : list.getTempBuffer()) {

			String type = task.getType().toString();

			if (type.equals("APPOINTMENT")) {

				Appointment appt = (Appointment) task;

				id = task.getTaskID();
				name = task.getTaskName();
				start = appt.getStartDateString();
				end = appt.getDueDateString();
				remarks = task.getRemarks();
			}

			else if (type.equals("DEADLINE")) {
				Deadline dl = (Deadline) task;

				id = task.getTaskID();
				name = task.getTaskName();
				start = " - ";
				end = dl.getDueDateString();
				remarks = task.getRemarks();

			} else {

				id = task.getTaskID();
				name = task.getTaskName();
				start = " - ";
				end = " - ";
				remarks = task.getRemarks();
			}

			String str = "<html>" + "<br>" + id + ": " + " " + name + "<br>"
					+ "Start: " + start + " " + "Due: " + end + "<br>"
					+ "Remarks: " + remarks + "<br>";

			str += "____________________________________________________________________________"
					+ "</html>";

			JLabel JL = new JLabel(str);
			toDoPanel.add(JL);
		}
	}

	private void proTasklogo() {

		frame = new JFrame("ProTask");
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 28));
		frame.setResizable(false);
		frame.getContentPane().setEnabled(false);
		frame.setBounds(100, 100, 662, 817);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel proTaskLabel = new JLabel("ProTask");
		proTaskLabel.setForeground(Color.DARK_GRAY);
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
