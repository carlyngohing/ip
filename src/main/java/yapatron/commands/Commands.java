package yapatron.commands;

import java.util.List;

import yapatron.YapException;
import yapatron.save.Save;
import yapatron.task.Deadline;
import yapatron.task.Event;
import yapatron.task.Task;
import yapatron.task.TaskList;
import yapatron.task.Todo;
import yapatron.ui.Ui;

/**
 * Handles command execution in the Yapatron chatbot
 */
public class Commands {

    /**
     * Handles command execution by parsing through command strings and does the corresponding actions
     *
     * @param cmd Full command string
     * @param tasks TaskList instance with task data
     * @param ui Current ui instance
     * @param save Current Save instance 
     * @return True if command signals a program exit, False otherwise
     * @throws YapException if commands or the command formats are invalid
     */
    public static boolean doCommands(String cmd, TaskList tasks, Ui ui, Save save) throws YapException {
        String[] parts = cmd.split(" ", 2);
        String fn = parts[0];
        // use t/f to know whether to leave

        if (fn.equals("bye") || fn.equals("BYE") || fn.equals("Bye")) {
            ui.printBye();
            return true;

        } else if (fn.equals("delete")) {
            int idx = getIndex(parts);
            Task t = tasks.delete(idx);
            save.saveTasks(tasks.getTasks());
            System.out.println("Alright! I've removed this task for you :)");
            System.out.println("  " + t);
            System.out.println("You have " + tasks.size() + " tasks left!!");
            ui.printLine();
            return false;

        } else if (fn.equals("list")) {
            // asking for list
            tasks.printList();
            ui.printLine();
            return false;

        } else if (fn.equals("find")) {
            handleFind(parts[1].trim(), tasks, ui);
            return false;


        } else if (fn.equals("mark")) {
            // mark as done
            int idx = getIndex(parts);
            Task t = tasks.mark(idx);
            save.saveTasks(tasks.getTasks());
            System.out.println("Good job! That's one thing down!!");
            System.out.println("  " + t);
            System.out.println();
            ui.printLine();
            return false;


        } else if (fn.equals("unmark")) {
            int idx = getIndex(parts);
            Task t = tasks.unmark(idx);
            save.saveTasks(tasks.getTasks());
            System.out.println("Alright! I've unmarked this task for you :)");
            System.out.println("  " + t);
            System.out.println();
            ui.printLine();
            return false;




        } else {
            Task task = createTask(fn, parts);
            tasks.addTask(task);
            save.saveTasks(tasks.getTasks());
            ui.printTaskLine(task, tasks.size() - 1);
            ui.printLine();
            return false;
        }
    }

    /**
     * Creates a task from a parsed command.
     *
     * @param function command name
     * @param parts command name and its argument
     * @return newly created task
     * @throws YapException if the command or its argument is invalid
     */
    private static Task createTask(String function, String[] parts) throws YapException {
        switch (function) {
            case "todo":
                return createTodo(parts);
            case "deadline":
                return createDeadline(parts);
            case "event":
                return createEvent(parts);
            default:
                throw new YapException("Sorry!!! I don't know how to do that!");
        }
    }

    /**
     * Creates a todo task from command parts.
     *
     * @param parts command name and description
     * @return newly created todo task
     * @throws YapException if the description is missing
     */
    private static Task createTodo(String[] parts) throws YapException {
        if (parts.length < 2 || parts[1].isEmpty()) {
            throw new YapException("TODO is missing a description!");
        }
        return new Todo(parts[1]);
    }

    /**
     * Creates a deadline task from command parts.
     *
     * @param parts command name and deadline details
     * @return newly created deadline task
     * @throws YapException if the deadline details are missing or malformed
     */
    private static Task createDeadline(String[] parts) throws YapException {
        if (parts.length < 2 || parts[1].isEmpty()) {
            throw new YapException("DEADLINE is missing a description!");
        }

        String[] deadlineParts = parts[1].split(" /by ");
        if (deadlineParts.length < 2 || deadlineParts[0].isEmpty()
                || deadlineParts[1].isEmpty()) {
            throw new YapException(
                    "DEADLINE is missing details!! The correct format is deadline <desc> /by <time>");
        }

        return new Deadline(deadlineParts[0], deadlineParts[1]);
    }

    /**
     * Creates an event task from command parts.
     *
     * @param parts command name and event details
     * @return newly created event task
     * @throws YapException if the event details are missing or malformed
     */
    private static Task createEvent(String[] parts) throws YapException {
        if (parts.length < 2 || parts[1].isEmpty()) {
            throw new YapException("EVENT is missing a description!");
        }

        String[] eventParts = parts[1].split(" /from ");
        if (eventParts.length < 2 || eventParts[1].isEmpty()) {
            throw new YapException("EVENT is missing a description or timings!");
        }

        String[] times = eventParts[1].split(" /to ");
        if (times.length < 2 || times[0].isEmpty() || times[1].isEmpty()) {
            throw new YapException(
                    "Event times are missing!! Please include a '/from <time> and '/to <time>");
        }

        return new Event(eventParts[0], times[0], times[1]);
    }

    private static int getIndex(String[] parts) throws YapException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new YapException("Please specify a task number!");
        }

        try {
            return Integer.parseInt(parts[1].trim()) - 1;
        } catch (NumberFormatException e) {
            throw new YapException("Please enter a valid integer!");
        }
    }

    private static void handleFind(String word, TaskList tasks, Ui ui) throws YapException {
        if (word.isEmpty()) {
            throw new YapException("Please share a keyword!");
        }
        List<Task> matchingTasks = tasks.find(word);
        ui.printMatchingTasks(matchingTasks);
    }

}






