
public class ProTask {

	private int ID;
	private String description;
	
	public ProTask(int ID, String taskName)
	{
		this.ID = ID;
		description = taskName;
	}
	
	public String getTaskName()
	{
		return description;
	}
	public void setTaskName(String newTask)
	{
		description = newTask;
	}
	public int getID()
	{
		return ID;
	}
	
}
