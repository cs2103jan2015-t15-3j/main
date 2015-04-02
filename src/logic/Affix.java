package logic;

import java.util.ArrayList;

import logic.Enumerator.TaskType;
import parser.Interpreter;

public class Affix {

	protected static void addTask(Interpreter item, ArrayList<Task> buffer,
			int index) {

		TaskType type = item.getType();

		switch (type) {
		case DEADLINE:
			addDeadlineTask(item, buffer, index);
			break;
		case APPOINTMENT:
			addAppointmentTask(item, buffer, index);
			break;
		default:
			addFloatTask(item, buffer, index);
		}
	}

	private static void addFloatTask(Interpreter item, ArrayList<Task> buffer,
			int index) {
		Task floating = new Task();

		floating.setTaskID(index);
		floating.setTaskName(item.getTaskName());
		floating.setRemarks(item.getRemarks());

		addToBuffer(floating, buffer);
	}

	private static void addDeadlineTask(Interpreter item,
			ArrayList<Task> buffer, int index) {
		Deadline deadline = new Deadline();

		deadline.setTaskID(index);
		deadline.setTaskName(item.getTaskName());
		deadline.setDate(item.getDueDate());
		deadline.setRemarks(item.getRemarks());

		addToBuffer(deadline, buffer);
	}

	protected static void addAppointmentTask(Interpreter item,
			ArrayList<Task> buffer, int index) {
		Appointment appt = new Appointment();

		appt.setTaskID(index);
		appt.setTaskName(item.getTaskName());
		appt.setStartDate(item.getStartDate());
		appt.setDate(item.getDueDate());
		appt.setRemarks(item.getRemarks());

		addToBuffer(appt, buffer);
	}
	
	protected static void addToBuffer(Task task, ArrayList<Task> buffer) {
		buffer.add(buffer.size(), task);
	}
}