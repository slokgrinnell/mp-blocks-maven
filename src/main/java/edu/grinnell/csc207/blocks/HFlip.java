package edu.grinnell.csc207.blocks;

/**
 * A horizontally flipped ASCII block.
 *
 * @author Samuel A. Rebelsky
 * @author Slok Rajbhandari
 */
public class HFlip implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The original block.
   */
  AsciiBlock block;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new block with the specified contents.
   *
   * @param original
   *   The original block.
   */
  public HFlip(AsciiBlock original) {
    this.block = original;
  } // HFlip(AsciiBlock)

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
    String originalRow = block.row(i);
    int maxLength = this.width();  // Get the width to determine the number of spaces needed
    String reversedRow = "";

    // Reverse the row
    for (int j = maxLength - 1; j >= 0; j--) {
      if (j < originalRow.length()) {
        reversedRow += originalRow.charAt(j);
      } else {
        reversedRow += " ";  // Add spaces to maintain the same length
      } //endif
    } //for
    return reversedRow;
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    return block.height();
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    return block.width();
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
    return ((other instanceof HFlip) && (this.eqv((HFlip) other)));
  } // eqv(AsciiBlock)

  /**
   * Determine if two HFlip blocks have the same contents.
   *
   * @param other
   *   The block to compare to this block.
   *
   * @return true if the two blocks are equivalent and false otherwise.
   */
  public boolean eqv(HFlip other) {
    return block.eqv(other.block)
            && (this.height() == other.height())
            && (this.width() == other.width());
  } //eqv(HFlip)
} // class HFlip
