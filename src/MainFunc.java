import java.util.*;


public class MainFunc {
public static ArrayList<String> myList;
public static ArrayList<String> undoList;
private static final String MESSAGE_TASK_ADDED = "Task has been added.\n";

	public String addTask(String taskName) {
		myList.add(taskName);
		return MESSAGE_TASK_ADDED;		
	}
	
	public void deleteTask(String taskName) {}
	
	public void displayTask(String taskName) {}
	
	public void searchTask(String taskName) {}
	
	public void undoTask(String taskName) {}
	
	public void sortTask(String taskName) {}
	
	public void POWERsearchTask(String taskName) {}
}
