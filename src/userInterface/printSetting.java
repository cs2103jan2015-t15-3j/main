package userInterface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

import logic.Compare;
import logic.Repository;
import logic.Task;

public class printSetting {

	private static final Stack<String> stack = new Stack<String>();
	private static final Stack<String> temp = new Stack<String>();
	ArrayList<Task> psList = new ArrayList<Task>();

	/*
	 * Display Setting for UI
	 */
	protected static void displaySetting(String firstWord, Repository mem) {

		if ((firstWord.toLowerCase().equals("search"))
				|| (firstWord.toLowerCase().equals("find"))) {

			clearAndReloadBothPanelForTempList(mem);
			UserInterfaceMain.tabbedPane.setSelectedIndex(0);
		}

		if ((firstWord.toLowerCase().equals("sort"))
				|| (firstWord.toLowerCase().equals("s"))) {
			stack.push(firstWord);
			clearAndReloadBothPanelForTempList(mem);
			UserInterfaceMain.tabbedPane.setSelectedIndex(0);
		}

		else if ((firstWord.toLowerCase().equals("clear") || (firstWord
				.toLowerCase().equals("cl")))) {
			stack.push(firstWord);
			clearToDoPanel();
			clearCompletedPanel();
			UserInterfaceMain.tabbedPane.setSelectedIndex(0);
		}

		else if ((firstWord.toLowerCase().equals("display") || (firstWord
				.toLowerCase().equals("dp")))) {

			clearAndReloadBothPanel(mem);
			UserInterfaceMain.tabbedPane.setSelectedIndex(0);
		}

		else if ((firstWord.toLowerCase().equals("add") || (firstWord
				.toLowerCase().equals("a") || (firstWord.toLowerCase().equals(
				"delete") || (firstWord.toLowerCase().equals("d")))))) {

			stack.push(firstWord);
			clearAndReloadBothPanel(mem);
			UserInterfaceMain.tabbedPane.setSelectedIndex(0);
		}

		else if ((firstWord.toLowerCase().equals("edit") || (firstWord
				.toLowerCase().equals("e")))) {

			stack.push(firstWord);
			clearAndReloadBothPanel(mem);
			UserInterfaceMain.tabbedPane.setSelectedIndex(0);
		}

		else if ((firstWord.toLowerCase().equals("complete"))
				|| (firstWord.toLowerCase().equals("cp"))) {

			stack.push(firstWord);
			clearAndReloadBothPanel(mem);
			UserInterfaceMain.tabbedPane.setSelectedIndex(1);
		}

		else if ((firstWord.toLowerCase().equals("uncomplete") || (firstWord
				.toLowerCase().equals("ucp")))) {

			stack.push(firstWord);
			clearAndReloadBothPanel(mem);
			UserInterfaceMain.tabbedPane.setSelectedIndex(0);

		}

		else if ((firstWord.toLowerCase().equals("undo") || (firstWord
				.toLowerCase().equals("u")))) {

			clearAndReloadBothPanel(mem);

			if (!(stack.isEmpty())) {
				if ((stack.peek().equals("uncomplete"))
						|| (stack.peek().equals("ucp"))) {

					stack.pop();
					temp.push("ucp");
					temp.push("u");

					clearAndReloadBothPanel(mem);
					UserInterfaceMain.tabbedPane.setSelectedIndex(1);
				}

				else if ((stack.peek().equals("complete"))
						|| (stack.peek().equals("cp"))) {

					stack.pop();
					temp.push("cp");
					temp.push("u");

					clearAndReloadBothPanel(mem);
					UserInterfaceMain.tabbedPane.setSelectedIndex(0);
				}

				else if ((stack.peek().equals("add"))
						|| (stack.peek().equals("a"))) {

					stack.pop();
					temp.push("a");
					temp.push("u");

					clearAndReloadBothPanel(mem);
					UserInterfaceMain.tabbedPane.setSelectedIndex(0);
				}

				else if ((stack.peek().equals("delete"))
						|| (stack.peek().equals("d"))) {

					stack.pop();
					temp.push("d");
					temp.push("u");

					clearAndReloadBothPanel(mem);
					UserInterfaceMain.tabbedPane.setSelectedIndex(0);
				} else if ((stack.peek().equals("clear"))
						|| (stack.peek().equals("cl"))) {

					stack.pop();
					temp.push("cl");
					temp.push("u");

					clearAndReloadBothPanel(mem);
					UserInterfaceMain.tabbedPane.setSelectedIndex(0);
				} else if ((stack.peek().equals("edit"))
						|| (stack.peek().equals("e"))) {

					stack.pop();
					temp.push("e");
					temp.push("u");

					clearAndReloadBothPanel(mem);
					UserInterfaceMain.tabbedPane.setSelectedIndex(0);
				} else if ((stack.peek().equals("sort"))
						|| (stack.peek().equals("s"))) {

					stack.pop();
					temp.push("s");
					temp.push("u");

					clearAndReloadBothPanel(mem);
					UserInterfaceMain.tabbedPane.setSelectedIndex(0);
				}

			}// end if
			else if (stack.isEmpty()) {
				clearAndReloadBothPanel(mem);
				UserInterfaceMain.tabbedPane.setSelectedIndex(0);

			}

		}// end undo

		else if ((firstWord.toLowerCase().equals("redo") || (firstWord
				.toLowerCase().equals("r")))) {

			clearAndReloadBothPanel(mem);

			if (!(temp.isEmpty())) {
				if (temp.pop().equals("u")) {

					if (temp.peek().equals("cp")) {

						temp.pop();
						clearAndReloadBothPanel(mem);
						UserInterfaceMain.tabbedPane.setSelectedIndex(1);

					} else if (temp.peek().equals("ucp")) {

						temp.pop();
						clearAndReloadBothPanel(mem);
						UserInterfaceMain.tabbedPane.setSelectedIndex(0);

					} else if (temp.peek().equals("a")) {

						temp.pop();
						clearAndReloadBothPanel(mem);
						UserInterfaceMain.tabbedPane.setSelectedIndex(0);

					} else if (temp.peek().equals("d")) {

						temp.pop();
						clearAndReloadBothPanel(mem);
						UserInterfaceMain.tabbedPane.setSelectedIndex(0);

					} else if (temp.peek().equals("cl")) {

						temp.pop();
						clearAndReloadBothPanel(mem);
						UserInterfaceMain.tabbedPane.setSelectedIndex(0);

					} else if (temp.peek().equals("e")) {

						temp.pop();
						clearAndReloadBothPanel(mem);
						UserInterfaceMain.tabbedPane.setSelectedIndex(0);

					} else if (temp.peek().equals("s")) {

						temp.pop();
						clearAndReloadBothPanel(mem);
						UserInterfaceMain.tabbedPane.setSelectedIndex(0);
					}
				}

			}// end of if

			else if (temp.isEmpty()) {
				clearAndReloadBothPanel(mem);
				UserInterfaceMain.tabbedPane.setSelectedIndex(0);
			}

		}// end of Re-do
	}

