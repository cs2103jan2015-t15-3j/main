package logic;

import java.util.ArrayList;

import parser.Interpreter;

public class Obliterator {
	protected static void deleteTask(int taskID, ArrayList<Task> buffer) {
		try {
		int index = SearchEngine.searchBufferIndex(taskID, buffer);
		buffer.remove(index);	
		}
		catch(IndexOutOfBoundsException e) {
			
		}
	}

	protected static void clearTask(Interpreter item, ArrayList<Task> buffer) {
		buffer.removeAll(buffer);
	}
}
