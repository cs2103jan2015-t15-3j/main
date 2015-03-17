package logic;

import java.util.ArrayList;

import logic.Enumerator.AssignmentType;
import parser.Interpreter;

public class Amend {
	
	protected static void setCompletionTask(Interpreter item, ArrayList<Floating> buffer) {
		int taskId = item.getTaskId();
		boolean isCompleted = item.getCompleted();
		
		int index = SearchEngine.searchByTaskId(taskId, buffer);
		buffer.get(index).setIsCompleted(isCompleted);
	}
	
	protected static void editTask(Interpreter item, ArrayList<Floating> buffer, int index) {
		AssignmentType assign = item.getAssignment();

		switch (assign) {
		case DEADLINE:
			editDeadlineTask(item, buffer, index);
			break;
		case APPOINTMENT:
			editAppointmentTask(item, buffer, index);
			break;
		default:
			editFloatingTask(item, buffer, index);
		}
	}
	
	private static void editDeadlineTask(Interpreter item,
			ArrayList<Floating> buffer, int index) {
		Deadline newDeadline = new Deadline();

		newDeadline.setTaskName(item.getTaskName());
		newDeadline.setDate(item.getDueDate());
		newDeadline.setRemarks(item.getRemarks());

		ToBuffer.addDeadlineToBuffer(newDeadline, buffer);
	}
	
	private static void editFloatingTask(Interpreter item,
			ArrayList<Floating> buffer, int index) {
		Floating newFloating = new Floating();

		newFloating.setTaskName(item.getTaskName());
		newFloating.setRemarks(item.getRemarks());

		ToBuffer.addFloatingToBuffer(newFloating, buffer);
	}
		
	}
}
