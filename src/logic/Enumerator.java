package logic;

public class Enumerator {

	public enum TaskType {
		FLOATING, APPOINTMENT, DEADLINE 
	}
	
	public enum ErrorType {
		INVALID_INPUT, INVALID_TEXT, INVALID_DATE_TIME_FORMAT, INVALID_CATEGORY, INVALID_ID
	}
	
	public enum KEY {
		TASKNAME, STARTDATE, DUEDATE, REMARKS
	}
}
