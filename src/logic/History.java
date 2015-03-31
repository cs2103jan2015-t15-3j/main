package logic;

import java.util.ArrayList;

import parser.Interpreter;
import parser.Interpreter.CommandType;
import logic.Enumerator.TaskType;

public class History {

	private Task task;
	private Deadline deadline;
	private Appointment appointment;
	private ArrayList<Task> historyBuffer;

	private TaskType taskType;
	private Interpreter interpret;

	public History() {
		interpret = new Interpreter();
		this.task = new Task();
		this.deadline = new Deadline();
		this.appointment = new Appointment();
		this.historyBuffer = new ArrayList<Task>();
		this.taskType = null;
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

	public CommandType getCommand() {
		return this.interpret.getCommand();
	}

	public TaskType getTaskType() {
		return this.taskType;
	}

	public ArrayList<Task> getHistory() {
		return this.historyBuffer;
	}

	public int getTaskID() {
		return this.interpret.getTaskID();
	}

	public void setTaskType(TaskType type) {
		this.taskType = type;
	}
	public void setCommand(CommandType command) {
		this.interpret.setCommandType(command);
	}

	public void setTaskID(int taskID) {
		this.interpret.setTaskID(taskID);
	}

	public void setHistoryBuffer(ArrayList<Task> historyBuffer) {
		this.historyBuffer = historyBuffer;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public void setDeadline(Deadline deadline) {
		this.deadline = deadline;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
}