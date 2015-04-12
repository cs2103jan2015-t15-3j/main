//@author A0111842R
package parser;

public class ParserMove {

	public static void moveDataBase(Interpreter item, String[] inputArray) {
		boolean wrongFormat = false;
		boolean expectedParameter = false;
		String dataBasePath = "";

		for (int i = 0; i < inputArray.length; i++) {
			if (expectedParameter && !inputArray[i].isEmpty()) {
				wrongFormat = true;
				item.setIsError(true);
			} else if (i != 0 && !inputArray[i].isEmpty() && !expectedParameter) {
				expectedParameter = true;
				dataBasePath = inputArray[i];
			}
		}
		if (wrongFormat) {
			item.setIsError(true);
			item.setFeedbackMsg(ParserMessage.INVALID_INPUT);
		} else {
			item.setDataBasePath(dataBasePath);
		}
	}
}
