package controller;

import view.View;

/**
 * This class represents a controller of the program. It can control the program in a batch-script
 * or interactive way.
 */
public interface IController {
  /**
   * Control the program in the batch-script way.
   *
   * @param in the script
   * @throws IllegalStateException    when command is invalid for the program.
   * @throws IllegalArgumentException when the generation and operation fails because of invalid
   *                                  arguments
   */
  void executeScript(Readable in) throws IllegalStateException;

  /**
   * Control the program in an interactive way.
   *
   * @param view the view.View of the program.
   * @throws IllegalStateException    when bach script command is invalid for the program.
   * @throws IllegalArgumentException in batch script when the generation and operation fails
   *                                  because of invalid arguments
   */
  void executeInteractive(View view) throws IllegalStateException;
}
