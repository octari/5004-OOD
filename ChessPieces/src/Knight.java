/**
 * This class represents a Knight. It inherits all the operations in AbstractChessPiece, and defines
 * the method canMove mandated by the ChessPiece interface.
 */
public class Knight extends AbstractChessPiece {

  /**
   * Construct a Knight object using the given position and color, and define the type of chess
   * piece.
   *
   * @param row the row of the chess piece
   * @param col the column of the chess piece
   * @param color the color of the chess piece
   */
  public Knight(int row, int col,  Color color) {
    super(row, col, color);
    this.type = "Knight";
  }

  /**
   * Determine whether a Knight can move (in an L pattern) to the given position. When the position
   * is out of  board, return false.
   *
   * @param row the row of the given position
   * @param col the column of the given position
   * @return true if a Knight can move to the position, otherwise, return false
   */
  @Override
  public boolean canMove(int row, int col) {
    if (row > 7 || col > 7 || row < 0 || col < 0) {
      return false;
    }
    Position pos = new Position(this.getRow(), this.getColumn());
    return pos.isLPattern(new Position(row, col));
  }

}
