package yapatron;

import yapatron.commands.Commands;
import yapatron.save.Save;
import yapatron.task.TaskList;
import yapatron.ui.Ui;

public class Yapatron {
    private TaskList tasks;
    private final Save save;
    private final Ui ui;

    public Yapatron(String path) {
        this.ui = new Ui();
        this.save = new Save(path);
        try {
            this.tasks = new TaskList(save.getTasks());
        } catch (YapException e) {
            ui.printLoadError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.printWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String cmd = ui.readCommand();
                isExit = Commands.doCommands(cmd, tasks, ui, save);
            } catch (YapException e) {
                ui.printError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        try {
            new Yapatron("./data/yapatron.txt").run();
        } catch (Exception e) {
            System.out.println("Uh oh! Something went wrong :( " + e.getMessage());
        }
    } 
}
