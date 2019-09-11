package model;

import java.awt.Color;

/**
 * Help class. This class represents a image generator. It have methods to generate checker board
 * pattern, rainbow stripes and flags of France, Greece and Switzerland.
 */
public class ImageGenerator {

  /**
   * Generate a image of checker board pattern (8 X 8) of the given size.
   *
   * @param size the size of the image
   * @return the 3D array of the generated checkerboard
   */
  public static int[][][] checkerBoardGenerator(int size) {
    int height = 8 * size;
    int width = 8 * size;
    return setCheckerColor(height, width, size);
  }

  /**
   * Help function: fill a pixel (i.e. result[i][j]) with the given color.
   *
   * @param result the 2D array representing the checker board.
   * @param i      the row of the pixel
   * @param j      the column of the pixel
   * @param t      the given color
   */
  private static void fillColor(int[][][] result, int i, int j, Color t) {
    result[i][j][0] = t.getRed();
    result[i][j][1] = t.getGreen();
    result[i][j][2] = t.getBlue();
  }


  /**
   * Help function: to fill the squares on the checker board with white or black. Generate the
   * checker board pattern.
   *
   * @param height the height of the image.
   * @param width  the width of the image.
   * @param size   the size of a single square on the checker board.
   * @return the 3D array of the image of checker board.
   */
  private static int[][][] setCheckerColor(int height, int width, int size) {
    int[][][] result = new int[height][width][3];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (i / size % 2 == 0 && j / size % 2 == 0) {
          fillColor(result, i, j, Color.black);
        } else if (i / size % 2 == 0 && j / size % 2 == 1) {
          fillColor(result, i, j, Color.white);
        } else if (i / size % 2 == 1 && j / size % 2 == 1) {
          fillColor(result, i, j, Color.black);
        } else {
          fillColor(result, i, j, Color.white);
        }
      }
    }
    return result;
  }

  /**
   * Generate a image if rainbow stripes (7 colors) with the given size.
   *
   * @param height the height of the image
   * @param width  the width of the image
   * @param vorh   the stripes should be vertical of horizontal.
   * @return the 3D array of the generate rainbow stripes.
   * @throws IllegalArgumentException when users enter anything other than vertical or horizontal.
   */
  public static int[][][] rainbowStripesGenerator(int height, int width, VOrH vorh)
          throws IllegalArgumentException {
    int[][][] result = new int[height][width][3];
    Color violet = new Color(128, 0, 128);
    Color[] colors = {Color.red, Color.orange, Color.yellow, Color.green,
                      Color.cyan, Color.blue, violet};
    if (vorh.equals(VOrH.vertical)) {
      for (int i = 0; i < width; i++) {
        setVerticalStripeColor(result, height, i, colors[(int) (i / Math.ceil(width / 7.0))]);
      }
    } else if (vorh.equals(VOrH.horizontal)) {
      for (int j = 0; j < height; j++) {
        setHorizontalStripeColor(result, width, j, colors[(int) (j / Math.ceil(height / 7.0))]);
      }
    } else {
      throw new IllegalArgumentException("It should be vertical or horizontal.");
    }

    return result;
  }

  /**
   * Help function: Fill a row with the given color.
   *
   * @param result the filled result 3D array
   * @param width  the width of the image
   * @param j      the index of the horizontal stripe
   * @param color  the given color
   */
  private static void setHorizontalStripeColor(int[][][] result, int width, int j, Color color) {
    for (int i = 0; i < width; i++) {
      fillColor(result, j, i, color);
    }
  }

  /**
   * Help function: Fill a column with the given color.
   *
   * @param result the filled result 3D array
   * @param height the height of the image
   * @param i      the index of the vertical stripe
   * @param color  the given color
   */
  private static void setVerticalStripeColor(int[][][] result, int height, int i, Color color) {
    for (int j = 0; j < height; j++) {
      fillColor(result, j, i, color);
    }
  }


  /**
   * Help function: Draw a flag of France with the given ratio. The flag's size should be height :
   * width = 2 : 3; there are 3 parts, blue, white and red. The width of each should be 30:33:37.
   * User should specify the ratio of the image size to the actual minimum flag size.
   *
   * @param ratio the given ratio of the flag
   * @return the 3D array of the generate flag of the France
   */
  private static int[][][] franceFlagGenerator(int ratio) {
    // Get the appropriate height according to the width
    int height = 20 * ratio;
    int width = 30 * ratio;

    int[][][] result = new int[height][width][3];

    // Generate the colors.
    Color franceRed = new Color(239, 65, 53);
    Color franceBlue = new Color(0, 85, 164);
    Color franceWhite = new Color(255, 255, 255);

    // The threshold of the blue and white parts.
    int bluePoint = (int) (width * 0.3);
    // The threshold of the white and red parts.
    int whitePoint = (int) (width * (0.3 + 0.33));

    for (int j = 0; j < height; j++) {
      for (int i = 0; i < width; i++) {
        if (i < bluePoint) {
          fillColor(result, j, i, franceBlue);
        } else if (i >= bluePoint && i < whitePoint) {
          fillColor(result, j, i, franceWhite);
        } else {
          fillColor(result, j, i, franceRed);
        }
      }
    }
    return result;
  }

  /**
   * Generate the appropriate-sized flags of a country with the given ratio.
   *
   * @param ratio   the given ratio of the flag.
   * @param country the country whose flag will be generated.
   * @return the 3D array of the generated flag
   * @throws IllegalArgumentException when the country is anything else.
   */
  public static int[][][] flagsGenerator(int ratio, String country)
          throws IllegalArgumentException {
    switch (country) {
      case "France":
        return franceFlagGenerator(ratio);
      case "Greece":
        return greeceFlagGenerator(ratio);
      case "Switzerland":
        return switzerlandFlagGenerator(ratio);
      default:
        throw new IllegalArgumentException("can't generate such a flag.");
    }
  }

  /**
   * Help function: Draw a flag of Greece with the given ratio. The flag's size should be height :
   * width = 2 : 3; there are nine equal horizontal stripes of blue alternating with white and a
   * blue canton in the upper hoist-side corner bearing a white cross. User should specify the ratio
   * of the image size to the actual minimum flag size.
   *
   * @param ratio the given ratio of the flag
   * @return the 3D array of the generated flag of Greece
   */
  private static int[][][] greeceFlagGenerator(int ratio) {
    // Get the appropriate height according to the width
    int height = 18 * ratio;
    int width = 27 * ratio;
    int[][][] result = new int[height][width][3];

    Color greeceBlue = new Color(13, 94, 175);
    Color greeceWhite = new Color(255, 255, 255);

    //Fill nine equal horizontal stripes with blue and white alternatively.
    int stripeHeight = height / 9;
    for (int j = 0; j < width; j++) {
      for (int i = 0; i < height; i++) {
        int stripeIndex = i / stripeHeight;
        if (stripeIndex % 2 == 0) {
          fillColor(result, i, j, greeceBlue);
        } else {
          fillColor(result, i, j, greeceWhite);
        }
      }
    }

    // The size of the canton : width of the flag = 10 : 27
    int squareSize = width / 27 * 10;
    for (int j = 0; j < squareSize; j++) {
      for (int i = 0; i < squareSize; i++) {

        // Fill the cross in the center of the canton with white.
        if ((squareSize * 0.4 <= j && j < squareSize * 0.6)
                || (squareSize * 0.4 <= i && i < squareSize * 0.6)) {
          fillColor(result, i, j, greeceWhite);
        }
        // Fill the rest of the canton with blue.
        else {
          fillColor(result, i, j, greeceBlue);
        }
      }
    }


    return result;
  }

  /**
   * Help function: Determine whether the pixel should be a part of the stripe in the cross.
   *
   * @param width the width of the flag
   * @param i     the row of the pixel
   * @param j     the column of the pixel
   * @return true if the pixel in the cross; otherwise, return false.
   */
  private static boolean swissCrossHelper(int width, int i, int j) {
    return (width / 32.0 * 6.0 <= j && j < width / 32.0 * 26.0)
            && (width / 32.0 * 13.0 <= i && i < width / 32.0 * 19.0);
  }

  /**
   * Help function: Draw a flag of Switzerland with the given ratio, and name it as the given
   * filename. The flag of Switzerland displays a white cross in the centre of a square red field.
   * Its arms are equilateral, and their ratio of length to width is 7:6. User should specify the
   * ratio of the image size to the actual minimum flag size.
   *
   * @param ratio the given ratio of the flag
   * @return the 3D array of the generated flag of Switzerland.
   */
  private static int[][][] switzerlandFlagGenerator(int ratio) {
    int width = 32 * ratio;
    int[][][] result = new int[width][width][3];
    Color swissRed = new Color(213, 43, 30);

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < width; j++) {

        fillColor(result, i, j, swissRed);

        // When the pixel in the horizontal stripe of the cross.
        if (swissCrossHelper(width, i, j)) {
          fillColor(result, i, j, Color.WHITE);
        }
        // When the pixel in the vertical stripe of the cross.
        if (swissCrossHelper(width, j, i)) {
          fillColor(result, i, j, Color.WHITE);
        }
      }
    }

    return result;


  }
}
