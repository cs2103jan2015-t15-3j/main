package parser;

import logic.Enumerator.ErrorType;

public class ParserUncompleteTask {
	public static void uncompleteTask(Interpreter item, String[] inputArray) {
		try {
			if(inputArray.length < 2) {
				throw new ParserException();
			} else {
				int ID = Integer.parseInt(inputArray[1]);
				item.setTaskID(ID);
				item.setIsCompleted(false);
			}
		} catch (ParserException pe) {			
			item.setIsError(true);
			item.setErrorType(ErrorType.INVALID_TEXT);
			
		} catch (NumberFormatException nfe) {
			item.setIsError(true);
			item.setErrorType(ErrorType.INVALID_ID);
		}
	}
}
