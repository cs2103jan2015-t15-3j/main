package parser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import logic.Enumerator.TaskType;

public class ProParser {
	
	Interpreter item = new Interpreter();
	
	public static Interpreter parse(String input) {
		Interpreter item = new Interpreter();
		String[] inputArray = input.split(" ");
		
		defineCommand(item, inputArray);

		
		/*
			This is the main parser function,
			Interpreter is the object, where the necessary information is stored
			Understand different patterns, 
			and edit your user guide while you found new easy pattern 
			for each command
			you'll need a few classes for it
		*/
		return item;
	}
	
	public static void defineCommand(Interpreter item, String[] inputArray) {
		String command = inputArray[0];
		
		switch (command) {
		case "add":
			item.setCommandType(Interpreter.CommandType.ADD);
			break;
		case "delete":
			item.setCommandType(Interpreter.CommandType.DELETE);
			break;
		case "display":
			item.setCommandType(Interpreter.CommandType.DISPLAY);
			break;
		case "search":
			item.setCommandType(Interpreter.CommandType.SEARCH);
			break;
		case "edit":
			item.setCommandType(Interpreter.CommandType.EDIT);
			break;
		case "undo":
			item.setCommandType(Interpreter.CommandType.UNDO);
			break;
		case "complete":
			item.setCommandType(Interpreter.CommandType.COMPLETE);
			break;
		case "uncomplete":
			item.setCommandType(Interpreter.CommandType.UNCOMPLETE);
			break;
		case "powersearch":
			item.setCommandType(Interpreter.CommandType.POWERSEARCH);
			break;
		}
	}
	
	public static void defineType(Interpreter item, String[] inputArray) {
		int inputArrayLength = inputArray.length; 
		
		
	}
	
}
	/*
	public ProParser() {
		id = null;
		command = null; 
		descriptionText = null;	
		startDate = null;
		endDate = null;
		commentText = null;
		isCompleted = false;
	}	
	
	public void Decode(String input) {
		id = input.trim().split("\\s+")[0];
		command = input.trim().split("\\s+")[1];
		String[] textInput = identifyTextInput(input, command);
		
	}
	
	public static String[] identifyTextInput(String userInput, String command) {
		String[] textInputWithoutID = (userInput.replace(id,"").trim()).trim().split("\\s+");
		String[] textContent = (userInput.replace(command,"").trim()).trim().split("\\s+");  
		return textContent;
	}
	
	private void identifyCommand(String command, String[] textContent) {
		switch (command){
		case "add":
			methods.add(textContent);
			break;
		case "update":
			methods.update(textContent);
			break;
		case "delete":
			methods.delete(textContent);
			break;
		case "display":
			methods.display();
			break;
		case "exit":
			//storeText();
			System.exit(0);
		}
	}
	*/