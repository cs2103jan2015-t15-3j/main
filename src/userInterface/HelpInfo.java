package userInterface;

//@author A0112961L

public class HelpInfo {
	private static final String STRING_WRAPPER = "%s%s%s";
	private static final String HTML_BREAK = "<br>";
	private static final String HTML_OPEN = "<html>";
	private static final String HTML_CLOSE = "</html>";
	private static final String HTML_FONT_SIZE = "<font size=+1>";
	private static final String HTML_RED_COLOR = "<font color=red>";
	private static final String HTML_MAROON_COLOR = "<font color=#800000>";
	private static final String HTML_PURPLE_COLOR = "<font color=#5d2e8a>";
	private static final String HTML_FONT_CLOSE = "</font>";
	private static final String HTML_UNDERLINE_OPEN = "<u>";
	private static final String HTML_UNDERLINE_CLOSE = "</u>";
	private static final String HTML_BOLD_OPEN = "<b>";
	private static final String HTML_BOLD_CLOSE = "</b>";
	private static final String HTML_ITALIC_OPEN = "<i>";
	private static final String HTML_ITALIC_CLOSE = "</i>";

	protected static String addCommandGuide() {

		StringBuilder str = new StringBuilder();

		str.append(createCommandTitle("Add"));
		str.append(HTML_BREAK);
		str.append("The add function allows you to add a new task. ");
		str.append(HTML_BREAK);
		str.append(HTML_BREAK);
		str.append(HTML_UNDERLINE_OPEN);
		str.append(maroonFont("Format"));
		str.append(HTML_UNDERLINE_CLOSE);
		str.append(HTML_BREAK);
		str.append(maroonFont(" add "));
		str.append("task name [start date/time end date/time] ");
		str.append(maroonFont("&#9668"));
		str.append("Remarks");
		str.append(maroonFont("&#9658"));
		str.append(HTML_BREAK);
		str.append(maroonFont(" a "));
		str.append("task name [start date/time end date/time] ");
		str.append(maroonFont("&#9668"));
		str.append("Remarks");
		str.append(maroonFont("&#9658"));
		str.append(HTML_BREAK);
		str.append(HTML_BREAK);

		str.append("There are ");
		str.append(HTML_UNDERLINE_OPEN);
		str.append("three");
		str.append(HTML_UNDERLINE_CLOSE);
		str.append(" types of categories of how you can add your tasks.");
		str.append(HTML_BREAK);
		str.append(HTML_BREAK);
		str.append(maroonFont("1. You can add a task without specifying the date or time."));
		str.append(HTML_BREAK);
		str.append(createExample("'add study for upcoming exams'"));
		str.append(HTML_BREAK);

		str.append(HTML_BREAK);
		str.append(maroonFont("2. You can add a task by specifying only the end date or with time."));
		str.append(HTML_BREAK);
		str.append(createExample("'add CS2103 V0.5 [13/04/15 23:59]'"));
		str.append(HTML_BREAK);

		str.append(HTML_BREAK);
		str.append(maroonFont("3. You can add a task by specifying both start and end date with time."));
		str.append(HTML_BREAK);
		str.append(createExample("'a CS2103 revision class [11/04/15 08:00 11/04/15 15:00] "));
		str.append(maroonFont("&#9668"));
		str.append(createExample("remember print notes"));
		str.append(maroonFont("&#9658"));
		str.append(createExample("'"));
		str.append(HTML_BREAK);
		str.append(HTML_BREAK);

		str.append(redFont("*Disclaimer*"));
		str.append(HTML_BREAK);
		str.append(redFont("Remarks & time are optional."));
		str.append(HTML_BREAK);
		str.append(redFont("If you do not specify a time, the time is set to '23:59' (24-hr format) by default."));
		str.append(HTML_BREAK);

		return wrapper(str.toString());
	}

