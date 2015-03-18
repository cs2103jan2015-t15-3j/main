package logic;

import logic.Enumerator.TaskType;

public class Task {

	private int taskId;
	private String taskName;
	private boolean isCompleted;
	private String remarks;
	private TaskType type;

	public Task() {
		this.taskId = 0;
		this.taskName = null;
		this.isCompleted = false;
		this.remarks = null;
		this.type = TaskType.FLOATING;
	}

	public Task(int taskId, String taskName, String remarks,
			boolean isCompleted) {
		this.taskId = taskId;
		this.taskName = taskName;
		this.remarks = remarks;
		this.isCompleted = isCompleted;
		this.type = TaskType.FLOATING;
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

	public TaskType getType() {
		return this.type;
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

	public void setType(TaskType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return this.getTaskId() + "+" + this.getTaskName() + "+"
				+ this.getRemarks() + "+" + this.getCompleted() + "+"
				+ this.getType();
	}
}
