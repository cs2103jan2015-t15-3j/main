package parser;

public class ProParser {
	
	Interpreter item = new Interpreter();
	
	public static Interpreter parse(String command) {
		Interpreter item = new Interpreter();
		
		/*
			This is your main parser function,
			Interpreter is your object, where you store your necessary information
			Understand different patterns, and edit your user guide while you found new easy pattern for each command
			you'll need a few classes for it
		*/
		return item;
	}
}
	/*
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
	*/