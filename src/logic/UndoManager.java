package logic;

import java.util.ArrayList;

import logic.Enumerator.TaskType;
import parser.Interpreter.CommandType;

public class UndoManager {

	protected static void determineUndo(Repository repo) {
		History history = repo.undoActionPop();
		ArrayList<Task> buffer = repo.getBuffer();

		if (history.getCommand().equals(CommandType.ADD)) {
			undoAddAction(history.getTaskID(), buffer);
		}

		if (history.getCommand().equals(CommandType.DELETE)) {
			undoDeleteAction(history, buffer);
		}
	}

	protected static History pushAddToStack(int taskID) {
		History history = new History();
		history.setCommand(CommandType.ADD);
		history.setTaskID(taskID);
		return history;
	}

	protected static History pushDeleteToStack(int taskID, Repository repo) {
		History history = new History();
		history.setCommand(CommandType.DELETE);

		if (repo.getBuffer().get(taskID).getType().equals(TaskType.FLOATING)) {
			history.setTask(repo.getBuffer().get(taskID));
			history.setTaskType(TaskType.FLOATING);
		} else if (repo.getBuffer().get(taskID).getType()
				.equals(TaskType.DEADLINE)) {
			Deadline deadline = (Deadline) repo.getBuffer().get(taskID);
			history.setDeadline(deadline);

		} else if (repo.getBuffer().get(taskID).getType()
				.equals(TaskType.APPOINTMENT)) {
			Appointment appt = (Appointment) repo.getBuffer().get(taskID);
			history.setAppointment(appt);
		}

		return history;
	}

	private static void undoAddAction(int taskID, ArrayList<Task> buffer) {
		int index = SearchEngine.searchBufferIndex(taskID, buffer);
		System.out.println("ID: " + taskID);
		System.out.println("Index: " + index);
		buffer.remove(index);
	}

	private static void undoDeleteAction(History history, ArrayList<Task> buffer) {
		if (history.getTaskType().equals(TaskType.FLOATING)) {
			DataBuffer.addTaskToBuffer(history.getTask(), buffer);
		} else if (history.getTaskType().equals(TaskType.DEADLINE)) {
			DataBuffer.addDeadlineToBuffer(history.getDeadLine(), buffer);	
		}	
	}
}
