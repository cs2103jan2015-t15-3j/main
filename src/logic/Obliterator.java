package logic;

import java.util.ArrayList;

import parser.Interpreter;

public class Obliterator {
	protected static void deleteTask(Interpreter item, ArrayList<Task> buffer) {
		int taskId = item.getTaskID();
		buffer.remove(taskId);
	}
	
	protected static void clearTask(Interpreter item, ArrayList<Task> buffer) {
		buffer.removeAll(buffer);
	}
}
