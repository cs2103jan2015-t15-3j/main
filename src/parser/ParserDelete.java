package parser;

public class ParserDelete {

	public static void deleteTask(Interpreter item, String[] inputArray) {
		int ID = Integer.parseInt(inputArray[1]);
		item.setTaskID(ID);
	}	
}
