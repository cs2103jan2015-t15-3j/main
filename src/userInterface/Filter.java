package userInterface;

import java.util.ArrayList;

import logic.Appointment;
import logic.Deadline;
import logic.Repository;
import logic.Task;

public class Filter {

	public static ArrayList<String> record = new ArrayList<String>();
	public static String text;

	// constructor
	public Filter() {
	}

	public static void separator(Repository list) {

		for (Task task : list.getBuffer()) {
			String type = task.getType().toString();

			if (type.equals("APPOINTMENT")) {

				Appointment item = (Appointment) task;

				filter(task.getTaskID(), task.getTaskName(),
						item.getStartDateString(), item.getDueDateString(),
						task.getRemarks(), task.getCompleted(), task.getType()
								.toString());

			}

			else if (type.equals("DEADLINE")) {

				Deadline item = (Deadline) task;
				filter(task.getTaskID(), task.getTaskName(), "",
						item.getDueDateString(), task.getRemarks(),
						task.getCompleted(), task.getType().toString());
			}

			else {

				filter(task.getTaskID(), task.getTaskName(), "", "",
						task.getRemarks(), task.getCompleted(), task.getType()
								.toString());
			}
		}
	}

	public static void filter(int id, String desc, String startTime,
			String endTime, String remarks, boolean isCompleted, String type) {

		FilterInfo cloning = new FilterInfo(id, desc, startTime, endTime,
				remarks, isCompleted, type);

		record.add(intToString(id));
		record.add(desc);
		record.add(startTime);
		record.add(endTime);
		record.add(remarks);
		// record.add(String.valueOf(isCompleted));
		// record.add(convertToAbbreviation(type));
		// record.toArray(new String[0]);

		text = id + " " + desc + " " + startTime + " " + endTime + " " + " "
				+ remarks;

		System.out.println("in filter" + record);
	}

	public static ArrayList<String> returnList() {
		return record;
	}

	public static String returnLine() {
		return text;

	}

	private static String intToString(int id) {
		StringBuilder sb = new StringBuilder();
		sb.append(id);

		return sb.toString();
	}

	private static String convertToAbbreviation(String type) {
		String returnType = "NIL";

		if (type.equals("FLOATING")) {
			returnType = "FL";
		} else if (type.equals("APPOINTMENT")) {
			returnType = "AP";
		} else if (type.equals("DEADLINE")) {
			returnType = "DL";
		}
		return returnType;
	}
}
