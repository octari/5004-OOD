/**
 * This is an abstract class. It represents a observer of a grade record of a student. It implements
 * the methods in interface Observer partly.
 */
public abstract class AbstractObserver implements Observer {
  // the observed student.
  protected Student student;
  // the status of the observer itself
  protected boolean status;

  /**
   * Constructor the new observer with default status. No subject to observe.
   */
  public AbstractObserver() {
    student = null;
    status = false;
  }

  /**
   * Register the subject to this observer.
   *
   * @param s the subject to be observed
   */
  @Override
  public void register(Subject s) {
    this.student = (Student) s;
  }

  /**
   * Return the status of this observer.
   *
   * @return its status
   */
  @Override
  public boolean getStatus() {
    return status;
  }
}
