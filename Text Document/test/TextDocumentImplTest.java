import org.junit.Before;
import org.junit.Test;


import document.TextDocumentImpl;

import static org.junit.Assert.assertEquals;

/**
 * JUnit test for TextDocumentImpl class.
 */
public class TextDocumentImplTest {
  private TextDocumentImpl case1;
  private TextDocumentImpl case3;
  private TextDocumentImpl case4;
  private TextDocumentImpl case5;
  private TextDocumentImpl case6;
  private TextDocumentImpl emptyCase;

  @Before
  public void setUp() {
    case1 = new TextDocumentImpl(
            "so that all line widths are within the specified threshold. "
                    + "Technically the output of text wrapping is text that");

    case3 = new TextDocumentImpl("a b c");
    case4 = new TextDocumentImpl("aaa nthespecifiedthreshold");
    case5 = new TextDocumentImpl("aaaaaaaaaa bbbbb    ccccccccc ddddddd ");
    case6 = new TextDocumentImpl("aaaaa. bbbbb. ccccc. ddddd,");

    emptyCase = new TextDocumentImpl("");


  }

  @Test
  public void testException() {
    try {
      case1.wrap(0);
    } catch (IllegalArgumentException e) {
      assertEquals("Column width should be greater than 0.", e.getMessage());
    }
  }

  /**
   * Test when the column width is 1.
   */
  @Test
  public void testWidth1() {
    // Test when there is a word longer than 1 character(punctuation included.)
    try {
      case1.wrap(1);
    } catch (IllegalArgumentException e) {
      assertEquals("Column width should be greater than 1.", e.getMessage());
    }

    try {
      TextDocumentImpl testcase = new TextDocumentImpl("a b, c");
      testcase.wrap(1);
    } catch (IllegalArgumentException e) {
      assertEquals("Column width should be greater than 1.", e.getMessage());
    }

    // Test when the text is empty.
    assertEquals("", emptyCase.wrap(1).getText());

    // Test when all the words in the text have only one character.
    assertEquals(3, case3.getWordCount());
    assertEquals("a b c", case3.getText());
    assertEquals("a\nb\nc", case3.wrap(1).getText());
  }


  /**
   * Test when there is no text.
   */
  @Test
  public void testEmpty() {
    assertEquals(0, emptyCase.getWordCount());
    assertEquals("", emptyCase.getText());
    assertEquals("", emptyCase.wrap(2).getText());
  }

  /**
   * Test when the total size of text is less than the column width.
   */
  @Test
  public void testOneLineShorter() {
    assertEquals("a b c", case3.wrap(10).getText());
  }

  /**
   * Test when the length of words in the text are the same as the column width.
   */
  @Test
  public void testMultipleLines() {
    assertEquals(4, case6.getWordCount());
    assertEquals("aaaaa.\n"
            + "bbbbb.\n"
            + "ccccc.\n"
            + "ddddd,", case6.wrap(6).getText());
  }

  /**
   * Test when it need a "-" to connect separate parts of a word in 2 lines.
   */
  @Test
  public void testHyphens() {
    assertEquals(19, case1.getWordCount());
    assertEquals("so that all line widths are w-\n"
            + "ithin the specified threshold.\n"
            + "Technically the output of text\n"
            + "wrapping is text that", case1.wrap(30).getText());

    assertEquals("aaaa-\na. b-\n" +
            "bbbb.\n" + "cccc-\n" + "c. d-\n" + "dddd,", case6.wrap(5).getText());

  }

  /**
   * Test when need to add a fit space at the end (i.e. the line size is 1 less than the column
   * width)
   */
  @Test
  public void testFitSpace() {
    assertEquals("aaaaa. \n" + "bbbbb. \n" + "ccccc. \n"
            + "ddddd,", case6.wrap(7).getText());


    TextDocumentImpl case7 = new TextDocumentImpl("aaaaa. b ccccc. ddddd,");
    assertEquals("aaaaa. b\n" + "ccccc. \n"
            + "ddddd,", case7.wrap(8).getText());

  }

  @Test
  public void testCantAddHyphen() {
    assertEquals("aaaaa. \n" + "bbbbb. \n" + "ccccc. \n"
            + "ddddd,", case6.wrap(8).getText());
  }

  /**
   * Test when a word's length is greater than several lines.
   */
  @Test
  public void testOneWordMultipleLines() {
    // Only one lines-long in the text.
    TextDocumentImpl oneWord = new TextDocumentImpl("aaaaaaaaaaaaaaaaaa");
    assertEquals(1, oneWord.getWordCount());
    assertEquals("aaaaaaa-\n" + "aaaaaaa-\n"
            + "aaaa", oneWord.wrap(8).getText());

    // There is a lines-long word in the text.
    assertEquals("aaa \n" + "nth-\n" + "esp-\n" + "eci-\n" + "fie-\n"
            + "dth-\n" + "res-\n" + "hold", case4.wrap(4).getText());

    // Every word in the text is lines-long.
    assertEquals("a-\n" + "a-\n" + "a-\n" + "a-\n" + "a-\n" + "a-\n" + "a-\n"
            + "a-\n" + "aa\n" + "b-\n" + "b-\n" + "b-\n" + "bb\n" + "c-\n"
            + "c-\n" + "c-\n" + "c-\n" + "c-\n" + "c-\n" + "c-\n" + "cc\n" + "d-\n"
            + "d-\n" + "d-\n" + "d-\n" + "d-\n" + "dd", case5.wrap(2).getText());
  }


  /**
   * Test when there are multiple spaces in the text.
   */
  @Test
  public void testMultipleSpaces() {
    assertEquals(4, case5.getWordCount());
    assertEquals("aaaaaaaaaa\n" + "bbbbb ccc-\n" + "cccccc dd-\n"
            + "ddddd", case5.wrap(10).getText());
  }

  @Test
  public void testCounter() {
    TextDocumentImpl soloPunctuation = new TextDocumentImpl(".");
    assertEquals(1, soloPunctuation.getWordCount());

    // Punctuations go along with words.
    assertEquals(4, case6.getWordCount());

  }

}