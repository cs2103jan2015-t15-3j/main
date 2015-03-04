import java.util.*;


public class MainFunc {
public static ArrayList<String> myList;
public static ArrayList<String> undoList;
private static final String MESSAGE_TASK_ADDED = "Task has been added.\n";
private static final String MESSAGE_TEXT_DELETED = "Task has been deleted\n";
private static final String MESSAGE_TEXT_UPDATED = "Task has been updated\n";
private static final String MESSAGE_TEXT_SORTED = "all content sorted in %1$s\n";
private static final String MESSAGE_NO_SEARCH_RESULT = "%1$s is not found in %2$s\n";
private static final String MESSAGE_EMPTY_LIST = "%1$s is empty\n";
private static final String MESSAGE_ERROR = "Error: Invalid command\n";

	public String addTask(String taskName) {
		myList.add(taskName);
		return MESSAGE_TASK_ADDED;		
	}
	
	public String deleteTask(String taskName) {
		return MESSAGE_TEXT_DELETED;
	}
	
	public void displayTask(String taskName) {}
	
	public String updateTask(String taskName) {
		return MESSAGE_TEXT_UPDATED;
	}
	
	public void searchTask(String taskName) {}
	
	public void undoTask(String taskName) {}
	
	public void sortTask(String taskName) {}
	
	public void POWERsearchTask(String taskName) {}
}
