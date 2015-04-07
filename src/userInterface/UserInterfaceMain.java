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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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

	Repository mem = new Repository();

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

		feedbackTextArea = new JTextArea();
		feedbackTextArea.setForeground(Color.BLACK);
		feedbackTextArea.setBackground(Color.LIGHT_GRAY);
		feedbackTextArea.setBounds(38, 591, 448, 27);
		frame.getContentPane().add(feedbackTextArea);

		inputTextField = new JTextField();
		inputTextField.setBounds(38, 632, 448, 27);
		frame.getContentPane().add(inputTextField);
		inputTextField.setColumns(10);

		JLabel helpLabel = new JLabel("Press 'F1' for Help Guide");
		helpLabel.setForeground(new Color(0, 139, 139));
		helpLabel.setBounds(38, 668, 448, 27);
		frame.getContentPane().add(helpLabel);

		// initial load
		mem = LogicMain.loadStorage();
		clearAndReloadBothPanel();

		feedbackTextArea.setText(Message.WELCOME);

		KeyListener listener = new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					userInput = inputTextField.getText().toString();

					mem = LogicMain.parseString(userInput, mem);

					String firstWord = getFirstWord(userInput);

					// testing getFirstWord
					System.out.println(firstWord);

					displaySetting(firstWord);

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
		frame.setBounds(100, 100, 533, 762);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

	}

	protected static String getFirstWord(String userCommand) {
		String commandTypeString = userCommand.trim().split("\\s+")[0];
		return commandTypeString;
	}

	protected void displaySetting(String firstWord) {

		if ((firstWord.toLowerCase().equals("search"))
				|| (firstWord.toLowerCase().equals("find") || (firstWord
						.toLowerCase().equals("sort") || (firstWord
						.toLowerCase().equals("s"))))) {

			clearAndReloadBothPanelForTempList();
			tabbedPane.setSelectedIndex(0);
		}

		else if ((firstWord.toLowerCase().equals("complete"))
				|| (firstWord.toLowerCase().equals("cp"))) {

			clearAndReloadBothPanel();
			tabbedPane.setSelectedIndex(1);
		}

		else if ((firstWord.toLowerCase().equals("clear") || (firstWord
				.toLowerCase().equals("cl")))) {
			clearAndReloadBothPanel();
			tabbedPane.setSelectedIndex(0);
		}
		
		else if ((firstWord.toLowerCase().equals("add") || (firstWord
				.toLowerCase().equals("a")))) {
			clearAndReloadBothPanel();
			tabbedPane.setSelectedIndex(0);
		}
		else {
			clearAndReloadBothPanel();
			tabbedPane.setSelectedIndex(0);
		}
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
		for (int i = 0; i < list.getBufferSize(); i++) {

			Task task = list.getBuffer().get(i);

			String str = printCompletedList.returnString(task);

			JLabel completeLabel = new JLabel(str);
			completedPanel.add(completeLabel);
		}
	}

	protected void printLabel(Repository list) {
		for (int i = 0; i < list.getBufferSize(); i++) {

			Task task = list.getBuffer().get(i);

			String str = printToDoList.returnString(task);

			JLabel toDoLabel = new JLabel(str);
			toDoPanel.add(toDoLabel);
		}
	}

	protected void printTempLabel(Repository list) {
		for (int i = 0; i < list.getTempBufferSize(); i++) {

			Task task = list.getTempBuffer().get(i);

			String str = printTempToDoList.returnString(task);

			JLabel tempLabel = new JLabel(str);
			toDoPanel.add(tempLabel);
		}
	}
}
