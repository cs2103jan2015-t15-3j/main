package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import logic.Enumerator.TaskType;

public class Organizer {

	protected static void sort(Memory mem) {
		ArrayList<Task> addToTempBuffer = new ArrayList<Task>();

		for (int count = 0; count < mem.getBuffer().size(); count++) {
			addToTempBuffer.add(mem.getBuffer().get(count));
		}
		mem.setTempBuffer(addToTempBuffer);
		Collections.sort(mem.getTempBuffer(), Comparable.numComparator);
	}

	protected static int indexInsertion(Deadline deadline,
			ArrayList<Task> buffer) {
		int index = 0;
		Iterator<Task> list = buffer.iterator();
		while (list.hasNext()) {
			Task task = list.next();
			if (task.getType().equals(TaskType.FLOATING)) {
				break;
			} else if (task.getType().equals(TaskType.DEADLINE)
					|| task.getType().equals(TaskType.APPOINTMENT)) {
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

	protected static int indexInsertion(Appointment appt, ArrayList<Task> buffer) {
		int index = 0;
		Iterator<Task> list = buffer.iterator();
		while (list.hasNext()) {
			Task task = list.next();
			if (task.getType().equals(TaskType.FLOATING)) {
				break;
			} else if (task.getType().equals(TaskType.DEADLINE)
					|| task.getType().equals(TaskType.APPOINTMENT)) {
				Deadline item = (Deadline) task;
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
