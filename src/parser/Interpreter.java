package parser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import logic.Enumerator.TaskType;

public class Interpreter {

	public enum CommandType {
		ADD, DELETE, CLEAR, UPDATE, DISPLAY, EDIT, COMPLETE, UNCOMPLETE, POWERSEARCH, EXIT, SEARCH, UNDO;
	}

	private int taskID;
	private String taskName;
	private String remarks;
	private boolean isCompleted;
	private Date dueDate;
	private Date startDate;
	private CommandType command;
	private TaskType type;
	private String searchKey;

	public CommandType getCommand() {
		return this.command;
	}

	public int getTaskID() {
		return this.taskID;
	}

	public String getTaskName() {
		return this.taskName;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public boolean getCompleted() {
		return this.isCompleted;
	}

	public TaskType getType() {
		return this.type;
	}

	public Date getDueDate() {
		return this.dueDate;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public String getKey() {
		return this.searchKey;
	}

	public void setCommandType(CommandType command) {
		this.command = command;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setIsCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public void setType(TaskType type) {
		this.type = type;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setKey(String key) {
		this.searchKey = key;
	}

	public String getDueDateString() {
		String dateString = "";
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm a");
		dateString = df.format(this.dueDate);
		return dateString;
	}

	public String getStartDateString() {
		String dateString = "";
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm a");
		dateString = df.format(this.startDate);
		return dateString;
	}

	public String toString() {
		return this.getCommand() + "+" + this.getTaskID() + "+"
				+ this.getTaskName() + "+" + this.getRemarks() + "+"
				+ this.getCompleted() + "+" + this.getType() + "+"
				+ this.getDueDateString() + "+" + this.getStartDateString();
	}
}