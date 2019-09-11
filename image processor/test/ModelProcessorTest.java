import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import controller.ImageUtil;
import model.ImageProcessor;

import static org.junit.Assert.assertArrayEquals;

/**
 * test of imageProcessor class.
 */
public class ModelProcessorTest {
  private ImageProcessor testCase;


  @Before
  public void setUp() throws IOException {
    testCase = new ImageProcessor(ImageUtil.readImage("testPic.png"),
            ImageUtil.getHeight("testPic.png"), ImageUtil.getWidth("testPic.png"));

  }


  @Test
  public void testBlur() {
    int[][][] result = testCase.blur();


    assertArrayEquals(new int[]{0, 0, 0}, result[0][0]);
    assertArrayEquals(new int[]{48, 48, 48}, result[0][3]);
    assertArrayEquals(new int[]{64, 64, 64}, result[1][3]);
    assertArrayEquals(new int[]{96, 96, 96}, result[3][3]);
    assertArrayEquals(new int[]{143, 143, 143}, result[0][4]);
    assertArrayEquals(new int[]{191, 191, 191}, result[4][1]);
    assertArrayEquals(new int[]{96, 96, 96}, result[4][4]);
  }

  @Test
  public void testSharping() {
    int[][][] result = testCase.sharpening();

    assertArrayEquals(new int[]{0, 0, 0}, result[0][0]);
    assertArrayEquals(new int[]{32, 32, 32}, result[1][3]);
    assertArrayEquals(new int[]{32, 32, 32}, result[3][1]);
    assertArrayEquals(new int[]{255, 255, 255}, result[2][5]);
    assertArrayEquals(new int[]{0, 0, 0}, result[7][4]);
  }

  @Test
  public void testGrey() {
    int[][][] result = testCase.greyScale();

    assertArrayEquals(new int[]{0, 0, 0}, result[0][0]);
    assertArrayEquals(new int[]{255, 255, 255}, result[1][5]);

  }

  @Test
  public void testSepia() {
    int[][][] result = testCase.sepiaTone();

    assertArrayEquals(new int[]{0, 0, 0}, result[0][0]);
    assertArrayEquals(new int[]{255, 255, 239}, result[1][5]);
  }


}