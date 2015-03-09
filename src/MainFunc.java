import java.io.IOException;
import java.util.*;


public class MainFunc {
	
private static MainFunc instance = null;
private Parser parser;
private ProTaskStorage storage;


//private static ArrayList<>
private boolean isRunning;

public static MainFunc getInstance() {
	if(instance == null) {
		instance = new MainFunc();
	}
	return instance;
}


/*public static ArrayList<String> myList = new ArrayList<String>();
public static ArrayList<String> undoList;
private static final String MESSAGE_TASK_ADDED = "Task has been added.\n";
private static final String MESSAGE_TEXT_DELETED = "Task has been deleted\n";
private static final String MESSAGE_TEXT_UPDATED = "Task has been updated\n";
private static final String MESSAGE_TEXT_SORTED = "all content sorted in %1$s\n";
private static final String MESSAGE_NO_SEARCH_RESULT = "%1$s is not found in %2$s\n";
private static final String MESSAGE_EMPTY_LIST = "%1$s is empty\n";
private static final String MESSAGE_ERROR = "Error: Invalid command\n";
private static Scanner sc = new Scanner(System.in);
*/

<<<<<<< HEAD
	public static String userInput(String userInput) {
		return userInput;
	}
=======
	public static ArrayList<Floating> userInput(String userInput) {
		ArrayList<Floating> arrayList = new ArrayList<Floating>();
		return arrayList;
	}
	
	public
>>>>>>> origin/master

	public static String identifyCommand(String command){
		switch (command){
		case "add":
			return addTask();
		case "delete":
			return deleteTask();
		case "display":
			return displayTask();
		case "exit":
			System.exit(0);
		default:
			return MESSAGE_ERROR;
		}
	}

	public static String addTask() {
		System.out.println("input text to be added here");
		String result = sc.nextLine();
		myList.add(result);
		return MESSAGE_TASK_ADDED;		
	}
	
	
	public static String deleteTask() {
		System.out.println("input index of text to be deleted here");
		String indexNum = sc.nextLine();
		int index = Integer.parseInt(indexNum);
		myList.remove(index-1);
		return MESSAGE_TEXT_DELETED;
	}
	
	public static String displayTask() {
		for(int i=0; i < myList.size(); i++) {
			System.out.println(myList.get(i));
		}
		System.out.println();
		return "";
	}
	
	public String updateTask(String taskName) {
		return MESSAGE_TEXT_UPDATED;
	}
	
	public void searchTask(String taskName) {}
	
	public void undoTask(String taskName) {}
	
	public void sortTask(String taskName) {}
	
	public void powerSearchTask(String taskName) {}
	
	public static void main(String args[]) throws IOException {
	
		while(true){
			System.out.println("Hi! Input command first: ");
			String input = sc.nextLine();
			String command = input.trim().split("\\s+")[0];
			identifyCommand(command);
		}
	}
}
