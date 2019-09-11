/**
 * This interface represents a subject that can be observed by an observer. When it makes some
 * change, its observer will be notified.
 */
public interface Subject {

  /**
   * Register an observer to monitor this subject.
   *
   * @param obj the observer
   */
  void register(Observer obj);

  /**
   * Change this subject according to the given message. It will also notify the observer.
   *
   * @param classname the title of the change message
   * @param grade     the corresponding value of the title.
   */
  void change(String classname, String grade);
}
