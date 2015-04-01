package storage;

import java.util.ArrayList;

public class KeyAlphabet {

	private Character alphabet;
	private int positionNumber;
	private int indexNumber;
	private ArrayList<KeyWord> wordList;
	private ArrayList<KeyAlphabet> children;

	public KeyAlphabet() {
		wordList = new ArrayList<KeyWord>();
		children = new ArrayList<KeyAlphabet>();
	}

	public Character getAlphabet() {
		return alphabet;
	}

	public void setAlphabet(Character alphabet) {
		this.alphabet = alphabet;
	}

	public int getPositionNumber() {
		return positionNumber;
	}

	public void setPositionNumber(int poositionNumber) {
		this.positionNumber = poositionNumber;
	}

	public void addWord(KeyWord word) {
		wordList.add(word);
	}
	public ArrayList<KeyWord> getWordList()
	{
		return wordList;
	}
	public boolean checkChildExist(Character c) {
		boolean exist = false;
		for (KeyAlphabet alpha : children) {
			if (alpha.getAlphabet().equals(Character.toUpperCase(c))) {
				exist = true;
				break;
			}
		}
		return exist;
	}

	public KeyAlphabet getChildAlphabet(Character c) {

		KeyAlphabet returnChild = null;
		for (KeyAlphabet child : children) {
			if (child.getAlphabet().equals(Character.toUpperCase(c))) {
				returnChild = child;
			}
		}
		return returnChild;
	}

	public boolean addChildKeyAlphabet(KeyAlphabet childAlphabet) {

		boolean successAdd = false;
		boolean exist = false;
		for (KeyAlphabet alpha : children) {
			if (alpha.getAlphabet().equals(childAlphabet.getAlphabet())) {
				exist = true;
				break;
			}
		}
		if (exist) {
			successAdd = false;
		} else {
			children.add(childAlphabet);
			successAdd = true;
		}

		return successAdd;
	}

	public int getIndexNumber() {
		return indexNumber;
	}

	public void setIndexNumber(int indexNumber) {
		this.indexNumber = indexNumber;
	}

	@Override
	public String toString() {
		return "*" + this.getIndexNumber() + " " + this.getPositionNumber()
				+ " " + this.getAlphabet() + "\n" + this.children + "*\n";
	}

}
