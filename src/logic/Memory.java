package logic;

import java.util.ArrayList;
import java.util.Scanner;

public class Memory {

	private ArrayList<Task> buffer;
	private ArrayList<Task> searchBuffer;
	private Scanner scanner;
	private int currentId;
	private String feedbackMsg;
	
	public Memory() {
		buffer = new ArrayList<Task>();
		scanner = new Scanner(System.in);
		currentId = 0;
	}

	public ArrayList<Task> getBuffer() {
		return this.buffer;
	}
	
	public ArrayList<Task> getSearchBuffer() {
		return this.searchBuffer;
	}

	public int numberGenerator () {
		this.currentId++;
		return this.currentId;
	}

	public int getCurrentId() {
		return this.currentId;
	}
	
	public String getFeedback() {
		return this.feedbackMsg;
	}
	
	public Scanner getScanner() {
		return this.scanner;
	}
	
	public void setBuffer(ArrayList<Task> buffer) {
		this.buffer = buffer;
	}
	
	public void setSearchBuffer(ArrayList<Task> searchBuffer) {
		this.searchBuffer = searchBuffer;
	}

	public void setCurrentId(int currentId) {
		this.currentId = currentId;
	}
	
	public void setFeedbackMsg(String feedbackMsg) {
		this.feedbackMsg = feedbackMsg;
	}
}