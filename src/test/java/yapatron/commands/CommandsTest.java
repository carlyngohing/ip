package yapatron.commands;
import org.junit.jupiter.api.Test;
import yapatron.YapException;
import yapatron.save.Save;
import yapatron.task.TaskList;
import yapatron.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandsTest {
  @Test
  public void doCommands_bye_returnsTrue() throws YapException {
    TaskList tasks = new TaskList();
    Ui ui = new Ui();
    Save save = new Save("./data/test.txt");
    boolean isExit = Commands.doCommands("bye", tasks, ui, save);

    assertTrue(isExit);
  }

  @Test 
  public void doCommands_emptyTodoDesc_exceptionThrown() {
    TaskList tasks = new TaskList();
    Ui ui = new Ui();
    Save save = new Save("./data/test.txt");
    assertThrows(YapException.class, () -> Commands.doCommands("todo", tasks, ui, save));
  }
}

