import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * A JUnit test class for the Essay class.
 */
public class EssayTest {
  private Question case2;

  @Before
  public void setUp() {
    case2 = new Essay("Talk about your university.", TypeOfStatus.Optional);
  }

  @Test
  public void testType() {
    assertEquals("Essay-form", case2.getQuestionType());
  }

  @Test
  public void testText() {
    assertEquals("Talk about your university.", case2.getQuestionText());
  }

  @Test
  public void testStatus() {
    assertEquals(TypeOfStatus.Optional, case2.getQuestionStatus());
  }

  @Test
  public void testAnswer() {
    case2.answer("My university is NEU.");
    assertEquals("My university is NEU.", case2.getAnswer());
  }

  @Test
  public void testInvalidAnswer() {
    try {
      case2.answer("Northeastern University (NU, formerly NEU) is a private research "
              + "university in Boston, Massachusetts, established in 1898. It is categorized as an "
              + "R1 institution (Doctoral Universities: Highest Research Activity) by the Carnegie"
              + " Classification of Institutions of Higher Education.The university offers "
              + "undergraduate and graduate programs on its main campus in the Fenway-Kenmore, "
              + "Roxbury, South End, and Back Bay neighborhoods of Boston. ");
      fail("Exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("Your answer is too long.", e.getMessage());
    }
  }

}
