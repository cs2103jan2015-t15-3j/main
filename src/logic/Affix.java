package logic;

import java.util.ArrayList;

import logic.Enumerator.AssignmentType;
import parser.Interpreter;

public class Affix {

	protected static void addTask(Interpreter item, ArrayList<Floating> buffer,
			int index) {

		AssignmentType assign = item.getAssignment();

		switch (assign) {
		case DEADLINE:
			addDeadlineTask(item, buffer, index);
			break;
		case APPOINTMENT:
			addAppointmentTask(item, buffer, index);
			break;
		default:
			addFloatingTask(item, buffer, index);
		}
	}

	private static void addFloatingTask(Interpreter item,
			ArrayList<Floating> buffer, int index) {
		Floating newFloating = new Floating();

		newFloating.setTaskId(index);
		newFloating.setTaskName(item.getTaskName());
		newFloating.setRemarks(item.getRemarks());

		ToBuffer.addFloatingToBuffer(newFloating, buffer);
	}

	private static void addDeadlineTask(Interpreter item,
			ArrayList<Floating> buffer, int index) {
		Deadline newDeadline = new Deadline();

		newDeadline.setTaskId(index);
		newDeadline.setTaskName(item.getTaskName());
		newDeadline.setDate(item.getDueDate());
		newDeadline.setRemarks(item.getRemarks());

		ToBuffer.addDeadlineToBuffer(newDeadline, buffer);
	}

	protected static void addAppointmentTask(Interpreter item,
			ArrayList<Floating> buffer, int index) {
		Appointment newAppointmentTask = new Appointment();

		newAppointmentTask.setTaskId(index);
		newAppointmentTask.setTaskName(item.getTaskName());
		newAppointmentTask.setStartDate(item.getStartDate());
		newAppointmentTask.setDate(item.getDueDate());
		newAppointmentTask.setRemarks(item.getRemarks());

		ToBuffer.addAppointmentToBuffer(newAppointmentTask, buffer);
	}
}