import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * A JUnit test class for Queen.
 */
public class QueenTest {
  private Queen q1;

  @Before
  public void setUp() {
    q1 = new Queen(2, 3, Color.WHITE);

  }

  @Test
  public void testRow() {
    assertEquals(2, q1.getRow());
  }

  @Test
  public void testColumn() {
    assertEquals(3, q1.getColumn());

  }

  @Test
  public void testColor() {
    assertEquals(Color.WHITE, q1.getColor());
  }

  /**
   * Test when set up a Queen object out of the board.
   */
  @Test
  public void testOutOfBoard() {
    try {
      ChessPiece q2 = new Queen(-1, 4, Color.WHITE);
      fail("An exception should been have thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("This position is out of range.", e.getMessage());
    }

    try {
      ChessPiece q2 = new Queen(1, -4, Color.WHITE);
      fail("An exception should been have thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("This position is out of range.", e.getMessage());
    }

    try {
      ChessPiece q2 = new Queen(9, 4, Color.WHITE);
      fail("An exception should been have thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("This position is out of range.", e.getMessage());
    }

    try {
      ChessPiece q2 = new Queen(9, 10, Color.WHITE);
      fail("An exception should been have thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("This position is out of range.", e.getMessage());
    }
  }

  @Test
  public void testCanMove() {
    assertEquals(true, q1.canMove(2, 0));
    assertEquals(true, q1.canMove(2, 5));

    assertEquals(true, q1.canMove(0, 3));
    assertEquals(true, q1.canMove(6, 3));

    assertEquals(true, q1.canMove(0, 1));
    assertEquals(true, q1.canMove(3, 4));

    assertEquals(true, q1.canMove(5, 0));
    assertEquals(true, q1.canMove(0, 5));
  }

  @Test
  public void testCantMove() {
    assertEquals(false, q1.canMove(3, 1));
    assertEquals(false, q1.canMove(4, 4));
    assertEquals(false, q1.canMove(1, 5));
    assertEquals(false, q1.canMove(0, 0));

    assertEquals(false, q1.canMove(2, 3));
  }

  /**
   * Test method canMove when the position is out of board.
   */
  @Test
  public void testMoveOutOfBoard() {
    assertEquals(false, q1.canMove(-1, 3));
    assertEquals(false, q1.canMove(9, 3));
    assertEquals(false, q1.canMove(6, 8));
    assertEquals(false, q1.canMove(6, -1));
  }

  @Test
  public void testCanKill() {
    Queen q2 = new Queen(3, 2, Color.BLACK);
    assertEquals(true, q1.canKill(q2));

    Queen q3 = new Queen(4, 3, Color.BLACK);
    assertEquals(true, q1.canKill(q3));

    Knight q4 = new Knight(5, 6, Color.BLACK);
    assertEquals(true, q1.canKill(q4));

    Bishop q5 = new Bishop(2, 5, Color.BLACK);
    assertEquals(true, q1.canKill(q5));

    Pawn q6 = new Pawn(0, 5, Color.BLACK);
    assertEquals(true, q1.canKill(q6));

    Rook q7 = new Rook(1, 3, Color.BLACK);
    assertEquals(true, q1.canKill(q7));

    Queen q8 = new Queen(1, 2, Color.BLACK);
    assertEquals(true, q1.canKill(q8));

    Pawn q9 = new Pawn(2, 0, Color.BLACK);
    assertEquals(true, q1.canKill(q9));
  }

  /**
   * Test method canKill when the piece to be killed is in the same color as the Queen.
   */
  @Test
  public void testKillColor() {
    Queen q2 = new Queen(3, 2, Color.WHITE);
    assertEquals(false, q1.canKill(q2));

    Queen q3 = new Queen(4, 3, Color.WHITE);
    assertEquals(false, q1.canKill(q3));

    Knight q4 = new Knight(5, 6, Color.WHITE);
    assertEquals(false, q1.canKill(q4));

    Bishop q5 = new Bishop(2, 5, Color.WHITE);
    assertEquals(false, q1.canKill(q5));

    Pawn q6 = new Pawn(0, 5, Color.WHITE);
    assertEquals(false, q1.canKill(q6));

    Rook q7 = new Rook(1, 3, Color.WHITE);
    assertEquals(false, q1.canKill(q7));

    Queen q8 = new Queen(1, 2, Color.WHITE);
    assertEquals(false, q1.canKill(q8));

    Pawn q9 = new Pawn(2, 0, Color.WHITE);
    assertEquals(false, q1.canKill(q9));
  }

  /**
   * Test method canKill when the piece to be killed is in the place the Queen can't reach.
   */
  @Test
  public void testKillMove() {
    ChessPiece q2 = new Queen(3, 1, Color.BLACK);
    assertEquals(false, q1.canKill(q2));

    ChessPiece q3 = new Queen(4, 4, Color.BLACK);
    assertEquals(false, q1.canKill(q3));

    ChessPiece q4 = new Knight(4, 6, Color.BLACK);
    assertEquals(false, q1.canKill(q4));

    ChessPiece q5 = new Bishop(3, 5, Color.BLACK);
    assertEquals(false, q1.canKill(q5));

    ChessPiece q6 = new Pawn(0, 4, Color.BLACK);
    assertEquals(false, q1.canKill(q6));

    ChessPiece q7 = new Rook(0, 2, Color.BLACK);
    assertEquals(false, q1.canKill(q7));

    ChessPiece q8 = new Queen(1, 1, Color.BLACK);
    assertEquals(false, q1.canKill(q8));

    ChessPiece q9 = new Pawn(1, 0, Color.BLACK);
    assertEquals(false, q1.canKill(q9));
  }

  /**
   * Test the consistence between methods canMove and canKill, when the two pieces have different
   * colors.
   */
  @Test
  public void testConsistence() {
    Queen q2 = new Queen(3, 2, Color.BLACK);
    if (q1.canKill(q2)) {
      assertEquals(true, q1.canMove(q2.getRow(), q2.getColumn()));
    } else {
      assertEquals(false, q1.canMove(q2.getRow(), q2.getColumn()));
    }

    ChessPiece q3 = new Pawn(1, 0, Color.BLACK);
    if (q1.canKill(q3)) {
      assertEquals(true, q1.canMove(q3.getRow(), q3.getColumn()));
    } else {
      assertEquals(false, q1.canMove(q3.getRow(), q3.getColumn()));
    }

    Pawn q4 = new Pawn(0, 5, Color.BLACK);
    if (q1.canMove(q4.getRow(), q4.getColumn())) {
      assertEquals(true, q1.canKill(q4));
    } else {
      assertEquals(false, q1.canKill(q4));
    }

    Rook q5 = new Rook(1, 3, Color.BLACK);
    if (q1.canMove(q5.getRow(), q5.getColumn())) {
      assertEquals(true, q1.canKill(q5));
    } else {
      assertEquals(false, q1.canKill(q5));
    }

  }

}