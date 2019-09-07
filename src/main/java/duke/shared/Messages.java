package duke.shared;

/**
 * Hardcoded messages to be display in the Duke GUI.
 */
public class Messages {
    public static final String COMMAND_INDENTATION = "    ";
    public static final String COMPLETION_INDENTATION = "  ";
    public static final String BYE_MESSAGE = COMMAND_INDENTATION + "Bye. Hope to see you again soon!";
    public static final String GREETING_MESSAGE = COMMAND_INDENTATION + "Hello! I'm Duke\n"
            + COMMAND_INDENTATION + "What can I do for you?";
    public static final String ADDED_MESSAGE = COMMAND_INDENTATION + "added: ";
    public static final String LIST_MESSAGE = COMMAND_INDENTATION + "Here are the tasks in your list:";
    public static final String DONE_MESSAGE = COMMAND_INDENTATION + "Nice! I've marked this task as done:";
    public static final String ADDED_TASK_MESSAGE = COMMAND_INDENTATION + "Got it. I've added this task:";
    public static final String DELETE_TASK_MESSAGE = COMMAND_INDENTATION + "Noted. I've removed this task:";
    public static final String FIND_TASK_MESSAGE = COMMAND_INDENTATION + "Here are the matching tasks in your list:";

    public static final String LIST_SIZE_FORMAT = COMMAND_INDENTATION + "Now you have %d tasks in the list.";

    public static final String DESCRIPTION_MISSING_EXCEPTION = COMMAND_INDENTATION
            + "OOPS!!! The description of a %s cannot be empty.";
    public static final String UNKNOWN_COMMAND_EXCEPTION = COMMAND_INDENTATION
            + "OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String DESCRIPTION_FORMAT_EXCEPTION = COMMAND_INDENTATION
            + "OOPS!!! The description need to be %s.";
    public static final String SUBDESCRIPTION_MISSING_EXCEPTION = COMMAND_INDENTATION
            + "OOPS!!! The description of a %s cannot be empty.";
    public static final String NO_SUBDESCRIPTION_EXCEPTION = COMMAND_INDENTATION
            + "OOPS!!! \"%s\" cannot be found in the command";
    public static final String INVALID_SIZE_EXCEPTION = COMMAND_INDENTATION
            + "OOPS!!! Invalid task number";
    public static final String DATETIME_PARSE_EXCEPTION = COMMAND_INDENTATION
            + "The description of /by must be in the correct format (dd/MM/yyyy HHmm). E.g. 2/12/2019 1800";
}
