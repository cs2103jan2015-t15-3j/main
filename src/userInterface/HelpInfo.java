package userInterface;

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

	public static String addCommandGuide() {

		StringBuilder str = new StringBuilder();

		str.append(createMainTitle("Help Command for "));
		str.append(createCommandTitle("'Add'"));
		str.append(HTML_BREAK);
		str.append("(You can either use ");
		str.append(maroonFont("'add'"));
		str.append(" or ");
		str.append(maroonFont("'a'"));
		str.append(" to create a new task)");
		str.append(HTML_BREAK);
		str.append(HTML_BREAK);
		str.append("Format Type: ");
		str.append(maroonFont(" add/a "));
		str.append("[Description][Start][End][");
		str.append(maroonFont("<"));
		str.append("Remarks");
		str.append(maroonFont(">"));
		str.append("]");
		str.append(HTML_BREAK);

		str.append(HTML_BREAK);
		str.append(maroonFont("1. " + bold("Floating Task:")));
		str.append(HTML_BREAK);
		str.append("You can add a task without any date or time");
		str.append(HTML_BREAK);
		str.append(createExample("Example: 'add study for upcoming exams'"));
		str.append(HTML_BREAK);

		str.append(HTML_BREAK);
		str.append(maroonFont("2. " + bold("Deadline Task:")));
		str.append(HTML_BREAK);
		str.append("You can add a task with end date and time only");
		str.append(HTML_BREAK);
		str.append(createExample("Example: 'add CS2103 V0.5 13/04/15 23:59'"));
		str.append(HTML_BREAK);

		str.append(HTML_BREAK);
		str.append(maroonFont("3. " + bold("Appointment Task:")));
		str.append(HTML_BREAK);
		str.append("You can add a task with both start and end date together with time");
		str.append(HTML_BREAK);
		str.append(createExample("Example: 'a revision class 11/04/15 08:00 11/04/15 15:00'"));
		str.append(HTML_BREAK);
		str.append(HTML_BREAK);

		str.append(redFont("*NOTE*"));
		str.append(HTML_BREAK);
		str.append(redFont("Adding of remarks & time is optional."));
		str.append(HTML_BREAK);
		str.append(redFont("By default, time is set to '23:59' (24 Hours Format)"));
		str.append(HTML_BREAK);

		return wrapper(str.toString());
	}

	public static String deleteCommandGuide() {

		StringBuilder str = new StringBuilder();

		str.append(createMainTitle("Help Command for "));
		str.append(createCommandTitle("'Delete'"));
		str.append(HTML_BREAK);
		str.append("(You can either use ");
		str.append(maroonFont("'delete'"));
		str.append(" or ");
		str.append(maroonFont("'del'"));
		str.append(" to delete a task)");
		str.append(HTML_BREAK);

		str.append(HTML_BREAK);
		str.append(maroonFont("1. " + bold("Delete by ID:")));
		str.append(HTML_BREAK);
		str.append("You can delete a task by specifying the ID");
		str.append(HTML_BREAK);
		str.append(createExample("Example: 'delete 11'"));
		str.append(HTML_BREAK);
		str.append(createExample("Example: 'del 21'"));
		str.append(HTML_BREAK);

		return wrapper(str.toString());
	}

	public static String editCommandGuide() {

		StringBuilder str = new StringBuilder();

		str.append(createMainTitle("Help Command for "));
		str.append(createCommandTitle("'Edit'"));
		str.append(HTML_BREAK);
		str.append("(You can either use ");
		str.append(maroonFont("'edit'"));
		str.append(" or ");
		str.append(maroonFont("'e'"));
		str.append(" to edit a task)");
		str.append(HTML_BREAK);

		str.append(HTML_BREAK);
		str.append(maroonFont("1. " + bold("Edit by ID:")));
		str.append(HTML_BREAK);
		str.append("You can edit a task by specifying the ID, followed by the details that you wish to edit");
		str.append(HTML_BREAK);
		str.append(HTML_BREAK);
		str.append(createExample("Example: 'edit 22 dinner with family next friday evening"));
		str.append(HTML_BREAK);
		str.append(createExample("17/04/15 19:00 17/04/15 22:00'"));
		str.append(HTML_BREAK);
		str.append(HTML_BREAK);
		str.append(createExample("Example: 'e 12 additional revision class this week 21/04/15'"));
		str.append(HTML_BREAK);

		return wrapper(str.toString());
	}

	public static String completeAndUncompleteCommandGuide() {

		StringBuilder str = new StringBuilder();

		str.append(createMainTitle("Help Command for "));
		str.append(createCommandTitle("'Complete'"));
		str.append(HTML_BREAK);
		str.append("(You can either use ");
		str.append(maroonFont("'complete'"));
		str.append(" or ");
		str.append(maroonFont("'comp'"));
		str.append(" to mark a task as complete)");
		str.append(HTML_BREAK);

		str.append(HTML_BREAK);
		str.append(maroonFont("1. " + bold("Mark by ID:")));
		str.append(HTML_BREAK);
		str.append("You can mark a task as complete by specifying the ID");
		str.append(HTML_BREAK);
		str.append(createExample("Example: 'complete 9'"));
		str.append(HTML_BREAK);
		str.append(createExample("Example: 'comp 18'"));
		str.append(HTML_BREAK);

		str.append(HTML_BREAK);
		str.append(HTML_BREAK);
		str.append(createMainTitle("Help Command for "));
		str.append(createCommandTitle("'Uncomplete'"));
		str.append(HTML_BREAK);
		str.append("(You can either use ");
		str.append(maroonFont("'uncomplete'"));
		str.append(" or ");
		str.append(maroonFont("'ucomp'"));
		str.append(" to mark a task as uncomplete)");
		str.append(HTML_BREAK);

		str.append(HTML_BREAK);
		str.append(maroonFont("1. " + bold("Mark by ID:")));
		str.append(HTML_BREAK);
		str.append("You can mark a task as uncomplete by specifying the ID");
		str.append(HTML_BREAK);
		str.append(createExample("Example: 'uncomplete 9'"));
		str.append(HTML_BREAK);
		str.append(createExample("Example: 'ucomp 18'"));
		str.append(HTML_BREAK);

		return wrapper(str.toString());
	}

	public static String searchCommandGuide() {

		StringBuilder str = new StringBuilder();

		str.append(createMainTitle("Help Command for "));
		str.append(createCommandTitle("'Search'"));
		str.append(HTML_BREAK);
		str.append("(You can either use ");
		str.append(maroonFont("'search'"));
		str.append(" or ");
		str.append(maroonFont("'find'"));
		str.append(" to search for a specific task)");
		str.append(HTML_BREAK);

		str.append(HTML_BREAK);
		str.append(maroonFont("1. " + bold("Search by ID:")));
		str.append(HTML_BREAK);
		str.append("You can search for a specific task by specifying the ID");
		str.append(HTML_BREAK);
		str.append(createExample("Example: 'search 2'"));
		str.append(HTML_BREAK);
		str.append(createExample("Example: 'find 14'"));
		str.append(HTML_BREAK);

		return wrapper(str.toString());
	}

	private static String createMainTitle(String str) {
		str = bold(str);
		str = underline(str);
		str = titleFontSize(str);

		return str;
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
