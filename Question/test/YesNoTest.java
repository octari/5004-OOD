import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * The JUnit test class for the YesNo class.
 */
public class YesNoTest {
  private Question case1;
  private Question case2;

  @Before
  public void setUp() {
    case1 = new YesNo("1 == 1?", TypeOfStatus.Required);
    case2 = new YesNo("1 == 2?", TypeOfStatus.Optional);
  }

  @Test
  public void testType() {
    assertEquals("Yes/No", case1.getQuestionType());
    assertEquals("Yes/No", case2.getQuestionType());
  }

  @Test
  public void testStatus() {
    assertEquals(TypeOfStatus.Required, case1.getQuestionStatus());
    assertEquals(TypeOfStatus.Optional, case2.getQuestionStatus());
  }

  @Test
  public void testText() {
    assertEquals("1 == 1?", case1.getQuestionText());
    assertEquals("1 == 2?", case2.getQuestionText());
  }

  @Test
  public void testAnswer() {
    case1.answer("Yes");
    assertEquals("Yes", case1.getAnswer());

    case2.answer("No");
    assertEquals("No", case2.getAnswer());
  }

  @Test
  public void testInvalidAnswer() {
    try {
      case1.answer("aa");
      fail("Exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid answer.", e.getMessage());
    }

  }
}