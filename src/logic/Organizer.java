package logic;

import java.util.ArrayList;

public class Organizer {

	private static final int LENGTH_COUNT = 1;

	protected static ArrayList<Task> determineSort(String sort,
			ArrayList<Task> buffer) {

		ArrayList<Task> sortedList = new ArrayList<Task>();

		if (sort.equalsIgnoreCase("id")) {
			sortedList = sortById(buffer);
		}
		return sortedList;
	}

	private static ArrayList<Task> sortById(ArrayList<Task> buffer) {
		ArrayList<Task> sortedListById = new ArrayList<Task>();
		for (int count = 0; count < buffer.size(); count++) {

			if (sortedListById.size() >= LENGTH_COUNT) {
				sortedListById = stringComparator(buffer, sortedListById);
			} else {
				sortedListById.add(buffer.get(count));
			}
		}
		return sortedListById;
	}

	private static ArrayList<Task> stringComparator(ArrayList<Task> buffer,
			ArrayList<Task> sortedListById) {
		return sortedListById;
	}

	protected static int indexInsertion(Deadline deadline, ArrayList<Task> buffer) {
		int index = 0;
		
		
		return index;
	}
}
