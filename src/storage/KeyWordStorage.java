//@author A0111842R
package storage;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import logic.Task;

import com.opencsv.CSVParser;
import com.opencsv.CSVReader;

public class KeyWordStorage {

	// Find the index of the input (2nd letter?)
	// Then find what alphabet is on the 2nd letter? (r?)

	ArrayList<KeyAlphabet> allAlphabets;
	private boolean isColumn = true;
	private int keyWordID;
	private String taskDataBase = "test.csv";
	private final Character[] letters = { 'A', 'B', 'C', 'D', 'E', 'F', 'G',
			'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
			'U', 'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', ' ', '/', ':' };
	private ArrayList<KeyWord> allKeyWords;
	private ProTaskStorage pStorage;
	private final String weightPercentage = "0.6";

	public KeyWordStorage() {

		ProTaskStorage proT = new ProTaskStorage();
		taskDataBase = proT.getCurrentDataBasePath() + "/test.csv";
		allAlphabets = new ArrayList<KeyAlphabet>();
		keyWordID = 1;
		allKeyWords = new ArrayList<KeyWord>();
		pStorage = new ProTaskStorage();

		for (int j = 0; j < letters.length; j++) {
			KeyAlphabet alphabet = new KeyAlphabet();
			alphabet.setAlphabet(letters[j]);
			alphabet.setIndexNumber(j);
			alphabet.setPositionNumber(0);
			allAlphabets.add(alphabet);
		}

		try {
			populateDataBase();
			Collections.sort(allKeyWords, KeyWord.WordNameComparator);
			mapKeyWords();
			// System.out.println(allAlphabets);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<Task> powerSearch(String input) {

		ArrayList<KeyWord> results = new ArrayList<KeyWord>();
		ArrayList<Task> tasksResults = new ArrayList<Task>();

		if (!input.equals(" ")) {
			Queue<Character> queueInput = new LinkedList<Character>();

			for (int i = 0; i < input.length(); i++) {

				if (i != 0 && input.charAt(i) != ' ') {
					queueInput.add(input.charAt(i));
				}

			}
			results = getBestMatch(queueInput,
					allAlphabets.get(getAlphabetIndex(queueInput.peek())));

			ArrayList<KeyWord> secondResult = weightSearch(input);
			for (KeyWord result : secondResult) {
				if (!results.contains(result)) {
					results.add(result);
				}
			}
			for (KeyWord result : results) {
				Task task = pStorage.getTask(result.getTaskID());
				if (!tasksResults.contains(task)) {
					tasksResults.add(task);
				}
			}
		}
		return tasksResults;

	}

	private ArrayList<KeyWord> weightSearch(String input) {

		if (input.charAt(0) == ' ' && input.length() > 1) {
			input = input.substring(1, input.length());
		}
		ArrayList<KeyWord> results = new ArrayList<KeyWord>();
		for (KeyWord word : allKeyWords) {
			int matchCount = 0;
			String keyword = word.getWord();
			ArrayList<String> tempCharWord = new ArrayList<String>();
			for (int i = 0; i < keyword.length(); i++) {
				tempCharWord.add(keyword.toUpperCase().split("")[i]);
			}
			Collections.sort(tempCharWord);
			for (String s : input.split("")) {
				if (tempCharWord.indexOf(s.toUpperCase()) != -1) {
					tempCharWord.remove(tempCharWord.indexOf(s.toUpperCase()));
					matchCount++;
				}
			}

			int scale = 100;
			BigDecimal num1 = new BigDecimal(matchCount);
			BigDecimal num2 = new BigDecimal(word.getWord().length());
			BigDecimal referenceNum = new BigDecimal(weightPercentage);
			BigDecimal answer = num1.divide(num2, scale, RoundingMode.HALF_UP);

			if (answer.compareTo(referenceNum) >= 0) {
				System.out.println(answer.toString());
				System.out.println(word.getWord());
				results.add(word);
			}
		}
		return results;
	}

	private ArrayList<KeyWord> getBestMatch(Queue<Character> queueChar,
			KeyAlphabet alphabet) {
		ArrayList<KeyWord> results = new ArrayList<KeyWord>();
		queueChar.poll();
		Character nextChar = queueChar.peek();
		if (nextChar != null) {
			if (alphabet.getChildAlphabet(nextChar) != null) {
				results = getBestMatch(queueChar,
						alphabet.getChildAlphabet(nextChar));
			} else // if reached the end of the available keywords
			{
				ArrayList<KeyWord> wordList = alphabet.getWordList();
				results.addAll(wordList);
			}
		} else // if reached the end of input
		{
			ArrayList<KeyWord> wordList = alphabet.getWordList();
			results.addAll(wordList);
		}
		return results;
	}

	private int getAlphabetIndex(Character c) {
		int index = 0;
		for (int i = 0; i < letters.length; i++) {
			if (letters[i].equals(Character.toUpperCase(c))) {
				index = i;
				break;
			}
		}
		return index;
	}

	private void mapKeyWords() {
		for (KeyWord word : allKeyWords) {

			if (!word.getWord().isEmpty()) {
				Character[] chars = word.getSplitName().toArray(
						new Character[0]);
				int index = 0;

				if (allAlphabets.get(getAlphabetIndex(chars[index])) != null) {
					allAlphabets.get(getAlphabetIndex(chars[index])).addWord(
							word);

					if (index + 1 != chars.length) {

						mapping(word, chars[index + 1], index + 1,
								allAlphabets
										.get(getAlphabetIndex(chars[index])));

					}
				}

				else {// create one
				}

			}
		}

	}

	private void mapping(KeyWord word, Character nextChar, int index,
			KeyAlphabet rootAlphabet) {

		KeyAlphabet newAlphabet = new KeyAlphabet();
		if (rootAlphabet.checkChildExist(nextChar)) {
			newAlphabet = rootAlphabet.getChildAlphabet(nextChar);
		} else {
			newAlphabet.setAlphabet(Character.toUpperCase(nextChar));
			newAlphabet.setPositionNumber(index);
			newAlphabet.setIndexNumber(getAlphabetIndex(nextChar));
			rootAlphabet.addChildKeyAlphabet(newAlphabet);
		}
		newAlphabet.addWord(word);

		if (index + 1 != word.getWord().length()) {

			mapping(word, word.getSplitName().get(index + 1), index + 1,
					newAlphabet);
		}
	}

	private void populateDataBase() throws IOException {
		CSVReader reader = new CSVReader(new FileReader(taskDataBase),
				CSVParser.DEFAULT_SEPARATOR, CSVParser.DEFAULT_QUOTE_CHARACTER,
				'\0');
		List<String[]> allRows = reader.readAll();

		for (String[] row : allRows) {
			if (isColumn) {
				isColumn = false;
			} else {

				int taskID = Integer.parseInt(row[0]);
				String taskName = row[1];
				ArrayList<Character> chars = new ArrayList<Character>();
				for (int i = 0; i < taskName.length(); i++) {
					chars.add(Character.toUpperCase(taskName.charAt(i)));
				}
				KeyWord keyTaskName = new KeyWord();
				keyTaskName.setTaskID(taskID);
				keyTaskName.setWord(taskName);
				keyTaskName.setSplitName(chars);
				keyTaskName.setKeyWordID(keyWordID);
				keyWordID++;
				allKeyWords.add(keyTaskName);

				if (row[6] == "AP") {
					String startDate = row[2];
					if (!startDate.isEmpty()) {
						KeyWord keyStartDate = new KeyWord();
						keyStartDate.setTaskID(taskID);
						keyStartDate.setWord(startDate);
						chars = new ArrayList<Character>();
						for (int i = 0; i < taskName.length(); i++) {
							chars.add(Character.toUpperCase(startDate.charAt(i)));
						}
						keyStartDate.setSplitName(chars);
						keyStartDate.setKeyWordID(keyWordID);
						keyWordID++;
						allKeyWords.add(keyStartDate);
					}
				}
				if (row[6] == "AP" || row[6] == "DE") {
					String endDate = row[3];
					if (!endDate.isEmpty()) {
						KeyWord keyEndDate = new KeyWord();
						keyEndDate.setTaskID(taskID);
						keyEndDate.setWord(endDate);
						chars = new ArrayList<Character>();
						for (int i = 0; i < taskName.length(); i++) {
							chars.add(Character.toUpperCase(endDate.charAt(i)));
						}
						keyEndDate.setSplitName(chars);
						keyEndDate.setKeyWordID(keyWordID);
						keyWordID++;
						allKeyWords.add(keyEndDate);
					}
				}
				String remarks = row[4];
				// if remarks is not empty
				if (!remarks.equals("")) {
					String[] splitRemarks = remarks.split(" ");
					for (String remark : splitRemarks) {
						KeyWord keyRemarks = new KeyWord();
						ArrayList<Character> charsRemarks = new ArrayList<Character>();

						for (int j = 0; j < remark.length(); j++) {
							charsRemarks.add(Character.toUpperCase(remark
									.charAt(j)));
						}
						keyRemarks.setTaskID(taskID);
						keyRemarks.setWord(remark);
						keyRemarks.setSplitName(charsRemarks);
						keyRemarks.setKeyWordID(keyWordID);
						keyWordID++;
						allKeyWords.add(keyRemarks);
					}
				}

			}
		}
		reader.close();
	}// end of method
}