	protected static String deleteCommandGuide() {

		StringBuilder str = new StringBuilder();

		str.append(createCommandTitle("Delete"));
		str.append(HTML_BREAK);
		str.append("The delete function allows you to delete your unwanted tasks.");
		str.append(HTML_BREAK);
		str.append(HTML_BREAK);

		str.append(HTML_UNDERLINE_OPEN);
		str.append(maroonFont("Format"));
		str.append(HTML_UNDERLINE_CLOSE);
		str.append(HTML_BREAK);
		str.append(maroonFont(" delete "));
		str.append("id");
		str.append(HTML_BREAK);
		str.append(maroonFont(" d "));
		str.append("id");
		str.append(HTML_BREAK);
		str.append(HTML_BREAK);

		str.append(maroonFont("1. "
				+ bold("You can delete your task by specifying the ID.")));
		str.append(HTML_BREAK);
		str.append(createExample("'delete 11'"));
		str.append(" OR ");
		str.append(createExample("'d 1'"));
		str.append(HTML_BREAK);
		str.append(HTML_BREAK);

		// undo/redo
		str.append(createCommandTitle("Undo/Redo"));
		str.append(HTML_BREAK);
		str.append("The undo function allows you to revoke your last actions.");
		str.append(HTML_BREAK);
		str.append("The redo function allows you to revoke your previous undo actions.");
		str.append(HTML_BREAK);
		str.append(HTML_BREAK);

		str.append(HTML_UNDERLINE_OPEN);
		str.append(maroonFont("Format"));
		str.append(HTML_UNDERLINE_CLOSE);
		str.append(HTML_BREAK);
		str.append(maroonFont(" undo "));
		str.append(HTML_BREAK);
		str.append(maroonFont(" redo "));
		str.append(HTML_BREAK);
		str.append(HTML_BREAK);

		str.append(createExample("'undo'"));
		str.append(" OR ");
		str.append(createExample("'redo'"));
		str.append(HTML_BREAK);
		str.append(HTML_BREAK);
		str.append(redFont("*Disclaimer*"));
		str.append(HTML_BREAK);
		str.append(redFont("You can only redo when you had undo."));
		str.append(HTML_BREAK);

		return wrapper(str.toString());
	}

	protected static String editCommandGuide() {

		StringBuilder str = new StringBuilder();

		str.append(createCommandTitle("Edit"));
		str.append(HTML_BREAK);
		str.append("The edit function allows your to amend your tasks.");
		str.append(HTML_BREAK);
		str.append(HTML_BREAK);

		str.append(HTML_UNDERLINE_OPEN);
		str.append(maroonFont("Format"));
		str.append(HTML_UNDERLINE_CLOSE);
		str.append(HTML_BREAK);
		str.append(maroonFont(" edit "));
		str.append("id");
		str.append(HTML_BREAK);
		str.append(maroonFont(" e "));
		str.append("id");
		str.append(HTML_BREAK);
		str.append(HTML_BREAK);

		str.append(HTML_BREAK);
		str.append(maroonFont("1. "
				+ bold("You can edit your task name by specifying the ID.")));
		str.append(HTML_BREAK);
		str.append(createExample("'edit 22 dinner with family next friday evening"));
		str.append(HTML_BREAK);
		str.append(HTML_BREAK);
		str.append(maroonFont("2. "
				+ bold("You can edit your start and end date by specifying the ID.")));
		str.append(HTML_BREAK);
		str.append(createExample("'edit 11 17/04/15 19:00 17/04/15 22:00'"));
		str.append(HTML_BREAK);
		str.append(HTML_BREAK);
		str.append(maroonFont("3. "
				+ bold("You can edit your task name and dates by specifying the ID.")));
		str.append(HTML_BREAK);
		str.append(createExample("'e 12 additional revision class this week 21/04/15'"));
		str.append(HTML_BREAK);
		str.append(HTML_BREAK);

		//display
		str.append(createCommandTitle("Display"));
		str.append(HTML_BREAK);
		str.append("The display function allows you to display all your tasks.");
		str.append(HTML_BREAK);
		str.append(HTML_BREAK);

		str.append(HTML_UNDERLINE_OPEN);
		str.append(maroonFont("Format"));
		str.append(HTML_UNDERLINE_CLOSE);
		str.append(HTML_BREAK);
		str.append(maroonFont(" display "));
		str.append(HTML_BREAK);
		str.append(maroonFont(" dp "));
		str.append(HTML_BREAK);
		str.append(HTML_BREAK);

		str.append(createExample("'display'"));
		str.append(" OR ");
		str.append(createExample("'dp'"));
		str.append(HTML_BREAK);

		return wrapper(str.toString());
	}

