package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import parser.Interpreter;

public class SearchEngine {

	protected static void determineSearch(String input, Memory mem) {

		ArrayList<Task> buffer = mem.getBuffer();

		if (input.matches("\\d+")) {
			mem.setTempBuffer(searchByTaskID(input.toLowerCase(), buffer));
		} else {
			mem.setTempBuffer(searchByKeyWords(input.toLowerCase(), buffer));
		}
	}

	protected static int searchBufferIndex(int taskID, ArrayList<Task> buffer) {
		int index = 0;
		Iterator<Task> bufferList = buffer.iterator();

		while (bufferList.hasNext()) {
			if (bufferList.next().getTaskID() == taskID) {
				break;
			}
			index++;
		}
		return index;
	}

	private static ArrayList<Task> searchByTaskID(String input,
			ArrayList<Task> buffer) {
		ArrayList<Task> searchByIDList = new ArrayList<Task>();
		Collections.sort(buffer, Comparable.numComparator);
		int num = Converter.convertToInt(input);
		int index = searchBufferIndex(num, buffer);

		searchByIDList.add(buffer.get(index));

		return searchByIDList;
	}

	private static ArrayList<Task> searchByKeyWords(String wordSearch,
			ArrayList<Task> buffer) {
		ArrayList<Task> searchByWordsList = new ArrayList<Task>();
		for (int count = 0; count < buffer.size(); count++) {
			String description = buffer.get(count).getTaskName().toLowerCase();
			if (description.contains(wordSearch.toLowerCase())) {
				searchByWordsList.add(buffer.get(count));
			}
		}
		Collections.sort(searchByWordsList, Comparable.numComparator);
		return searchByWordsList;
	}

	protected static Task retrieveTask(Interpreter item, ArrayList<Task> buffer) {
		int taskID = item.getTaskID();
		int index = searchBufferIndex(taskID, buffer);

		Task retrieveType = buffer.get(index);

		return retrieveType;
	}
}
