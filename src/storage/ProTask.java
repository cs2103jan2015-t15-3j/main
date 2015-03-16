package storage;

public class ProTask {

	private int ID;
	private String description;
	private String date;
	private String startTime;
	private String endTime;
	private String remarks;
	private boolean isCompleted;

	public ProTask(int ID, String taskName) {
		this.ID = ID;
		description = taskName;
	}

	public String getTaskName() {
		return description;
	}

	public void setTaskName(String newTask) {
		description = newTask;
	}

	public int getID() {
		return ID;
	}

	public String getIDString() {
		StringBuilder sb = new StringBuilder();
		sb.append(ID);

		return sb.toString();
	}

	public void setID(int id) {
		ID = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public boolean getCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
}
