package parser;

public class ParserClear {
	
	public static void clearTask(Interpreter item, String[] inputArray) {
		int inputLength = inputArray.length;
		if(inputLength == 2) {
			item.setClear("COMP");
		} else {
			item.setClear("ALL");		
		}
	}
}
