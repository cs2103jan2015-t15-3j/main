package logic;

import java.util.ArrayList;
//import java.util.Collections;

import parser.Interpreter;

public class Obliterator {
	protected static void deleteTask(int taskID, ArrayList<Task> buffer) {
		int index = SearchEngine.searchBufferIndex(taskID, buffer);
		buffer.remove(index);
	}

	protected static void clearTask(Interpreter item, ArrayList<Task> buffer) {
		buffer.removeAll(buffer);
	}
}
