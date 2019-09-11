package controller;

import java.awt.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.EmptyStackException;


import javax.imageio.ImageIO;

import javax.swing.*;

import controller.ScriptController;
import model.Model;
import model.VOrH;
import view.View;

/**
 * This class represents a interactive controller for the program.
 */
public class InteractiveController implements ActionListener {
  private Model model;
  private View view;

  /**
   * Construct the controller using the given model and view.
   *
   * @param image the model of the program to complete all the functionality
   * @param view  the view of the program to display.
   */
  public InteractiveController(Model image, View view) {
    model = image;
    this.view = view;
    view.setResizable(false);
    view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    view.setVisible(true);
    view.setListeners(this);

  }

  /**
   * Help function: Covert a image in Image to the BufferImage.
   *
   * @param image the image in Image.
   * @return the BufferedImage.
   */
  private BufferedImage toBufferedImage(java.awt.Image image) {
    BufferedImage bImage = new BufferedImage(image.getWidth(null), image
            .getHeight(null), BufferedImage.TYPE_INT_ARGB);

    Graphics g = bImage.createGraphics();

    g.drawImage(image, 0, 0, null);
    g.dispose();

    return bImage;
  }

  /**
   * React to the action the user takes on view, using model to complete the command and the view to
   * show the result.
   *
   * @param e the action the user takes in view.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Open file":
        String loadPath = view.choosePath();
        if (loadPath != null) {
          view.display(new ImageIcon(loadPath).getImage());
          try {
            model.setStack(ImageUtil.readImage(loadPath));
          } catch (IOException i) {
          }

        }
        break;
      case "Save file":
        String path = view.savePath();
        if (path != null) {
          BufferedImage curr = toBufferedImage(view.getDisplay());


          try {

            String format = path.substring(path.length() - 3);
            ImageIO.write(curr, format, new FileOutputStream(path));
          } catch (IOException e1) {
            e1.printStackTrace();
          }
        }
        break;

      case "generate checker":
        String input = view.setSingleInput("checker size");
        if (input != null) {
          int checkerSize = Integer.parseInt(input);
          int[][][] checker = model.generateChecker(checkerSize);
          helpDisplayProcess(checker);
        }
        break;

      case "generate flag":

        ArrayList<String> flagArguments = view.setFlag();
        if (flagArguments != null) {
          int[][][] flag = model.generateFlags(Integer.parseInt(flagArguments.get(1)),
                  flagArguments.get(0));
          helpDisplayProcess(flag);
        }
        break;

      case "generate rainbow":
        ArrayList<String> rainbowArguments = view.setRainbow();
        if (rainbowArguments != null) {
          int[][][] rainbow = null;
          if (rainbowArguments.get(2).equals("vertical")) {
            rainbow = model.generateRainbow(Integer.parseInt(rainbowArguments.get(0)),
                    Integer.parseInt(rainbowArguments.get(1)), VOrH.vertical);
          } else if (rainbowArguments.get(2).equals("horizontal")) {
            rainbow = model.generateRainbow(Integer.parseInt(rainbowArguments.get(0)),
                    Integer.parseInt(rainbowArguments.get(1)), VOrH.horizontal);
          }
          helpDisplayProcess(rainbow);
        }

        break;

      case "mosaic":
        String inputM = view.setSingleInput("mosaic seed");
        if (inputM != null) {
          int seed = Integer.parseInt(inputM);
          BufferedImage toMosaic = toBufferedImage(view.getDisplay());
          int[][][] mosaic = model.mosaicingImage(ImageUtil.readBufferedImage(toMosaic),
                  toMosaic.getHeight(), toMosaic.getWidth(), seed);
          helpDisplayProcess(mosaic);
        }
        break;

      case "blur":
        BufferedImage toBlur = toBufferedImage(view.getDisplay());
        int[][][] blur = model.blurImage(ImageUtil.readBufferedImage(toBlur),
                toBlur.getHeight(), toBlur.getWidth());
        helpDisplayProcess(blur);
        break;

      case "sharpen":
        BufferedImage toSharpen = toBufferedImage(view.getDisplay());
        int[][][] sharpen = model.sharpenImage(ImageUtil.readBufferedImage(toSharpen),
                toSharpen.getHeight(), toSharpen.getWidth());
        helpDisplayProcess(sharpen);
        break;

      case "greyscale":
        BufferedImage toGrey = toBufferedImage(view.getDisplay());
        int[][][] greyscale = model.greyscaleImage(ImageUtil.readBufferedImage(toGrey),
                toGrey.getHeight(), toGrey.getWidth());
        helpDisplayProcess(greyscale);
        break;

      case "sepia":
        BufferedImage toSepia = toBufferedImage(view.getDisplay());
        int[][][] sepia = model.sepiaToneImage(ImageUtil.readBufferedImage(toSepia),
                toSepia.getHeight(), toSepia.getWidth());
        helpDisplayProcess(sepia);
        break;

      case "dither":
        BufferedImage toDither = toBufferedImage(view.getDisplay());
        int[][][] dither = model.ditheringImage(ImageUtil.readBufferedImage(toDither),
                toDither.getHeight(), toDither.getWidth());
        helpDisplayProcess(dither);
        break;

      case "undo":
        try {
          int[][][] undo = model.undo();
          Image toShow = ImageUtil.convertToImage(undo, undo[0].length, undo.length);
          view.display(toShow);
        } catch (EmptyStackException noUndo) {
          view.cantDo("Has no more undo history operation.");
        }
        break;

      case "redo":
        try {
          int[][][] redo = model.redo();
          Image toShow = ImageUtil.convertToImage(redo, redo[0].length, redo.length);
          view.display(toShow);
        } catch (EmptyStackException noRedo) {
          view.cantDo("Has no more redo history operation.");
        }
        break;

      case "run command":
        try {
          new ScriptController(model, new StringReader(view.getText())).executeCommands();
        } catch (Exception i) {
          view.cantDo("Fail to execute the command. Check it please.");
        }

        break;
      default:
        break;

    }
  }

  /**
   * Help function: ask the view to display the image in 3D array, and the model to hold it in case
   * of undo and redo.
   *
   * @param display the image to be displayed in 3D array
   */
  private void helpDisplayProcess(int[][][] display) {
    Image toDisplay = ImageUtil.convertToImage(display, display[0].length, display.length);
    view.display(toDisplay);
    model.setStack(display);
  }
}
