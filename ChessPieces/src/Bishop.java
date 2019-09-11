/**
 * This class represents a Bishop. It inherits all the operations in AbstractChessPiece, and defines
 * the method canMove mandated by the ChessPiece interface.
 */
public class Bishop extends AbstractChessPiece {

  /**
   * Construct a Bishop object using the given position and color, and define the type of chess
   * piece.
   *
   * @param row   the row of the chess piece
   * @param col   the column of the chess piece
   * @param color the color of the chess piece
   */
  public Bishop(int row, int col, Color color) {
    super(row, col, color);
    this.type = "Bishop";
  }

  /**
   * Determine whether a Bishop can move (diagonally) to that position. When the position is out of
   * the board, return false.
   *
   * @param row the row of the given position
   * @param col the column of the given position
   * @return true if it can move, otherwise, return false
   */
  @Override
  public boolean canMove(int row, int col) {
    if (row > 7 || col > 7 || row < 0 || col < 0) {
      return false;
    }
    Position pos = new Position(this.getRow(), this.getColumn());
    return pos.isDiagonal(new Position(row, col));
  }

}
