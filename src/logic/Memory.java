package logic;

import java.util.ArrayList;
import java.util.Scanner;

public class Memory {

	private ArrayList<Floating> buffer;
	private Scanner scanner;
	private int latestId;

	public Memory() {
		buffer = new ArrayList<Floating>();
		scanner = new Scanner(System.in);
		latestId = 0;
	}

	public ArrayList<Floating> getBuffer() {
		return this.buffer;
	}

	public int serialNumGen() {
		this.latestId++;
		return this.latestId;
	}

	public int getId() {
		return this.latestId;
	}

	public Scanner getScanner() {
		return this.scanner;
	}

	public void setBuffer(ArrayList<Floating> buffer) {
		this.buffer = buffer;
	}

	public void setId(int latestId) {
		this.latestId = latestId;
	}
}
