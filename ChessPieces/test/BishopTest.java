import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * A JUnit test class for Bishop.
 */
public class BishopTest {
  private Bishop b1;

  @Before
  public void setUp() {
    b1 = new Bishop(4, 2, Color.BLACK);
  }

  @Test
  public void testRow() {
    assertEquals(4, b1.getRow());
  }

  @Test
  public void testColumn() {
    assertEquals(2, b1.getColumn());
  }

  @Test
  public void testColor() {
    assertEquals(Color.BLACK, b1.getColor());
  }

  /**
   * Test when set up a Bishop out of the board.
   */
  @Test
  public void testOutOfBoard() {
    try {
      ChessPiece b2 = new Bishop(7, 8, Color.WHITE);
      fail("An exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("This position is out of range.", e.getMessage());
    }

    try {
      ChessPiece b2 = new Bishop(7, -8, Color.WHITE);
      fail("An exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("This position is out of range.", e.getMessage());
    }

    try {
      ChessPiece b3 = new Bishop(7, -6, Color.WHITE);
      fail("An exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("This position is out of range.", e.getMessage());
    }

    try {
      ChessPiece b3 = new Bishop(9, 6, Color.WHITE);
      fail("An exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("This position is out of range.", e.getMessage());
    }

  }

  @Test
  public void testCanMove() {
    assertEquals(true, b1.canMove(2, 4));
    assertEquals(true, b1.canMove(3, 1));
    assertEquals(true, b1.canMove(7, 5));
    assertEquals(true, b1.canMove(6, 0));
  }

  @Test
  public void testCantMove() {
    assertEquals(false, b1.canMove(3, 2));
    assertEquals(false, b1.canMove(4, 0));
    assertEquals(false, b1.canMove(7, 2));
    assertEquals(false, b1.canMove(4, 6));

    assertEquals(false, b1.canMove(4, 2));

  }

  /**
   * Test canMove when the moving spot is out of board.
   */
  @Test
  public void testMoveOutOfBoard() {
    assertEquals(false, b1.canMove(6, -1));
    assertEquals(false, b1.canMove(1, -1));
    assertEquals(false, b1.canMove(8, 6));
    assertEquals(false, b1.canMove(-1, 7));
  }

  @Test
  public void testCanKill() {
    ChessPiece b2 = new Bishop(5, 3, Color.WHITE);
    assertEquals(true, b1.canKill(b2));

    ChessPiece b3 = new Knight(2, 4, Color.WHITE);
    assertEquals(true, b1.canKill(b3));

    ChessPiece b4 = new Queen(2, 0, Color.WHITE);
    assertEquals(true, b1.canKill(b4));

    ChessPiece b5 = new Rook(5, 1, Color.WHITE);
    assertEquals(true, b1.canKill(b5));
  }

  /**
   * Test method canKill when the one to be killed is the color with the Bishop.
   */
  @Test
  public void testKillColor() {
    ChessPiece b2 = new Bishop(5, 3, Color.BLACK);
    assertEquals(false, b1.canKill(b2));

    ChessPiece b3 = new Pawn(2, 4, Color.BLACK);
    assertEquals(false, b1.canKill(b3));

    ChessPiece b4 = new Knight(2, 0, Color.BLACK);
    assertEquals(false, b1.canKill(b4));

    ChessPiece b5 = new Queen(5, 1, Color.BLACK);
    assertEquals(false, b1.canKill(b5));

  }

  /**
   * Test method canKill when the one to be killed is in a position the Bishop can't reach.
   */
  @Test
  public void testKillMove() {
    ChessPiece b2 = new Bishop(5, 5, Color.WHITE);
    assertEquals(false, b1.canKill(b2));

    ChessPiece b3 = new Queen(6, 3, Color.WHITE);
    assertEquals(false, b1.canKill(b3));

    ChessPiece b4 = new Pawn(2, 1, Color.WHITE);
    assertEquals(false, b1.canKill(b4));

    ChessPiece b5 = new Knight(5, 0, Color.WHITE);
    assertEquals(false, b1.canKill(b5));

  }

  /**
   * Test the consistence between methods canMove and canKill, when the two pieces have different
   * colors.
   */
  @Test
  public void testConsistence() {
    ChessPiece b2 = new Bishop(3, 3, Color.WHITE);
    if (b1.canMove(b2.getRow(), b2.getColumn())) {
      assertEquals(true, b1.canKill(b2));
    } else {
      assertEquals(false, b1.canKill(b2));
    }

    if (b1.canKill(b2)) {
      assertEquals(true, b1.canMove(b2.getRow(), b2.getColumn()));
    } else {
      assertEquals(false, b1.canMove(b2.getRow(), b2.getColumn()));
    }

    ChessPiece b3 = new Bishop(5, 3, Color.WHITE);
    if (b1.canMove(b3.getRow(), b3.getColumn())) {
      assertEquals(true, b1.canKill(b3));
    } else {
      assertEquals(false, b1.canKill(b3));
    }

    if (b1.canKill(b3)) {
      assertEquals(true, b1.canMove(b3.getRow(), b3.getColumn()));
    } else {
      assertEquals(false, b1.canMove(b3.getRow(), b3.getColumn()));
    }


  }

}