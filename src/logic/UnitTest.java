package logic;

import java.util.ArrayList;

import parser.Interpreter;

public class UnitTest {
	//public static Repository executeCommand(String command, Repository mem) {
	//	return LogicMain.executeCommand(command, mem);
	//}
	public static void parseString(String command, Repository repo) {

	}
	
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

	public static void determineAmend(Interpreter item, Repository repo) {
		Amend.determineAmend(item, repo);
	}

	public static void sort(Repository mem) {
		Organizer.sort(mem);
	}

	public static void setCompletion(Interpreter item, Repository repo) {
		Amend.setCompletion(item, repo);
	}

	public static void searchBufferIndex(int taskID, ArrayList<Task> buffer) {
		SearchEngine.searchBufferIndex(taskID, buffer);
	}

	public static void determineSearch(String input, Repository mem) {
		SearchEngine.determineSearch(input, mem);
	}
	
	public static Task retrieveTask(Interpreter item, ArrayList<Task> buffer) {
		return SearchEngine.retrieveTask(item, buffer);
	}
}
