package logic;

import java.util.ArrayList;
//import java.util.Collections;

import java.util.Iterator;

import parser.Interpreter;

public class Obliterator {

	protected static void determineClear(Interpreter item,
			ArrayList<Task> buffer) {
		System.out.println(item.getClear());
		if (item.getClear().toLowerCase().equals("comp")) {
			clearCompletedTask(buffer);
		} else {
			clearTask(buffer);
		}
	}

	protected static void deleteTask(int taskID, ArrayList<Task> buffer) {
		int index = SearchEngine.searchBufferIndex(taskID, buffer);
		buffer.remove(index);
	}

	protected static void clearCompletedTask(ArrayList<Task> buffer) {
		boolean isCompleted = true;
		Iterator<Task> bufferList = buffer.iterator();

		while (bufferList.hasNext()) {
			if (bufferList.next().getCompleted() == isCompleted) {
				bufferList.remove();
			}
		}
	}

	protected static void clearTask(ArrayList<Task> buffer) {
		buffer.removeAll(buffer);
	}
}
