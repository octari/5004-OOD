import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * A JUnit test class for Pawn.
 */
public class PawnTest {
  private ChessPiece p1;
  private ChessPiece p2;

  @Before
  public void setUp() {
    p1 = new Pawn(1, 2, Color.WHITE);
    p2 = new Pawn(6, 6, Color.BLACK);
  }

  @Test
  public void testRow() {
    assertEquals(1, p1.getRow());
    assertEquals(6, p2.getRow());
  }

  @Test
  public void testColumn() {
    assertEquals(2, p1.getColumn());
    assertEquals(6, p2.getColumn());
  }

  @Test
  public void testColor() {
    assertEquals(Color.WHITE, p1.getColor());
    assertEquals(Color.BLACK, p2.getColor());
  }

  /**
   * Test when set up a Pawn object out of the board.
   */
  @Test
  public void testOutOfBoard() {
    try {
      ChessPiece k2 = new Pawn(-1, 4, Color.WHITE);
      fail("An exception should been have thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("This position is out of range.", e.getMessage());
    }
    try {
      ChessPiece k2 = new Pawn(1, -4, Color.WHITE);
      fail("An exception should been have thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("This position is out of range.", e.getMessage());
    }
    try {
      ChessPiece k2 = new Pawn(10, 4, Color.WHITE);
      fail("An exception should been have thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("This position is out of range.", e.getMessage());
    }
    try {
      ChessPiece k2 = new Pawn(1, 14, Color.WHITE);
      fail("An exception should been have thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("This position is out of range.", e.getMessage());
    }

  }

  @Test
  public void testCanMove() {
    assertEquals(true, p1.canMove(2, 2));
    assertEquals(true, p2.canMove(5, 6));
  }

  @Test
  public void testCantMove() {
    assertEquals(false, p1.canMove(0, 2));
    assertEquals(false, p1.canMove(3, 2));
    assertEquals(false, p1.canMove(1, 0));
    assertEquals(false, p1.canMove(2, 3));

    assertEquals(false, p1.canMove(1, 2));

    assertEquals(false, p2.canMove(7, 6));
    assertEquals(false, p2.canMove(4, 6));
    assertEquals(false, p2.canMove(4, 4));
    assertEquals(false, p2.canMove(5, 7));

    assertEquals(false, p2.canMove(6, 6));
  }

  /**
   * Test method canMove when the position is out of the board.
   */
  @Test
  public void testMoveOutBoard() {
    assertEquals(false, p2.canMove(7, -6));
    assertEquals(false, p2.canMove(-4, 6));
    assertEquals(false, p2.canMove(4, 8));
    assertEquals(false, p2.canMove(9, 7));
  }

  @Test
  public void testCanKill() {
    ChessPiece p4 = new Queen(2, 1, Color.BLACK);
    assertEquals(true, p1.canKill(p4));
    ChessPiece p5 = new Knight(2, 3, Color.BLACK);
    assertEquals(true, p1.canKill(p5));

    ChessPiece p6 = new Bishop(5, 5, Color.WHITE);
    assertEquals(true, p2.canKill(p6));
    ChessPiece p7 = new Rook(5, 7, Color.WHITE);
    assertEquals(true, p2.canKill(p7));
  }

  /**
   * Test method canKill when the piece to be killed is in the same color as the Knight.
   */
  @Test
  public void testKillColor() {
    ChessPiece p4 = new Queen(2, 1, Color.WHITE);
    assertEquals(false, p1.canKill(p4));
    ChessPiece p5 = new Knight(2, 3, Color.WHITE);
    assertEquals(false, p1.canKill(p5));

    ChessPiece p6 = new Bishop(5, 5, Color.BLACK);
    assertEquals(false, p2.canKill(p6));
    ChessPiece p7 = new Rook(5, 7, Color.BLACK);
    assertEquals(false, p2.canKill(p7));
  }

  /**
   * Test method canKill when the piece to be killed is in the place the Knight can't reach.
   */
  @Test
  public void testKillMove() {
    ChessPiece p4 = new Pawn(0, 2, Color.BLACK);
    assertEquals(false, p1.canKill(p4));
    ChessPiece p5 = new Queen(3, 2, Color.BLACK);
    assertEquals(false, p1.canKill(p5));
    ChessPiece p6 = new Knight(0, 1, Color.BLACK);
    assertEquals(false, p1.canKill(p6));
    ChessPiece p7 = new Bishop(1, 5, Color.BLACK);
    assertEquals(false, p1.canKill(p7));

    ChessPiece p8 = new Rook(7, 6, Color.WHITE);
    assertEquals(false, p2.canKill(p8));
    ChessPiece p9 = new Pawn(4, 6, Color.WHITE);
    assertEquals(false, p2.canKill(p9));
    ChessPiece p10 = new Queen(6, 5, Color.WHITE);
    assertEquals(false, p2.canKill(p10));
    ChessPiece p11 = new Bishop(4, 7, Color.WHITE);
    assertEquals(false, p2.canKill(p11));
  }


}