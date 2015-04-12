package logic;

import logic.Enumerator.TaskType;

//@author A0112643R

public class Task {

	private int taskID;
	private String taskName;
	private boolean isCompleted;
	private String remarks;
	private TaskType type;

	public Task() {
		this.taskID = 0;
		this.taskName = "";
		this.isCompleted = false;
		this.remarks = "";
		this.type = TaskType.FLOATING;
	}

	public Task(int taskID, String taskName, String remarks, boolean isCompleted) {
		this.taskID = taskID;
		this.taskName = taskName;
		this.remarks = remarks;
		this.isCompleted = isCompleted;
		this.type = TaskType.FLOATING;
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

	@Override
	public String toString() {
		return "[" + this.getTaskID() + " " + this.getTaskName() + " "
				+ this.getRemarks() + " " + this.getCompleted() + " "
				+ this.getType() + "]";
	}
}
