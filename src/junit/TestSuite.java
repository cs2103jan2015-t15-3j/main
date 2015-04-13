package junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//@author A0112643R

@RunWith(Suite.class)
@Suite.SuiteClasses({ MainUnitTest.class, MainUnitUndoTest.class,
		LogicUndoUnitTest.class, LogicUnitTest.class,
		ParserTestAddDateTime.class, ParserTestDelete.class,
		ParserTestEdit.class, ParserTestSearch.class, StorageTest.class })
public class TestSuite {

}
