import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import controller.ScriptController;
import model.MockModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

/**
 * JUnit test for controller to parse the command and ues the model to execute teh command
 * properly.
 */
public class ScriptControllerTest {
  private ScriptController controller;
  private StringBuilder result;
  private ScriptController controllerExecute;

  @Before
  public void setUp() throws FileNotFoundException {
    result = new StringBuilder();
    controller = new ScriptController(new MockModel(result),
            new InputStreamReader(new FileInputStream("input1.txt")));
    controllerExecute = new ScriptController(new MockModel(result),
            new InputStreamReader(new FileInputStream("input1.txt")));
  }

  @Test
  public void parseCommand() {
    String[] result1 = {"load", "test.png"};
    String c1 = "load test.png";
    assertArrayEquals(result1, controller.parseCommand(c1));

    String[] result2 = {"load", "test.png"};
    String c2 = "  load    test.png  ";
    assertArrayEquals(result2, controller.parseCommand(c2));

    String[] result3 = {"blur", "test.png", "123", "456", "s"};
    String c3 = "blur   test.png 123 456   s";
    assertArrayEquals(result3, controller.parseCommand(c3));
  }

  @Test
  public void executeCommands() {
    controller.executeCommands();
    assertEquals("rainbow height: 200 width: 300 vertical\n" +
            "rainbow height: 400 width: 400 vertical\n" +
            "flag Greece ratio: 8\n" +
            "blur\n", result.toString());


  }

  @Test
  public void executeCommands1() {

    controllerExecute.executeCommands();
    assertEquals("checkerboard 10\n" +
            "mosaic 1000\n" +
            "mosaic 800\n" +
            "sepia-tone\n" +
            "sepia-tone\n", result.toString());
  }
}