package yapatron;

import yapatron.commands.Commands;
import yapatron.save.Save;
import yapatron.task.TaskList;
import yapatron.ui.Ui;

/**
 * Main program for the Yapatron chatbot
 * Initialises Ui, task list, storage and command execution
 */ 

public class Yapatron {

    /** Collection of tasks being accounted for */
    private TaskList tasks;

    /** Component to load and save data into local storage */
    private final Save save;

    /** Component for handing user interactions */
    private final Ui ui;


    /** 
     * Creates a Yapatron instance with the specified file path
     * Initialises Ui and storage
     * Attempts to load existing tasks from the file
     *
     * @param filePath Path to the file where task data is stored
     */

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

    /** 
     * Starts main program, reads user commands and perfoms actions until exit
     */

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

    /**
     * Starts the Yapatron app
     *
     * @param args Command Line arguments
     */

    public static void main(String[] args) {
        try {
            new Yapatron("./data/yapatron.txt").run();
        } catch (Exception e) {
            System.out.println("Uh oh! Something went wrong :( " + e.getMessage());
        }
    }
}
