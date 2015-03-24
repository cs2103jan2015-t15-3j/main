package logic;

import java.sql.Date;
import java.util.Comparator;

public class Compare {
	
	public static Comparator<Task> numComparator = new Comparator<Task>() {
		public int compare(Task bufferOne, Task bufferTwo) {
			int first = bufferOne.getTaskID();
			int second = bufferTwo.getTaskID();
			return first - second;
		}
	};
	
	public static Comparator<Date> dateComparator = new Comparator<Date>() {
		public int compare(Date dateOne, Date dateTwo) {
				return dateOne.compareTo(dateTwo);
		}
	};
}
