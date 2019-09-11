package model;

import java.util.EmptyStackException;

/**
 * This interface represents a model that can generate and process an image. The methods in it can
 * be divided into 2 parts, to generate images and to process an image. Each part will be
 * implemented with the help class model.ImageGenerator and model.ImageProcessor.
 */
public interface Model {
  /**
   * Generate a image of checker board pattern (8 X 8) of the given size.
   *
   * @param size the size of the image
   * @return the 3D array of the generated checkerboard.
   */
  int[][][] generateChecker(int size);

  /**
   * Generate a image if rainbow stripes (7 colors) with the given size.
   *
   * @param height the height of the image
   * @param width  the width of the image
   * @param vOrH   the stripes should be vertical of horizontal.
   * @return the 3D array of the generated rainbow stripes.
   */
  int[][][] generateRainbow(int height, int width, VOrH vOrH);

  /**
   * Generate the appropriate-sized flags of a country with the given ratio.
   *
   * @param ratio   the given ratio of the flag.
   * @param country the country whose flag will be generated.
   * @return the 3D array of the generated flag.
   */
  int[][][] generateFlags(int ratio, String country);

  /**
   * Blur the image using the image processor.
   *
   * @param imageArray the 3D array of the image
   * @param height     the height of the image
   * @param width      the width of the image
   * @return the 3D array of the blurred image
   */
  int[][][] blurImage(int[][][] imageArray, int height, int width);

  /**
   * Sharpen the image using the image processor.
   *
   * @param imageArray the 3D array of the image
   * @param height     the height of the image
   * @param width      the width of the image
   * @return the 3D array the sharpened image
   */
  int[][][] sharpenImage(int[][][] imageArray, int height, int width);

  /**
   * Grey scale the image using the image processor.
   *
   * @param imageArray the 3D array of the image
   * @param height     the height of the image
   * @param width      the width of the image
   * @return 3D array of the greyscale image
   */
  int[][][] greyscaleImage(int[][][] imageArray, int height, int width);

  /**
   * Sepia- tone the image using the image processor.
   *
   * @param imageArray the 3D array of the image
   * @param height     the height of the image
   * @param width      the width of the image
   * @return the 3D array of the sepia-tone image
   */
  int[][][] sepiaToneImage(int[][][] imageArray, int height, int width);

  /**
   * Dither the image using the image processor.
   *
   * @param imageArray the 3D array of the image
   * @param height     the height of the image
   * @param width      the width of the image
   * @return 3D array of the dithered image
   */
  int[][][] ditheringImage(int[][][] imageArray, int height, int width);

  /**
   * Mosaic the image using the image processor.
   *
   * @param imageArray the 3D array of the image
   * @param height     the height of the image.
   * @param width      the width of the image.
   * @param seedNum    the number of seeds.
   * @return the 3D array of mosaic image.
   */
  int[][][] mosaicingImage(int[][][] imageArray, int height, int width, int seedNum);

  /**
   * Undo. Return a previous result before the operation.
   *
   * @return a previous result.
   * @throws EmptyStackException when there is no previous one.
   */
  int[][][] undo() throws EmptyStackException;

  /**
   * Redo. Return a previous result before an undo.
   *
   * @return a previous result before an undo.
   * @throws EmptyStackException when there is no previous one.
   */
  int[][][] redo() throws EmptyStackException;

  /**
   * Set the stacks for undo and redo. Add a new element to the undo stack, and clear the redo
   * stack.
   * @param add the image in 3D array to be added to the stack.
   */
  void setStack(int[][][] add);
}
