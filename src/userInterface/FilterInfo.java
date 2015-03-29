package userInterface;

import java.util.ArrayList;

public class FilterInfo {

	protected static int id;
	protected static String name, start, end, remarks, type;
	protected static Boolean completed;
	protected ArrayList<FilterInfo> list;

	public FilterInfo() {

		id = 0;
		name = "";
		start = "";
		end = "";
		remarks = "";
		type = "";
		completed = false;

		list = new ArrayList<FilterInfo>();

	}

	public FilterInfo(int id, String name, String start, String end,
			String remarks, boolean isCompleted, String type) {

		FilterInfo.id = id;
		FilterInfo.name = name;
		FilterInfo.start = start;
		FilterInfo.end = end;
		FilterInfo.remarks = remarks;
		FilterInfo.type = type;
	}

	public static int getId() {
		return FilterInfo.id;
	}

	public static void setId(int id) {
		FilterInfo.id = id;
	}

	public static String getName() {
		return FilterInfo.name;
	}

	public static void setName(String name) {
		FilterInfo.name = name;
	}

	public static String getStart() {
		return FilterInfo.start;
	}

	public void setStart(String start) {
		FilterInfo.start = start;
	}

	public static String getEnd() {
		return FilterInfo.end;
	}

	public static void setEnd(String end) {
		FilterInfo.end = end;
	}

	public static String getRemarks() {
		return FilterInfo.remarks;
	}

	public static void setRemarks(String remarks) {
		FilterInfo.remarks = remarks;
	}

	public static String getType() {
		return FilterInfo.type;
	}

	public static void setType(String type) {
		FilterInfo.type = type;
	}

	public static Boolean getCompleted() {

		return FilterInfo.completed;
	}

	public void setCompleted(Boolean completed) {
		FilterInfo.completed = completed;
	}

	private static String intToString(int id) {
		StringBuilder sb = new StringBuilder();
		sb.append(id);

		return sb.toString();
	}

	private static String convertToAbbreviation(String type) {
		String returnType = "NIL";

		if (type.equals("FLOATING")) {
			returnType = "FL";
		} else if (type.equals("APPOINTMENT")) {
			returnType = "AP";
		} else if (type.equals("DEADLINE")) {
			returnType = "DL";
		}
		return returnType;
	}

}
