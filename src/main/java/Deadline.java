import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Deadline extends Task {
  protected LocalDateTime deadline;

  private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
  private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
  private static final DateTimeFormatter STORED_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHMM");


  public Deadline(String desc, String deadline) throws YapException {
    super(desc);
    this.deadline = parseDateTime(deadline.trim());

  }
  private LocalDateTime parseDateTime(String deadline) throws YapException {
    try {
      return LocalDateTime.parse(deadline, INPUT_FORMAT);
    } catch (DateTimeParseException e1) {
      try {
        return LocalDateTime.parse(deadline, STORED_FORMAT);
      } catch (DateTimeParseException e2) {
        throw new YapException("I can't read that date! Please use the d/M/yyyy format :)");
      }
    }
  }

  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + this.deadline.format(OUTPUT_FORMAT) + ")";
  }
  @Override
  public String toFileFormat() {
    return "[DEADLINE] | " + super.toFileFormat() + " | " + this.deadline.format(STORED_FORMAT);
  }
}
    

