/*
 * 
 */

package parser;

import java.util.*;
import java.io.*; 
import java.lang.*;

public class ProParser {
	
	Interpreter item = new Interpreter();
	
	private static StringTokenizer inputString;
	private static String id;
	private static String command;	
	private static String descriptionText;	
	private static String startDate;
	private static String endDate;
	private static String commentText;
	private static boolean isCompleted;
	
	
	public ProParser() {
		id = null;
		command = null; 
		descriptionText = null;	
		startDate = null;
		endDate = null;
		commentText = null;
		isCompleted = false;
	}	
	
	public void Decode(String input) {
		id = input.trim().split("\\s+")[0];
		command = input.trim().split("\\s+")[1];
		String[] textInput = identifyTextInput(input, command);
		
	}
	
	public static String[] identifyTextInput(String userInput, String command) {
		String[] textInputWithoutID = (userInput.replace(id,"").trim()).trim().split("\\s+");
		String[] textContent = (userInput.replace(command,"").trim()).trim().split("\\s+");  
		return textContent;
	}
	
}
	
	
	
