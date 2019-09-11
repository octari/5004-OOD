package model;

/**
 * Help class. This class represents a image processor, it can support blurring, sharpening, grey
 * scale and sepia tone operations.
 */
public class ImageProcessor {
  private int[][][] imageArray;
  private int height;
  private int width;

  /**
   * Construct an image processor used to process the given image.
   */
  public ImageProcessor(int[][][] imageArray, int height, int width) {
    this.imageArray = imageArray;
    this.height = height;
    this.width = width;
  }


  /**
   * Blur the image using the blurring filter.
   *
   * @return the blurred image
   */
  public int[][][] blur() {
    HelpMatrix filter = setBlurFilter();
    return filter.filterImage(imageArray, height, width);
  }

  /**
   * Help function: Set up the blurring filter.
   *
   * @return the the blurring filter.
   */
  private HelpMatrix setBlurFilter() {
    HelpMatrix filter = new HelpMatrixImpl(3, 3);
    filter.set(0, 0, (double) 1 / 16);
    filter.set(0, 2, (double) 1 / 16);
    filter.set(2, 0, (double) 1 / 16);
    filter.set(2, 2, (double) 1 / 16);

    filter.set(0, 1, (double) 1 / 8);
    filter.set(2, 1, (double) 1 / 8);
    filter.set(1, 2, (double) 1 / 8);
    filter.set(1, 0, (double) 1 / 8);

    filter.set(1, 1, (double) 1 / 4);

    return filter;
  }


  /**
   * Sharpen the image using the sharpening filter.
   *
   * @return the sharpened image
   */
  public int[][][] sharpening() {
    HelpMatrix filter = setSharpeningFilter();
    return filter.filterImage(imageArray, height, width);
  }

  /**
   * Help function: set up the sharpening filter.
   *
   * @return the sharpening filter.
   */
  private HelpMatrix setSharpeningFilter() {
    HelpMatrix filter = new HelpMatrixImpl(5, 5);
    for (int i = 0; i < 5; i++) {
      filter.set(0, i, (double) -1 / 8);
      filter.set(4, i, (double) -1 / 8);
      filter.set(i, 0, (double) -1 / 8);
      filter.set(i, 4, (double) -1 / 8);
    }
    for (int k = 1; k < 4; k++) {
      filter.set(1, k, (double) 1 / 4);
      filter.set(3, k, (double) 1 / 4);
      filter.set(k, 1, (double) 1 / 4);
      filter.set(k, 3, (double) 1 / 4);
    }

    filter.set(2, 2, 1);
    return filter;
  }


  /**
   * Convert a color image into a greyscale image using the grey scale transformation.
   *
   * @return the converted greyscale image.
   */
  public int[][][] greyScale() {
    HelpMatrixImpl transformation = setGreyscaleTransformation();

    return transformation.transformImage(imageArray, height, width);
  }

  /**
   * Help function: Set up the color transformation matrix for greyscale.
   *
   * @return the color transformation matrix for greyscale.
   */
  private HelpMatrixImpl setGreyscaleTransformation() {
    HelpMatrixImpl transformation = new HelpMatrixImpl(3, 3);
    for (int i = 0; i < 3; i++) {
      transformation.set(i, 0, 0.2126);
      transformation.set(i, 1, 0.7152);
      transformation.set(i, 2, 0.0722);
    }
    return transformation;
  }

  /**
   * Convert a color image into a sepia tone image using the sepia tone transformation.
   *
   * @return the converted sepia tone image.
   */
  public int[][][] sepiaTone() {
    HelpMatrixImpl transformation = setSepiaToneTransformation();

    return transformation.transformImage(imageArray, height, width);
  }

  /**
   * Help function: Set up the color transformation matrix for sepia tone.
   *
   * @return the color transformation matrix for sepia tone.
   */
  private HelpMatrixImpl setSepiaToneTransformation() {
    HelpMatrixImpl transformation = new HelpMatrixImpl(3, 3);
    transformation.set(0, 0, 0.393);
    transformation.set(1, 0, 0.349);
    transformation.set(2, 0, 0.272);

    transformation.set(0, 1, 0.769);
    transformation.set(1, 1, 0.686);
    transformation.set(2, 1, 0.534);

    transformation.set(0, 2, 0.189);
    transformation.set(1, 2, 0.168);
    transformation.set(2, 2, 0.131);

    return transformation;
  }

