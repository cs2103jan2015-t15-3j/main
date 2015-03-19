package logic;

import java.io.FileNotFoundException;
import java.text.ParseException;

import parser.Interpreter;
import parser.Interpreter.CommandType;
import parser.ProParser;
import storage.ProTaskStorage;

public class LogicMain {
	
	// For testing
	public static void main(String[] args) throws ParseException {
		
		Printer.printToUser(Message.WELCOME);
		Memory mem = new Memory();
		
		// Storage.openFile(InternalStorage.getFilePath(), InternalStorage.getBuffer());
		toDoManager(mem);
		//storage.addTasks(mem);
	}
	
	// For testing
	public static void toDoManager(Memory mem) throws ParseException {
		
		while (true) {
			Printer.printToUser(Message.PROMPT);
			mem = executeCommand(mem.getScanner().nextLine(), mem);
			Printer.print(mem.getBuffer());
		}		
	} 
	/* Note: 
	 * Memory is being passed around among UI, logic and storage
	 * storage will update the respective information inside memory and return back to you when it first load
	 * you will need another function for the UI to call upon to load the storage, and display on the front end
	 * (When first open) [UI --> logic --> storage(load, aka update memory) --> return to logic --> return to UI]
	 * 
	 */
	
	/* Note: 
	 * Store feedback msg before you return memory back to UI  
	 */

	public Memory displayToUI(Memory mem) throws ParseException, FileNotFoundException {
		//storage.addTasks(mem);
		//storage.loadAllTasks();
		return executeCommand("display", mem);
	}
	
	public static Memory executeCommand(String command, Memory mem) {
		ProTaskStorage storage1 = new ProTaskStorage();
		Interpreter input = new Interpreter();

		try {
			input = ProParser.parse(command);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}

		CommandType commandInfo = input.getCommand();
		
		switch (commandInfo){
		case ADD:
			Affix.addTask(input, mem.getBuffer(), mem.numberGenerator()); //incomplete
			break;
		case DELETE:
			Obliterator.deleteTask(input, mem.getBuffer());
			break;
		case CLEAR:
			Obliterator.clearTask(input, mem.getBuffer());
			break;
		case DISPLAY:
			Printer.print(mem.getBuffer());
			break;
		case SEARCH:
			SearchEngine.determineSearch(input.getKey().toLowerCase(), mem);
			break;
		case EDIT:
			Amend.determineEdit(input, mem); //incomplete
			break;
		case UNDO:
			//incomplete
			break;
		case COMPLETE:
			Amend.setCompletionTask(input, mem.getBuffer());
			break;
		case UNCOMPLETE:
			Amend.setCompletionTask(input, mem.getBuffer());
			break;
		case POWERSEARCH:
			// incomplete
			break;
		case EXIT:
			System.exit(0);
		default:
			Printer.printToUser(Message.INVALID_COMMAND);
			break;
		}
		// Save to file
		storage1.addTasks(mem);
		return mem;
	}
/*
	public ArrayList<Floating> userInput(Floating userInput) {
		ArrayList<Floating> arrayList = new ArrayList<Floating>();
		arrayList.add(userInput); 
		return arrayList;
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
	
	public static void main(String args[]) throws IOException {
	
		while(true){
			System.out.println("Hi! Input command first: ");
			String input = sc.nextLine();
			String command = input.trim().split("\\s+")[0];
			identifyCommand(command);
		}
	} */
}
