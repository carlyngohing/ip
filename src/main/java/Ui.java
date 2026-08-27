import java.util.Scanner;

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

  public void printError(String msg) {
    System.out.println(msg);
    System.out.println(LINE);
  }

  public void printLoadError() {
    System.out.println("Whoops!! I couldn't save that file :(");
    System.out.println(LINE);
  }
}



