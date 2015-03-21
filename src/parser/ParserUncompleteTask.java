package parser;

public class ParserUncompleteTask {
	public static void uncompleteTask(Interpreter item, String[] inputArray) {
		int ID = Integer.parseInt(inputArray[1]);
		item.setTaskID(ID);
		item.setIsCompleted(false);
	}
}
