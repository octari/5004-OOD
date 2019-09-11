package view;

import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This interface represents a view.View for the program. It offers a frame to show the operations and
 * result.
 */
public interface IView {
  /**
   * Display the result image.
   *
   * @param image teh image to displayed.
   */
  void display(Image image);

  /**
   * Get the absolute path the user choose when loading a file.
   *
   * @return the absolute loading path.
   */
  String choosePath();

  /**
   * Get the absolute path the user choose when saving a image.
   *
   * @return the absolute saving path.
   */
  String savePath();

  /**
   * Set the a listener for the components on the frame to react.
   *
   * @param clicked the action listener.
   */
  void setListeners(ActionListener clicked);

  /**
   * Return the image being displayed.
   *
   * @return the image being displayed.
   */
  java.awt.Image getDisplay();

  /**
   * When clicked the button for the operation which needs an input as parameter, pop up a dialog
   * for the input.
   *
   * @param title the title of the dialog.
   * @return the single input from a dialog.
   */
  String setSingleInput(String title);

  /**
   * When click the button for generating a flag, which needs to choose country and enter a ratio,
   * pop up a dialog for the setting.
   *
   * @return an array list of the all the settings from the user.
   */
  ArrayList<String> setFlag();

  /**
   * When click the button for generating a rainbow, which needs to choose vertical or horizontal
   * and enter height and width, pop up a dialog for the setting.
   *
   * @return an array list of all the settings from the user.
   */
  ArrayList<String> setRainbow();

  /**
   * When fail to execute the operation, a notification will a appear in the view.
   *
   * @param command the command fail to executed.
   */
  void cantDo(String command);

  /**
   * Get the all the input in the text area.
   *
   * @return teh all the input in the text area.
   */
  String getText();
}
