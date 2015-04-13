package logic;

import java.util.ArrayList;

import parser.Interpreter;

//@author A0112643R

public class UnitTest {

	public static void addTask(Interpreter item, ArrayList<Task> buffer,
			int index) {
		Affix.addTask(item, buffer, index);
	}

	public static void deleteTask(int taskID, Repository repo) {
		Obliterator.deleteTask(taskID, repo);
	}

	public static void clearTask(ArrayList<Task> buffer) {
		Obliterator.clearTask(buffer);
	}

	public static void sort(Repository mem) {
		Organizer.sort(mem);
	}

	public static void setCompletion(Interpreter item, Repository repo) {
		Amend.setCompletion(item, repo);
	}

	public static int searchBufferIndex(int taskID, ArrayList<Task> buffer) {
		return SearchEngine.searchBufferIndex(taskID, buffer);
	}

	public static void determineAmend(Interpreter item, Repository repo) {
		Amend.determineAmend(item, repo);
	}

	public static void determineClear(Interpreter item, ArrayList<Task> buffer) {
		Obliterator.determineClear(item, buffer);
	}

	public static void determineSearch(String input, Repository mem) {
		SearchEngine.determineSearch(input, mem);
	}

	public static void determineUndo(Repository repo) {
		UndoManager.determineUndo(repo);
	}

	public static History pushAddToStack(Interpreter input, Repository repo) {
		return UndoManager.pushAddToStack(input, repo);
	}

	public static History pushDeleteToStack(Interpreter input, Repository repo) {
		return UndoManager.pushDeleteToStack(input, repo);
	}

	public static History pushAmendToStack(Interpreter input,
			ArrayList<Task> buffer) {
		return UndoManager.pushAmendToStack(input, buffer);
	}

	public static History pushClearToStack(Interpreter input,
			ArrayList<Task> buffer) {
		return UndoManager.pushClearToStack(input, buffer);
	}

	public static History pushCompleteOrUncompleteToStack(Interpreter input,
			Repository repo) {
		return UndoManager.pushCompleteOrUncompleteToStack(input, repo);
	}

	public static void determineRedo(Repository repo) {
		RedoManager.determineRedo(repo);
	}

	public static Task retrieveTask(ArrayList<Task> buffer, int taskID) {
		return SearchEngine.retrieveTask(buffer, taskID);
	}
}
