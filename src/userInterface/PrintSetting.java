package userInterface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

import logic.Compare;
import logic.Repository;
import logic.Task;

//@author A0112961L

public class PrintSetting {

	private static final Stack<String> stack = new Stack<String>();
	private static final Stack<String> temp = new Stack<String>();
	ArrayList<Task> psList = new ArrayList<Task>();

	/*
	 * Display Setting for UI
	 */
	protected static void displaySetting(String firstWord, Repository repo) {

		if ((firstWord.toLowerCase().equals("search"))
				|| (firstWord.toLowerCase().equals("find"))) {

			clearAndReloadBothPanelForTempList(repo);
			UserInterfaceMain.tabbedPane.setSelectedIndex(0);
		}

		else if ((firstWord.toLowerCase().equals("sort"))
				|| (firstWord.toLowerCase().equals("s"))) {
			stack.push(firstWord);
			clearAndReloadBothPanelForTempList(repo);
			UserInterfaceMain.tabbedPane.setSelectedIndex(0);
		}

		else if ((firstWord.toLowerCase().equals("move"))
				|| (firstWord.toLowerCase().equals("mv"))) {
			stack.push(firstWord);
			clearAndReloadBothPanel(repo);
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

			clearAndReloadBothPanel(repo);
			UserInterfaceMain.tabbedPane.setSelectedIndex(0);
		}

		else if ((firstWord.toLowerCase().equals("add") || (firstWord
				.toLowerCase().equals("a") || (firstWord.toLowerCase().equals(
				"delete") || (firstWord.toLowerCase().equals("d")))))) {

			stack.push(firstWord);
			clearAndReloadBothPanel(repo);
			UserInterfaceMain.tabbedPane.setSelectedIndex(0);
		}

		else if ((firstWord.toLowerCase().equals("edit") || (firstWord
				.toLowerCase().equals("e")))) {

			stack.push(firstWord);
			clearAndReloadBothPanel(repo);
			UserInterfaceMain.tabbedPane.setSelectedIndex(0);
		}

		else if ((firstWord.toLowerCase().equals("complete"))
				|| (firstWord.toLowerCase().equals("cp"))) {

			stack.push(firstWord);
			clearAndReloadBothPanel(repo);
			UserInterfaceMain.tabbedPane.setSelectedIndex(1);
		}

		else if ((firstWord.toLowerCase().equals("uncomplete") || (firstWord
				.toLowerCase().equals("ucp")))) {

			stack.push(firstWord);
			clearAndReloadBothPanel(repo);
			UserInterfaceMain.tabbedPane.setSelectedIndex(0);
		}

		else if ((firstWord.toLowerCase().equals("undo") || (firstWord
				.toLowerCase().equals("u")))) {

			clearAndReloadBothPanel(repo);

			if (!(stack.isEmpty())) {
				if ((stack.peek().equals("uncomplete"))
						|| (stack.peek().equals("ucp"))) {

					stack.pop();
					temp.push("ucp");

					clearAndReloadBothPanel(repo);
					UserInterfaceMain.tabbedPane.setSelectedIndex(1);
				}

				else if ((stack.peek().equals("complete"))
						|| (stack.peek().equals("cp"))) {

					stack.pop();
					temp.push("cp");

					clearAndReloadBothPanel(repo);
					UserInterfaceMain.tabbedPane.setSelectedIndex(0);
				}

				else if ((stack.peek().equals("add"))
						|| (stack.peek().equals("a"))) {

					stack.pop();
					temp.push("a");

					clearAndReloadBothPanel(repo);
					UserInterfaceMain.tabbedPane.setSelectedIndex(0);
				}

				else if ((stack.peek().equals("delete"))
						|| (stack.peek().equals("d"))) {

					stack.pop();
					temp.push("d");

					clearAndReloadBothPanel(repo);
					UserInterfaceMain.tabbedPane.setSelectedIndex(0);
				} else if ((stack.peek().equals("clear"))
						|| (stack.peek().equals("cl"))) {

					stack.pop();
					temp.push("cl");

					clearAndReloadBothPanel(repo);
					UserInterfaceMain.tabbedPane.setSelectedIndex(0);
				} else if ((stack.peek().equals("edit"))
						|| (stack.peek().equals("e"))) {

					stack.pop();
					temp.push("e");

					clearAndReloadBothPanel(repo);
					UserInterfaceMain.tabbedPane.setSelectedIndex(0);
				} else if ((stack.peek().equals("sort"))
						|| (stack.peek().equals("s"))) {

					stack.pop();
					temp.push("s");

					clearAndReloadBothPanel(repo);
					UserInterfaceMain.tabbedPane.setSelectedIndex(0);
				}
			} else if (stack.isEmpty()) {
				clearAndReloadBothPanel(repo);
				UserInterfaceMain.tabbedPane.setSelectedIndex(0);
			}
		} else if ((firstWord.toLowerCase().equals("redo") || (firstWord
				.toLowerCase().equals("r")))) {

			clearAndReloadBothPanel(repo);

			if (!(temp.isEmpty())) {
				if (temp.peek().equals("cp")) {

					temp.pop();
					clearAndReloadBothPanel(repo);
					UserInterfaceMain.tabbedPane.setSelectedIndex(1);

				} else if (temp.peek().equals("ucp")) {

					temp.pop();
					clearAndReloadBothPanel(repo);
					UserInterfaceMain.tabbedPane.setSelectedIndex(0);

				} else if (temp.peek().equals("a")) {

					temp.pop();
					clearAndReloadBothPanel(repo);
					UserInterfaceMain.tabbedPane.setSelectedIndex(0);

				} else if (temp.peek().equals("d")) {

					temp.pop();
					clearAndReloadBothPanel(repo);
					UserInterfaceMain.tabbedPane.setSelectedIndex(0);

				} else if (temp.peek().equals("cl")) {

					temp.pop();
					clearAndReloadBothPanel(repo);
					UserInterfaceMain.tabbedPane.setSelectedIndex(0);

				} else if (temp.peek().equals("e")) {

					temp.pop();
					clearAndReloadBothPanel(repo);
					UserInterfaceMain.tabbedPane.setSelectedIndex(0);

				} else if (temp.peek().equals("s")) {

					temp.pop();
					clearAndReloadBothPanelForTempList(repo);
					UserInterfaceMain.tabbedPane.setSelectedIndex(0);
				}
			}

			else if (temp.isEmpty()) {
				clearAndReloadBothPanel(repo);
				UserInterfaceMain.tabbedPane.setSelectedIndex(0);
			}
		}
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

	protected static void clearAndReloadBothPanel(Repository repo) {
		clearToDoPanel();
		clearCompletedPanel();
		printLabel(repo);
		printCompletedLabel(repo);
	}

	protected static void clearAndReloadBothPanelForTempList(Repository repo) {
		clearToDoPanel();
		clearCompletedPanel();
		printTempLabel(repo);
		printCompletedLabel(repo);
	}

	protected static void printCompletedLabel(Repository list) {
		Collections.sort(list.getBuffer(), Compare.numComparator);

		for (int i = 0; i < list.getBufferSize(); i++) {
			Task task = list.getBuffer().get(i);

			String str = PrintCompletedList.returnString(task);
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

			String str = PrintToDoList.returnString(task);
			JLabel toDoLabel = new JLabel(str);
			Border border = BorderFactory.createMatteBorder(0, 0, 1, 0,
					Color.darkGray);
			toDoLabel.setBorder(border);
			UserInterfaceMain.toDoPanel.add(toDoLabel);
		}
	}

	protected static void printTempLabel(Repository list) {
		Collections.sort(list.getTempBuffer(), Compare.numComparator);
		for (int i = 0; i < list.getTempBufferSize(); i++) {
			Task task = list.getTempBuffer().get(i);

			String str = PrintTempToDoList.returnString(task);
			JLabel tempLabel = new JLabel(str);
			Border border = BorderFactory.createMatteBorder(0, 0, 1, 0,
					Color.darkGray);
			tempLabel.setBorder(border);
			UserInterfaceMain.toDoPanel.add(tempLabel);
		}
	}

	protected static void printPowerSearchLabel(ArrayList<Task> list) {
		Collections.sort(list, Compare.numComparator);
		for (int i = 0; i < list.size(); i++) {
			Task task = list.get(i);

			String str = PrintPowerSearchList.returnString(task);
			JLabel psLabel = new JLabel(str);
			Border border = BorderFactory.createMatteBorder(0, 0, 1, 0,
					Color.darkGray);
			psLabel.setBorder(border);
			UserInterfaceMain.toDoPanel.add(psLabel);
		}
	}
}
