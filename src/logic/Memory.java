package logic;

import java.util.ArrayList;
import java.util.Scanner;

public class Memory {

	private ArrayList<Task> buffer;
	private ArrayList<Task> tempBuffer;
	private Scanner scanner;
	private int currentID;
	private String feedbackMsg;
	// private Stack<History> action;
	
	public Memory() {
		buffer = new ArrayList<Task>();
		scanner = new Scanner(System.in);
		currentID = 0;
	}

	public ArrayList<Task> getBuffer() {
		return this.buffer;
	}
	
	public ArrayList<Task> getTempBuffer() {
		return this.tempBuffer;
	}

	public int numberGenerator () {
		this.currentID++;
		return this.currentID;
	}

	public int getCurrentID() {
		return this.currentID;
	}
	
	public String getFeedback() {
		return this.feedbackMsg;
	}
	/*
	public Stack<History> getAction() {
		return this.action;
	}
	 */
	public Scanner getScanner() {
		return this.scanner;
	}
	
	public void setBuffer(ArrayList<Task> buffer) {
		this.buffer = buffer;
	}
	
	public void setTempBuffer(ArrayList<Task> tempBuffer) {
		this.tempBuffer = tempBuffer;
	}

	public void setCurrentID(int currentID) {
		this.currentID = currentID;
	}
	/*
	public void setAction(Stack<History> action) {
		this.action = action;
	}
	 */
	public void setFeedbackMsg(String feedbackMsg) {
		this.feedbackMsg = feedbackMsg;
	}
}