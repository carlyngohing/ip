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
    int count = 0;
    List<Task> list = new ArrayList<>();

    while (scanner.hasNextLine()) {
      String ans = scanner.nextLine();
      String[] parts = ans.split(" ", 2); // split into cmd and details
      String fn = parts[0];

      // saying bye
      if (fn.equals("bye") || fn.equals("BYE") || fn.equals("Bye")) {
        System.out.println("Bye!! Hope to see you again soon :)");
        break;
        // asking for list
      } else if (fn.equals("list")) {
        System.out.println();
        System.out.println(LINE);
        System.out.println("Here's your current list!");
        System.out.println();
        for (int i = 0; i < count; i++) {
          System.out.println((i + 1) + ". " + list.get(i));
        }
        System.out.println();
        System.out.println("Missing anything?");
        System.out.println(LINE);

      } else if (fn.equals("mark")) {
        // mark as done
        int idx = Integer.parseInt(parts[1]) - 1;

        if (list.get(idx).isDone()) {
          // check if task is already done
          System.out.println("Don't worry!! You've already done this!");
          System.out.println("  " + list.get(idx));
          System.out.println();
          System.out.println(LINE);

        } else { 
          // if task is indeed not done
          list.get(idx).markAsDone();
          System.out.println("Good job! That's one thing down!!");
          System.out.println("  " + list.get(idx));
          System.out.println();
          System.out.println(LINE);
        }

      } else if (fn.equals("unmark")) {
        // unmark to not Done

        int idx = Integer.parseInt(parts[1]) - 1;
        if (!list.get(idx).isDone()) {
          // if already unmarked 
          System.out.println("This task is already unmarked!");
          System.out.println("  " + list.get(idx));
          System.out.println();
          System.out.println(LINE);
        } else {
          list.get(idx).unmark();
          System.out.println("Alright! I've unmarked this task for you :)");
          System.out.println("  " + list.get(idx));
          System.out.println();
          System.out.println(LINE);
        }

      } else {
        // adding to list and incre count

        //create new task with scanner
        Task t = null;
        if (fn.equals("todo")) {
          t = new Todo(parts[1]);
        } else if (fn.equals("deadline")) {
          String[] deadlineParts = parts[1].split(" /by "); // split into desc and date
          t = new Deadline(deadlineParts[0], deadlineParts[1]);
        } else if (fn.equals("event")) {
          String[] eventParts = parts[1].split(" /from "); // split into desc and times
          String[] times = eventParts[1].split(" /to ");
          t = new Event(eventParts[0], times[0], times[1]);
        } 

        if (t != null) {
          count += 1;

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
    scanner.close();

  }
}
