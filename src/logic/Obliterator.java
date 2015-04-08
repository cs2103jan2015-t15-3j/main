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

	protected static void deleteTask(int taskID, Repository repo) {
		String taskName = "";
		ArrayList<Task> buffer = repo.getBuffer();

		int index = SearchEngine.searchBufferIndex(taskID, buffer);
		taskName = buffer.get(index).getTaskName();
		buffer.remove(index);

		repo.setFeedbackMsg(String.format(Message.DELETED_SUCCESSFUL, taskName));
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
