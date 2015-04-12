package parser;

public class ParserDelete {

	public static void deleteTask(Interpreter item, String[] inputArray) {
		int ID;
		try {
			if(inputArray.length != 2) {
				throw new ParserException(); 				
			} else {
				ID = Integer.parseInt(inputArray[1]);
				item.setTaskID(ID);
			}
			
		} catch (ParserException pe) {			
			System.out.println("pe");
			item.setIsError(true);
			item.setFeedbackMsg(ParserMessage.INVALID_INPUT);
			
		} catch (NumberFormatException nfe) {
			System.out.println("nfe");
			item.setIsError(true);
			item.setFeedbackMsg(ParserMessage.INVALID_ID);
		} 
	}	
}
