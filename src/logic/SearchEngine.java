package logic;

import java.util.ArrayList;

import parser.Interpreter;

public class SearchEngine {

	protected static void determineSearch(String input, Memory mem) {

		ArrayList<Task> buffer = mem.getBuffer();

		if (input.matches("\\d+")) {
			mem.setTempBuffer(searchByTaskId(input, buffer));
		} else {
			mem.setTempBuffer(searchByWords(input, buffer));
		}
	}

	protected static int searchBufferIndex(int taskId, ArrayList<Task> buffer) {
		int index = 0;

		for (int count = 0; count < buffer.size(); count++) {
			if (buffer.get(count).getTaskId() == taskId) {
				index = count;
				break;
			}
		}
		return index;
	}

	// tell ash to convert to string

	private static ArrayList<Task> searchByTaskId(String input,
			ArrayList<Task> buffer) {
		ArrayList<Task> searchList = new ArrayList<Task>();
		int id = Converter.convertToInt(input);
		Task assignment = buffer.get(id);

		searchList.add(assignment);

		return searchList;
	}

	private static ArrayList<Task> searchByWords(String wordSearch,
			ArrayList<Task> buffer) {
		ArrayList<Task> searchList = new ArrayList<Task>();

		for (int count = 0; count < buffer.size(); count++) {
			String description = buffer.get(count).getTaskName().toLowerCase();

			if (description.contains(wordSearch)) {
				searchList.add(searchBySentence(wordSearch, count, description,
						buffer));
			}
		}
		return searchList;
	}

	private static Task searchBySentence(String wordSearch, int count,
			String description, ArrayList<Task> buffer) {
		Task taskFound = new Task();

		if (description.equals(wordSearch)) {
			taskFound = buffer.get(count);
		} else {
			boolean isFound;
			isFound = searchByKeyWord(description, wordSearch);

			if (isFound) {
				taskFound = buffer.get(count);
			}
		}
		return taskFound;
	}

	private static boolean searchByKeyWord(String description,
			String searchKeyWord) {
		boolean found = false;

		String[] textArray = description.split(" ");

		for (int count = 0; count < textArray.length; count++) {
			String checkText = textArray[count];

			if (searchKeyWord.equals(checkText)) {
				found = true;
			} else {
				found = searchByKeyPhrase(count, textArray, searchKeyWord,
						checkText);
			}
		}
		return found;
	}

	private static boolean searchByKeyPhrase(int count, String[] textArray,
			String searchKeyWord, String checkText) {
		boolean found = false;

		for (int textExtendCount = count++; textExtendCount < textArray.length; textExtendCount++) {
			checkText += " " + textArray[textExtendCount];

			if (checkText.equalsIgnoreCase(searchKeyWord)) {
				found = true;
			}
		}
		return found;
	}

	protected static Task retrieveTask(Interpreter item, ArrayList<Task> buffer) {
		int taskId = item.getTaskId();
		int index = SearchEngine.searchBufferIndex(taskId, buffer);

		Task retrieveType = buffer.get(index);

		return retrieveType;
	}

}
