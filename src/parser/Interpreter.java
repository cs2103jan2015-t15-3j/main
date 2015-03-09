/* Interpreter class takes in input from ProParser
 * and decodes them. 
 * Methods will be called in Methods class
 * Methods decoded to fetch stuff will be implemented 
 * in Interpreter class
 */
package parser;

import java.util.*;
import java.io.*; 
import java.lang.*;

public class Interpreter {
	
	Methods methods = new Methods();
	
	enum CommandType {
		ADD, DELETE, UPDATE, SEARCH, EXIT; 
	}
	private CommandType command;

	private static StringTokenizer inputString;
	private static String ID;
	private static String descriptionText;	
	private static String startDate;
	private static String endDate;
	private static String commentText;
	private static boolean isCompleted;
	
	private void identifyCommand(String[] textContent) {
		switch (command){
		case ADD:
			methods.add(textContent);
			break;
		case UPDATE:
			methods.update(textContent);
			break;
		case DELETE:
			methods.delete(textContent);
			break;
		case SEARCH:
			methods.search();
			break;
		case EXIT:
			//storeText();
			System.exit(0);
		}
	}
	
	/*==========GET METHODS==========*/	
	private String getStartDate() {
		return this.startDate;
	}
	
	private String getEndDate() {
		return this.endDate;
	}
	
	private String getDescription() {
		return this.descriptionText;
	}
	
	private String getComment() {
		return this.commentText;
	}

}