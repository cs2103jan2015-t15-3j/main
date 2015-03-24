package junit;

import static org.junit.Assert.*;
import logic.LogicMain;
import logic.Repository;

import org.junit.Test;

public class AnotherTest {
	Repository mem = new Repository();
	@Test
	public void test() {
		
		String test = "add hello";
		LogicMain.executeCommand(test, mem);
		System.out.println(mem.getBufferSize());
		System.out.println(mem.getTempBufferSize());
		System.out.println(mem.getFeedback());
		
		String test1 = "add google";
		LogicMain.executeCommand(test1, mem);
		System.out.println(mem.getBufferSize());
		System.out.println(mem.getTempBufferSize());
		System.out.println(mem.getFeedback());
		
		String test2 = "add apple";
		LogicMain.executeCommand(test2, mem);
		System.out.println(mem.getBufferSize());
		System.out.println(mem.getTempBufferSize());
		System.out.println(mem.getFeedback());
		
		String test3 = "search 3";
		LogicMain.executeCommand(test3, mem);
		System.out.println(mem.getBufferSize());
		System.out.println(mem.getTempBufferSize());
		System.out.println(mem.getFeedback());
		
	}

}
