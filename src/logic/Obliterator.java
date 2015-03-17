package logic;

import java.util.ArrayList;

import parser.Interpreter;

public class Obliterator {
	protected static void deleteTask(Interpreter item, ArrayList<Floating> buffer) {
		int taskId = item.getTaskId();
		buffer.remove(taskId);
	}
	
	protected static void clearTask(Interpreter item, ArrayList<Floating> buffer) {
		buffer.removeAll(buffer);
	}
}
