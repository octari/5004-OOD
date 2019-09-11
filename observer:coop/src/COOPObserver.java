/**
 * This class represents an observer to monitor the qualification of student for COOP. It extends
 * class AbstractObserver, and completes the method signal() to response the change.
 */
public class COOPObserver extends AbstractObserver {

  /**
   * Constructor, inherited from the AbstractObserver.
   */
  public COOPObserver() {
    super();
  }

  /**
   * After change, if the student has 16 credits and at least gpa 3.0, the status of this COOP
   * observer will turn to true; otherwise, it will remain false.
   */
  @Override
  public void signal() {
    this.status = student.totalCredit() >= 16 && student.getGPA() >= 3.0;
  }
}