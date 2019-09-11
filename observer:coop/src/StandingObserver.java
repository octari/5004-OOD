/**
 * This class represents an observer to monitor if the student is in good academic standing It
 * extends class AbstractObserver, and completes the method signal() to response the change.
 */
public class StandingObserver extends AbstractObserver {

  /**
   * Constructor, inherited from the AbstractObserver.
   */
  public StandingObserver() {
    super();
  }

  /**
   * After change, if the student has at least gpa 3.0, the status of this Standing observer will
   * turn to true; otherwise, it will remain false.
   */
  @Override
  public void signal() {
    this.status = student.getGPA() >= 3.0;
  }

}
