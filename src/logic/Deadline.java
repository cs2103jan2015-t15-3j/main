package logic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import logic.Enumerator.TaskType;

//@author A0112643R-reused

public class Deadline extends Task {

	private Date dueDate;

	public Deadline() {
		super();
		this.dueDate = new Date();
		this.setType(TaskType.DEADLINE);
	}

	public Deadline(int taskID, String taskName, String remarks,
			boolean isCompleted, Date dueDate) {
		super(taskID, taskName, remarks, isCompleted);
		this.dueDate = dueDate;
		this.setType(TaskType.DEADLINE);
	}

	public Date getDate() {
		return this.dueDate;
	}

	public void setDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public void setDueDateString(String dueDateString) {
		String dateString = "";
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm");
		dateString = df.format(this.dueDate);
	}

	public String getDueDateString() {
		String dateString = "";
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm");
		dateString = df.format(this.dueDate);
		return dateString;
	}

	@Override
	public String toString() {
		return "[" + this.getTaskID() + " " + this.getTaskName() + " "
				+ this.getRemarks() + " " + this.getCompleted() + " "
				+ this.getType() + " " + this.getDueDateString() + "]";
	}
}
