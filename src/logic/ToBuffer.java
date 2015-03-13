package logic;

import java.util.ArrayList;

public class ToBuffer {

	protected static void addFloatingToBuffer(Floating task,
			ArrayList<Floating> buffer) {
		buffer.add(buffer.size(), task);
	}

	protected static void addDeadlineToBuffer(Deadline task,
			ArrayList<Floating> buffer) {

	}

	protected static void addAppointmentToBuffer(Appointment task,
			ArrayList<Floating> buffer) {

	}
}
