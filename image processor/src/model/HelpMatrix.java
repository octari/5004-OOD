package model;

/**
 * This interface represents a help matrix used in the image processor.
 */
public interface HelpMatrix {
  /**
   * Set the element (row i, column j) with the given element.
   *
   * @param i       the row of the position (from top to bottom, starting from 0)
   * @param j       the column of the position (from left to right, starting from 0)
   * @param element the given element.
   */
  void set(int i, int j, double element);

  /**
   * Return the position of the center in the matrix.
   *
   * @return array of row and column of the matrix center.
   */
  int[] getCenter();

  /**
   * Return the relative difference between the given position and the center.
   *
   * @param i the row of the given position
   * @param j the column of the given position
   * @return the array of row and column difference
   */
  int[] getRelativeDistance(int i, int j);

  /**
   * Apply this filter to a pixel of a channel.
   *
   * @param target the 3D array of the image.
   * @param m      the row index of the pixel in a array of a channel.
   * @param n      the column index of the pixel in a array of a channel.
   * @return the clamped result of the filter.
   */
  int filterPixel(int[][] target, int m, int n);

  /**
   * Apply the the filter to every channel of every pixel.
   *
   * @param target the 3D array of image
   * @param h      the height of the image
   * @param w      the width of the image
   * @return the filtered 3D array
   */
  int[][][] filterImage(int[][][] target, int h, int w);

  /**
   * Transform the color of a pixel using color transformation.
   *
   * @param target the 3D array of the image.
   * @param m      the row index of the pixel.
   * @param n      the column index of the pixel
   * @param c      the channel index
   * @return the clamped result of the transformation.
   */
  int transformPixel(int[][][] target, int m, int n, int c);

  /**
   * Apply the color transformation to the image.
   *
   * @param target 3D array of the image
   * @param h      the height of the image
   * @param w      the width of the image
   * @return the transformed 3D array
   */
  int[][][] transformImage(int[][][] target, int h, int w);


  int[][][] transformDitheringImage(int[][][] target, int h, int w);

  /**
   * Convert the rgb of pixels into the mosaic ones. The values of pixel's rgb should be the average
   * of its cluster. Iterate the pixels to reset the rgb with the help of 2D array of clusters' rgb
   * sums and counters.
   *
   * @param target  the 3D array of the image
   * @param seedNum the seed number
   * @param h       the height of the image
   * @param w       the width of the image
   * @return the 3D array of mosaic image.
   */
  int[][][] transformMosaicImage(int[][][] target, int seedNum, int h, int w);
}
