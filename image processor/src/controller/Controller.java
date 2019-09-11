package controller;


import model.Model;
import view.View;

/**
 * This class implements the interface controller.IController. The view is not compulsory since it's
 * unnecessary for batch-script.
 */
public class Controller implements IController {

  private Model model;

  /**
   * Construct a controller with the given model.
   *
   * @param model the given model.
   */
  public Controller(Model model) {
    this.model = model;
  }

  /**
   * Control the program in the batch-script way.
   *
   * @param in the script
   * @throws IllegalStateException    when command is invalid for the program.
   * @throws IllegalArgumentException when the generation and operation fails because of invalid
   *                                  arguments
   */
  public void executeScript(Readable in) throws IllegalStateException {
    new ScriptController(model, in).executeCommands();
  }


  /**
   * Control the program in an interactive way.
   *
   * @param view the view.View of the program.
   * @throws IllegalStateException    when bach script command is invalid for the program.
   * @throws IllegalArgumentException in batch script when the generation and operation fails
   *                                  because of invalid arguments
   */
  public void executeInteractive(View view) throws IllegalStateException {
    new InteractiveController(model, view);
  }

}