	protected static void clearToDoPanel() {
		UserInterfaceMain.toDoPanel.revalidate();
		UserInterfaceMain.toDoPanel.repaint();
		UserInterfaceMain.toDoPanel.removeAll();
	}

	protected static void clearCompletedPanel() {
		UserInterfaceMain.completedPanel.revalidate();
		UserInterfaceMain.completedPanel.repaint();
		UserInterfaceMain.completedPanel.removeAll();
	}

	protected static void clearAndReloadBothPanel(Repository mem) {
		clearToDoPanel();
		clearCompletedPanel();
		printLabel(mem);
		printCompletedLabel(mem);
	}

	protected static void clearAndReloadBothPanelForTempList(Repository mem) {
		clearToDoPanel();
		clearCompletedPanel();
		printTempLabel(mem);
		printCompletedLabel(mem);
	}

	protected static void printCompletedLabel(Repository list) {
		Collections.sort(list.getBuffer(), Compare.numComparator);

		for (int i = 0; i < list.getBufferSize(); i++) {

			Task task = list.getBuffer().get(i);

			String str = printCompletedList.returnString(task);

			JLabel completeLabel = new JLabel(str);

			Border border = BorderFactory.createMatteBorder(0, 0, 1, 0,
					Color.darkGray);

			completeLabel.setBorder(border);

			UserInterfaceMain.completedPanel.add(completeLabel);
		}
	}

	protected static void printLabel(Repository list) {
		Collections.sort(list.getBuffer(), Compare.numComparator);
		for (int i = 0; i < list.getBufferSize(); i++) {

			Task task = list.getBuffer().get(i);

			String str = printToDoList.returnString(task);

			JLabel toDoLabel = new JLabel(str);

			Border border = BorderFactory.createMatteBorder(0, 0, 1, 0,
					Color.darkGray);

			toDoLabel.setBorder(border);

			UserInterfaceMain.toDoPanel.add(toDoLabel);
		}
	}

	protected static void printTempLabel(Repository list) {
		Collections.sort(list.getBuffer(), Compare.numComparator);
		for (int i = 0; i < list.getTempBufferSize(); i++) {

			Task task = list.getTempBuffer().get(i);

			String str = printTempToDoList.returnString(task);

			JLabel tempLabel = new JLabel(str);

			Border border = BorderFactory.createMatteBorder(0, 0, 1, 0,
					Color.darkGray);

			tempLabel.setBorder(border);
			UserInterfaceMain.toDoPanel.add(tempLabel);
		}
	}

	protected static void printPowerSearchLabel(ArrayList<Task> list) {

		// Collections.sort(list.getBuffer(), Compare.numComparator);

		for (int i = 0; i < list.size(); i++) {

			Task task = list.get(i);

			String str = printPowerSearchList.returnString(task);

			JLabel psLabel = new JLabel(str);

			Border border = BorderFactory.createMatteBorder(0, 0, 1, 0,
					Color.darkGray);

			psLabel.setBorder(border);
			UserInterfaceMain.toDoPanel.add(psLabel);
		}
	}

	protected static void feedbackTimerReset() {

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				UserInterfaceMain.feedbackTextArea.setText("");
			}
		}, 10000);
	}
}
