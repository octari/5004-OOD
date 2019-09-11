import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * A JUnit test for the Likert class.
 */
public class LikertTest {
  private Question case3;

  @Before
  public void setUp() {
    case3 = new Likert("You like NEU.", TypeOfStatus.Required);
  }

  @Test
  public void testType() {
    assertEquals("Likert", case3.getQuestionType());
  }

  @Test
  public void testText() {
    assertEquals("You like NEU.", case3.getQuestionText());
  }

  @Test
  public void testStatus() {
    assertEquals(TypeOfStatus.Required, case3.getQuestionStatus());
  }

  @Test
  public void testAnswer() {
    case3.answer("Strongly Agree");
    assertEquals("Strongly Agree", case3.getAnswer());

    case3.answer("Agree");
    assertEquals("Agree", case3.getAnswer());

    case3.answer("Neither Agree nor Disagree");
    assertEquals("Neither Agree nor Disagree", case3.getAnswer());

    case3.answer("Disagree");
    assertEquals("Disagree", case3.getAnswer());

    case3.answer("Strongly Disagree");
    assertEquals("Strongly Disagree", case3.getAnswer());

  }

  @Test
  public void testInvalidAnswer() {
    try {
      case3.answer("Yes");
      fail("Exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid answer.", e.getMessage());
    }
  }
}