package logic;

import java.util.ArrayList;

import parser.Interpreter;

public class Amend {
	
	public static void setCompletionTask(Interpreter item, ArrayList<Floating> buffer) {
		int taskId = item.getTaskId();
		boolean isCompleted = item.getCompleted();
		
		buffer.get(taskId).setIsCompleted(isCompleted);
	}
}
