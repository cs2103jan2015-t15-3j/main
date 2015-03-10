package logic;
public class Floating {
	
	enum AssignmentType {
		FLOATING, APPOINTMENT, DEADLINE
	}

	private int taskID;
	private String taskName;
	private boolean isCompleted;
	private String remarks;
	private AssignmentType assignType;
	
	public Floating () {
		this.taskID = 0;
		this.taskName = null;
		this.isCompleted = false;
		this.remarks = null;
		this.assignType = AssignmentType.FLOATING;
	}
	
	public Floating(int taskID, String taskName, String remarks, boolean isCompleted) {
		this.taskID = taskID;
		this.taskName = taskName;
		this.remarks = remarks;
		this.isCompleted = isCompleted;
		this.assignType = AssignmentType.FLOATING;
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
	
	public AssignmentType getAssignment() {
		return this.assignType;
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
	
	public void setAssignment(AssignmentType assignType) {
		this.assignType = assignType;
	}
	
	@Override
	  public String toString() {
	    return this.getTaskID() + "+" + this.getTaskName() + "+" + this.getRemarks() + "+" + this.getCompleted() 
	    		+ "+" + this.getAssignment();
	  }
}
