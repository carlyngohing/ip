import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Yapatron {
  public static final String LINE = "____________________________________________________________";
  public static void main(String[] args) {
    String banner = "██╗   ██╗ █████╗ ██████╗  █████╗ ████████╗██████╗  ██████╗ ███╗   ██╗\n" +
      "╚██╗ ██╔╝██╔══██╗██╔══██╗██╔══██╗╚══██╔══╝██╔══██╗██╔═══██╗████╗  ██║\n" +
      " \\████╔╝ ███████║██████╔╝███████║   ██║   ██████╔╝██║   ██║██╔██╗ ██║\n" +
      "  ╚██╔╝  ██╔══██║██╔═══╝ ██╔══██║   ██║   ██╔══██╗██║   ██║██║╚██╗██║\n" +
      "   ██║   ██║  ██║██║     ██║  ██║   ██║   ██║  ██║╚██████╔╝██║ ╚████║\n" +
      "   ╚═╝   ╚═╝  ╚═╝╚═╝     ╚═╝  ╚═╝   ╚═╝   ╚═╝  ╚═╝ ╚═════╝ ╚═╝  ╚═══╝";
    System.out.println(banner);
    System.out.println("Hello Hello!  I'm Yapatron :D");
    System.out.println("What can I do for you?");
    System.out.println();
    System.out.println(LINE);

    Scanner scanner = new Scanner(System.in);
    List<Task> list = new ArrayList<>();

    while (scanner.hasNextLine()) {
      String ans = scanner.nextLine();

      try {
        action(ans, list);
      } catch (YapException e) {
        System.out.println(e.getMessage());
      }

    }

    scanner.close();
  }

  private static void action(String ans, List<Task> list) throws YapException {

    String[] parts = ans.split(" ", 2); // split into cmd and details
    String fn = parts[0];

    // saying bye
    if (fn.equals("bye") || fn.equals("BYE") || fn.equals("Bye")) {
      System.out.println("Bye!! Hope to see you again soon :)");
      System.exit(0);


    } else if (fn.equals("delete")) {
      handleDelete(parts, list);

    } else if (fn.equals("list")) {
      // asking for list
      System.out.println();
      System.out.println(LINE);
      System.out.println("Here's your current list!");
      System.out.println();
      for (int i = 0; i < list.size(); i++) {
        System.out.println((i + 1) + ". " + list.get(i));
      }
      System.out.println();
      System.out.println("Missing anything?");
      System.out.println(LINE);

    } else if (fn.equals("mark")) {
      // mark as done

      handleMark(parts, list, true);


    } else if (fn.equals("unmark")) {

      handleMark(parts, list, false);


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
        int count = list.size();

        if (count >= 5 && count < 10) {
          list.add(t);
          System.out.println("added: " + t);
          System.out.println("Wow!!! You have a lot to add!! Anything else??");
        } else if (count >= 10 && count < 20) {
          list.add(t);
          System.out.println("added: " + t);
          System.out.println("R u done.");
        } else if (count >= 20) {
          list.add(t);
          System.out.println("added: " + t);
          System.out.println("LEAVE ME ALONEEEE");
        } else {
          list.add(t);
          System.out.println("added: " + t);
          System.out.println("What's next?");
        } 
      }
      System.out.println(LINE);
    }
  }

  private static void handleMark(String[] parts, List<Task> list, boolean isDone) throws YapException {
    if (parts.length < 2 || parts[1].isEmpty()) {
      throw new YapException("Please specify a task number!");
    }

    try {
      int idx = Integer.parseInt(parts[1]) - 1;
      if (idx < 0 || idx >= list.size()) {
        throw new YapException("There's no task with that number!");
      }

      Task t = list.get(idx);
      if (isDone) {

        if (t.isDone()) {
          throw new YapException("Don't worry!! You've already done this!");
        }

        t.markAsDone();
        System.out.println("Good job! That's one thing down!!");
        System.out.println("  " + t);
        System.out.println();
      } else {

        if (!t.isDone()) {
          throw new YapException("This task is already unmarked!");
        }
        t.unmark();
        System.out.println("Alright! I've unmarked this task for you :)");
        System.out.println("  " + t);
        System.out.println();
      }

    } catch (NumberFormatException e) {
      throw new YapException("Please enter a valid integer!");
    }

    System.out.println(LINE);


  }

  private static void handleDelete(String[] parts, List<Task> list) throws YapException {
    if (parts.length < 2 || parts[1].isEmpty()) {
      throw new YapException("Please specify the task number!");
    }

    try {
      int idx = Integer.parseInt(parts[1]) - 1;
      if (idx < 0 || idx >= list.size()) {
        throw new YapException("There's no task with that number!");
      }
      Task t = list.remove(idx);
      System.out.println("Alright! I've removed this task for you :)");
      System.out.println("  " + t);
      System.out.println("You have " + list.size() + " tasks left!!");
    } catch (NumberFormatException e) {
      throw new YapException("Please enter a valid integer!");
    }
  }









}








