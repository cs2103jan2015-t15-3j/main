package parser;

import java.util.*;
import java.io.*; 
import java.lang.*;

public class Methods {
	
	NewEntry newEntry = new NewEntry();
	CurrentEntry currEntry = new CurrentEntry();
	
	private static StringTokenizer inputString;
	private static String ID;
	private static String descriptionText;	
	private static String startDate;
	private static String endDate;
	private static String commentText;
	private static boolean isCompleted;

	
	public void add(String[] input) {
		if(isDate()) {
			newEntry.add(input);
		} else {
			newEntry.addFloating(input);
		}	
	}
	
	
	
	public void update(String[] input) {
		
	}
	
	public void delete(String[] input) {}
	
	public void search() {}
		

	private String setDefaultEndDate(String startDate) {
		return startDate;
	}
	
	private void setDescription() {
		String textContent = new String();
		
	}
	
	
	private boolean isComment(){

		return true;
	}

	
	private void setComment() {
		
	}
	
	

}
