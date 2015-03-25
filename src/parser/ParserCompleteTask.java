package parser;

public class ParserCompleteTask {

	public static void completeTask(Interpreter item, String[] inputArray) {
		int ID = Integer.parseInt(inputArray[1]);
		item.setTaskID(ID);
		item.setIsCompleted(true);
	}
}
