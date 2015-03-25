package junit;

import static org.junit.Assert.*;
import logic.LogicMain;
import logic.Repository;

import org.junit.Test;

public class AnotherTest {
	Repository mem = new Repository();
	@Test
	public void test() {
		
		String test5 = "add hello2 12/12/11 13:59 <hello>";
		LogicMain.executeCommand(test5, mem);
		System.out.println(mem.getBufferSize());
		System.out.println(mem.getTempBufferSize());
		System.out.println(mem.getFeedback());
		
		System.out.print(mem.getBuffer());
		
		String test6 = "add hello3 13/12/11 15/12/11";
		LogicMain.executeCommand(test6, mem);
		System.out.println(mem.getBufferSize());
		System.out.println(mem.getTempBufferSize());
		System.out.println(mem.getFeedback());
		
		System.out.print(mem.getBuffer());
		
	}

}
