package edu.grinnell.csc207.blocks;

/**
 * One line of text. The line is mutable (that is, it can be changed).
 *
 * @author Samuel A. Rebelsky
 */
public class Line implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The contents of the line.
   */
  String line;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new line.
   *
   * @param contents
   *   The contents of the line.
   */
  public Line(String contents) {
    this.line = contents;
  } // Line(String)

  // +--------------------+------------------------------------------
  // | AsciiBlock methods |
  // +--------------------+

  /**
   * Get one row from the block.
   *
   * @param i the number of the row
   *
   * @return row i.
   *
   * @exception Exception
   *   if i is outside the range of valid rows.
   */
  public String row(int i) throws Exception {
    if (i != 0) {
      throw new Exception("Invalid row " + i);
    } // if
    return this.line;
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    return 1;
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    return this.line.length();
  } // width()

  /**
   * Determine if another block is structurally equivalent to this block.
   *
   * @param other
   *   The block to compare to this block.
   *
   * @return true if the two blocks are structurally equivalent and
   *    false otherwise.
   */
  public boolean eqv(AsciiBlock other){
    return ((other instanceof Line) && (this.eqv((Line)other)));
  } // eqv(AsciiBlock)

  public boolean eqv(Line other){
    try {
      boolean same = false;
      if((this.height() == other.height()) && (this.height() == 1) && (this.width() == other.width())){
        same = (this.row(0).equals(other.row(0)));
      }
      return same;
    } catch (Exception e) {
      return false;
    } 
  }

  // +---------------+-----------------------------------------------
  // | Other methods |
  // +---------------+

  /**
   * Update the line.
   *
   * @param newContents
   *   The new contents of the line.
   */
  public void update(String newContents) {
    this.line = newContents;
  } // update(String)
} // class Line
