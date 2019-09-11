package controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

import model.Model;
import model.VOrH;


/**
 * This is controller.ScriptController class can take the user's input in a txt file. According to the
 * commands, the controller would take action and executes them using the model. It implements the
 * interface controller.IScriptController.
 */
public class ScriptController implements IScriptController {
  private final Readable in;
  private Model model;

  /**
   * Construct a controller to the given model. In this case, it is model.Model. And the controller also
   * requires a input file.
   *
   * @param model the given model
   */
  public ScriptController(Model model, Readable script) {
    this.model = model;
    this.in = script;
  }

  /**
   * When the command is load, generate and save, there is a path following. Get the path.
   *
   * @param cmd the load, generate or save command.
   * @return the path in the command following load, generate and save.
   */
  private String getPath(String cmd) {
    return parseCommand(cmd)[1];
  }

  /**
   * Check if the command is load. It should starts with "load", followed a path.
   *
   * @param load the command
   * @return true, if the command is load. Otherwise, return false.
   */
  private boolean isLoad(String load) {
    return parseCommand(load)[0].equals("load") && parseCommand(load).length > 1;
  }

  /**
   * Check if the command is save. It should start with "save", followed a path.
   *
   * @param save the command
   * @return true if the command is save. Otherwise, return false.
   */
  private boolean isSave(String save) {
    return parseCommand(save)[0].equals("save") && parseCommand(save).length > 1;
  }

  /**
   * Check if the command is generate. It should start with "generate", followed a path.
   *
   * @param generate the command.
   * @return true if the command is generate. Otherwise, return false.
   */
  private boolean isGenerate(String generate) {
    return parseCommand(generate)[0].equals("generate") && parseCommand(generate).length > 1;
  }

  /**
   * Load the image as a controller.ImageReader. Read the image into 3D array, along with its height and
   * width.
   *
   * @param load the command load. "load path"
   * @return the controller.ImageReader of this image.
   * @throws IOException when it fails to read the image.
   */
  public ImageReader load(String load) throws IOException {
    return new ImageReader(getPath(load));
  }

  /**
   * Process the image when operate command calls with the help of the model.
   *
   * @param operation the operate command along with necessary arguments.
   * @param origin    the image as an controller.ImageReader to be processed.
   */
  public void operate(String[] operation, ImageReader origin) {
    if (origin != null) {
      if (operation.length >= 2) {
        try {
          origin.setArray(doOperation(operation[0], origin, Integer.parseInt(operation[1])));
        } catch (NumberFormatException e) {
          throw new IllegalArgumentException("the argument for operation should an integer");
        }
      } else {
        origin.setArray(doOperation(operation[0], origin, 0));
      }
    }
  }

  /**
   * Save the processed image when command save calls. It will write the controller.ImageReader into an image
   * using its 3D array, height and width.
   *
   * @param processed the controller.ImageReader to be saved
   * @param save      the saving path parsed from command save
   * @throws IOException when it fails to write the image.
   */
  public void save(ImageReader processed, String save) throws IOException {
    if (processed != null) {
      ImageUtil.writeImage(processed.getArray(), processed.getWidth(), processed.getHeight(),
              save);
    }
  }

  /**
   * Generate an image when command generation calls. Generated the specified image in the path
   * specified in the last generate command.
   *
   * @param path       the filename of the generated image
   * @param generation the command generation, which are the argument in generation.
   * @return the generated image in the controller.ImageReader.
   * @throws IOException when it fails to write the generated image.
   */
  public ImageReader generate(String path, String[] generation) throws IOException {
    ImageReader generated = doGeneration(generation[0], generation);
    ImageUtil.writeImage(generated.getArray(), generated.getWidth(), generated.getHeight(), path);
    return generated;
  }

  /**
   * Parse the command line by spaces. It should also strip leading and tailing spaces.
   *
   * @param cmd the command line.
   * @return a array of parsed command.
   */
  public String[] parseCommand(String cmd) {
    return cmd.trim().split("[\\s]+");
  }

  /**
   * Read and parse the input line by line. Execute the command using the model. The leading and
   * tailing spaces in the command line are allowed. There can be multiple spaces bwt words in a
   * command line. Before operation or generation and save, there must a load or a generate to
   * identify the source.
   *
   * @throws IllegalStateException    when command is invalid for the program.
   * @throws IllegalArgumentException when the generation and operation fails because invalid
   *                                  arguments
   */
  public void executeCommands() throws IllegalStateException {
    Scanner cmds = new Scanner(in);

    ImageReader src = null;
    String generateFile = null;

    while (cmds.hasNextLine()) {

      // skip the leading tailing spaces and multiple spaces btw words.
      String cmd = cmds.nextLine().trim();
      if (cmd.equals("")) {
        continue;
      }

      // When the command is load, identify the valid source path.
      // Otherwise, throw IllegalStateException.
      if (isLoad(cmd)) {
        try {
          src = load(cmd);
        } catch (IOException e) {
          throw new IllegalStateException("invalid source file");
        }
      }

      // When the command is process. Get the argument and do the operation.
      // If the source path is invalid, IllegalArgumentException would be thrown.
      else if (isOperation(cmd)) {
        operate(parseCommand(cmd), src);
      }

      // When it's save command, write the image using the result 3D array and
      // name the file as dest.
      else if (isSave(cmd)) {

        try {
          save(src, getPath(cmd));
        } catch (IOException e) {
          throw new IllegalStateException("Invalid save command");
        }

      } else if (isGenerate(cmd)) {
        generateFile = getPath(cmd);
      } else if (isValidGeneration(cmd)) {

        try {
          src = generate(generateFile, parseCommand(cmd));
        } catch (IOException e) {
          throw new IllegalStateException("Fail to generate");
        }
      } else {
        throw new IllegalStateException("Illegal command.");
      }

    }

  }

