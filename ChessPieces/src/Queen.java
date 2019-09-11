/**
 * This class represents a Queen. It inherits all the operations in AbstractChessPiece, and defines
 * the method canMove mandated by the ChessPiece interface.
 */
public class Queen extends AbstractChessPiece {

  /**
   * Construct a Queen object using the given position and color, and define the type of chess
   * piece.
   *
   * @param row  the row of the chess piece
   * @param col the column of the piece
   * @param color the color of the chess piece
   */
  public Queen(int row, int col, Color color) {
    super(row, col, color);
    this.type = "Queen";
  }

  /**
   * Determine whether a Queen can move (horizontally, vertically or diagonally) to the given
   * position. When the position is out of board, return false.
   *
   * @param row the row of the given position
   * @param col the column of the given position
   * @return true if a Queen can move to the position, otherwise, return false
   */
  @Override
  public boolean canMove(int row, int col) {
    if (row > 7 || col > 7 || row < 0 || col < 0) {
      return false;
    }
    Position pos = new Position(this.getRow(), this.getColumn());
    Position moveto = new Position(row, col);
    return pos.isHorizontal(moveto) || pos.isVertical(moveto)
            || pos.isDiagonal(moveto);
  }

}
