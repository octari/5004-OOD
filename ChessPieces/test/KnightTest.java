import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * A JUnit test class for Knight.
 */
public class KnightTest {
  private Knight k1;

  @Before
  public void setUp() {
    k1 = new Knight(2, 3, Color.BLACK);
  }

  @Test
  public void testRow() {
    assertEquals(2, k1.getRow());
  }

  @Test
  public void testColumn() {
    assertEquals(3, k1.getColumn());
  }

  @Test
  public void testColor() {
    assertEquals(Color.BLACK, k1.getColor());
  }

  /**
   * Test when set up a Knight out of the board.
   */
  @Test
  public void testOutOfBoard() {
    try {
      ChessPiece k2 = new Knight(-1, 4, Color.WHITE);
      fail("An exception should been have thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("This position is out of range.", e.getMessage());
    }

    try {
      ChessPiece k2 = new Knight(1, 9, Color.WHITE);
      fail("An exception should been have thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("This position is out of range.", e.getMessage());
    }

    try {
      ChessPiece k2 = new Knight(1, -9, Color.WHITE);
      fail("An exception should been have thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("This position is out of range.", e.getMessage());
    }

    try {
      ChessPiece k2 = new Knight(10, 6, Color.WHITE);
      fail("An exception should been have thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("This position is out of range.", e.getMessage());
    }

  }

  @Test
  public void testCanMove() {
    assertEquals(true, k1.canMove(1, 5));
    assertEquals(true, k1.canMove(1, 1));
    assertEquals(true, k1.canMove(3, 5));
    assertEquals(true, k1.canMove(3, 1));

    assertEquals(true, k1.canMove(0, 4));
    assertEquals(true, k1.canMove(0, 2));
    assertEquals(true, k1.canMove(4, 4));
    assertEquals(true, k1.canMove(4, 2));

  }

  @Test
  public void testCantMove() {
    assertEquals(false, k1.canMove(1, 4));
    assertEquals(false, k1.canMove(0, 3));
    assertEquals(false, k1.canMove(0, 1));
    assertEquals(false, k1.canMove(2, 0));
    assertEquals(false, k1.canMove(1, 4));
    assertEquals(false, k1.canMove(5, 0));
    assertEquals(false, k1.canMove(4, 3));
    assertEquals(false, k1.canMove(4, 5));

    assertEquals(false, k1.canMove(2, 3));
  }

  /**
   * Test canMove when the moving spot is out of board.
   */
  @Test
  public void testMoveOutOfBoard() {
    assertEquals(false, k1.canMove(-1, 4));
    assertEquals(false, k1.canMove(5, -4));
    assertEquals(false, k1.canMove(14, 3));
    assertEquals(false, k1.canMove(4, 9));
  }

  @Test
  public void testCanKill() {
    ChessPiece k2 = new Knight(1, 5, Color.WHITE);
    assertEquals(true, k1.canKill(k2));

    ChessPiece k3 = new Knight(1, 1, Color.WHITE);
    assertEquals(true, k1.canKill(k3));

    ChessPiece k4 = new Knight(3, 5, Color.WHITE);
    assertEquals(true, k1.canKill(k4));

    ChessPiece k5 = new Knight(3, 1, Color.WHITE);
    assertEquals(true, k1.canKill(k5));

    ChessPiece k6 = new Knight(0, 4, Color.WHITE);
    assertEquals(true, k1.canKill(k6));

    ChessPiece k7 = new Knight(0, 2, Color.WHITE);
    assertEquals(true, k1.canKill(k7));

    ChessPiece k8 = new Knight(4, 4, Color.WHITE);
    assertEquals(true, k1.canKill(k8));

    ChessPiece k9 = new Knight(4, 2, Color.WHITE);
    assertEquals(true, k1.canKill(k9));

  }

  /**
   * Test canKill when the to be killed piece is in the same color with the Knight.
   */
  @Test
  public void testKillColor() {
    ChessPiece k2 = new Knight(1, 5, Color.BLACK);
    assertEquals(false, k1.canKill(k2));

    ChessPiece k3 = new Knight(1, 1, Color.BLACK);
    assertEquals(false, k1.canKill(k3));

    ChessPiece k4 = new Knight(3, 5, Color.BLACK);
    assertEquals(false, k1.canKill(k4));

    ChessPiece k5 = new Knight(3, 1, Color.BLACK);
    assertEquals(false, k1.canKill(k5));

    ChessPiece k6 = new Knight(0, 4, Color.BLACK);
    assertEquals(false, k1.canKill(k6));

    ChessPiece k7 = new Knight(0, 2, Color.BLACK);
    assertEquals(false, k1.canKill(k7));

    ChessPiece k8 = new Knight(4, 4, Color.BLACK);
    assertEquals(false, k1.canKill(k8));

    ChessPiece k9 = new Knight(4, 2, Color.BLACK);
    assertEquals(false, k1.canKill(k9));

  }

  /**
   * Test method canKill when the piece to be killed is in the place the Knight can't reach.
   */
  @Test
  public void testKillMove() {
    ChessPiece k2 = new Knight(0, 0, Color.WHITE);
    assertEquals(false, k1.canKill(k2));

    ChessPiece k3 = new Knight(3, 2, Color.WHITE);
    assertEquals(false, k1.canKill(k3));

    ChessPiece k4 = new Knight(4, 5, Color.WHITE);
    assertEquals(false, k1.canKill(k4));

    ChessPiece k5 = new Knight(6, 1, Color.WHITE);
    assertEquals(false, k1.canKill(k5));
  }

  /**
   * Test the consistence between methods canMove and canKill, when the two pieces have different
   * colors.
   */
  @Test
  public void testConsistence() {
    ChessPiece k2 = new Knight(1, 5, Color.WHITE);
    if (k1.canMove(k2.getRow(), k2.getColumn())) {
      assertEquals(true, k1.canKill(k2));
    } else {
      assertEquals(false, k1.canKill(k2));
    }

    if (k1.canKill(k2)) {
      assertEquals(true, k1.canMove(k2.getRow(), k2.getColumn()));
    } else {
      assertEquals(false, k1.canMove(k2.getRow(), k2.getColumn()));
    }

    ChessPiece b3 = new Bishop(3, 2, Color.WHITE);
    if (k1.canMove(b3.getRow(), b3.getColumn())) {
      assertEquals(true, k1.canKill(b3));
    } else {
      assertEquals(false, k1.canKill(b3));
    }

    if (k1.canKill(b3)) {
      assertEquals(true, k1.canMove(b3.getRow(), b3.getColumn()));
    } else {
      assertEquals(false, k1.canMove(b3.getRow(), b3.getColumn()));
    }
  }

}