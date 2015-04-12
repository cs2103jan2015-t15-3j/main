package logic;

import java.util.Comparator;

//@author A0112643R

public class Compare {

	public static Comparator<Task> numComparator = new Comparator<Task>() {
		public int compare(Task bufferOne, Task bufferTwo) {
			int first = bufferOne.getTaskID();
			int second = bufferTwo.getTaskID();
			return first - second;
		}
	};

	public static Comparator<Task> StringComparator = new Comparator<Task>() {
		public int compare(Task bufferOne, Task bufferTwo) {
			String first = bufferOne.getTaskName();
			String second = bufferTwo.getTaskName();
			return first.compareTo(second);
		}
	};
}
