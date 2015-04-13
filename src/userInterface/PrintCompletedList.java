package userInterface;

import logic.Appointment;
import logic.Deadline;
import logic.Task;

//@author A0112961L

public class PrintCompletedList {

	private static final String HTML_OPEN = "<html>";
	private static final String HTML_CLOSE = "</html>";
	private static final String HTML_BREAK = "<br>";
	private static final String HTML_FONT_SIZE_4 = "<font size=4>";
	private static final String HTML_MAROON_COLOR = "<font color=#800000>";
	private static final String HTML_PURPLE_COLOR = "<font color=#5d2e8a>";
	private static final String HTML_FONT_CLOSE = "</font>";

	protected static String returnString(Task task) {

		int id;
		String name, start, end, remarks, str = null;

		boolean completed = task.getCompleted();

		if (completed == true) {

			String type = task.getType().toString();

			if (type.equals("APPOINTMENT")) {

				Appointment appt = (Appointment) task;

				id = task.getTaskID();
				name = task.getTaskName();
				start = appt.getStartDateString();
				end = appt.getDueDateString();
				remarks = task.getRemarks();

				str = HTML_OPEN + HTML_FONT_SIZE_4 + HTML_PURPLE_COLOR
						+ HTML_BREAK + id + ". " + " " + name + HTML_BREAK
						+ "Start: " + start + HTML_BREAK + "Due: " + end
						+ HTML_BREAK + "Remarks: " + remarks + HTML_FONT_CLOSE
						+ HTML_BREAK + HTML_CLOSE;
			} else if (type.equals("DEADLINE")) {
				Deadline dl = (Deadline) task;

				id = task.getTaskID();
				name = task.getTaskName();
				start = " - ";
				end = dl.getDueDateString();
				remarks = task.getRemarks();

				str = HTML_OPEN + HTML_FONT_SIZE_4 + HTML_MAROON_COLOR
						+ HTML_BREAK + id + ". " + " " + name + HTML_BREAK
						+ "Start: " + start + HTML_BREAK + "Due: " + end
						+ HTML_BREAK + "Remarks: " + remarks + HTML_FONT_CLOSE
						+ HTML_BREAK + HTML_CLOSE;
			} else {

				id = task.getTaskID();
				name = task.getTaskName();
				start = " - ";
				end = " - ";
				remarks = task.getRemarks();

				str = HTML_OPEN + HTML_FONT_SIZE_4 + HTML_BREAK + id + ". "
						+ " " + name + HTML_BREAK + "Start: " + start
						+ HTML_BREAK + "Due: " + end + HTML_BREAK + "Remarks: "
						+ remarks + HTML_FONT_CLOSE + HTML_BREAK + HTML_CLOSE;
			}

		}
		return str;
	}
}