package model;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * This class implements the interface model.Model, and it implements all the methods in the interface,
 * including generating and processing, taking advantage of 2 help class model.ImageGenerator and
 * model.ImageProcessor.
 */
public class ImageImpl implements Model {
  private Stack<int[][][]> undoStack;
  private Stack<int[][][]> redoStack;

  public ImageImpl() {
    undoStack = new Stack<>();
    redoStack = new Stack<>();
  }

  /**
   * Undo. Return a previous result before the operation.
   *
   * @return a previous result.
   * @throws EmptyStackException when there is no previous one.
   */
  public int[][][] undo() throws EmptyStackException {
    int[][][] toStore = undoStack.pop();
    redoStack.push(toStore);
    int[][][] toShow = undoStack.peek();

    return toShow;
  }

  /**
   * Redo. Return a previous result before an undo.
   *
   * @return a previous result before an undo.
   * @throws EmptyStackException when there is no previous one.
   */
  public int[][][] redo() throws EmptyStackException {
    int[][][] toShow = redoStack.pop();
    undoStack.push(toShow);
    return toShow;
  }

  /**
   * Set the stacks for undo and redo. Add a new element to the undo stack, and clear the redo
   * stack.
   */
  public void setStack(int[][][] add) {
    undoStack.push(add);
    redoStack.clear();
  }

  /**
   * Generate a image of checker board pattern (8 X 8) of the given size. Name the image as the
   * given file name.
   *
   * @param size the size of the image
   */
  @Override
  public int[][][] generateChecker(int size) {
    return ImageGenerator.checkerBoardGenerator(size);
  }

  /**
   * Generate a image if rainbow stripes (7 colors) with the given size. Name the image as the given
   * filename.
   *
   * @param height the height of the image
   * @param width  the width of the image
   * @param vOrH   the stripes should be vertical of horizontal.
   * @throws IllegalArgumentException when users enter anything other than vertical or horizontal.
   */
  @Override
  public int[][][] generateRainbow(int height, int width, VOrH vOrH) {
    return ImageGenerator.rainbowStripesGenerator(height, width, vOrH);
  }

  /**
   * Generate the appropriate-sized flags of a country with the given ratio, and name it as the
   * given filename.
   *
   * @param ratio   the given ratio of the flag.
   * @param country the country whose flag will be generated.
   */
  @Override
  public int[][][] generateFlags(int ratio, String country) {
    return ImageGenerator.flagsGenerator(ratio, country);
  }

  /**
   * Blur the image using the image processor.
   *
   * @param imageArray the 3D array of the image
   * @param height     the height of the image
   * @param width      the width of the image
   * @return the 3D array of the blurred image
   */
  @Override
  public int[][][] blurImage(int[][][] imageArray, int height, int width) {
    ImageProcessor image = new ImageProcessor(imageArray, height, width);
    return image.blur();

  }

  /**
   * Sharpen the image using the image processor.
   *
   * @param imageArray the 3D array of the image
   * @param height     the height of the image
   * @param width      the width of the image
   * @return the 3D array the sharpened image
   */
  @Override
  public int[][][] sharpenImage(int[][][] imageArray, int height, int width) {
    ImageProcessor image = new ImageProcessor(imageArray, height, width);
    return image.sharpening();

  }

  /**
   * Grey scale the image using the image processor.
   *
   * @param imageArray the 3D array of the image
   * @param height     the height of the image
   * @param width      the width of the image
   * @return 3D array of the greyscale image
   */
  @Override
  public int[][][] greyscaleImage(int[][][] imageArray, int height, int width) {
    ImageProcessor image = new ImageProcessor(imageArray, height, width);
    return image.greyScale();
  }

  /**
   * Sepia-tone the image using the image processor.
   *
   * @param imageArray the 3D array of the image
   * @param height     the height of the image
   * @param width      the width of the image
   * @return the 3D array of the sepia-tone image
   */
  @Override
  public int[][][] sepiaToneImage(int[][][] imageArray, int height, int width) {
    ImageProcessor image = new ImageProcessor(imageArray, height, width);
    return image.sepiaTone();
  }

  /**
   * Dither the image using the image processor.
   *
   * @param imageArray the 3D array of the image
   * @param height     the height of the image
   * @param width      the width of the image
   * @return 3D array of the dithered image
   */
  @Override
  public int[][][] ditheringImage(int[][][] imageArray, int height, int width) {
    ImageProcessor image = new ImageProcessor(imageArray, height, width);
    return image.dithering();

  }

  /**
   * Mosaic the image using the image processor.
   *
   * @param imageArray the 3D array of the image
   * @param height     the height of the image.
   * @param width      the width of the image.
   * @param seedNum    the number of seeds.
   * @return the 3D array of mosaic image.
   */
  @Override
  public int[][][] mosaicingImage(int[][][] imageArray, int height, int width, int seedNum) {
    ImageProcessor image = new ImageProcessor(imageArray, height, width);
    return image.mosaicing(seedNum);

  }


}
