import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
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
    List<String> list = new ArrayList<>();
    while (scanner.hasNextLine()) {
      String ans = scanner.nextLine();
      // saying bye
      if (ans.equals("bye") || ans.equals("BYE") || ans.equals("Bye")) {
        System.out.println("Bye!! Hope to see you again soon :)");
        break;
        // asking for list
      } else if (ans.equals("list")) {
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

      } else {
        // adding to list and incre count
        count += 1;

        if (count >= 5 && count < 10) {
          list.add(ans);
          System.out.println("added: " + ans);
          System.out.println("Wow!!! You have a lot to add!! Anything else??");
          System.out.println(LINE);
        } else if (count >= 10 && count < 20) {
          list.add(ans);
          System.out.println("added: " + ans);
          System.out.println("R u done.");
          System.out.println(LINE);
        } else if (count >= 20) {
          list.add(ans);
          System.out.println("added: " + ans);
          System.out.println("LEAVE ME ALONEEEE");
          System.out.println(LINE);
        } else {
          list.add(ans);
          System.out.println("added: " + ans);
          System.out.println("What's next?");
          System.out.println(LINE);
        } 
      }
    }
    scanner.close();

  }
}
