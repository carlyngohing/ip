import java.util.Scanner;
public class Duke {
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
    Scanner scanner = new Scanner(System.in);

    int count = 0;
    while (scanner.hasNextLine()) {
        String ans = scanner.nextLine();
      if (ans.equals("bye") || ans.equals("BYE") || ans.equals("Bye")) {
        System.out.println("Bye!! Hope to see you again soon :)");
        break;
      } else {
        count += 1;
          if (count >= 5 && count < 10) {
            System.out.println(ans);
            System.out.println("Wow!!! You have a lot to say!! Anything else??");
          } else if (count >= 10 && count < 20) {
            System.out.println(ans);
            System.out.println("R u done.");
          } else if (count >= 20) {
            System.out.println(ans);
            System.out.println("LEAVE ME ALONEEEE");
          } else {
            System.out.println(ans);
            System.out.println("What's next?");
          } 
      }
    }
    scanner.close();

  }
}
