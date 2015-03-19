package logic;

import java.util.ArrayList;

import parser.Interpreter.CommandType;
import logic.Enumerator.TaskType;;

public class History {

	private Task task; 
	private Deadline deadline; 
	private Appointment appointment;
	private ArrayList<Task> history;
	private TaskType tasktype;

	private CommandType command;
	private int count;
	
	public History() {
		this.task = new Task();
		this.deadline = new Deadline();
		this.appointment = new Appointment();
		this.history = new ArrayList<Task>();
		this.count = 0;
	}
	
	public Task getTask() {
		return this.task;
	}
	
	public Deadline getDeadLine() {
		return this.deadline;
	}
	
	public Appointment getAppointment() {
		return this.appointment;
	}
	
	public int getCount() {
		return this.count;
	}
	
	public CommandType getCommand() {
		return this.command;
	}
	
	public TaskType getTaskType() {
		return this.tasktype;
	}
	
}