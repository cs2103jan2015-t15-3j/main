package parser;

import java.util.*;
import java.io.*; 
import java.lang.*;

public class Boolean {
	
	private boolean isDate() {
		if(isStartDate() && isEndDate()) {
			return true;
		} else if(isStartDate() && !isEndDate()) {
			endDate = setDefaultEndDate(startDate);
			return true;
		} else {
			return false;
		}
	}

	private boolean isStartDate() {
		
		return true;
	}

	private boolean isEndDate() {
		
		return true;
	}
	
	
}
