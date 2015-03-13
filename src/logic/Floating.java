package logic;

import logic.Enumerator.AssignmentType;

public class Floating {

	private int taskId;
	private String taskName;
	private boolean isCompleted;
	private String remarks;
	private AssignmentType assignType;

	public Floating() {
		this.taskId = 0;
		this.taskName = null;
		this.isCompleted = false;
		this.remarks = null;
		this.assignType = AssignmentType.FLOATING;
	}

	public Floating(int taskId, String taskName, String remarks,
			boolean isCompleted) {
		this.taskId = taskId;
		this.taskName = taskName;
		this.remarks = remarks;
		this.isCompleted = isCompleted;
		this.assignType = AssignmentType.FLOATING;
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

	@Override
	public String toString() {
		return this.getTaskId() + "+" + this.getTaskName() + "+"
				+ this.getRemarks() + "+" + this.getCompleted() + "+"
				+ this.getAssignment();
	}
}
