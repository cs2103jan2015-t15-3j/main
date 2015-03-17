package logic;

import java.util.ArrayList;

import logic.Enumerator.AssignmentType;
import parser.Interpreter;

public class SearchEngine {
	
	
	
	protected static void determineSearch(String input, ArrayList<Floating> buffer) {
		if(input.matches("[0-9]+")) {
			searchByTaskId(convertToInt(input), buffer);
		}
		else {
			searchByKeyWords(input, buffer);
		}
	}
	
	private static int convertToInt(String input) {
		int number = Integer.parseInt(input);
		return number;
	}
	
	protected static int searchByTaskId(int taskId, ArrayList<Floating> buffer) {
		int index = 0;
		for(int count = 0; count < buffer.size(); count++) {
			if(buffer.get(count).getTaskId() == taskId) {
				index = count;
				break;
			}
		}
		return index;
	}
	
	//tell ash to convert to string
	
	private static void searchByKeyWords(Interpreter item, ArrayList<Floating> buffer) {
		ArrayList<Floating> searchList = new ArrayList<Floating>();
		for(int count = 0; count < buffer.size(); count++) {
			if(buffer.get(count)) {
				searchList.add(buffer.get(count));
			}
		}
	}
}
