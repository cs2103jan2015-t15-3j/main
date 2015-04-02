package userInterface;

public class HelpInfo {
	private static final String TAG_WRAP_STRING = "%s%s%s";
	private static final String HTML_BREAK = "<br>";
	private static final String HTML_OPEN = "<html>";
	private static final String HTML_CLOSE = "</html>";
	private static final String HTML_UNDERLINE_OPEN = "<u>";
	private static final String HTML_UNDERLINE_CLOSE = "</u>";
	private static final String HTML_BOLD_OPEN = "<b>";
	private static final String HTML_BOLD_CLOSE = "</b>";
	private static final String HTML_ITALIC_OPEN = "<i>";
	private static final String HTML_ITALIC_CLOSE = "</i>";
	private static final String HTML_FONTSIZE_OPEN = "<font size=+1>";
	private static final String HTML_FONTCOLORRED_OPEN = "<font color=#C80000 >"; //red
	private static final String HTML_FONTCOLORGREEN_OPEN = "<font color=green>"; 
	private static final String HTML_FONT_CLOSE = "</font>";
	private static final String spaceOr = " | ";
	
	/**
	 * @return Advance help command for "Add" in String
	 */
	public static String addGuide() {

		StringBuilder str = new StringBuilder();

		str.append("<html>");
		str.append(HTML_BREAK);
	
		str.append("<b>" + "'Add' Command Guide" + "</b>");
		

		str.append(HTML_BREAK);
		str.append("You can add task with the 'add' or 'a':");	
		str.append(HTML_BREAK);

		str.append("You can add a task specifying the date and time duration");

		
		str.append(HTML_BREAK);
		str.append(HTML_BREAK);
		str.append("You can add a task specifying the deadline");
		str.append(HTML_BREAK);
	
		
		str.append(HTML_BREAK);

		str.append(HTML_BREAK);
		str.append("You can add a task WITHOUT any date or time");
		str.append(HTML_BREAK);

		
		str.append(HTML_BREAK + HTML_BREAK);
		
		str.append("---------------------------- END ----------------------------");
		str.append("</html>");
		
		return str.toString();
	}
}
