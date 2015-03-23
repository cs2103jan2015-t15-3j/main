package logic;

import java.util.ArrayList;
import java.util.Stack;

public class Repository {

	private ArrayList<Task> buffer;
	private ArrayList<Task> tempBuffer;
	private int currentID;
	private String feedbackMsg;
	private Stack<History> undoAction;
	private Stack<History> redoAction;

	public Repository() {
		buffer = new ArrayList<Task>();
		currentID = 0;
		undoAction = new Stack<History>();
		redoAction = new Stack<History>();
	}

	public ArrayList<Task> getBuffer() {
		return this.buffer;
	}

	public ArrayList<Task> getTempBuffer() {
		return this.tempBuffer;
	}

	public int numberGenerator() {
		this.currentID++;
		return this.currentID;
	}

	public int getCurrentID() {
		return this.currentID;
	}

	public String getFeedback() {
		return this.feedbackMsg;
	}

	public Stack<History> getUndoAction() {
		if(this.undoAction == null) {
			Printer.printToUser(Message.UNDO_UNSUCCESSFUL);
		}
		return this.undoAction;
	}
	
	public Stack<History> getRedoAction() {
		if(this.undoAction == null) {
			Printer.printToUser(Message.REDO_UNSUCCESSFUL);
		}
		return this.redoAction;
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

	public void setUndoAction(Stack<History> undoAction) {
		this.undoAction = undoAction;
	}
	
	public void setRedoAction(Stack<History> redoAction) {
		this.redoAction = redoAction;
	}

	public void setFeedbackMsg(String feedbackMsg) {
		this.feedbackMsg = feedbackMsg;
	}
	
	public void undoActionPush(History history) {
		undoAction.push(history);
	}
	
	public History undoActionPop() {
		return undoAction.pop();
	}
	
	public History redoActionPeek() {
		return undoAction.peek();
	}
	
	public void redoActionPush(History history) {
		redoAction.push(history);
	}
	
	public History redoActionPop() {
		return redoAction.pop();
	}
	
	public History undoActionPeek() {
		return redoAction.peek();
	}
}