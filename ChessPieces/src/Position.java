import static java.lang.Math.abs;

/**
 * This class represents a cell on the chess board. This position in denoted as (row, column).
 */
public class Position {
  private int row;
  private int column;

  /**
   * Construct a position on the chess board.
   *
   * @param row    the row of the cell on the board
   * @param column the column of the cell on the board
   */
  public Position(int row, int column) {

    this.row = row;
    this.column = column;
  }

  /**
   * Return the row of the position.
   *
   * @return the row of the position
   */
  public int getRow() {
    return this.row;
  }

  /**
   * Return the column of the position.
   *
   * @return the column of the position
   */
  public int getColumn() {
    return this.column;
  }

  /**
   * Determine if two positions are in the same row (the two positions shouldn't be the same).
   *
   * @param other another position to be determined
   * @return true when they are in the same row, otherwise, return false
   */
  public boolean isHorizontal(Position other) {
    return this.row == other.row && this.column != other.column;
  }

  /**
   * Determine if two positions are in the same column (the two positions shouldn't be the same).
   *
   * @param other another position to be determined
   * @return true when they are in the same column, otherwise, return false
   */
  public boolean isVertical(Position other) {
    return this.column == other.column && this.row != other.row;
  }

  /**
   * Determine whether two positions can form a diagonal line (the two positions shouldn't be the
   * same).
   *
   * @param other another position to be determined
   * @return true of they can form a diagonal line, otherwise, return false
   */
  public boolean isDiagonal(Position other) {
    return abs(this.column - other.column) == abs(this.row - other.row)
            && this.column - other.column != 0;
  }


  /**
   * Determine whether another position is one place ahead of this position.
   *
   * @param other another position to be determined
   * @return true if the other is one place ahead of this one, otherwise, return false
   */
  public boolean isAhead(Position other) {
    return other.row - this.row == 1 && other.column == this.column;
  }

  /**
   * Determine whether another position is one place backward of this position.
   *
   * @param other another position to be determined
   * @return true if the other is one place backward of this one, otherwise, return false
   */
  public boolean isBackward(Position other) {
    return other.row - this.row == -1 && other.column == this.column;
  }

  /**
   * Determine whether the two positions are in an L pattern.
   *
   * @param other another position to be determined
   * @return true if they are, otherwise, return false
   */
  public boolean isLPattern(Position other) {
    boolean pattern1 = (abs(this.row - other.row) == 1) && (abs(this.column - other.column) == 2);
    boolean pattern2 = (abs(this.row - other.row) == 2) && (abs(this.column - other.column) == 1);
    return pattern1 || pattern2;
  }
}