  /**
   * Dither the image with the help of dithering matrix. The image should be greyscale first.
   *
   * @return the 3D array of the dithered image
   */
  public int[][][] dithering() {
    HelpMatrixImpl transformation = setDitheringTransformation();
    //We first convert our image to greyscale.
    return transformation.transformDitheringImage(greyScale(), height, width);
  }


  /**
   * Help function: set up a matrix used to dither the image.
   *
   * @return the dithering matrix.
   */
  private HelpMatrixImpl setDitheringTransformation() {
    HelpMatrixImpl transformation = new HelpMatrixImpl(3, 3);
    transformation.set(0, 0, 0);
    transformation.set(0, 1, 0);
    transformation.set(0, 2, 0);

    transformation.set(1, 0, 0);
    transformation.set(1, 1, 0);
    transformation.set(1, 2, (double) 7 / 16);

    transformation.set(2, 0, (double) 3 / 16);
    transformation.set(2, 1, (double) 5 / 16);
    transformation.set(2, 2, (double) 1 / 16);

    return transformation;

  }

  /**
   * Mosaic the image with the given number of seeds using the seed assignment matrix.
   *
   * @param seedNum the number of the seeds
   * @return the 3D array of the mosaic image
   */
  public int[][][] mosaicing(int seedNum) {
    HelpMatrixImpl transformation = setMosaicTransformation(seedNum);
    return transformation.transformMosaicImage(imageArray, seedNum, height, width);
  }


  /**
   * Help function: Set up a cluster assignment matrix. The matrix is in the same size as the image.
   * In the image every pixel has an array of rgb. But in the matrix, every element is the index of
   * the cluster the pixel of the image in the same position.
   *
   * @param seedNum the number of the clusters.
   * @return the cluster assignment matrix
   */
  private HelpMatrixImpl setMosaicTransformation(int seedNum) {
    int w = width;
    int h = height;
    HelpMatrixImpl transformation = new HelpMatrixImpl(w, h);
    int[][] centers = clusterCenter(seedNum);

    for (int i = 0; i < h; i++) {
      for (int j = 0; j < w; j++) {
        double[] dis = distancesToCenters(i, j, centers, seedNum);
        int index = getMinIndex(dis, seedNum);
        transformation.set(i, j, index);
      }
    }

    return transformation;
  }

  /**
   * Help function: Generate centers of clusters randomly within the range of the image. Return a 2D
   * array of index of clusters and centers' position. Duplicate seeds is allowed.
   *
   * @param seedNum the number of clusters
   * @return 2D array of clusters index and centers' position. array[cluster index][height, width].
   */
  private int[][] clusterCenter(int seedNum) {
    int[][] centers = new int[seedNum][2];
    int height = this.height;
    int width = this.width;
    for (int i = 0; i < seedNum; i++) {
      centers[i][0] = (int) (Math.random() * height);
      centers[i][1] = (int) (Math.random() * width);
    }
    return centers;
  }

  /**
   * Help function: Calculate the distance between the pixel and a center of cluster.
   *
   * @param i      the height of the pixel
   * @param j      the width of the pixel
   * @param center array of the center's position. array[height][width]
   * @return the distance between the pixel and the center.
   */
  private double distance(int i, int j, int[] center) {
    return Math.sqrt((i - center[0]) * (i - center[0])
            + (j - center[1]) * (j - center[1]));

  }

  /**
   * Help function: Return an array of distances from the pixel to all the centers.
   *
   * @param h       the height of the pixel
   * @param w       the width of the pixel
   * @param centers the 2D array of the index of cluster the center belongs to and the center's
   *                position. array[index][height, width].
   * @param seedNum the number of clusters
   * @return an array of distances from the pixel to all the centers.
   */
  private double[] distancesToCenters(int h, int w, int[][] centers, int seedNum) {
    double[] result = new double[seedNum];
    for (int i = 0; i < seedNum; i++) {
      result[i] = distance(h, w, centers[i]);
    }
    return result;
  }

  /**
   * Help function: Find out which cluster the pixel should be in by picking the index minimum in
   * the distances array to centers. The index of minimum should be the index of cluster.
   *
   * @param disToC  the array of distances from the pixel to the centers.
   * @param seedNum the number of clusters
   * @return the index of the cluster the pixel should be.
   */
  private int getMinIndex(double[] disToC, int seedNum) {
    double min = Integer.MAX_VALUE;
    int index = 0;
    for (int i = 0; i < seedNum; i++) {
      if (disToC[i] < min) {
        min = disToC[i];
        index = i;
      }
    }
    return index;
  }


}
