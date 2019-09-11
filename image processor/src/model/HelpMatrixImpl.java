package model;

/**
 * This class represents a help double matrix used in image processor. It implements all the
 * operations in the interface model.HelpMatrix and also has private help methods.
 */
public class HelpMatrixImpl implements HelpMatrix {
  private int height;
  private int width;
  private double[][] elements;


  /**
   * Construct a matrix whose elements are double with the given height and width.
   *
   * @param width  number of columns of the matrix
   * @param height number of rows of the matrix
   */
  public HelpMatrixImpl(int width, int height) {
    this.height = height;
    this.width = width;
    elements = new double[height][width];
  }


  /**
   * Set the element (row i, column j) with the given element.
   *
   * @param i       the row of the position (from top to bottom, starting from 0)
   * @param j       the column of the position (from left to right, starting from 0)
   * @param element the given element.
   */
  @Override
  public void set(int i, int j, double element) {
    elements[i][j] = element;
  }

  /**
   * Return the position of the center in the matrix.
   *
   * @return array of row and column of the matrix center.
   */
  @Override
  public int[] getCenter() {
    return new int[]{height / 2, width / 2};
  }

  /**
   * Return the relative difference between the given position and the center.
   *
   * @param i the row of the given position
   * @param j the column of the given position
   * @return the array of row and column difference
   */
  @Override
  public int[] getRelativeDistance(int i, int j) {
    return new int[]{i - getCenter()[0], j - getCenter()[1]};
  }

  /**
   * Help function: clamp the filtered and transformed result. When it's greater than 255, return
   * 255. When it's negative, return 0.
   *
   * @param n the result to be clamped.
   * @return the clamped result
   */
  private int clamp(int n) {
    if (n > 255) {
      n = 255;
    } else if (n < 0) {
      n = 0;
    }
    return n;
  }

