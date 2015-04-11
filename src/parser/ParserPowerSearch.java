package parser;

import java.util.ArrayList;

import storage.KeyWordStorage;
import logic.Task;

public class ParserPowerSearch {
	
	private static int START_INDEX_CASE_PS = 3;
	private static int START_INDEX_CASE_PSEARCH = 8;
	
	
	public static ArrayList<Task> powerSearch(String input) {
		String keyWords, checkCmd;
		KeyWordStorage storage = new KeyWordStorage("test.csv");
		ArrayList<Task> keyWordsList = new ArrayList<Task>();
		
		checkCmd = input.substring(0,2);
		
		switch(checkCmd) {
		case "ps ":
			keyWords = input.substring(START_INDEX_CASE_PS, input.length() - 1);
			keyWordsList =  storage.powerSearch(keyWords);
		case "pse":
			keyWords = input.substring(START_INDEX_CASE_PSEARCH, input.length() - 1);
			keyWordsList =  storage.powerSearch(keyWords);
		}
		return keyWordsList;
	}
}
