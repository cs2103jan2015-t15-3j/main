package logic;

public class Message {

	public static final String WELCOME = "Welcome to ProTask!";
	protected static final String CLEAR = "";
	protected static final String HELP_GUIDE = "For help, enter:'help' to refer to our guide!";
	protected static final String SPECIFIED_COMMAND = "Please follow the specified command.";

	protected static final String INVALID_COMMAND = "The command you entered does not exist.";
	protected static final String TASK_NOT_FOUND = " is not found.";
	
	protected static final String ADDED_SUCCESSFUL = " has been added successfully!";
	protected static final String EDITED_SUCCESSFUL = "has been edited.";
	protected static final String DELETED_SUCCESSFUL = " has been successfully deleted.";
	protected static final String DELETE_ALL_SUCCESSFUL = "All tasks have been deleted successfully!";
	protected static final String DELETE_UNSUCCESSFUL = "There is nothing to delete.";
	protected static final String UNDO_DELETE_ALL = "Your tasks are undo successfully.";
	
	protected static final String SEARCH_FOUND = " search results(s) found.";
	protected static final String SEARCH_IS_EMPTY = "There is nothing to search.";
	protected static final String SEARCH_INVALID = "Please follow the specified command to search.";

	protected static final String SORTED_SUCCESSFUL = "Your tasks are sorted alphabetically!";
	protected static final String SORT_UNSUCCESSFUL = "There is nothing to be sort!";
	// protected static final String MESSAGE_FILTER = "";

	protected static final String COMPLETE_TASK = " has been marked as completed.";
	protected static final String UNCOMPLETE_TASK = " has been marked as uncompleted.";
	protected static final String COMPLETED_TASK = " is already marked as completed. "; 
	protected static final String UNCOMPLETED_TASK = " is already marked as uncompleted.";
	protected static final String CLEAR_COMPLETE_TASK = "All completed tasks have been successfully cleared.";
	
	protected static final String REDO_ACTION = " has been redo successfully.";
	protected static final String UNDO_ACTION = " has been undo successfully.";
	protected static final String REDO_UNSUCCESSFUL = "There is nothing to redo.";
	protected static final String UNDO_UNSUCCESSFUL = "There is nothing to undo.";

	protected static final String COMPLETE_ERROR = "[COMPLETE] - IndexOutOfBoundsException";
	protected static final String UNCOMPLETE_ERROR = "[UNCOMPLETE] - IndexOutOfBoundsException";
	protected static final String FILE_INEXISTS = "File is not found.";
}
