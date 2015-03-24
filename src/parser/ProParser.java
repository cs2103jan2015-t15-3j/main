package parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import logic.Enumerator.TaskType;

public class ProParser {
	
	public static Interpreter parse(String input) throws ParseException {
		Interpreter item = new Interpreter();
		//Split the input string and check for remarks
		if(input.contains("<")){
			String[] splitInput = input.split("<");
			String remarks = splitInput[1];
			item.setRemarks(remarks);
			String[] inputArray = splitInput[0].split(" ");
			defineCommand(item, inputArray);
		} else {
			String[] inputArray = input.split(" ");
			item.setRemarks("");
			defineCommand(item, inputArray);
		}
		
		//System.out.println("ProParserGetCmd: " + item.getCommand());
		return item;
	}
	
	private static void defineCommand(Interpreter item, String[] inputArray) throws ParseException {
	
		String command = inputArray[0].toLowerCase();
		
		switch (command) {
		case "add":
			item.setCommandType(Interpreter.CommandType.ADD);
			ParserAdd.addTask(item, inputArray);
			break;
		case "delete":
			item.setCommandType(Interpreter.CommandType.DELETE);
			ParserDelete.deleteTask(item, inputArray);
			break;
		case "clear":
			item.setCommandType(Interpreter.CommandType.CLEAR);
			break;	
		case "display":
			item.setCommandType(Interpreter.CommandType.DISPLAY);
			break;
		case "search":
			item.setCommandType(Interpreter.CommandType.SEARCH);
			ParserSearch.searchTask(item, inputArray);
			break;
		case "edit":
			item.setCommandType(Interpreter.CommandType.AMEND);
			ParserEdit.editTask(item, inputArray);
			break;
		case "undo":
			item.setCommandType(Interpreter.CommandType.UNDO);
			break;
		case "complete":
			item.setCommandType(Interpreter.CommandType.COMPLETE);
			ParserCompleteTask.completeTask(item, inputArray);
			break;
		case "uncomplete":
			item.setCommandType(Interpreter.CommandType.UNCOMPLETE);
			ParserUncompleteTask.uncompleteTask(item, inputArray);
			break;
		case "powersearch":
			item.setCommandType(Interpreter.CommandType.POWERSEARCH);
			break;
		case "sort":
			item.setCommandType(Interpreter.CommandType.SORT);
			break;
		case "exit":
			item.setCommandType(Interpreter.CommandType.EXIT);
			break;
		}
	}
}

	

	
	
	
