package logic;

import java.util.ArrayList;

import parser.Interpreter.CommandType;

public class UndoManager {

	protected static void determineUndo(ArrayList<Task> buffer) {
		Repository repo = new Repository();
		History history = repo.undoActionPop();
		
		if(history.getCommand().equals(CommandType.ADD)) {
			undoActionAdd(history.getTaskID(), buffer);
		}
	}
	
	protected static History pushToAdd(int taskID) {
		History history = new History();
		history.setCommand(CommandType.ADD);
		history.setTaskID(taskID);
		return history;
	}

	private static void undoActionAdd(int taskID, ArrayList<Task> buffer) {
		History hist = new History();
		
		int index = SearchEngine.searchBufferIndex(taskID, buffer);
		buffer.remove(index);

	}
}
