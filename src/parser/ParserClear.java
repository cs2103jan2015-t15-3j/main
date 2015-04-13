package parser;

//@author: A0110818M
public class ParserClear {
	
	public static void clearTask(Interpreter item, String input, String[] inputArray) {
		int inputLength = inputArray.length;
		try {
			if(inputLength == 2) {
				if(inputArray[1].toLowerCase().equals("cp")) {
					item.setClear("COMP");
				} else {
					throw new ParserException();
				}
			} else {
				item.setClear("ALL");		
			}
		} catch (ParserException pe) {
			item.setIsError(true);
			item.setFeedbackMsg(ParserMessage.INVALID_COMMAND);
		}
		
	}
}
