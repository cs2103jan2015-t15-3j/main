package junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.text.ParseException;

import org.junit.Assert;
import org.junit.Test;

import parser.Interpreter;
import parser.Interpreter.CommandType;
import parser.ParserDelete;
import parser.ParserException;
import parser.ProParser;

//@author: A0110818M
public class ParserTestDelete {

	ProParser Pro = new ProParser();
	Interpreter item, item2, item3; 
	boolean result;
	
	@Test
	public void test() throws ParseException {
		
		String input00 = "add checking no date no time format <remarks here>";
		String input01 = "add checking no date no time format <remarks here>";
		String input02 = "add checking no date no time format <remarks here>";
		item = Pro.parse(input00);
		item = Pro.parse(input01);
		item = Pro.parse(input02);
		
		// 
		// 
		String input1 = "delete 1";
		item = Pro.parse(input1);
		assertEquals("Command: ", CommandType.DELETE, item.getCommand());
		
		
		String input2 = "d 2";
		item2 = Pro.parse(input2);
		assertEquals("Command: ", CommandType.DELETE, item.getCommand());
		
		
		String input3 = "delete";
		item2 = Pro.parse(input3);
		result = false;
		if(item2.getIsError()) {
			result = true;
		}
		assertEquals("isError is true", true, result);
		System.out.println("isError is true as input is wrong");
		
		
		String input4 = "delete 1 1";
		item2 = Pro.parse(input4);
		result = false;
		if(item2.getIsError()) {
			result = true;
		}
		assertEquals("isError is true", true, result);
		System.out.println("isError is true as input is wrong");

	}
	
	
}
