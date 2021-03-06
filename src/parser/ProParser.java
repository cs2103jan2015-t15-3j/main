package parser;

import java.text.ParseException;

//@author: A0110818M
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
			} else {
				String[] inputArray = input.split(" ");
			
				item.setIsRemarks(false);
				item.setRemarks("");
				defineCommand(item, input, inputArray);
			}
		} catch (NullPointerException npe) {
			item.setIsError(true);
			item.setFeedbackMsg(ParserMessage.INVALID_INPUT);
		}
		return item;
	}

	private static void defineRemarks(Interpreter item, String remarks) {
		if(remarks == null || remarks.equals(">")) {
			item.setIsRemarks(false);
			item.setRemarks(" ");
		} else {		
			String[] remarksArray = remarks.split(">");
			item.setIsRemarks(true);
			item.setRemarks(remarksArray[0]);
		}
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
			ParserClear.clearTask(item, input, inputArray);
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
		case "sort":
		case "s":
			item.setCommandType(Interpreter.CommandType.SORT);
			break;
		case "exit":
			item.setCommandType(Interpreter.CommandType.EXIT);
			break;
		case "move":
		case "mv":
			item.setCommandType(Interpreter.CommandType.MOVE);
			ParserMove.moveDataBase(item, inputArray);
			break;
		default:
			item.setIsError(true);
			item.setFeedbackMsg(ParserMessage.INVALID_COMMAND);
			item.setCommandType(Interpreter.CommandType.INVALID_COMMAND);
			break;
		}
	}
}
