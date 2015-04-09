package userInterface;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;

import logic.Compare;
import logic.LogicMain;
import logic.Repository;
import logic.Task;
import logic.Message;

@SuppressWarnings("serial")
public class UserInterfaceMain extends JPanel {

	private JFrame frame;
	private static String userInput = new String();
	protected static JTextField inputTextField;
	private static JTextArea feedbackTextArea;
	private JPanel completedPanel;
	private JPanel toDoPanel;
	private JTabbedPane tabbedPane;
	private AdjustmentListener adjustListener;

	private static final Stack<String> stack = new Stack<String>();
	private static final Stack<String> temp = new Stack<String>();

	Repository mem = new Repository();
	private JScrollPane scrollPane;

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
	 * 
	 * @throws FileNotFoundException
	 */
	public UserInterfaceMain() throws FileNotFoundException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws FileNotFoundException
	 */
	private void initialize() throws FileNotFoundException {
		proTaskFrame();

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(38, 14, 448, 561);
		frame.getContentPane().add(tabbedPane);

		completedPanel = new JPanel();
		completedPanel.setForeground(Color.DARK_GRAY);
		completedPanel.setBackground(new Color(245, 245, 245));
		ImageIcon completedIcon = new ImageIcon(
				(UserInterfaceMain.class
						.getResource("/userInterface/ImageIcon/completedIcon.png")));

		toDoPanel = new JPanel();
		toDoPanel.setForeground(Color.DARK_GRAY);
		toDoPanel.setBackground(new Color(245, 245, 245));
		ImageIcon toDoIcon = new ImageIcon(
				(UserInterfaceMain.class
						.getResource("/userInterface/ImageIcon/toDoIcon.png")));

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

		scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 591, 448, 46);
		frame.getContentPane().add(scrollPane);

		feedbackTextArea = new JTextArea();
		scrollPane.setViewportView(feedbackTextArea);
		feedbackTextArea.setFont(new Font("Trebuchet MS", Font.BOLD
				| Font.ITALIC, 16));
		feedbackTextArea.setForeground(Color.BLACK);
		feedbackTextArea.setBackground(Color.LIGHT_GRAY);

		feedbackTextArea.setText(Message.WELCOME);

		inputTextField = new JTextField();
		inputTextField.setBounds(38, 651, 448, 27);
		frame.getContentPane().add(inputTextField);
		inputTextField.setColumns(10);

		JLabel helpLabel = new JLabel("Press 'F1' for Help Guide");
		helpLabel.setForeground(new Color(0, 139, 139));
		helpLabel.setBounds(38, 679, 448, 27);
		frame.getContentPane().add(helpLabel);

		// initial load
		mem = LogicMain.loadStorage();
		clearAndReloadBothPanel();

		KeyListener listener = new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					userInput = inputTextField.getText().toString();

					mem = LogicMain.parseString(userInput, mem);

					String firstWord = getFirstWord(userInput);

					// testing getFirstWord
					System.out.println(firstWord);

					try {
						displaySetting(firstWord);

					} catch (EmptyStackException e1) {

						Logging.getInputLog("Empty Stack Exception from Display Setting");
					}
					// ScrollPane adjust automatically when new input is entered
					adjustListener = new AdjustmentListener() {

						public void adjustmentValueChanged(AdjustmentEvent e) {

							e.getAdjustable().setValue(
									e.getAdjustable().getMaximum());
						}
					};
					toDoScroller.getVerticalScrollBar().addAdjustmentListener(
							adjustListener);

					completedScroller.getVerticalScrollBar()
							.addAdjustmentListener(adjustListener);

					InputHistory.getInput(userInput);

					Logging.getInputLog(userInput);

					feedbackTextArea.setText(mem.getFeedback());

