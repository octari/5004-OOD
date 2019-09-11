import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


/**
 * A JUnit test class for the Interface Sentence, WordNode class, PunctuationNode class and
 * EmptyNode class.
 */
public class SentenceTest {
  Sentence case1;
  Sentence case2;
  Sentence testCase1;
  Sentence testCase2;
  Sentence case3;
  Sentence case4;

  @Before
  public void setUp() {

    case1 = new WordNode("Hello", new WordNode("world",
            new PunctuationNode(",",
                    new WordNode("asds", new WordNode("dkhsad", new EmptyNode())))));

    case2 = new WordNode("a", new WordNode("bc",
            new PunctuationNode(",",
                    new WordNode("def", new WordNode("ghij",
                            new PunctuationNode("!", new EmptyNode()))))));
    testCase1 = new PunctuationNode(",",
            new PunctuationNode(",",
                    new PunctuationNode(",",
                            new PunctuationNode(",",
                                    new PunctuationNode(".", new EmptyNode())))));

    testCase2 = new EmptyNode();
    case3 = new WordNode("Hezllo", new WordNode("world",
            new PunctuationNode(",",
                    new WordNode("asdzs", new WordNode("dkhsad", new EmptyNode())))));

    case4 = new WordNode("a", new WordNode("bc",
            new PunctuationNode(",",
                    new WordNode("def", new WordNode("ghij",
                            new PunctuationNode("!", new EmptyNode()))))));
  }

  @Test
  public void testCounter() {
    assertEquals(4, case1.getNumberOfWords());

    assertEquals(0, testCase1.getNumberOfWords());

    assertEquals(0, testCase2.getNumberOfWords());
  }


  @Test
  public void testLongest() {
    assertEquals("dkhsad", case1.longestWord());

    assertEquals("", testCase1.longestWord());

    assertEquals("", testCase2.longestWord());


  }

  @Test
  public void testToString() {
    assertEquals("Hello world, asds dkhsad.", case1.toString());

    // There is no period in the end of first sentence.
    // This means that method toString didn't add the period to the original sentence
    assertEquals("Hello world, asds dkhsad a bc, def ghij!",
            case1.merge(case2).toString());

    assertEquals("a bc, def ghij!", case2.toString());
    assertEquals(", , , , .", testCase1.toString());
    assertEquals("", testCase2.toString());
  }

  @Test
  public void testClone() {
    assertEquals("Hello world, asds dkhsad.", case1.clone().toString());
    Sentence testCase1 = case1.clone();
    assertFalse(testCase1 == case1);

    assertEquals("a bc, def ghij!", case2.clone().toString());
    assertFalse(case2 == case2.clone());
  }


  @Test
  public void testMerge() {

    assertEquals("Hello world, asds dkhsad a bc, def ghij!",
            case1.merge(case2).toString());
    assertEquals("a bc, def ghij! Hello world, asds dkhsad.",
            case2.merge(case1).toString());

    assertEquals("Hello world, asds dkhsad.", case1.toString());
    assertEquals("a bc, def ghij!", case2.toString());
  }

  @Test
  public void testCountPunc() {
    assertEquals(2, case2.fold(s -> s.countPuncHelper()));
    assertEquals(1, case1.fold(s -> s.countPuncHelper()));
    assertEquals(5, testCase1.fold(s -> s.countPuncHelper()));
    assertEquals(0, testCase2.fold(s -> s.countPuncHelper()));
  }

  @Test
  public void testCountZ() {
    assertEquals(2, case3.fold(s -> s.countHelper()));
    assertEquals(0, case4.fold(s -> s.countHelper()));
    assertEquals(0, testCase1.fold(s -> s.countHelper()));
  }

  @Test
  public void testCode() {
    Sentence a = new WordNode("MAKING", new WordNode("A", new WordNode("PIG",
            new WordNode("DEAL", new WordNode("ABOUT", new WordNode("PIG",
                    new WordNode("LATIN", new EmptyNode())))))));
    assertEquals("AKINGMAY AWAY IGPAY EALDAY ABOUTWAY IGPAY ATINLAY.",
            a.code().toString());
  }

}