import java.util.Date;

public class Deadline extends Floating {


	private Date dueDate;

	public Deadline() {
		super();
		this.dueDate = new Date();
		this.setAssignment(AssignmentType.DEADLINE);
	}

	public Deadline(int taskID, String taskName, String remarks, boolean isCompleted, Date dueDate) {
		super(taskID, taskName, remarks, isCompleted);
		this.dueDate = dueDate;
		this.setAssignment(AssignmentType.DEADLINE);
	}

	public Date getDate() {
		return this.dueDate;
	}

	public void setDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	@Override
	  public String toString() {
	    return this.getTaskID() + "+" + this.getTaskName() + "+" + this.getRemarks() + "+" + this.getCompleted() 
	    		+ "+" + this.getAssignment() + "+" + this.getDate();
	  }
}