					inputTextField.setText(null);

				} else if (e.getKeyCode() == KeyEvent.VK_F1) {
					HelpGuide.main(null);
				}
			}

			public void keyReleased(KeyEvent e) {

				InputHistory.retrieveInputText(e);
				toDoScroller.getVerticalScrollBar().removeAdjustmentListener(
						adjustListener);
				completedScroller.getVerticalScrollBar()
						.removeAdjustmentListener(adjustListener);
			}

			public void keyTyped(KeyEvent e) {
			}
		};
		inputTextField.addKeyListener(listener);
	}

	private void proTaskFrame() {

		frame = new JFrame("ProTask");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setResizable(false);
		frame.setIconImage(Toolkit
				.getDefaultToolkit()
				.getImage(
						UserInterfaceMain.class
								.getResource("/userInterface/ImageIcon/proTaskLogo.png")));
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 28));
		frame.getContentPane().setEnabled(false);
		frame.setBounds(100, 100, 535, 770);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

	}

	protected static String getFirstWord(String userCommand) {
		String commandTypeString = userCommand.trim().split("\\s+")[0];
		return commandTypeString;
	}

	// Decide which panel to refresh and reload
	protected void displaySetting(String firstWord) {

		if ((firstWord.toLowerCase().equals("search"))
				|| (firstWord.toLowerCase().equals("find") || (firstWord
						.toLowerCase().equals("sort") || (firstWord
						.toLowerCase().equals("s"))))) {

			clearAndReloadBothPanelForTempList();
			tabbedPane.setSelectedIndex(0);
		}

		else if ((firstWord.toLowerCase().equals("clear") || (firstWord
				.toLowerCase().equals("cl")))) {
			stack.push(firstWord);
			clearAndReloadBothPanel();
			tabbedPane.setSelectedIndex(0);
		}

		else if ((firstWord.toLowerCase().equals("display") || (firstWord
				.toLowerCase().equals("dp")))) {

			clearAndReloadBothPanel();
			tabbedPane.setSelectedIndex(0);
		}

		else if ((firstWord.toLowerCase().equals("add") || (firstWord
				.toLowerCase().equals("a") || (firstWord.toLowerCase().equals(
				"delete") || (firstWord.toLowerCase().equals("d")))))) {

			stack.push(firstWord);
			clearAndReloadBothPanel();
			tabbedPane.setSelectedIndex(0);
		}

		else if ((firstWord.toLowerCase().equals("edit") || (firstWord
				.toLowerCase().equals("e")))) {

			stack.push(firstWord);
			clearAndReloadBothPanel();
			tabbedPane.setSelectedIndex(0);
		}

		else if ((firstWord.toLowerCase().equals("complete"))
				|| (firstWord.toLowerCase().equals("cp"))) {

			stack.push(firstWord);
			clearAndReloadBothPanel();
			tabbedPane.setSelectedIndex(1);
		}

		else if ((firstWord.toLowerCase().equals("uncomplete") || (firstWord
				.toLowerCase().equals("ucp")))) {

			stack.push(firstWord);
			clearAndReloadBothPanel();
			tabbedPane.setSelectedIndex(0);

		}

		else if ((firstWord.toLowerCase().equals("undo") || (firstWord
				.toLowerCase().equals("u")))) {

			clearAndReloadBothPanel();

			if (!(stack.isEmpty())) {
				if ((stack.peek().equals("uncomplete"))
						|| (stack.peek().equals("ucp"))) {

					stack.pop();
					temp.push("ucp");
					temp.push("u");

					clearAndReloadBothPanel();
					tabbedPane.setSelectedIndex(1);
				}

				else if ((stack.peek().equals("complete"))
						|| (stack.peek().equals("cp"))) {

					stack.pop();
					temp.push("cp");
					temp.push("u");

					clearAndReloadBothPanel();
					tabbedPane.setSelectedIndex(0);
				}

				else if ((stack.peek().equals("add"))
						|| (stack.peek().equals("a"))) {

					stack.pop();
					temp.push("a");
					temp.push("u");

					clearAndReloadBothPanel();
					tabbedPane.setSelectedIndex(0);
				}

				else if ((stack.peek().equals("delete"))
						|| (stack.peek().equals("d"))) {

					stack.pop();
					temp.push("d");
					temp.push("u");

					clearAndReloadBothPanel();
					tabbedPane.setSelectedIndex(0);
				} else if ((stack.peek().equals("clear"))
						|| (stack.peek().equals("cl"))) {

					stack.pop();
					temp.push("cl");
					temp.push("u");

					clearAndReloadBothPanel();
					tabbedPane.setSelectedIndex(0);
				} else if ((stack.peek().equals("edit"))
						|| (stack.peek().equals("e"))) {

					stack.pop();
					temp.push("e");
					temp.push("u");

					clearAndReloadBothPanel();
					tabbedPane.setSelectedIndex(0);
				}

			}// end if
			else if (stack.isEmpty()) {
				clearAndReloadBothPanel();
				tabbedPane.setSelectedIndex(0);

			}

		}// end undo

		else if ((firstWord.toLowerCase().equals("redo") || (firstWord
				.toLowerCase().equals("r")))) {

			clearAndReloadBothPanel();

			if (!(temp.isEmpty())) {
				if (temp.pop().equals("u")) {

					if (temp.peek().equals("cp")) {

						temp.pop();
						clearAndReloadBothPanel();
						tabbedPane.setSelectedIndex(1);

					} else if (temp.peek().equals("ucp")) {

						temp.pop();
						clearAndReloadBothPanel();
						tabbedPane.setSelectedIndex(0);

					} else if (temp.peek().equals("a")) {

						temp.pop();
						clearAndReloadBothPanel();
						tabbedPane.setSelectedIndex(0);

					} else if (temp.peek().equals("d")) {

						temp.pop();
						clearAndReloadBothPanel();
						tabbedPane.setSelectedIndex(0);

					} else if (temp.peek().equals("cl")) {

						temp.pop();
						clearAndReloadBothPanel();
						tabbedPane.setSelectedIndex(0);

					} else if (temp.peek().equals("e")) {

						temp.pop();
						clearAndReloadBothPanel();
						tabbedPane.setSelectedIndex(0);
					}
				}

			}// end of if

			else if (temp.isEmpty()) {
				clearAndReloadBothPanel();
				tabbedPane.setSelectedIndex(0);
			}

		}// end of Re-do
	}

	private void clearToDoPanel() {
		toDoPanel.revalidate();
		toDoPanel.repaint();
		toDoPanel.removeAll();
	}

	private void clearCompletedPanel() {
		completedPanel.revalidate();
		completedPanel.repaint();
		completedPanel.removeAll();
	}

	private void clearAndReloadBothPanel() {
		clearToDoPanel();
		clearCompletedPanel();
		printLabel(mem);
		printCompletedLabel(mem);
	}

	private void clearAndReloadBothPanelForTempList() {
		clearToDoPanel();
		clearCompletedPanel();
		printTempLabel(mem);
		printCompletedLabel(mem);
	}

	protected void printCompletedLabel(Repository list) {
		Collections.sort(list.getBuffer(), Compare.numComparator);

		for (int i = 0; i < list.getBufferSize(); i++) {

			Task task = list.getBuffer().get(i);

			String str = printCompletedList.returnString(task);

			JLabel completeLabel = new JLabel(str);
			completedPanel.add(completeLabel);
		}
	}

	protected void printLabel(Repository list) {
		Collections.sort(list.getBuffer(), Compare.numComparator);
		for (int i = 0; i < list.getBufferSize(); i++) {

			Task task = list.getBuffer().get(i);

			String str = printToDoList.returnString(task);

			JLabel toDoLabel = new JLabel(str);
			toDoPanel.add(toDoLabel);
		}
	}

	protected void printTempLabel(Repository list) {
		Collections.sort(list.getBuffer(), Compare.numComparator);
		for (int i = 0; i < list.getTempBufferSize(); i++) {

			Task task = list.getTempBuffer().get(i);

			String str = printTempToDoList.returnString(task);

			JLabel tempLabel = new JLabel(str);
			toDoPanel.add(tempLabel);
		}
	}
}
