package yapatron.ui;

import yapatron.task.Task;
import java.util.Scanner;
import java.util.List;

/**
 * Handles user interactions such as reading inputs and printing responses
 */
public class Ui {

    public static final String LINE = "____________________________________________________________";
    private final Scanner scanner;
    private String lastResponse = "";

    /**
     * Creates new Ui instance with scanner
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Helper method to print to stdout and cache response for GUI.
     */
    private void print(String message) {
        System.out.println(message);
        this.lastResponse = message;
    }

    /**
     * Returns the last generated response string for the GUI.
     *
     * @return String output of the last action
     */
    public String getLastResponse() {
        return lastResponse;
    }

    /**
     * Displays welcome message when program starts
     */
    public void printWelcome() {
        String banner = "██╗    ██╗ █████╗ ██████╗  █████╗ ████████╗██████╗  ██████╗ ███╗   ██╗\n" +
            "╚██╗ ██╔╝██╔══██╗██╔══██╗██╔══██╗╚══██╔══╝██╔══██╗██╔═══██╗████╗  ██║\n" +
            " \\████╔╝ ███████║██████╔╝███████║   ██║   ██████╔╝██║   ██║██╔██╗ ██║\n" +
            "  ╚██╔╝  ██╔══██║██╔═══╝ ██╔══██║   ██║   ██╔══██╗██║   ██║██║╚██╗██║\n" +
            "   ██║   ██║  ██║██║     ██║  ██║   ██║   ██║  ██║╚██████╔╝██║ ╚████║\n" +
            "   ╚═╝   ╚═╝  ╚═╝╚═╝     ╚═╝  ╚═╝   ╚═╝   ╚═╝  ╚═╝ ╚═════╝ ╚═╝  ╚═══╝";

        String welcomeMsg = banner + "\nHello Hello!  I'm Yapatron :D\nWhat can I do for you?";
        print(welcomeMsg);
    }

    /** 
     * Displays tasks that correspond to a keyword
     *
     * @param matchingTasks List of matching tasks to display
     */
    public void printMatchingTasks(List<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            print("Oops!! There are no matching tasks in your list :(");
            return;
        }
        StringBuilder sb = new StringBuilder("Here are the tasks I found!!!\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            sb.append((i + 1)).append(". ").append(matchingTasks.get(i));
            if (i < matchingTasks.size() - 1) {
                sb.append("\n");
            }
        }
        print(sb.toString());
    }

    /**
     * Gets user input from command line
     *
     * @return user input string
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints pre-set line string
     */
    public void printLine() {
        System.out.println(LINE);
    }

    /**
     * Prints message when user wants to terminate the app
     */
    public void printBye() {
        print("Bye!! Hope to see you again soon :)");
    }

    /**
     * Displays a message when task has been added
     * Additional messages are printed based on the number of tasks
     *
     * @param t Task to be added
     * @param count total number of tasks in the list
     */
    public void printTaskLine(Task t, int count) {
        String msg = "added: " + t + "\n";
        if (count >= 5 && count < 10) {
            msg += "Wow!!! You have a lot to add!! Anything else??";
        } else if (count >= 10 && count < 20) {
            msg += "R u done.";
        } else if (count >= 20) {
            msg += "LEAVE ME ALONEEEE";
        } else {
            msg += "What's next?";
        }
        print(msg);
    }

    /**
     * Prints an error message to standard output
     *
     * @param msg Error description
     */
    public void printError(String msg) {
        print(msg);
    }

    /** 
     * Prints an error message when saved task couldn't be loaded from storage
     */
    public void printLoadError() {
        print("Whoops!! I couldn't save that file :(");
    }
}
