/**
 * This class represents a Rook. It inherits all the operations in AbstractChessPiece, and defines
 * the method canMove mandated by the ChessPiece interface.
 */
public class Rook extends AbstractChessPiece {

  /**
   * Construct a Rook object using the given position and color, and define the type of the chess
   * piece.
   *
   * @param row   the position of the chess piece
   * @param col  the column of the chess piece
   * @param color the color of the chess piece
   */
  public Rook(int row, int col, Color color) {
    super(row, col, color);
    this.type = "Rook";
  }

  /**
   * Determine whether a Rook can move (horizontally or vertically) to the given position. When the
   * position is out of the board, return false.
   *
   * @param row the row of the given position
   * @param col the column of the given position
   * @return true if a Rook can move to the position, otherwise, return false
   */
  @Override
  public boolean canMove(int row, int col) {
    if (row > 7 || col > 7 || row < 0 || col < 0) {
      return false;
    }
    Position pos = new Position(this.getRow(), this.getColumn());
    return pos.isHorizontal(new Position(row, col))
            || pos.isVertical(new Position(row, col));
  }
}
