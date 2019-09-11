import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * A JUnit test class for Rook.
 */
public class RookTest {
  private ChessPiece r1;

  @Before
  public void setUp() {
    r1 = new Rook(3, 5, Color.BLACK);
  }

  @Test
  public void testRow() {
    assertEquals(3, r1.getRow());
  }

  @Test
  public void testColumn() {
    assertEquals(5, r1.getColumn());
  }

  @Test
  public void testColor() {
    assertEquals(Color.BLACK, r1.getColor());
  }

  /**
   * Test when set up a Rook object out of the board.
   */
  @Test
  public void testOutOfBoard() {
    try {
      ChessPiece q2 = new Rook(-1, 4, Color.WHITE);
      fail("An exception should been have thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("This position is out of range.", e.getMessage());
    }

    try {
      ChessPiece q2 = new Rook(1, -4, Color.WHITE);
      fail("An exception should been have thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("This position is out of range.", e.getMessage());
    }
    try {
      ChessPiece q2 = new Rook(9, 4, Color.WHITE);
      fail("An exception should been have thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("This position is out of range.", e.getMessage());
    }

    try {
      ChessPiece q2 = new Rook(1, 8, Color.WHITE);
      fail("An exception should been have thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("This position is out of range.", e.getMessage());
    }
  }

  @Test
  public void testCanMove() {
    assertEquals(true, r1.canMove(3, 7));
    assertEquals(true, r1.canMove(0, 5));
    assertEquals(true, r1.canMove(3, 4));
    assertEquals(true, r1.canMove(7, 5));
  }

  @Test
  public void testCantMove() {
    assertEquals(false, r1.canMove(4, 4));
    assertEquals(false, r1.canMove(4, 7));
    assertEquals(false, r1.canMove(1, 6));
    assertEquals(false, r1.canMove(1, 3));

    assertEquals(false, r1.canMove(3, 5));
  }

  /**
   * Test method canMove when the position is out of board.
   */
  @Test
  public void testMoveOutOfBoard() {
    assertEquals(false, r1.canMove(3, -1));
    assertEquals(false, r1.canMove(3, 9));
    assertEquals(false, r1.canMove(-1, 5));
    assertEquals(false, r1.canMove(10, 5));
  }

  @Test
  public void testCanKill() {
    ChessPiece r2 = new Rook(5, 5, Color.WHITE);
    assertEquals(true, r1.canKill(r2));

    ChessPiece r3 = new Queen(3, 6, Color.WHITE);
    assertEquals(true, r1.canKill(r3));

    ChessPiece r4 = new Knight(0, 5, Color.WHITE);
    assertEquals(true, r1.canKill(r4));

    ChessPiece r5 = new Bishop(3, 1, Color.WHITE);
    assertEquals(true, r1.canKill(r5));
  }

  /**
   * Test method canKill when the piece to be killed is in the same color as the Rook.
   */
  @Test
  public void testKillColor() {
    ChessPiece r2 = new Rook(5, 5, Color.BLACK);
    assertEquals(false, r1.canKill(r2));

    ChessPiece r3 = new Queen(3, 6, Color.BLACK);
    assertEquals(false, r1.canKill(r3));

    ChessPiece r4 = new Knight(0, 5, Color.BLACK);
    assertEquals(false, r1.canKill(r4));

    ChessPiece r5 = new Bishop(3, 1, Color.BLACK);
    assertEquals(false, r1.canKill(r5));
  }

  /**
   * Test method canKill when the piece is in a place the Rook can't reach.
   */
  @Test
  public void testKillMove() {
    ChessPiece r2 = new Rook(5, 3, Color.WHITE);
    assertEquals(false, r1.canKill(r2));

    ChessPiece r3 = new Queen(4, 6, Color.WHITE);
    assertEquals(false, r1.canKill(r3));

    ChessPiece r4 = new Knight(1, 4, Color.WHITE);
    assertEquals(false, r1.canKill(r4));

    ChessPiece r5 = new Bishop(2, 1, Color.WHITE);
    assertEquals(false, r1.canKill(r5));
  }

  /**
   * Test the consistence between methods canMove and canKill, when the two pieces have different
   * colors.
   */
  @Test
  public void testConsistence() {
    ChessPiece r2 = new Rook(5, 5, Color.WHITE);
    if (r1.canKill(r2)) {
      assertEquals(true, r1.canMove(r2.getRow(), r2.getColumn()));
    } else {
      assertEquals(false, r1.canMove(r2.getRow(), r2.getColumn()));
    }

    ChessPiece r3 = new Queen(5, 7, Color.WHITE);
    if (r1.canKill(r3)) {
      assertEquals(true, r1.canMove(r3.getRow(), r3.getColumn()));
    } else {
      assertEquals(false, r1.canMove(r3.getRow(), r3.getColumn()));
    }

    ChessPiece r4 = new Knight(0, 5, Color.WHITE);
    if (r1.canMove(r4.getRow(), r4.getColumn())) {
      assertEquals(true, r1.canKill(r4));
    } else {
      assertEquals(false, r1.canKill(r4));
    }

    ChessPiece r5 = new Bishop(2, 1, Color.WHITE);
    if (r1.canMove(r5.getRow(), r5.getColumn())) {
      assertEquals(true, r1.canKill(r5));
    } else {
      assertEquals(false, r1.canKill(r5));
    }
  }


}