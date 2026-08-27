package yapatron.ui;
import yapatron.task.Task;
import java.util.Scanner;

/**
 * Handles user interactions such as reading inputs and printing responses
 */

public class Ui {

  public static final String LINE = "____________________________________________________________";
  private final Scanner scanner;
  
  /**
   * Creates new Ui instance with scanner
   */
  public Ui() {
    this.scanner = new Scanner(System.in);
  }

  /**
   * Displays welcome message when program starts
   */
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
    System.out.println("Bye!! Hope to see you again soon :)");
  }

  /**
   * Displays a message when task has been added
   * Additional messages are printed based on the number of tasks
   *
   * @param t Task to be added
   * @param count total number of tasks in the list
   */
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
   * Prints an error message to standard output
   *
   * @param msg Error description
   */
  public void printError(String msg) {
    System.out.println(msg);
    System.out.println(LINE);
  }
  
  /** 
   * Prints an error message when saved task couldn't be loaded from storage
   */
  public void printLoadError() {
    System.out.println("Whoops!! I couldn't save that file :(");
    System.out.println(LINE);
  }
}