	protected static String completeAndUncompleteCommandGuide() {

		StringBuilder str = new StringBuilder();
		str.append(createCommandTitle("Complete"));
		str.append(HTML_BREAK);
		str.append("The complete function allows you to mark your tasks as completed.");
		str.append(HTML_BREAK);
		str.append(HTML_BREAK);

		str.append(HTML_UNDERLINE_OPEN);
		str.append(maroonFont("Format"));
		str.append(HTML_UNDERLINE_CLOSE);
		str.append(HTML_BREAK);
		str.append(maroonFont(" complete "));
		str.append("id");
		str.append(HTML_BREAK);
		str.append(maroonFont(" cp "));
		str.append("id");
		str.append(HTML_BREAK);
		str.append(HTML_BREAK);

		str.append(maroonFont("1. "
				+ bold("You can mark by specifying the ID to complete your task.")));
		str.append(HTML_BREAK);
		str.append(createExample("'complete 9'"));
		str.append(" OR ");
		str.append(createExample("'cp 18'"));
		str.append(HTML_BREAK);

		str.append(HTML_BREAK);
		str.append(createCommandTitle("Uncomplete"));
		str.append(HTML_BREAK);
		str.append("The uncomplete function allows you to mark your tasks as uncompleted.");
		str.append(HTML_BREAK);
		str.append(HTML_BREAK);

		str.append(HTML_UNDERLINE_OPEN);
		str.append(maroonFont("Format"));
		str.append(HTML_UNDERLINE_CLOSE);
		str.append(HTML_BREAK);
		str.append(maroonFont(" uncomplete "));
		str.append("id");
		str.append(HTML_BREAK);
		str.append(maroonFont(" ucp "));
		str.append("id");
		str.append(HTML_BREAK);
		str.append(HTML_BREAK);

		str.append(maroonFont("1. "
				+ bold("You can mark by specifying the ID to uncomplete your task.")));
		str.append(HTML_BREAK);
		str.append(createExample("'uncomplete 9'"));
		str.append(" OR ");
		str.append(createExample("'ucp 18'"));
		str.append(HTML_BREAK);

		return wrapper(str.toString());
	}

	protected static String searchCommandGuide() {

		StringBuilder str = new StringBuilder();

		str.append(createCommandTitle("Search"));
		str.append(HTML_BREAK);
		str.append("The search function allows you to search for your tasks.");
		str.append(HTML_BREAK);
		str.append(HTML_BREAK);

		str.append(HTML_UNDERLINE_OPEN);
		str.append(maroonFont("Format"));
		str.append(HTML_UNDERLINE_CLOSE);
		str.append(HTML_BREAK);
		str.append(maroonFont(" search "));
		str.append("id OR keywords");
		str.append(HTML_BREAK);
		str.append(maroonFont(" find "));
		str.append("id OR keywords ");
		str.append(HTML_BREAK);
		str.append(HTML_BREAK);

		str.append(maroonFont("1. "
				+ bold("You can search a specific task by specifying the ID.")));
		str.append(HTML_BREAK);
		str.append(createExample("'search 2' OR 'find 2'"));
		str.append(HTML_BREAK);
		str.append(HTML_BREAK);
		str.append(maroonFont("2. "
				+ bold("You can search a specific task by providing keywords.")));
		str.append(HTML_BREAK);
		str.append("Let's say we have a task name called ");
		str.append(HTML_UNDERLINE_OPEN);
		str.append("PEAR");
		str.append(HTML_UNDERLINE_CLOSE);
		str.append(HTML_BREAK);
		str.append("Both outcomes will return the desired results.");
		str.append(HTML_BREAK);
		str.append(HTML_BREAK);
		str.append(createExample("'search pear' "));
		str.append("OR");
		str.append(createExample(" 'find pear'"));
		str.append(HTML_BREAK);
		str.append(createExample("'search p' "));
		str.append("OR");
		str.append(createExample(" 'find p'"));

		return wrapper(str.toString());
	}

