package yapatron.ui;
import yapatron.task.Task;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Ui {

  public static final String LINE = "____________________________________________________________";
  private final Scanner scanner;


  public Ui() {
    this.scanner = new Scanner(System.in);
  }

  public void printWelcome() {
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
  }

  public String readCommand() {
    return scanner.nextLine();
  }

  public void printLine() {
    System.out.println(LINE);
  }

  public void printBye() {
    System.out.println("Bye!! Hope to see you again soon :)");
  }

  public void printTaskLine(Task t, int count) {
    System.out.println("added: " + t);

    if (count >= 5 && count < 10) {
      System.out.println("Wow!!! You have a lot to add!! Anything else??");
    } else if (count >= 10 && count < 20) {
      System.out.println("R u done.");
    } else if (count >= 20) {
      System.out.println("LEAVE ME ALONEEEE");
    } else {
      System.out.println("What's next?");
    }
  }

  /** 
   * Displays tasks that correspond to a keyword
   *
   * @param matchingTasks List of matching tasks to display
   */
   public void printMatchingTasks(List<Task> matchingTasks) {
       if (matchingTasks.isEmpty()) {
           System.out.println("Oops!! There are no matching tasks in your list :(");
           return;
       }
       System.out.println("Here are the tasks I found!!!");
       for (int i = 0; i < matchingTasks.size(); i++) {
           System.out.println( (i + 1) + ". " + matchingTasks.get(i));
       }
   }

  public void printError(String msg) {
    System.out.println(msg);
    System.out.println(LINE);
  }

  public void printLoadError() {
    System.out.println("Whoops!! I couldn't save that file :(");
    System.out.println(LINE);
  }
}



