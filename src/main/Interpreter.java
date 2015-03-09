package main;

public class Interpreter {
	enum CommandType {
		ADD, DELETE; 
	}
	private CommandType command;
	private boolean isComplete;
	private int taskID;
	
	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}
	
	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}
	
	public int getTaskID() {
		return this.taskID;
	}
}
