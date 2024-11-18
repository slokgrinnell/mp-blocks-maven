package edu.grinnell.csc207.blocks;

/**
 * A block that arranges two blocks in a checkerboard pattern.
 * @author Alyssa Ryan
 * @author Slok Rajbhandari
 */
public class CheckerboardBlock implements AsciiBlock {
  // Fields
  /**
   * One of the blocks the checkerboard will be made out of.
   */
  AsciiBlock block1;
  /**
   * The other block the checkerboard will be made out of.
   */
  AsciiBlock block2;
  /**
   * The number of rows the checkerboard will have.
   */
  int rows;
  /**
   * The number of columns the checkerboard will have.
   */
  int cols;

  // Constructor
  /**
   * Creates a checkerboard asciiblock made of the two given blocks and
   * with the given number of rows / columns.
   * @param first One of the blocks to make the checkerboard out of
   * @param second The other block to make the checkerboard out of
   * @param numRows The number of rows the checkerboard should have
   * @param numCols The number of columns the checkerboard should have
   */
  public CheckerboardBlock(AsciiBlock first, AsciiBlock second, int numRows, int numCols) {
    this.block1 = first;
    this.block2 = second;
    this.rows = numRows;
    this.cols = numCols;
  } // CheckerboardBlock(AsciiBlock, AsciiBlock, int, int)

  /**
   * Get one row from the block.
   *
   * @param i the number of the row
   * @return row i
   * @exception Exception if i is outside the range of valid rows
   */
  @Override
  public String row(int i) throws Exception {
    if (i < 0 || i >= height()) {
      throw new Exception("Invalid row " + i);
    } // end if

    int blockRowIndex = i % biggerHeight();
    String result = "";
    //This will end up 0 if the first block should be first in the row, 1 for the second
    int whichFirst = 0;
    for (int k = 0; k < i; k+=biggerHeight()) {
      if (whichFirst == 0) {
        whichFirst = 1;
      } else {
        whichFirst = 0;
      } //endif
    }//for

    for (int j = 0; j < cols*2; j++) {
      if ((whichFirst + j) % 2 == 0) {
        result += block1.row(blockRowIndex);
      } else {
        result += block2.row(blockRowIndex);
      } // end if-else
    } // end for

    return result;
  } // row(int)

  /**
   * Determines which block has a bigger height
   * @return The height of the bigger block
   */
  public int biggerHeight() {
    if (block1.height() > block2.height()) {
      return block1.height();
    } else {
      return block2.height();
    } //endif
  } //biggerHeight

  /**
   * Determine how many rows (height) are in the block.
   *
   * @return the total number of rows
   */
  @Override
  public int height() {
    if (block1.height() > block2.height()) {
      return block1.height() * rows;
    } else {
      return block2.height() * rows;
    } //endif
  } // height()

  /**
   * Determine how many columns (width) are in the block.
   *
   * @return the total number of columns
   */
  @Override
  public int width() {
    return (block1.width() + block2.width()) * cols;
  } // width()

  /**
   * Determine if another block is structurally equivalent to this block.
   *
   * @param other the block to compare to this block
   * @return true if the two blocks are structurally equivalent, false otherwise
   */
  @Override
  public boolean eqv(AsciiBlock other) {
    if (!(other instanceof CheckerboardBlock)) {
      return false;
    } // end if

    CheckerboardBlock otherBlock = (CheckerboardBlock) other;
    return this.rows == otherBlock.rows
          && this.cols == otherBlock.cols
          && this.block1.eqv(otherBlock.block1)
          && this.block2.eqv(otherBlock.block2);
  } // eqv(AsciiBlock)
} // class CheckerboardBlock
