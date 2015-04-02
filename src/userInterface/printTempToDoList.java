package userInterface;

import logic.Appointment;
import logic.Deadline;
import logic.Task;

public class printTempToDoList {

	public static String returnString(Task task) {

		int id;
		String name, start, end, remarks, str = null;

		boolean completed = task.getCompleted();

		if (completed == false) {

			String type = task.getType().toString();

			if (type.equals("APPOINTMENT")) {

				Appointment appt = (Appointment) task;

				id = task.getTaskID();
				name = task.getTaskName();
				start = appt.getStartDateString();
				end = appt.getDueDateString();
				remarks = task.getRemarks();

				str = "<html>" + "<font size=4>" + "<font color=#5d2e8a>" + "<br>" + id + ": "
						+ " " + name + "<br>" + "Start: " + start + " "
						+ "Due: " + end + "<br>" + "Remarks: " + remarks
						+ "</font>" + "<br>";

				str += "____________________________________________________________________________"
						+ "</html>";
			}

			else if (type.equals("DEADLINE")) {
				Deadline dl = (Deadline) task;

				id = task.getTaskID();
				name = task.getTaskName();
				start = " - ";
				end = dl.getDueDateString();
				remarks = task.getRemarks();

				str = "<html>" + "<font size=4>" + "<font color=#800000" + "<br>" + +id + ": "
						+ " " + name + "<br>" + "Start: " + start + " "
						+ "Due: " + end + "<br>" + "Remarks: " + remarks
						+ "</font>" + "<br>";

				str += "____________________________________________________________________________"
						+ "</html>";

			} else {

				id = task.getTaskID();
				name = task.getTaskName();
				start = " - ";
				end = " - ";
				remarks = task.getRemarks();

				str = "<html>" + "<font size=4>" + "<br>" + id + ": " + " " + name + "<br>"
						+ "Start: " + start + " " + "Due: " + end + "<br>"
						+ "Remarks: " + remarks + "<br>";

				str += "____________________________________________________________________________"
						+ "</html>";
			}

		}
		return str;
	}
}
