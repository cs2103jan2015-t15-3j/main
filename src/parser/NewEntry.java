
package parser;

import java.util.*;
import java.io.*; 
import java.lang.*;

public class NewEntry {
	
	private static StringTokenizer inputString;
	private static String ID;
	private static String descriptionText;	
	private static String startDate;
	private static String endDate;
	private static String commentText;
	private static boolean isCompleted;
	
	
	public void addFloating(String[] input) {
		setDescription();
		if(isComment()) {
			String comment = "true";
		
		}
	}
	
	public void add(String[] input) {
		setDescription();
		if(isComment()) {
			String comment = "true";
		
		}
	}
	
	public void setComplete(boolean isComplete) {
		this.isCompleted = isComplete;
	}
	
	public void setTaskID(String taskID) {
		this.ID = taskID;
	}
	
	public String getTaskID() {
		return this.ID;
	}

	
	private String setDate() {
		if(isDate()) {
			String date = "true";
			return date;
		} else {
			return "0";
		}
	}
}
