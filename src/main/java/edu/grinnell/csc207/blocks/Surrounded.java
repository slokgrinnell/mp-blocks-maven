package edu.grinnell.csc207.blocks;

/**
 * A text block surrounded by a single letter.
 *
 * @author Samuel A. Rebelsky
 * @author Alyssa Ryan
 * @author Slok Rajbhandari
 */
public class Surrounded implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The stuff in the box.
   */
  AsciiBlock contents;

  /**
   * The character we put around the box.
   */
  String surroundChar;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new block with the specified contents.
   *
   * @param blockContents
   *   The contents of the block.
   *
   * @param theChar
   *   The character that we use to surround the block.
   */
  public Surrounded(AsciiBlock blockContents, char theChar) {
    this.contents = blockContents;
    this.surroundChar = Character.toString(theChar);
  } // Surrounded(AsciiBlock)

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get one row from the block.
   *
   * @param i the number of the row
   *
   * @return row i.
   *
   * @exception Exception
   *   If the row is invalid.
   */
  public String row(int i) throws Exception {
    if (i < 0 || i >= height()) {
      throw new Exception("Row index out of bounds.");
    } //endif
    // If it's the top or bottom row, return the surrounding line
    if (i == 0 || i == height() - 1) {
      return surroundChar.repeat(width());
    } //endif
    // Otherwise, surround the content row with the surrounding character
    return surroundChar + contents.row(i - 1) + surroundChar;
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    return contents.height() + 2;
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    return contents.width() + 2;
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
  public boolean eqv(AsciiBlock other) {
    return ((other instanceof Surrounded) && (this.eqv((Surrounded) other)));
  } // eqv(AsciiBlock)

  /**
   * Determines if the contents of two Surrounded blocks are equal to each other.
   * @param other The block to compare to this block
   * @return true if the two blocks' contents are equal and false if not
   */
  public boolean eqv(Surrounded other) {
    return this.contents.eqv(other.contents)
            && (this.height() == other.height())
            && (this.width() == other.width())
            && this.surroundChar.equals(other.surroundChar);
  } //eqv(Surrounded)
} // class Surrounded
