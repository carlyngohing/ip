package yapatron.task;

public class Task {
    protected String desc;
    protected boolean isDone;

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }


    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
        // mark compl task with X
    }

    public String getDesc() {
        return this.desc;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
        public String toString() {
            return "[" + this.getStatusIcon() + "] " + this.desc;
        }

    public String toFileFormat() {
        return (isDone ? "X" : "0" ) + " | " + desc;
    }

}


