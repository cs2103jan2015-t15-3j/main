package junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//@author A0112643R

@RunWith(Suite.class)
@Suite.SuiteClasses({ MainUnitTest.class, LogicUndoUnitTest.class,
		LogicUnitTest.class, ParserTestDateTime.class })
public class TestSuite {
}
