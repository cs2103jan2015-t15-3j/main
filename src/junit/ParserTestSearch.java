package junit;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import logic.Enumerator.TaskType;

import org.junit.Test;

import parser.Interpreter.CommandType;
import parser.ProParser;
import parser.Interpreter;

import org.junit.Test;

//@author: A0110818M
public class ParserTestSearch {
	
	ProParser Pro = new ProParser();
	Interpreter item, item2; 
	
	@Test
	public void test() throws ParseException {
		
		String input0 = "add checking no date no time format <remarks here>";
		item = Pro.parse(input0);
		assertEquals("Command: ", CommandType.ADD, item.getCommand());
		assertEquals("TaskType: ", TaskType.FLOATING, item.getType());
		assertEquals("StartDateString: ", "null", item.getStartDateString());
		assertEquals("DueDateString: ", "null", item.getDueDateString());
		assertEquals("TaskName: ", "checking no date no time format", item.getTaskName());
		assertEquals("Remarks: ", "remarks here", item.getRemarks());
		
		String input1 = "search check";
		item = Pro.parse(input1);
		assertEquals("Command: ", CommandType.SEARCH, item.getCommand());
		assertEquals("Key words for Search: ", "check", item.getKey());
		
		String input2 = "search ";
		item2 = Pro.parse(input2);
		boolean result2 = false;
		if(item2.getIsError()) {
			result2 = true;
		}
		assertEquals("Invalid Input", true, result2);
		System.out.println("Exception thrown due to invalid input");
		
		
	}

}
