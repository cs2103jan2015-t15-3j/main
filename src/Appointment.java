import java.util.Date;

	public class Appointment extends Deadline {

		private Date startDate;
		
		public Appointment() {
			super();
			this.startDate = new Date();
			this.setAssignment(AssignmentType.APPOINTMENT);
		}

		public Appointment(int taskID, String taskName, String remarks, boolean isCompleted, Date dueDate, Date startDate) {
			super(taskID, taskName, remarks, isCompleted, dueDate);
			this.startDate = startDate;
			this.setAssignment(AssignmentType.APPOINTMENT);
		}

		public Date getStartDate() {
			return this.startDate;
		}

		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}
		
		@Override
		  public String toString() {
		    return this.getTaskID() + "+" + this.getTaskName() + "+" + this.getRemarks() + "+" + this.getCompleted() 
		    		+ "+" + this.getAssignment() + "+" + this.getDate() + "+" + this.getStartDate();
		  }
}
