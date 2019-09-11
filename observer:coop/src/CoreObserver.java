/**
 * This class represents an observer to monitor the qualification of core requirements. It extends
 * class AbstractObserver, and completes the method signal() to response the change.
 */
public class CoreObserver extends AbstractObserver {
  /**
   * Constructor, inherited from the AbstractObserver.
   */
  public CoreObserver() {
    super();
  }

  /**
   * After change, if the student fulfills the core requirements for general or align student, the
   * status of this core observer will turn to true; otherwise, it will remain false.
   */
  @Override
  public void signal() {
    String[] alignCore = {"CS 5004", "CS 5600", "CS 5500", "CS 5800"};
    String[] generalCore = {"CS 5010", "CS 5600", "CS 5500", "CS 5800"};
    this.status = (student.hasFulfilledAllCore(alignCore)
            || student.hasFulfilledAllCore(generalCore));

  }

}
