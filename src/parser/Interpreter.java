package parser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import logic.Enumerator.AssignmentType;

public class Interpreter {

	public enum CommandType {
		ADD, DELETE, CLEAR, UPDATE, DISPLAY, EDIT, COMPLETE, UNCOMPLETE, POWERSEARCH, EXIT, SEARCH, UNDO;
	}

	private int taskId;
	private String taskName;
	private String remarks;
	private boolean isCompleted;
	private Date dueDate;
	private Date startDate;
	private CommandType command;
	private AssignmentType assignType;
	private String searchKey;

	public CommandType getCommand() {
		return this.command;
	}

	public int getTaskId() {
		return this.taskId;
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

	public AssignmentType getAssignment() {
		return this.assignType;
	}

	public Date getDueDate() {
		return this.dueDate;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public String getSearchKey() {
		return this.searchKey;
	}

	public void setCommandType(CommandType command) {
		this.command = command;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setIsCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public void setAssignment(AssignmentType assignType) {
		this.assignType = assignType;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
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
		return this.getCommand() + "+" + this.getTaskId() + "+"
				+ this.getTaskName() + "+" + this.getRemarks() + "+"
				+ this.getCompleted() + "+" + this.getAssignment() + "+"
				+ this.getDueDateString() + "+" + this.getStartDateString();
	}
}