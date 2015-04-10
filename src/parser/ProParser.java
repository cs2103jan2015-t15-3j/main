package parser;

import java.text.ParseException;
import logic.Enumerator.ErrorType;

public class ProParser {

	public static Interpreter parse(String input) throws ParseException {
		Interpreter item = new Interpreter();
		
		try {
			if (input.contains("<")) {
				String[] splitInput = input.split("<");
				defineRemarks(item, splitInput[1]);
				String inputWithoutRemarks = splitInput[0];
				String[] inputArray = splitInput[0].split(" ");
				defineCommand(item, inputWithoutRemarks, inputArray);
			} else if(input.equals(" ")) {
				throw new NullPointerException();
			} else {
				String[] inputArray = input.split(" ");
				item.setRemarks("");
				defineCommand(item, input, inputArray);
			}
		} catch (NullPointerException npe) {
			item.setIsError(true);
			item.setErrorType(ErrorType.INVALID_INPUT);
		}
		return item;
	}

	private static void defineRemarks(Interpreter item, String remarks) {
		String[] remarksArray = remarks.split(">");
		item.setRemarks(remarksArray[0]);
	}

	private static void defineCommand(Interpreter item, String input, String[] inputArray)
			throws ParseException {

		String command = inputArray[0].toLowerCase();

		switch (command) {
		case "add":
		case "a":
			item.setCommandType(Interpreter.CommandType.ADD);
			ParserAdd.addTask(item, input, inputArray);
			break;
		case "delete":
		case "d":
			item.setCommandType(Interpreter.CommandType.DELETE);
			ParserDelete.deleteTask(item, inputArray);
			break;
		case "clear":
		case "cl":
			item.setCommandType(Interpreter.CommandType.CLEAR);
			ParserClear.clearTask(item, inputArray);
			break;
		case "display":
		case "dp":
			item.setCommandType(Interpreter.CommandType.DISPLAY);
			break;
		case "search":
		case "find":
			item.setCommandType(Interpreter.CommandType.SEARCH);
			ParserSearch.searchTask(item, inputArray);
			break;
		case "edit":
		case "e":
			item.setCommandType(Interpreter.CommandType.AMEND);
			ParserEdit.editTask(item, input, inputArray);
			break;
		case "undo":
		case "u":
			item.setCommandType(Interpreter.CommandType.UNDO);
			break;
		case "redo":
		case "r":
			item.setCommandType(Interpreter.CommandType.REDO);
			break;
		case "complete":
		case "cp":
			item.setCommandType(Interpreter.CommandType.COMPLETE);
			ParserCompleteTask.completeTask(item, inputArray);
			break;
		case "uncomplete":
		case "ucp":
			item.setCommandType(Interpreter.CommandType.UNCOMPLETE);
			ParserUncompleteTask.uncompleteTask(item, inputArray);
			break;
		case "psearch":
		case "ps":
			item.setCommandType(Interpreter.CommandType.POWERSEARCH);
			ParserPowerSearch.powerSearchTask(item, inputArray);
			break;
		case "sort":
		case "s":
			item.setCommandType(Interpreter.CommandType.SORT);
			break;
		case "exit":
			item.setCommandType(Interpreter.CommandType.EXIT);
			break;
		default:
			item.setCommandType(Interpreter.CommandType.INVALID_COMMAND);
			break;
		}
	}
}
