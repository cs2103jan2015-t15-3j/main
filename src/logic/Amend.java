package logic;

import java.util.ArrayList;

import logic.Enumerator.TaskType;
import parser.Interpreter;

public class Amend {

	protected static void setCompletionTask(Interpreter item,
			ArrayList<Task> buffer) {
		int taskId = item.getTaskID();
		boolean isCompleted = item.getCompleted();

		int index = SearchEngine.searchBufferIndex(taskId, buffer);
		buffer.get(index).setIsCompleted(isCompleted);
	}

	protected static void determineEdit(Interpreter item, Memory mem) {
		String key = item.getKey();
		ArrayList<Task> buffer = mem.getBuffer();

		switch (key) {
		case "taskName":
			editTaskName(item, buffer);
			break;
		case "startDate":
			editTaskStartDate(item, buffer);
			break;
		case "dueDate":
			editTaskDueDate(item, buffer);
			break;
		default:
			editTaskRemarks(item, buffer);
		}
	}

	private static void editTaskName(Interpreter item, ArrayList<Task> buffer) {
		int taskId = item.getTaskID();
		int index = SearchEngine.searchBufferIndex(taskId, buffer);
		Task task = buffer.get(index);

		task.setTaskName(item.getTaskName());
	}

	private static void editTaskStartDate(Interpreter item,
			ArrayList<Task> buffer) {
		int taskId = item.getTaskID();
		int index = SearchEngine.searchBufferIndex(taskId, buffer);
		Task task = buffer.get(index);
		
		
		if(task.getType().equals(TaskType.APPOINTMENT)) {
			Appointment appt = (Appointment) task;
			appt.setStartDate(item.getStartDate());
		}
		else if (task.getType().equals(TaskType.DEADLINE)) {
			Deadline deadline = (Deadline) task;
			Appointment appt = new Appointment();
			
			appt.setTaskId(item.getTaskID());
			appt.setTaskName(item.getTaskName());
			appt.setStartDate(item.getStartDate());
			appt.setDate(item.getDueDate());
			appt.setRemarks(item.getRemarks());
		}

		/*
		 * check task.assignmentType if(appointment, just .setStartDate) else
		 * if(deadline, store all of deadline data into a new appointment before
		 * you replace into the buffer) floating task not acceptable for this
		 * function since no duedate is set
		 */
	}

	private static void editTaskDueDate(Interpreter item, ArrayList<Task> buffer) {
		int taskId = item.getTaskID();
		int index = SearchEngine.searchBufferIndex(taskId, buffer);
		Task task = buffer.get(index);
		
		if (task.getType().equals(TaskType.APPOINTMENT)) {
			Appointment appt = (Appointment) task;
			appt.setDate(item.getDueDate());
		}
		else if (task.getType().equals(TaskType.DEADLINE)) {
			Deadline deadline = (Deadline) task;
			deadline.setDate(item.getDueDate());
		}
		else {
			Deadline deadline = new Deadline();
			
			deadline.setTaskId(item.getTaskID());
			deadline.setTaskName(item.getTaskName());
			deadline.setDate(item.getDueDate());
			deadline.setRemarks(item.getRemarks());
		}

		/*
		 * check task.assignmentType if(appointment/deadline, just .setDueDate)
		 * else if(Floating, store all of floating data into a new deadline
		 * before you replace into the buffer)
		 */
	}

	private static void editTaskRemarks(Interpreter item, ArrayList<Task> buffer) {
		int taskId = item.getTaskID();
		int index = SearchEngine.searchBufferIndex(taskId, buffer);
		Task type = buffer.get(index);
		
		type.setRemarks(item.getRemarks());
	}
}
