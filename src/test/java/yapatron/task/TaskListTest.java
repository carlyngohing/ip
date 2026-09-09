package yapatron.task;

import yapatron.YapException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TaskListTest {
  // add

  @Test
  public void add_oneTask_incrementsCorrectly() {
    TaskList tasks = new TaskList();
    Task t = new Todo("t1");

    tasks.addTask(t);

    assertEquals(1, tasks.size());
    assertEquals(t, tasks.getTasks().get(0));
  }

  @Test
  public void add_multipleTasks_correctOrder() {
    TaskList tasks = new TaskList();
    Task t1 = new Todo("t1");
    Task t2 = new Todo("t2");
    tasks.addTask(t1);
    tasks.addTask(t2);
    assertEquals(2, tasks.size());
    assertEquals(t1, tasks.getTasks().get(0));
    assertEquals(t2, tasks.getTasks().get(1));
  }

  @Test
  public void unmark_correctly() throws YapException {
    TaskList tasks = new TaskList();
    Task t = new Todo("t1");
    t.markAsDone();
    tasks.addTask(t);
    Task unmarked = tasks.unmark(0);
    assertFalse(unmarked.isDone());
  }

  @Test
  public void unmark_invalidIndex_exceptionThrown() {
    TaskList tasks = new TaskList();
    tasks.addTask(new Todo("t1"));
    assertThrows(YapException.class, () -> tasks.unmark(1));
    assertThrows(YapException.class, () -> tasks.unmark(-1));
  }

  @Test
  public void find_partialKeywordsInAnyOrder_returnsMatchingTasks() {
    TaskList tasks = new TaskList();
    Task matchingTask = new Todo("Complete CS2103T tutorial");
    tasks.addTask(matchingTask);
    tasks.addTask(new Todo("Read the project textbook"));

    List<Task> matchingTasks = tasks.find("TUTORIAL 2103");

    assertEquals(1, matchingTasks.size());
    assertEquals(matchingTask, matchingTasks.get(0));
    assertEquals(2, tasks.size());
  }

  @Test
  public void findKeywordMissingFromTask_returnsNoMatches() {
    TaskList tasks = new TaskList();
    tasks.addTask(new Todo("Complete CS2103T tutorial"));

    List<Task> matchingTasks = tasks.find("2103 meeting");

    assertTrue(matchingTasks.isEmpty());
  }

}

