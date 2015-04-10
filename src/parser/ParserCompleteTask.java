package parser;

import logic.Enumerator.ErrorType;

public class ParserCompleteTask {

	public static void completeTask(Interpreter item, String[] inputArray) {
		
		if(inputArray.length < 2) {
			item.setIsError(true);
			item.setErrorType(ErrorType.INVALID_TEXT);
		} else {
			int ID = Integer.parseInt(inputArray[1]);
			item.setTaskID(ID);
			item.setIsCompleted(true);
		}
	}
}
