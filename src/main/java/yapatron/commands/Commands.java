package yapatron.commands;

import yapatron.YapException;
import yapatron.save.Save;
import yapatron.task.TaskList;
import yapatron.task.Task;
import yapatron.task.Todo;
import yapatron.task.Deadline;
import yapatron.task.Event;
import yapatron.ui.Ui;

import java.util.List;
import java.util.ArrayList;

public class Commands {
  public static boolean doCommands(String cmd, TaskList tasks, Ui ui, Save save) throws YapException {
    String[] parts = cmd.split(" ", 2);
    String fn  = parts[0];
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
    // adding to list and incre count

    //create new task with scanner
    Task t = null;
    if (fn.equals("todo")) {
      if (parts.length < 2 || parts[1].isEmpty()) {
        throw new YapException("TODO is missing a description!");
      }

      t = new Todo(parts[1]);

    } else if (fn.equals("deadline")) {
      if (parts.length < 2 || parts[1].isEmpty()) {
        throw new YapException("DEADLINE is missing a description!");
      }

      String[] deadlineParts = parts[1].split(" /by "); // split into desc and date

      if (deadlineParts.length < 2 || deadlineParts[0].isEmpty() || deadlineParts[1].isEmpty()) {
        throw new YapException("DEADLINE is missing details!! The correct format is deadline <desc> /by <time>");
      }

      t = new Deadline(deadlineParts[0], deadlineParts[1]);

    } else if (fn.equals("event")) {
      if (parts.length < 2 || parts[1].isEmpty()) {
        throw new YapException("EVENT is missing a description!");
      }
      String[] eventParts = parts[1].split(" /from "); // split into desc and times
      if (eventParts.length < 2 || eventParts[1].isEmpty()) {
        throw new YapException("EVENT is missing a description or timings!");
      }
      String[] times = eventParts[1].split(" /to ");
      if (times.length < 2 || times[0].isEmpty() || times[1].isEmpty()) {
        throw new YapException("Event times are missing!! Please include a '/from <time> and '/to <time>");
      }
      t = new Event(eventParts[0], times[0], times[1]);
    } else {
      throw new YapException("Sorry!!! I don't know how to do that!");
    }

    if (t != null) {
      tasks.addTask(t);
      save.saveTasks(tasks.getTasks());
      ui.printTaskLine(t, tasks.size() - 1);
      ui.printLine();
    }
    return false;
  }
  }

  private static void handleFind(String word, TaskList tasks, Ui ui) throws YapException {
      if (word.isEmpty()) {
          throw new YapException("Please share a keyword!");
      }
      List<Task> matchingTasks = tasks.find(word);
      ui.printMatchingTasks(matchingTasks);
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
}





