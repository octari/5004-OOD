/**
 * This interface represents an observer.
 */
public interface Observer {

  /**
   * Executed in response to the change of the subject observed.
   */
  void signal();

  /**
   * Register the subject to this observer.
   * @param s the subject to be observed
   */
  void register(Subject s);

  /**
   * Return the status of this observer.
   * @return its status
   */
  boolean getStatus();
}
