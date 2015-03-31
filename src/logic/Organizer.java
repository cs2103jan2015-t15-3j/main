package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import userInterface.Logging;
import logic.Enumerator.TaskType;

public class Organizer {

	protected static void sort(Repository mem) {
		ArrayList<Task> addToTempBuffer = new ArrayList<Task>();

		for (int count = 0; count < mem.getBuffer().size(); count++) {
			addToTempBuffer.add(mem.getBuffer().get(count));
		}
		mem.setTempBuffer(addToTempBuffer);
		Collections.sort(mem.getTempBuffer(), Compare.StringComparator);
	}

	protected static int compareIndexInsertion(Deadline deadline,
			ArrayList<Task> buffer) {
		int index = 0;
		Iterator<Task> list = buffer.iterator();
		while (list.hasNext()) {
			Task task = list.next();
			if (task.getType().equals(TaskType.FLOATING)) {
				break;
			} else if (task.getType().equals(TaskType.DEADLINE)) {
				Deadline item = (Deadline) task;
				if (item.getDate().compareTo(deadline.getDate()) > 0
						|| item.getDate().compareTo(deadline.getDate()) == 0) {
					break;
				}
			}
			index++;
		}
		return index;
	}

	protected static int compareIndexInsertion(Appointment appt,
			ArrayList<Task> buffer) {
		int index = 0;
		Iterator<Task> list = buffer.iterator();
		while (list.hasNext()) {
			Task task = list.next();
			if (task.getType().equals(TaskType.FLOATING)
					|| task.getType().equals(TaskType.DEADLINE)) {
				break;
			} else if (task.getType().equals(TaskType.APPOINTMENT)) {
				Appointment item = (Appointment) task;
				if (item.getDate().compareTo(appt.getDate()) > 0
						|| item.getDate().compareTo(appt.getDate()) == 0) {
					break;
				}
			}
			index++;
		}
		return index;
	}
}
