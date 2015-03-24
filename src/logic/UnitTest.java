package logic;

import java.util.ArrayList;

import parser.Interpreter;

public class UnitTest {
	public static void addTask(Interpreter item, ArrayList<Task> buffer,
			int index) {
		Affix.addTask(item, buffer, index);
	}

	public static void deleteTask(int taskID, ArrayList<Task> buffer) {
		Obliterator.deleteTask(taskID, buffer);
	}

	public static void clearTask(Interpreter item, ArrayList<Task> buffer) {
		Obliterator.clearTask(item, buffer);
	}

	public static void determineAmend(Interpreter item, Repository mem) {
		Amend.determineAmend(item, mem);
	}

	public static void sort(Repository mem) {
		Organizer.sort(mem);
	}

	public static void setCompletion(Interpreter item, ArrayList<Task> buffer) {
		Amend.setCompletion(item, buffer);
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
