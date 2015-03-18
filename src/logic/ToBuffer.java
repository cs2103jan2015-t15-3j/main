package logic;

import java.util.ArrayList;

public class ToBuffer {

	protected static void addTaskToBuffer(Task task,
			ArrayList<Task> buffer) {
		buffer.add(buffer.size(), task);
	}

	protected static void addDeadlineToBuffer(Deadline task,
			ArrayList<Task> buffer) {

	}

	protected static void addAppointmentToBuffer(Appointment task,
			ArrayList<Task> buffer) {

	}
}
