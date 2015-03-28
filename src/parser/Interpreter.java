package parser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import logic.Enumerator.TaskType;

public class Interpreter {

	public enum CommandType {
		ADD, AMEND, DELETE, CLEAR, UPDATE, DISPLAY, SORT, COMPLETE, UNCOMPLETE, POWERSEARCH, EXIT, SEARCH, UNDO;
	}

	private int taskID;
	private int lastIndex;
	private String taskName;
	private String remarks;
	private boolean isCompleted;
	private boolean isDueDate;
	private boolean isStartDate;
	private Date dueDate;
	private Date startDate;
	private CommandType command;
	private TaskType type;
	private String key;
	private String feedbackMsg;

	public CommandType getCommand() {
		return this.command;
	}

	public int getTaskID() {
		return this.taskID;
	}
	
	public int getLastIndexTaskName() {
		return this.lastIndex;
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

	public boolean getIsDueDate() {
		return this.isDueDate;
	}

	public boolean getIsStartDate() {
		return this.isStartDate;
	}
	
	public Date getDueDate() {
		return this.dueDate;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public String getKey() {
		return this.key;
	}
	
	public String getFeedbackMsg() {
		return this.feedbackMsg;
	}
	
	public void setFeedbackMsg(String feedbackMsg) {
		this.feedbackMsg = feedbackMsg;
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
	
	public void setLastIndexTaskName(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setIsCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public void setIsDueDate(boolean isDueDate) {
		this.isDueDate = isDueDate;
	}

	public void setIsStartDate(boolean isStartDate) {
		this.isStartDate = isStartDate;
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
		this.key = key;
	}

	public String getDueDateString() {
		String dateString = "";
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm");
		if(this.dueDate == null) {
			return "null";
		}
		dateString = df.format(this.dueDate);
		return dateString;
	}

	public String getStartDateString() {
		String dateString = "";
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm");
		if(this.startDate == null) {
			return "null";
		}
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