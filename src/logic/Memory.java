package logic;

import java.util.ArrayList;
import java.util.Scanner;

public class Memory {

	private ArrayList<Floating> buffer;
	private Scanner scanner;
	private int currentId;
	private String feedbackMsg;
	
	public Memory() {
		buffer = new ArrayList<Floating>();
		scanner = new Scanner(System.in);
		currentId = 0;
	}

	public ArrayList<Floating> getBuffer() {
		return this.buffer;
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
	
	public void setBuffer(ArrayList<Floating> buffer) {
		this.buffer = buffer;
	}

	public void setCurrentId(int currentId) {
		this.currentId = currentId;
	}
	
	public void setFeedback(String feedbackMsg) {
		this.feedbackMsg = feedbackMsg;
	}
}