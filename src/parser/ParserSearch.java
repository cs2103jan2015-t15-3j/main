package parser;

public class ParserSearch {

	public static void searchTask(Interpreter item, String[] inputArray) {
		String searchKey = "";
		try {
			if(inputArray.length == 1) {
				throw new NullPointerException();
			} else {
				int lengthSearchKey = inputArray.length-1;
				for(int i=1; i<=lengthSearchKey; i++){
					searchKey = searchKey.concat(inputArray[i]);
				}
				item.setKey(searchKey);
			}
		} catch (NullPointerException npe) {
			item.setIsError(true);
			item.setFeedbackMsg(ParserMessage.INVALID_INPUT);
		}	
	}
}
