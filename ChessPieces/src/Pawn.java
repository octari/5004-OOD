/**
 * This class represents a Queen. It inherits all the operations in AbstractChessPiece, and defines
 * the method canMove and redefines the method canKill mandated by the ChessPiece interface.
 */
public class Pawn extends AbstractChessPiece {

  /**
   * Construct a Pawn object using the given position and color, and define the type of the chess
   * piece.
   *
   * @param row the row of the chess piece
   * @param col the column of the chess piece
   * @param color the color of the chess piece
   */
  public Pawn(int row, int col, Color color) {
    super(row, col, color);
    this.type = "Pawn";
  }

  /**
   * Determine whether a Pawn can move (one place forward) to the given position. When the position
   * is out of board, return false.
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
    if (this.getColor() == Color.WHITE) {
      return pos.isAhead(new Position(row, col));
    } else {
      return pos.isBackward(new Position(row, col));
    }
  }

  /**
   * Determine whether a Pawn can kill another piece. It can kill the one which is one place forward
   * and in opposite color.
   *
   * @param piece the chess piece intends to be killed
   * @return true if the Pawn can kill the one, otherwise, return false
   */
  @Override
  public boolean canKill(ChessPiece piece) {

    boolean legal_move = canMove(piece.getRow(), piece.getColumn() + 1)
            || canMove(piece.getRow(), piece.getColumn() - 1);
    return legal_move && !this.getColor().equals(piece.getColor());
  }
}
