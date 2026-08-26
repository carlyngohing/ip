import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class Save {
  private final String path;

  public Save(String path) {
    this.path = path;
  }

  public List<Task> getTasks() {
    // get tasks from the filepath
    List<Task> tasks = new ArrayList<>();
    File f = new File(this.path);

    if (!f.exists()) {
      return tasks;
    }

    try {
      Scanner s = new Scanner(f);
      while (s.hasNext()) {
        String line = s.nextLine();
        Task t = parseTask(line);
        if (t != null) {
          tasks.add(t);
        }
      }
    } catch (FileNotFoundException e) {
      System.out.println("Whoops! Couldn't find this file: " + e.getMessage());
    } catch (Exception e) {
      System.out.println("There seems to be problem: " + e.getMessage());
    }

    return tasks;
  }

  public void saveTasks(List<Task> tasks) {

    // saves list of tasks to the file
    try {
      File f = new File(this.path);
      if (f.getParentFile() != null && !f.getParentFile().exists()) {
        f.getParentFile().mkdirs();
      }

      FileWriter fw = new FileWriter(this.path);
      for (Task t: tasks) {
        fw.write(t.toFileFormat() + System.lineSeparator());
      }
      fw.close();
    } catch (IOException e) {
      System.out.println("Whoops! Something went wrong: " + e.getMessage());
      }
  }

  private Task parseTask(String line) {
    String[] parts = line.split(" \\| ");
    if (parts.length < 3) {
      return null;
    }

    String taskType = parts[0];
    boolean isDone = parts[1].equals("X");
    String desc = parts[2];
    Task t = null;

    switch (taskType) {
      case "[TASK]" :
          t = new Todo(desc);
          break;
      case "[DEADLINE]" :
          if (parts.length < 4) {
            return null;
          }
          t = new Deadline(desc, parts[3]);
          break;
      case "[EVENT]" :
          if (parts.length < 5) {
            return null;
          }
          t = new Event(desc, parts[3], parts[4]);
          break;
      default:
          return null;
      }

      if (isDone) {
        t.markAsDone();
      }

      return t;

  }
  }










