package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import logic.Enumerator.TaskType;

public class Organizer {

	/*
	 * protected static ArrayList<Memory> determineSort(ArrayList<Memory>
	 * buffer) { ArrayList<Memory> sortedList = new ArrayList<Memory>();
	 * sortedList = sortByID(buffer); return sortedList; }
	 * 
	 * protected static void sort(Memory mem, ArrayList<Task> buffer) { buffer.
	 * mem.setTempBuffer(mem.getBuffer());
	 * mem.getTempBuffer().sort(numComparator);
	 * //Collections.sort(mem.getTempBuffer(), ); }
	 */

	/*
	 * protected static void determineSort(ArrayList<Task> buffer) {
	 * 
	 * ArrayList<Task> sortedList = new ArrayList<Task>(); sortedList =
	 * sortByID(buffer); return sortedList; }
	 */

	protected static void sort(ArrayList<Task> buffer, Memory mem) {
		mem.setTempBuffer(buffer);
		Collections.sort(mem.getTempBuffer(), numComparator);
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

	protected static Comparator<Task> numComparator = new Comparator<Task>() {
		public int compare(Task bufferOne, Task bufferTwo) {
			int first = bufferOne.getTaskID();
			int second = bufferTwo.getTaskID();
			return first - second;
		}
	};

}
