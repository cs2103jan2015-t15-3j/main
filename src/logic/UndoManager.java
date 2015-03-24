package logic;

import parser.Interpreter.CommandType;

public class UndoManager {

	protected static History pushToAdd(int taskID) {
		History history = new History();
		history.setCommand(CommandType.ADD);
		history.setTaskID(taskID);
		return history;
	}

	protected static void determineUndoAction() {
		Repository repo = new Repository();
		History hist = repo.undoActionPop();

		if (hist.getCommand().equals(CommandType.ADD)) {
			undoAddAction(repo.getCurrentID());
		}
	}

	private static void undoAddAction(int taskID) {
		Repository repo = new Repository();
		History hist = new History();
		//int index = SearchEngine.searchBufferIndex(taskID, hist.getHistory());
		repo.undoActionPush(hist);

	}
}