  /**
   * Apply this filter to a pixel of a channel.
   *
   * @param target the 3D array of the image.
   * @param m      the row index of the pixel in a array of a channel.
   * @param n      the column index of the pixel in a array of a channel.
   * @return the clamped result of the filter.
   */
  @Override
  public int filterPixel(int[][] target, int m, int n) {
    double sum = 0;

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int[] dis = getRelativeDistance(i, j);
        try {
          sum += elements[i][j] * target[m + dis[0]][n + dis[1]];
        } catch (ArrayIndexOutOfBoundsException ignored) {
          sum += 0;
        }
      }
    }
    return clamp((int) Math.round(sum));
  }


  /**
   * Return 2D array of pixels of a channel.
   *
   * @param target the 3D array of the image
   * @param h      the height of the image
   * @param w      the width of the image
   * @param c      the index of the channel
   * @return 2D array of pixels of a channel
   */
  private int[][] getChannel(int[][][] target, int h, int w, int c) {
    int[][] result = new int[h][w];
    for (int i = 0; i < h; i++) {
      for (int j = 0; j < w; j++) {
        result[i][j] = target[i][j][c];
      }
    }
    return result;
  }

  /**
   * Apply the the filter to every channel of every pixel.
   *
   * @param target the 3D array of image
   * @param h      the height of the image
   * @param w      the width of the image
   * @return the filtered 3D array
   */
  @Override
  public int[][][] filterImage(int[][][] target, int h, int w) {
    int[][][] result = new int[h][w][3];
    for (int c = 0; c < 3; c++) {
      int[][] channel = getChannel(target, h, w, c);
      for (int i = 0; i < h; i++) {
        for (int j = 0; j < w; j++) {

          result[i][j][c] = filterPixel(channel, i, j);
        }

      }
    }
    return result;
  }

  /**
   * Transform the color of a pixel using color transformation.
   *
   * @param target the 3D array of the image.
   * @param m      the row index of the pixel.
   * @param n      the column index of the pixel
   * @param c      the channel index
   * @return the clamped result of the transformation.
   */
  @Override
  public int transformPixel(int[][][] target, int m, int n, int c) {
    double sum = 0;
    for (int i = 0; i < width; i++) {
      sum += elements[c][i] * target[m][n][i];
    }
    return clamp((int) Math.round(sum));
  }

  /**
   * Apply the color transformation to the image.
   *
   * @param target 3D array of the image
   * @param h      the height of the image
   * @param w      the width of the image
   * @return the transformed 3D array
   */
  @Override
  public int[][][] transformImage(int[][][] target, int h, int w) {
    int[][][] result = new int[h][w][3];
    for (int c = 0; c < 3; c++) {
      for (int i = 0; i < h; i++) {
        for (int j = 0; j < w; j++) {
          result[i][j][c] = transformPixel(target, i, j, c);
        }
      }
    }
    return result;
  }

  /**
   * The channel of the image is a 2D array. In a channel, find the new color of each pixel which is
   * 0 or 255. If the old color is closer to 0, the new color would be 0. Otherwise, it would be
   * 255.
   *
   * @param target a 2D array representing a channel of the image.
   * @param h      the height of the pixel
   * @param w      the width of the pixel
   * @return the new color of the pixel
   */
  private int ditheringNewColor(int[][] target, int h, int w) {
    int oldColor = target[h][w];
    if (Math.abs(255 - oldColor) < Math.abs(0 - oldColor)) {
      return 255;
    } else {
      return 0;
    }
  }

  /**
   * Return the error bwt the new and old color of each pixel in a channel.
   *
   * @param target a 2D array representing a channel of the image.
   * @param h      the height of the pixel
   * @param w      the width of the pixel
   * @return the error bwt the new and old color of each pixel in a channel.
   */
  private int error(int[][] target, int h, int w) {
    int oldColor = target[h][w];
    int newColor = ditheringNewColor(target, h, w);

    return oldColor - newColor;

  }

  /**
   * Change the color of the pixels around according to the error and the dithering matrix, as a
   * result adjusting the whole channel. The result has to be clamped.
   *
   * @param channel a 2D array representing a channel of the image.
   * @param h       the height of this pixel
   * @param w       the width of this pixel
   * @return the changed channel 2D array
   */
  private int[][] adjustChannel(int[][] channel, int h, int w) {
    int error = error(channel, h, w);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int[] dis = getRelativeDistance(i, j);
        try {
          channel[h + dis[0]][w + dis[1]] += elements[i][j] * error;
          channel[h + dis[0]][w + dis[1]] = clamp(channel[h + dis[0]][w + dis[1]]);
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
      }
    }
    return channel;

  }

  /**
   * Change the rgb colors of the every pixel in the image by adjusting the channel over and over
   * again.
   *
   * @param target the 3D array of the image to be dithered.
   * @param h      the height of the image
   * @param w      the width of the image
   * @return the 3D array of the dithered image.
   */
  public int[][][] transformDitheringImage(int[][][] target, int h, int w) {
    int[][][] result = new int[h][w][3];

    for (int c = 0; c < 3; c++) {
      int[][] channel = getChannel(target, h, w, c);
      for (int i = 0; i < h; i++) {
        for (int j = 0; j < w; j++) {
          result[i][j][c] = ditheringNewColor(channel, i, j);
          channel = adjustChannel(channel, i, j);
        }
      }
    }
    return result;
  }

  /**
   * In the cluster matrix, each element represents the index of the cluster the pixel in the same
   * position belongs to. Calculate the sum of r, g, b respectively in the every cluster. Store the
   * sums in an array along with the number of pixels in that cluster. Each cluster will have such
   * an array, forming a 2D array.
   *
   * @param target  the 3D array of the image
   * @param seedNum number of the seed
   * @param h       the height of the matrix
   * @param w       the width of the matrix
   * @return the 2D array of sums of rgb and counters in clusters.
   */
  private int[][] mosaicSumColor(int[][][] target, int seedNum, int h, int w) {
    int[][] sumColor = new int[seedNum][4];
    for (int i = 0; i < h; i++) {
      for (int j = 0; j < w; j++) {
        sumColor[(int) (elements[i][j])][0] += target[i][j][0];
        sumColor[(int) (elements[i][j])][1] += target[i][j][1];
        sumColor[(int) (elements[i][j])][2] += target[i][j][2];
        sumColor[(int) (elements[i][j])][3]++;
      }
    }
    return sumColor;
  }

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
  public int[][][] transformMosaicImage(int[][][] target, int seedNum, int h, int w) {
    int[][][] result = new int[h][w][3];
    int[][] sumColor = mosaicSumColor(target, seedNum, h, w);
    for (int i = 0; i < h; i++) {
      for (int j = 0; j < w; j++) {
        result[i][j][0] = sumColor[(int) elements[i][j]][0] / sumColor[(int) elements[i][j]][3];
        result[i][j][1] = sumColor[(int) elements[i][j]][1] / sumColor[(int) elements[i][j]][3];
        result[i][j][2] = sumColor[(int) elements[i][j]][2] / sumColor[(int) elements[i][j]][3];
      }
    }

    return result;
  }


}
