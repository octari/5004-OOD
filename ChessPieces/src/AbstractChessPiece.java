/**
 * This abstract class represents an abstract chess piece. it defines parts of the operations
 * mandated by the ChessPiece interface.
 */
public abstract class AbstractChessPiece implements ChessPiece {
  private int row;
  private int col;
  private Color color;
  protected String type;

  /**
   * Constructs a ChessPiece object incompletely with the provided position and color.
   *
   * @param row   the row of the chess piece
   * @param col   the column of the chess piece
   * @param color the color of the chess piece
   * @throws IllegalArgumentException when the position is out of range
   */
  public AbstractChessPiece(int row, int col, Color color) throws IllegalArgumentException {
    if (row > 7 || row < 0 || col < 0 || col > 7) {
      throw new IllegalArgumentException("This position is out of range.");
    } else {
      this.row = row;
      this.col = col;
      this.color = color;
    }
  }

  /**
   * Return the row of the chess piece.
   *
   * @return the row of the chess piece
   */
  @Override
  public int getRow() {
    return this.row;
  }

  /**
   * Return the column of the chess piece.
   *
   * @return the column of the chess piece
   */
  @Override
  public int getColumn() {
    return this.col;
  }

  /**
   * Return the color of the chess piece.
   *
   * @return the color of the chess piece
   */
  @Override
  public Color getColor() {
    return this.color;
  }

  /**
   * Determine if the chess piece can be killed by this one. To kill the piece, it should move to
   * its place and in opposite color.
   *
   * @param piece the chess piece intends to be killed
   * @return true if the piece can be killed, otherwise, return false
   */
  @Override
  public boolean canKill(ChessPiece piece) {
    return canMove(piece.getRow(), piece.getColumn()) && !this.getColor().equals(piece.getColor());
  }

}
