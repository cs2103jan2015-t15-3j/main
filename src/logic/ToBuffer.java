package logic;

import java.util.ArrayList;

public class ToBuffer {

	protected static void addTaskToBuffer(Task task,
			ArrayList<Task> buffer) {
		buffer.add(buffer.size(), task);
	}

	protected static void addDeadlineToBuffer(Deadline deadline,
			ArrayList<Task> buffer) {
		buffer.add(buffer.size(), deadline);
	}

	protected static void addAppointmentToBuffer(Appointment appt,
			ArrayList<Task> buffer) {
		buffer.add(buffer.size(), appt);
	}
}
