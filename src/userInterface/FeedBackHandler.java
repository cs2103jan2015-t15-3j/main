package userInterface;

import userInterface.FeedBackGuide;

public class FeedBackHandler {

	public static void successfulOperation() {
		UserIntSwing.lblFeedback.setText(FeedbackGuide.isValidString());
		//feedbackTimerReset();
	}

	public static void NotSuccessfulOperation(String exception) {
		UserIntSwing.lblFeedback.setText(exception);
		//feedbackTimerReset();
	}

	public static void emptyStringOperation() {
		UserIntSwing.lblFeedback.setText(FeedbackGuide.isEmptyString());
		//feedbackTimerReset();
	}

	public static void emptyHistoryStringOperation(){
	UserIntSwing.lblFeedback.setText(FeedbackGuide.isEmptyHistoryS
	tring());
	//feedbackTimerReset();
	}

	public static void emptyInputStringOperation() {
		UserIntSwing.lblFeedback.setText(FeedbackGuide.isEmptyInput());
		//feedbackTimerReset();
	}

	/*
	 * @param getText get the user input string
	 * 
	 * @return boolean true if user input double space false otherwise
	 */
	public static boolean isDoubleSpace(String getText) {
		getText = getText.trim().replaceAll("\\s+", " ");
		if (getText.isEmpty() || getText.matches(" ")) {
			return true;
		}
		return false;
	}
	/*
	 * This operation process the timer to clear the Warning Label. It is set at
	 * 2000 milli‐seconds.
	 */
	/*
	 * public static void feedbackTimerReset() { Timer timer = new Timer();
	 * timer.schedule(new TimerTask() {
	 * 
	 * @Override public void run() { UserIntSwing.lblFeedback.setText(""); } },
	 * 2000); } }
	 */
}
