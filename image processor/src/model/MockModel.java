package model;

import java.util.EmptyStackException;

import model.ImageGenerator;

/**
 * This class is a mock of model.ImageImpl used to test the the controller.
 */
public class MockModel implements Model {
  private StringBuilder out;

  public MockModel(StringBuilder out) {
    this.out = out;
  }

  @Override
  public int[][][] generateChecker(int size) {
    out.append("checkerboard ").append(size).append("\n");
    return ImageGenerator.checkerBoardGenerator(size);
  }

  @Override
  public int[][][] generateRainbow(int height, int width, VOrH vOrH) {
    out.append("rainbow ").append("height: ").append(height).append(" width: ").append(width)
            .append(" ").append(vOrH.toString()).append("\n");
    return ImageGenerator.rainbowStripesGenerator(height, width, vOrH);
  }

  @Override
  public int[][][] generateFlags(int ratio, String country) {
    out.append("flag ").append(country).append(" ").append("ratio: ").append(ratio).append("\n");
    return ImageGenerator.flagsGenerator(ratio, country);
  }

  @Override
  public int[][][] blurImage(int[][][] imageArray, int height, int width) {
    out.append("blur\n");
    return imageArray;
  }

  @Override
  public int[][][] sharpenImage(int[][][] imageArray, int height, int width) {
    out.append("sharpen\n");
    return imageArray;
  }

  @Override
  public int[][][] greyscaleImage(int[][][] imageArray, int height, int width) {
    out.append("greyscale\n");
    return imageArray;
  }

  @Override
  public int[][][] sepiaToneImage(int[][][] imageArray, int height, int width) {
    out.append("sepia-tone\n");
    return imageArray;
  }

  @Override
  public int[][][] ditheringImage(int[][][] imageArray, int height, int width) {
    out.append("dither\n");
    return imageArray;
  }

  @Override
  public int[][][] mosaicingImage(int[][][] imageArray, int height, int width, int seedNum) {
    out.append("mosaic ").append(seedNum).append("\n");
    return imageArray;
  }

  @Override
  public int[][][] undo() throws EmptyStackException {
    return new int[0][][];
  }

  @Override
  public int[][][] redo() throws EmptyStackException {
    return new int[0][][];
  }

  @Override
  public void setStack(int[][][] add) {

  }
}
