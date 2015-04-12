package userInterface;

import java.util.Stack;
import java.awt.event.KeyEvent;

import userInterface.UserInterfaceMain;

//@author A0112961L

public class InputHistory {

	private static final Stack<String> inputStack = new Stack<String>();
	private static final Stack<String> historyStack = new Stack<String>();
	private static String getInputText;

	public static void getInput(String getInputText) {

		if (!getInputText.isEmpty()) {

			while (!historyStack.isEmpty()) {

				inputStack.push(historyStack.pop());
			}
			inputStack.push(getInputText);
		}
	}

	public static void retrieveInputText(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (!inputStack.isEmpty()) 
			{
				pushToHistoryStack();
			}
		}
			else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				if (!historyStack.isEmpty()) 
				{
					pushToUserInputStack();
				}

			}
		}
	
	private static void pushToHistoryStack() {
		getInputText = inputStack.pop();
		historyStack.push(getInputText);
		UserInterfaceMain.inputTextField.setText(getInputText);
	}

	private static void pushToUserInputStack() {
		getInputText = historyStack.pop();
		inputStack.push(getInputText);
		UserInterfaceMain.inputTextField.setText(getInputText);
	}

}
