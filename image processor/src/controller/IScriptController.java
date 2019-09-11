package controller;

import java.io.IOException;

/**
 * The interface represents a controller of the image processing program. It contains only one
 * public method to the do operations using model according to the user's input.
 */
public interface IScriptController {
  String[] parseCommand(String cmd);

  /**
   * Do corresponding operation according to the user's input.
   *
   * @throws IllegalStateException when it fails execute the command.
   */
  void executeCommands() throws IllegalStateException;

  /**
   * Load the image as a controller.ImageReader. Read the image into 3D array, along with its height and
   * width.
   *
   * @param load the command load. "load path"
   * @return the controller.ImageReader of this image.
   * @throws IOException when it fails to read the image.
   */
  ImageReader load(String load) throws IOException;


  /**
   * Process the image when operate command calls with the help of the model.
   *
   * @param operation the operate command along with necessary arguments.
   * @param origin    the image as an controller.ImageReader to be processed.
   */
  void operate(String[] operation, ImageReader origin);


  /**
   * Save the processed image when command save calls. It will write the controller.ImageReader into an image
   * using its 3D array, height and width.
   *
   * @param processed the controller.ImageReader to be saved
   * @param save      the saving path parsed from command save
   * @throws IOException when it fails to write the image.
   */
  void save(ImageReader processed, String save) throws IOException;


  /**
   * Generate an image when command generation calls. Generated the specified image in the path
   * specified in the last generate command.
   *
   * @param path       the filename of the generated image
   * @param generation the command generation, which are the argument in generation.
   * @return the generated image in the controller.ImageReader.
   * @throws IOException when it fails to write the generated image.
   */
  ImageReader generate(String path, String[] generation) throws IOException;
}
