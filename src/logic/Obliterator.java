package logic;

import java.util.ArrayList;

import parser.Interpreter;

public class Obliterator {
	protected static void deleteTask(int taskID, ArrayList<Task> buffer) {
		buffer.remove(taskID);
	}
	
	protected static void clearTask(Interpreter item, ArrayList<Task> buffer) {
		buffer.removeAll(buffer);
	}
}
