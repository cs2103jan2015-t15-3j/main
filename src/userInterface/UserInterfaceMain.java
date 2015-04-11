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
import java.util.ArrayList;
import java.util.EmptyStackException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import logic.LogicMain;
import logic.Repository;
import logic.Task;
import logic.Message;
import parser.ParserPowerSearch;

//@author A0112961L

@SuppressWarnings("serial")
public class UserInterfaceMain extends JPanel {

	private JFrame frame;
	private static String userInput = new String();
	protected static JTextField inputTextField;
	static JTextArea feedbackTextArea;
	static JPanel completedPanel;
	static JPanel toDoPanel;
	static JTabbedPane tabbedPane;
	private AdjustmentListener adjustListener;
	private JScrollPane scrollPane;

	Repository mem = new Repository();
	ArrayList<Task> psList = new ArrayList<Task>();

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
		completedPanel.setBackground(new Color(240, 255, 240));
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
		printSetting.clearAndReloadBothPanel(mem);

		KeyListener listener = new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					userInput = inputTextField.getText().toString();
					
					String firstWord = getFirstWord(userInput);
					System.out.println(firstWord);

					mem = LogicMain.parseString(userInput, mem);

					//if no error
					printSetting.clearAndReloadBothPanel(mem);
					printSetting.clearAndReloadBothPanelForTempList(mem);
					//else dont do anything

					try {
						printSetting.displaySetting(firstWord, mem);

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
					printSetting.feedbackTimerReset();

					inputTextField.setText(null);

				} 
				else if (e.getKeyCode() == KeyEvent.VK_F1) {
					HelpGuide.main(null);
				}
			}

			public void keyReleased(KeyEvent e) {

				String input = inputTextField.getText().toString();
				System.out.println("input is " + input);
			
				if ((input.regionMatches(true, 0, "ps ", 0, 3))){

					printSetting.clearToDoPanel();
					tabbedPane.setSelectedIndex(0);
					psList = ParserPowerSearch.powerSearch(input);
					printSetting.printPowerSearchLabel(psList);
				}

				else if(input.isEmpty()){
					printSetting.clearAndReloadBothPanel(mem);
				}
				
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
}
