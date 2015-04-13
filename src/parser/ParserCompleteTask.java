package parser;

//@author: A0110818M
public class ParserCompleteTask {

	public static void completeTask(Interpreter item, String[] inputArray) {
		
		try {
			if(inputArray.length < 2) {
				throw new ParserException();
			} else {
				int ID = Integer.parseInt(inputArray[1]);
				item.setTaskID(ID);
				item.setIsCompleted(true);
			}
		} catch (ParserException pe) {			
			item.setIsError(true);
			item.setFeedbackMsg(ParserMessage.INVALID_TEXT);
			
		} catch (NumberFormatException nfe) {
			item.setIsError(true);
			item.setFeedbackMsg(ParserMessage.INVALID_ID);
		}
	}
}
