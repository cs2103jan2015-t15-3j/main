package userInterface;

public class RetrieveInfo {

	protected int id;
	protected String task, start, end, remarks;

	@SuppressWarnings("null")
	
	public RetrieveInfo() {
		id = (Integer) null;
		task = "";
		start = "";
		end = "";
		remarks = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
