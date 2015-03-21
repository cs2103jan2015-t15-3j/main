package logic;

import java.util.ArrayList;

public class ToBuffer {

	protected static void addTaskToBuffer(Task task, ArrayList<Task> buffer) {
		buffer.add(buffer.size(), task);
	}

	protected static void addDeadlineToBuffer(Deadline deadline,
			ArrayList<Task> buffer) {
		int index = Organizer.indexInsertion(deadline, buffer);
		buffer.add(index, deadline);
	}

	protected static void addAppointmentToBuffer(Appointment appt,
			ArrayList<Task> buffer) {
		int index = Organizer.indexInsertion(appt, buffer);
		buffer.add(index, appt);
	}
}
