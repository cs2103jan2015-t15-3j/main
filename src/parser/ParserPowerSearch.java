package parser;

import java.util.ArrayList;

import storage.KeyWordStorage;
import logic.Task;

public class ParserPowerSearch {
	
	private static final String commandPS = "ps";
	private static int START_INDEX_CASE_PS = commandPS.length();
	
	
	public static ArrayList<Task> powerSearch(String input) {
		String keyWords, checkCmd;
		KeyWordStorage storage = new KeyWordStorage();
		ArrayList<Task> keyWordsList = new ArrayList<Task>();
		
		checkCmd = input.substring(0,2);
		
		switch(checkCmd) {
		case commandPS:
			keyWords = input.substring(START_INDEX_CASE_PS, input.length());
			keyWordsList =  storage.powerSearch(keyWords);
			break;
		}
		return keyWordsList;
	}
}
