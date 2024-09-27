package edu.grinnell.csc207.blocks;

/**
 * A vertically flipped ASCII block.
 *
 * @author Samuel A. Rebelsky
 * @author Your Name Here
 */
public class VFlip implements AsciiBlock {
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
  public VFlip(AsciiBlock original) {
    this.block = original;
  } // VFlip(AsciiBlock)

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
    // Ensure the row number is valid
    if (i < 0 || i >= height()) {
      throw new Exception("Invalid row " + i);
    }
    // Return the row from the flipped block, which is (height - 1 - i)
    return block.row(height() - 1 - i);
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    return this.block.height();   // The height is the same as the original block
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    return this.block.width();   // The width is the same as the original block
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
  // Check if the other block is also a VFlip block and compare the underlying blocks
    return ((other instanceof VFlip) && (this.eqv((VFlip) other)));
  } // eqv(AsciiBlock)

  public boolean eqv(VFlip other){
    try {
      boolean same = (this.height() == other.height()) && (this.width() == other.width());
      for(int i=0; i<this.height(); i++){
        if((this.row(i).equals(other.row(i))) == false){
          same = false;
          return same;
        }
      }
      return same;
    } catch (Exception e) {
      return false;
    }
  }
} // class VFlip
