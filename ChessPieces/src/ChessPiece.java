/**
 * This interface contains all operations that all types of chess pieces should should.
 */
public interface ChessPiece {
  /**
   * Return the chess piece's current row.
   *
   * @return current row
   */
  int getRow();

  /**
   * Return the chess piece's current column.
   *
   * @return current column
   */
  int getColumn();

  /**
   * Return the color of the chess piece.
   *
   * @return the color of the chess piece
   */
  Color getColor();

  /**
   * Determine if the chess piece can move to the given position.
   *
   * @param row the row of the given position
   * @param col the column of the given position
   * @return true if it can, otherwise, return false
   */
  boolean canMove(int row, int col);

  /**
   * Determine whether the chess piece can kill the given one.
   *
   * @param piece the chess piece intends to be killed
   * @return true if it can kill the one, otherwise, return false
   */
  boolean canKill(ChessPiece piece);


}
