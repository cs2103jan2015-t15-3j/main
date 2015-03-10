package parser;

import java.util.StringTokenizer;

public class Interpreter {
	
	Methods methods = new Methods();
	
	enum CommandType {
		ADD, DELETE, UPDATE, DISPLAY; 
	}
	private CommandType command;


	private static StringTokenizer inputString;
	private static String ID;
	private static String descriptionText;	
	private static String startDate;
	private static String endDate;
	private static String commentText;
	private static boolean isCompleted;
	
	private void identifyCommand(String command, String[] textContent) {
		switch (command){
		case "add":
			methods.add(textContent);
			break;
		case "update":
			methods.update(textContent);
			break;
		case "delete":
			methods.delete(textContent);
			break;
		case "display":
			methods.display();
			break;
		case "exit":
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