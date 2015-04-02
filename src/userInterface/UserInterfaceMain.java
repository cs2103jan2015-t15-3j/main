package userInterface;

import java.util.ArrayList;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;

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
					System.out.println("Application can't launched");
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
		tabbedPane.setBounds(43, 28, 541, 561);
		frame.getContentPane().add(tabbedPane);

		completedPanel = new JPanel();
		completedPanel.setForeground(Color.DARK_GRAY);
		completedPanel.setBackground(Color.WHITE);
		ImageIcon completedIcon = new ImageIcon(
				(UserInterfaceMain.class
						.getResource("/userInterface/ImageIcon/completedIcon.png")));

		toDoPanel = new JPanel();
		toDoPanel.setForeground(Color.DARK_GRAY);
		toDoPanel.setBackground(Color.WHITE);
		ImageIcon toDoIcon = new ImageIcon(
				(UserInterfaceMain.class
						.getResource("/userInterface/ImageIcon/toDoIcon.png")));

		
		try{
			mem = LogicMain.getAllTasks(mem);
			toDoPanel.revalidate();
			toDoPanel.repaint();
			toDoPanel.removeAll();
			printLabel(mem);
		}
		
		catch(FileNotFoundException e1){
			
		}
		JScrollPane toDoScroller = new JScrollPane();
		toDoScroller.setVisible(true);

		tabbedPane.addTab("To-Do", toDoIcon, toDoScroller, null);
		toDoScroller.setViewportView(toDoPanel);
		toDoPanel.setLayout(new BoxLayout(toDoPanel, BoxLayout.Y_AXIS));

		JScrollPane completedScroller = new JScrollPane();
		completedScroller.setVisible(true);

		tabbedPane.addTab("Completed", completedIcon, completedScroller, null);
		completedScroller.setViewportView(completedPanel);
		completedPanel
				.setLayout(new BoxLayout(completedPanel, BoxLayout.Y_AXIS));

		feedbackTextArea = new JTextArea();
		feedbackTextArea.setForeground(Color.WHITE);
		feedbackTextArea.setBackground(Color.GRAY);
		feedbackTextArea.setBounds(43, 605, 541, 27);
		frame.getContentPane().add(feedbackTextArea);

		inputTextField = new JTextField();
		inputTextField.setBounds(43, 649, 541, 27);
		frame.getContentPane().add(inputTextField);
		inputTextField.setColumns(10);

		JLabel helpLabel = new JLabel("Press 'F1' for Help Guide");
		helpLabel.setForeground(new Color(0, 139, 139));
		helpLabel.setBounds(43, 680, 581, 27);
		frame.getContentPane().add(helpLabel);

		KeyListener listener = new KeyListener() {

			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					userInput = inputTextField.getText().toString();

					// getFirstWord
					String firstWord = getFirstWord(userInput);

					// testing getFirstWord
					System.out.println(firstWord);

					mem = LogicMain.parseString(userInput, mem);

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

					completedPanel.revalidate();
					completedPanel.repaint();
					completedPanel.removeAll();
					printCompletedLabel(mem);

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

	protected void printCompletedLabel(Repository list) {

		int id;
		String name, start, end, remarks;

		for (Task task : list.getBuffer()) {

			boolean completed = task.getCompleted();

			if (completed == true) {

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

				String str = "<html>" + "<br>" + id + ": " + " " + name
						+ "<br>" + "Start: " + start + " " + "Due: " + end
						+ "<br>" + "Remarks: " + remarks + "<br>";

				str += "____________________________________________________________________________"
						+ "</html>";

				JLabel CL = new JLabel(str);
				completedPanel.add(CL);
			}
		}
	}

	public void printLabel(Repository list) {

		int id;
		String name, start, end, remarks;

		for (Task task : list.getBuffer()) {

			boolean completed = task.getCompleted();

			if (completed == false) {

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

				String str = "<html>" + "<br>" + id + ": " + " " + name
						+ "<br>" + "Start: " + start + " " + "Due: " + end
						+ "<br>" + "Remarks: " + remarks + "<br>";

				str += "____________________________________________________________________________"
						+ "</html>";

				JLabel JL = new JLabel(str);
				toDoPanel.add(JL);
			}
		}
	}

	public void printTempLabel(Repository list) {

		int id;
		String name, start, end, remarks;

		for (Task task : list.getTempBuffer()) {

			boolean completed = task.getCompleted();

			if (completed == false) {

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

				String str = "<html>" + "<br>" + id + ": " + " " + name
						+ "<br>" + "Start: " + start + " " + "Due: " + end
						+ "<br>" + "Remarks: " + remarks + "<br>";

				str += "____________________________________________________________________________"
						+ "</html>";

				JLabel JL = new JLabel(str);
				toDoPanel.add(JL);
			}
		}
	}

	private void proTasklogo() {

		frame = new JFrame("ProTask");
		frame.setResizable(false);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(UserInterfaceMain.class.getResource("/userInterface/ImageIcon/proTaskLogo.png")));
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 28));
		frame.getContentPane().setEnabled(false);
		frame.setBounds(100, 100, 638, 829);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

	}

	// This method will return the command user enters.
	public static String getFirstWord(String userCommand) {
		String commandTypeString = userCommand.trim().split("\\s+")[0];
		return commandTypeString;
	}
}
