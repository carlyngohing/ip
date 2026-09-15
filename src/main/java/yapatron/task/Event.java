package yapatron.task;

import yapatron.YapException;

/**
 * Represents an event with a specific start and end 
 */

public class Event extends Task {
    /** String for starting point */
    protected String from;
    /** String for end point */
    protected String to;

    /**
     * Creates an Event instance with a description, start and end point
     *
     * @param desc Details of event
     * @param from Start point
     * @param to End point
     */
    public Event(String desc, String from, String to) throws YapException{
        super(desc);
        this.from = from;
        this.to = to;

         if (this.from.equalsIgnoreCase(this.to)) {
            throw new YapException(
                    "Oops! EVENT start and end times must be different!");
        }
    }

    /** 
     * Returns the string representation of the event 
     *
     * @return Formatted string with status icon, task description and time period
     */
    @Override
        public String toString() {
            return "[E]" + super.toString() + " (from: " + this.from + " to: " + 
                this.to + ")";
        }
    /**
     * Formats the task data into a pipe-delimted string for file storage
     *
     * @return Formatted string for file storage
     */
    @Override
        public String toFileFormat() {
            return "[EVENT] | " + super.toFileFormat() + " | " + this.from + " | " + this.to;
        }
}
