package logic;

import java.util.ArrayList;

import parser.Interpreter;
import parser.Interpreter.CommandType;
import logic.Enumerator.TaskType;

public class History {

	private int index;
	private ArrayList<Task> historyBuffer;

	private Task task;
	private Deadline deadline;
	private Appointment appointment;

	private TaskType taskType;
	private Interpreter interpret;

	public History() {
		interpret = new Interpreter();
		this.task = new Task();
		this.deadline = new Deadline();
		this.appointment = new Appointment();
		this.index = 0;
		this.taskType = null;
		this.historyBuffer = new ArrayList<Task>();
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

	public int getIndex() {
		return this.index;
	}

	public int getTaskID() {
		return this.interpret.getTaskID();
	}

	public ArrayList<Task> getHistoryBuffer() {
		return this.historyBuffer;
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

	public void setIndex(int index) {
		this.index = index;
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

	public void setHistoryBuffer(ArrayList<Task> historyBuffer) {
		this.historyBuffer = historyBuffer;
	}
}