  /**
   * Check if the command is valid as the arguments of generation.
   *
   * @param cmd the strong of the arguments line.
   * @return true if it's valid. Otherwise, return false.
   */
  private boolean isValidGeneration(String cmd) {
    String[] cmds = parseCommand(cmd);

    if (cmds[0].equals("checkerboard")) {
      // The syntax should be "checkerboard size".
      try {
        Integer.parseInt(cmds[1]);
        return true;
      } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
        return false;
      }


    } else if (cmds[0].equals("rainbow")) {
      // The syntax should be "rainbow height width vertical/horizontal
      try {
        Integer.parseInt(cmds[1]);
        Integer.parseInt(cmds[2]);
        return true;
      } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
        return false;
      }

    } else if (cmds[0].equals("flag")) {
      // The syntax should be "flag country ratio".
      try {
        Integer.parseInt(cmds[2]);
        return cmds[1].equals("France") || cmds[1].equals("Greece")
                || cmds[1].equals("Switzerland");
      } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
        return false;
      }

    } else {
      return false;
    }
  }

  /**
   * Check if the operation is supported by the model.
   *
   * @param s the operation command
   * @return true if it's supported in the model. Otherwise return false.
   */
  private boolean isOperation(String s) {
    HashSet<String> operations = new HashSet<>();
    operations.add("blur");
    operations.add("sepia");
    operations.add("sharpen");
    operations.add("greyscale");
    operations.add("dither");
    operations.add("mosaic");
    return operations.contains(parseCommand(s)[0]);
  }

  /**
   * Generate an image with the given command. Name it as the generate line mentioned.
   *
   * @param kind      the type of the image, checkerboard, rainbow or flag.
   * @param arguments the split command line as the arguments the model take in to generate.
   * @throws IllegalArgumentException when the type of image can't be generated.
   */
  private ImageReader doGeneration(String kind, String[] arguments)
          throws IllegalArgumentException {
    switch (kind) {
      case "checkerboard":
        // The height and width of image should be 8*square size.
        int range = Integer.parseInt(arguments[1]) * 8;

        return new ImageReader(model.generateChecker(Integer.parseInt(arguments[1])), range, range);

      case "rainbow":
        // The height and width of the image is given by the user. User should specify the strips
        // should be vertical or horizontal. They are vertical in default.
        int height = Integer.parseInt(arguments[1]);
        int width = Integer.parseInt(arguments[2]);
        try {
          if (arguments[3].equals("horizontal")) {
            return new ImageReader(model.generateRainbow(height, width, VOrH.horizontal), width,
                    height);

          } else {
            return new ImageReader(model.generateRainbow(height, width, VOrH.vertical), width,
                    height);

          }
        } catch (ArrayIndexOutOfBoundsException e) {
          return new ImageReader(model.generateRainbow(height, width, VOrH.vertical), width,
                  height);

        }


      case "flag":
        // The height and width should be calculated by ratio given by user and the original
        // minimum size of flag.
        String country = arguments[1];
        int ratio = Integer.parseInt(arguments[2]);
        if (country.equals("Greece")) {
          return new ImageReader(model.generateFlags(ratio, country),
                  27 * ratio, 18 * ratio);
        } else if (country.equals("France")) {
          return new ImageReader(model.generateFlags(ratio, country),
                  30 * ratio, 20 * ratio);
        } else if (country.equals("Switzerland")) {
          return new ImageReader(model.generateFlags(ratio, country),
                  32 * ratio, 32 * ratio);
        } else {
          throw new IllegalArgumentException("Can't generate such an flag.");
        }

      default:
        throw new IllegalArgumentException("Can't generate such an image.");
    }
  }

  /**
   * When the command is an operation. Use the model to execute it. Return the result as a 3D array,
   * prepared for the following save command to write the image.
   *
   * @param operation the operation command
   * @param argument  the argument required when executing the operation
   * @return processed image in 3D array
   * @throws IllegalArgumentException when the operation can't be supported be the model.
   */
  private int[][][] doOperation(String operation, ImageReader origin, int argument) {

    switch (operation) {
      case "blur":
        return model.blurImage(origin.getArray(), origin.getHeight(), origin.getWidth());
      case "sharpen":
        return model.sharpenImage(origin.getArray(), origin.getHeight(), origin.getWidth());
      case "greyscale":
        return model.greyscaleImage(origin.getArray(), origin.getHeight(), origin.getWidth());
      case "sepia":
        return model.sepiaToneImage(origin.getArray(), origin.getHeight(), origin.getWidth());
      case "dither":
        return model.ditheringImage(origin.getArray(), origin.getHeight(), origin.getWidth());
      case "mosaic":
        return model.mosaicingImage(origin.getArray(), origin.getHeight(), origin.getWidth(),
                argument);
      default:
        throw new IllegalArgumentException("No such an operation.");
    }

  }

}

