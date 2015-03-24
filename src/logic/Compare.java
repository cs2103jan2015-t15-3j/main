package logic;

import java.util.Comparator;
//import java.util.Date;

public class Compare {
	
	public static Comparator<Task> numComparator = new Comparator<Task>() {
		public int compare(Task bufferOne, Task bufferTwo) {
			int first = bufferOne.getTaskID();
			int second = bufferTwo.getTaskID();
			return first - second;
		}
	};
	
	//public static Comparator<Date> dateComparator = new Comparator<Date>() {
		//public int compare(Date dateOne, Date dateTwo) {
			//return dateOne.
		//}
	//};
}
