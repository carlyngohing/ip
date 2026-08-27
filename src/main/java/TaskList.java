import java.util.ArrayList;
import java.util.List;

public class TaskList {

  private final List<Task> tasks;

  public TaskList() {
    this.tasks = new ArrayList<>();
  }

  public TaskList(List<Task> tasks) {
    this.tasks = tasks;
  }

  public List<Task> getTasks() {
    return this.tasks;
  }

  public void addTask(Task task) {
    tasks.add(task);
  }

  public int size() {
    return tasks.size();
  }

  public Task delete(int idx) throws YapException {
    if (idx < 0 || idx >= tasks.size()) {
      throw new YapException("There's no task with that number!");
    }

    return tasks.remove(idx);
  }

  public Task mark(int idx) throws YapException {
    if (idx < 0 || idx >= tasks.size()) {
      throw new YapException("There's no task with that number!");
    }

    Task t = tasks.get(idx);
    if (t.isDone()) {
      throw new YapException("Don't worry!! You've already done this!");
    }
    t.markAsDone();
    return t;
  }

  public Task unmark(int idx) throws YapException {
    if (idx < 0 || idx >= tasks.size()) {
      throw new YapException("There's no task with that number!");
    }

    Task t = tasks.get(idx);
    if (!t.isDone()) {
      throw new YapException("This task is already unmarked!");
    }
    t.unmark();
    return t;
  }

  public void printList() {
    System.out.println();
    System.out.println("Here's your current list!");
    System.out.println();
    for (int i = 0; i < tasks.size(); i++) {
      System.out.println((i + 1) + ". " + tasks.get(i));
    }
    System.out.println();
    System.out.println("Missing anything?");
  }
}




