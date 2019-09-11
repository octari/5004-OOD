import org.junit.Before;
import org.junit.Test;

import bst.BST;
import bst.BSTImpl;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * the Test of BSTImpl class.
 */

public class BSTImplTest {
  private BST<Integer> testCase1;
  private BST<Double> testCase2;
  private BST<String> testCase3;

  @Before
  public void setUp() {
    testCase1 = new BSTImpl<>();
    testCase2 = new BSTImpl<>();
    testCase3 = new BSTImpl<>();
  }

  @Test
  public void testInteger() {
    assertEquals(0, testCase1.getSize());
    assertEquals(null, testCase1.minimum());
    assertEquals(0, testCase1.rank(3));
    assertEquals(null, testCase1.select(6));

    testCase1.add(7);
    testCase1.add(3);
    testCase1.add(1);
    testCase1.add(10);
    testCase1.add(8);
    testCase1.add(12);
    testCase1.add(9);
    testCase1.add(0);
    testCase1.add(-10);

    testCase1.add(0);

    assertTrue(testCase1.present(-10));
    assertTrue(testCase1.present(7));
    assertFalse(testCase1.present(233));
    assertEquals(9, testCase1.getSize());
    assertEquals(-10, (int) testCase1.minimum());
    assertEquals(4, testCase1.rank(3));
    assertEquals(3, (int) testCase1.select(4));
  }

  @Test
  public void testDouble() {
    assertEquals(0, testCase2.getSize());
    assertEquals(null, testCase2.minimum());
    assertEquals(0, testCase2.rank(3.12));
    assertEquals(null, testCase2.select(6));

    testCase2.add(7.1);
    testCase2.add(3.12);
    testCase2.add(1.123);
    testCase2.add(10.2333);
    testCase2.add(8.0);
    testCase2.add(12.00);
    testCase2.add(9.000);
    testCase2.add(0.0);
    testCase2.add(-10.0);

    testCase2.add(9.000);

    assertTrue(testCase2.present(-10.0));
    assertTrue(testCase2.present(7.1));
    assertFalse(testCase2.present(233.333));
    assertEquals(9, testCase2.getSize());
    assertEquals(-10.0, testCase2.minimum(), 0.001);
    assertEquals(4, testCase2.rank(3.12));
    assertEquals(8, testCase2.select(6), 0.001);
  }

  @Test
  public void testString() {
    assertEquals(0, testCase3.getSize());
    assertEquals(null, testCase3.minimum());
    assertEquals(0, testCase3.rank("a"));
    assertEquals(null, testCase3.select(4));

    testCase3.add("e");
    testCase3.add("b");
    testCase3.add("c");
    testCase3.add("d");
    testCase3.add("a");
    testCase3.add("f");
    testCase3.add("g");
    testCase3.add("h");
    testCase3.add("i");

    testCase3.add("g");

    assertTrue(testCase3.present("e"));
    assertTrue(testCase3.present("i"));
    assertFalse(testCase3.present("o"));
    assertEquals(9, testCase3.getSize());
    assertEquals("a", testCase3.minimum());
    assertEquals(1, testCase3.rank("a"));
    assertEquals("d", testCase3.select(4));
  }

}