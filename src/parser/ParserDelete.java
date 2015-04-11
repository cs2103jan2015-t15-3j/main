package parser;

import logic.Enumerator.ErrorType;

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
			item.setErrorType(ErrorType.INVALID_TEXT);
			
		} catch (NumberFormatException nfe) {
			System.out.println("nfe");
			item.setIsError(true);
			item.setErrorType(ErrorType.INVALID_ID);
		} 
	}	
}
