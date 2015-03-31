package logic;

import java.util.ArrayList;

import logic.Enumerator.TaskType;
import parser.Interpreter;
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

		if (history.getCommand().equals(CommandType.AMEND)) {
			undoAmendAction(history, buffer);
		}

		if (history.getCommand().equals(CommandType.COMPLETE)) {
			undoCompleteAction(history, buffer);
		}

		if (history.getCommand().equals(CommandType.UNCOMPLETE)) {
			undoUncompleteAction(history, buffer);
		}
	}

	protected static History pushAddToStack(Interpreter input, int taskID) {
		History history = new History();
		history.setCommand(input.getCommand());
		history.setTaskID(taskID);
		return history;
	}

	protected static History pushCompleteOrUncompleteToStack(Interpreter input,
			Repository repo) {
		History history = new History();
		history.setCommand(input.getCommand());
		int index = SearchEngine.searchBufferIndex(input.getTaskID(),
				repo.getBuffer());
		history.setIndex(index);
		return history;
	}

	protected static History pushDeleteToStack(Interpreter input,
			Repository repo) {
		History history = new History();
		history.setCommand(input.getCommand());
		int index = SearchEngine.searchBufferIndex(input.getTaskID(),
				repo.getBuffer());

		if (repo.getBuffer().get(index).getType().equals(TaskType.FLOATING)) {
			history.setTask(repo.getBuffer().get(index));
			history.setTaskType(TaskType.FLOATING);

		} else if (repo.getBuffer().get(index).getType()
				.equals(TaskType.DEADLINE)) {
			Deadline deadline = (Deadline) repo.getBuffer().get(index);
			history.setDeadline(deadline);

		} else if (repo.getBuffer().get(index).getType()
				.equals(TaskType.APPOINTMENT)) {
			Appointment appt = (Appointment) repo.getBuffer().get(index);
			history.setAppointment(appt);
		}
		return history;
	}

	protected static History pushAmendToStack(Interpreter input, Repository repo) {
		History history = new History();
		int index = SearchEngine.searchBufferIndex(input.getTaskID(),
				repo.getBuffer());
		history.setCommand(input.getCommand());
		history.setIndex(index);
		history.setTaskType(input.getType());

		if (repo.getBuffer().get(index).getType().equals(TaskType.FLOATING)) {
			history.setTask(repo.getBuffer().get(index));
			history.setTaskType(TaskType.FLOATING);
		} else if (repo.getBuffer().get(index).getType()
				.equals(TaskType.DEADLINE)) {
			Deadline deadline = (Deadline) repo.getBuffer().get(index);
			history.setDeadline(deadline);

		} else if (repo.getBuffer().get(index).getType()
				.equals(TaskType.APPOINTMENT)) {
			Appointment appt = (Appointment) repo.getBuffer().get(index);
			history.setAppointment(appt);
		}
		return history;
	}

	private static void undoAddAction(int taskID, ArrayList<Task> buffer) {
		int index = SearchEngine.searchBufferIndex(taskID, buffer);
		System.out.println("taskID : " + taskID);
		System.out.println("index: " + index);
		buffer.remove(index);
	}

	private static void undoDeleteAction(History history, ArrayList<Task> buffer) {
		if (history.getTaskType().equals(TaskType.FLOATING)) {
			DataBuffer.addTaskToBuffer(history.getTask(), buffer);

		} else if (history.getTaskType().equals(TaskType.DEADLINE)) {
			DataBuffer.addDeadlineToBuffer(history.getDeadLine(), buffer);

		} else if (history.getTaskType().equals(TaskType.APPOINTMENT)) {
			DataBuffer.addAppointmentToBuffer(history.getAppointment(), buffer);
		}
	}

	private static void undoAmendAction(History history, ArrayList<Task> buffer) {
		buffer.remove(history.getIndex());

		if (history.getTaskType().equals(TaskType.FLOATING)) {
			DataBuffer.addTaskToBuffer(history.getTask(), buffer);

		} else if (history.getTaskType().equals(TaskType.DEADLINE)) {
			DataBuffer.addDeadlineToBuffer(history.getDeadLine(), buffer);

		} else if (history.getTaskType().equals(TaskType.APPOINTMENT)) {
			DataBuffer.addAppointmentToBuffer(history.getAppointment(), buffer);
		}
	}

	private static void undoCompleteAction(History history,
			ArrayList<Task> buffer) {
		buffer.get(history.getIndex()).setIsCompleted(false);
	}

	private static void undoUncompleteAction(History history,
			ArrayList<Task> buffer) {
		buffer.get(history.getIndex()).setIsCompleted(true);
	}
}
