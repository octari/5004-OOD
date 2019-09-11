package controller;

import java.io.IOException;

/**
 * This class represent an image by the 3D array, height and width. It can be construct by an image
 * file directly or by a 3D array, height and width. It helps to link the model and controller.
 */
public class ImageReader {
  private int[][][] array;
  private int height;
  private int width;

  /**
   * Construct a controller.ImageReader by using reading the given image file.
   *
   * @param file the filename of the image
   * @throws IOException when it fails to read the file
   */
  public ImageReader(String file) throws IOException {
    array = ImageUtil.readImage(file);
    height = ImageUtil.getHeight(file);
    width = ImageUtil.getWidth(file);
  }

  /**
   * Construct a controller.ImageReader by using a 3D array, an integer for height and an integer for width,
   * which represents an image.
   *
   * @param array  the 3D array of the image
   * @param width  the width of the image
   * @param height the height of the image
   */
  public ImageReader(int[][][] array, int width, int height) {
    this.array = array;
    this.height = height;
    this.width = width;
  }

  /**
   * Return the height of the image.
   *
   * @return the height of the image
   */
  public int getHeight() {
    return height;
  }

  /**
   * Return the 3D array of the image.
   *
   * @return the 3D array of the image
   */
  public int[][][] getArray() {
    return array;
  }

  /**
   * Return the width of the image.
   *
   * @return the width of the image
   */
  public int getWidth() {
    return width;
  }

  /**
   * Change to 3D array of the image, when the image is processed.
   *
   * @param array the changed 3D array
   */
  public void setArray(int[][][] array) {
    this.array = array;
  }
}