	protected static String psearchCommandGuide() {

		StringBuilder str = new StringBuilder();

		str.append(createCommandTitle("Power Search"));
		str.append(HTML_BREAK);
		str.append("The power search function allows you to search for your tasks instantly based on the ");
		str.append(HTML_BREAK);
		str.append("keywords that you provide");
		str.append(HTML_BREAK);
		str.append("*Other attributes will be filter as well (e.g. start time, end time, remarks)*");
		str.append(HTML_BREAK);
		str.append(HTML_BREAK);

		str.append(HTML_UNDERLINE_OPEN);
		str.append(maroonFont("Format"));
		str.append(HTML_UNDERLINE_CLOSE);
		str.append(HTML_BREAK);
		str.append(maroonFont(" ps "));
		str.append("keywords");
		str.append(HTML_BREAK);
		str.append(HTML_BREAK);
		str.append(maroonFont("1. "
				+ bold("You can power search a specific task/tasks by specifying keywords.")));
		str.append(HTML_BREAK);
		str.append(createExample("ps a"));
		str.append(HTML_BREAK);
		str.append("The following result will reflect instantly");
		str.append(HTML_BREAK);
		str.append(createExample("apple 13/04/15 23:59"));
		str.append(HTML_BREAK);
		str.append(createExample("meet friends 15/05/15 15:00 accompany them to eat"));
		str.append(HTML_BREAK);
		str.append(HTML_BREAK);
		str.append("OR");
		str.append(HTML_BREAK);
		str.append(HTML_BREAK);
		str.append(createExample("ps app"));
		str.append(HTML_BREAK);
		str.append("The following result will instantly reflect");
		str.append(HTML_BREAK);
		str.append(createExample("apple 13/04/15 23:59"));
		str.append(HTML_BREAK);
		str.append(createExample("application for iphone to be launched 11/05/15 08:00 "));

		return wrapper(str.toString());
	}

	private static String createCommandTitle(String str) {
		str = bold(str);
		str = underline(str);
		str = titleFontSize(str);
		str = italic(str);
		str = maroonFont(str);

		return str;
	}

	private static String createExample(String str) {
		str = purpleFont(str);
		str = italic(str);

		return str;
	}

	private static String wrapper(String str) {
		return String.format(STRING_WRAPPER, HTML_OPEN, str, HTML_CLOSE);
	}

	private static String underline(String str) {
		return String.format(STRING_WRAPPER, HTML_UNDERLINE_OPEN, str,
				HTML_UNDERLINE_CLOSE);
	}

	private static String bold(String str) {
		return String.format(STRING_WRAPPER, HTML_BOLD_OPEN, str,
				HTML_BOLD_CLOSE);
	}

	private static String italic(String str) {
		return String.format(STRING_WRAPPER, HTML_ITALIC_OPEN, str,
				HTML_ITALIC_CLOSE);
	}

	private static String redFont(String str) {
		return String.format(STRING_WRAPPER, HTML_RED_COLOR, str,
				HTML_FONT_CLOSE);
	}

	private static String maroonFont(String str) {
		return String.format(STRING_WRAPPER, HTML_MAROON_COLOR, str,
				HTML_FONT_CLOSE);
	}

	private static String purpleFont(String str) {
		return String.format(STRING_WRAPPER, HTML_PURPLE_COLOR, str,
				HTML_FONT_CLOSE);
	}

	private static String titleFontSize(String str) {
		return String.format(STRING_WRAPPER, HTML_FONT_SIZE, str,
				HTML_FONT_CLOSE);
	}
}
