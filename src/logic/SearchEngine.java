package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

//@author A0112643R

public class SearchEngine {

	protected static int convertToInt(String input) {
		int number = Integer.parseInt(input);
		return number;
	}

	protected static void determineSearch(String input, Repository repo) {
		ArrayList<Task> buffer = repo.getBuffer();
		int searchResult;
		if (input.matches("-?\\d+(\\.\\d+)?")) {
			repo.setTempBuffer(searchByTaskID(input.toLowerCase(), buffer));
			searchResult = repo.getTempBufferSize();
			repo.setFeedbackMsg(String.format(Message.SEARCH_FOUND,
					searchResult));

		} else {
			repo.setTempBuffer(searchByKeyWords(input.toLowerCase(), buffer));
			searchResult = repo.getTempBufferSize();
			repo.setFeedbackMsg(String.format(Message.SEARCH_FOUND,
					searchResult));
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

		Collections.sort(buffer, Compare.numComparator);
		int num = convertToInt(input);
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
		Collections.sort(searchByWordsList, Compare.numComparator);
		return searchByWordsList;
	}

	protected static Task retrieveTask(ArrayList<Task> buffer, int taskID) {
		Task retrieveType;

		int index = searchBufferIndex(taskID, buffer);
		retrieveType = buffer.get(index);

		return retrieveType;
	}
}
