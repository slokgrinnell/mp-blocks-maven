package edu.grinnell.csc207.blocks;

import java.util.Arrays;

/**
 * The horizontal composition of blocks.
 *
 * @author Samuel A. Rebelsky
 * @author Alyssa Ryan
 * @author Slok Rajbhandari
 */
public class HComp implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The blocks.
   */
  AsciiBlock[] blocks;

  /**
   * How the blocks are aligned.
   */
  VAlignment align;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a horizontal composition of two blocks.
   *
   * @param alignment
   *   The way in which the blocks should be aligned.
   * @param leftBlock
   *   The block on the left.
   * @param rightBlock
   *   The block on the right.
   */
  public HComp(VAlignment alignment, AsciiBlock leftBlock,
      AsciiBlock rightBlock) {
    this.align = alignment;
    this.blocks = new AsciiBlock[] {leftBlock, rightBlock};
  } // HComp(VAlignment, AsciiBlock, AsciiBlock)

  /**
   * Build a horizontal composition of multiple blocks.
   *
   * @param alignment
   *   The alignment of the blocks.
   * @param blocksToCompose
   *   The blocks we will be composing.
   */
  public HComp(VAlignment alignment, AsciiBlock[] blocksToCompose) {
    this.align = alignment;
    this.blocks = Arrays.copyOf(blocksToCompose, blocksToCompose.length);
  } // HComp(Alignment, AsciiBLOCK[])

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
   *   if i is outside the range of valid rows.
   */
  public String row(int i) throws Exception {
    // Initialize the final row result as an empty string
    String result = "";
    // Iterate through the blocks to build the row string
    for (AsciiBlock block : blocks) {
      // Calculate the width difference between the block and the max width
      int widthDiff = this.width() - block.width();
      int leftPadding = 0;
      int rightPadding = 0;
      // Handle alignment: calculate how much space to pad on the left and right
      if (align == VAlignment.TOP) {
        // Align to the top: no padding needed for rows within block height
        if (i < block.height()) {
          // Fetch the row from the block
          String blockRow = block.row(i);
          result += blockRow;
        } else {
          // If row exceeds block height, add spaces
          result += " ".repeat(block.width());
        } // end if-else (top alignment check)
      } else if (align == VAlignment.BOTTOM) {
        // Align to the bottom: adjust for extra space at the top
        if (i >= this.height() - block.height()) {
          // Fetch the block's row, adjusted for bottom alignment
          String blockRow = block.row(i - (this.height() - block.height()));
          result += blockRow;
        } else {
          // If row is above the block's start, add spaces
          result += " ".repeat(block.width());
        } // end if-else (bottom alignment check)
      } else if (align == VAlignment.CENTER) {
        // Align to the center: distribute space evenly at the top and bottom
        int extraRows = this.height() - block.height();
        int paddingRows = extraRows / 2;
        if (i >= paddingRows && i < paddingRows + block.height()) {
          // Fetch the row from the block, adjusted for center alignment
          String blockRow = block.row(i - paddingRows);
          result += blockRow;
        } else {
          // If row is outside the block's centered range, add spaces
          result += " ".repeat(block.width());
        } // end if-else (center alignment check)
      } // end if-else (alignment type check)
    } // end for (iterate over blocks)
    return result;
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    int maxHeight = 0;
    for (AsciiBlock block : blocks) {
      maxHeight = Math.max(maxHeight, block.height());
    } //for
    return maxHeight;
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    int totalWidth = 0;
    for (AsciiBlock block : blocks) {
      totalWidth += block.width();
    } //for
    return totalWidth;
  } // width()

  private int calculateAlignmentOffset(AsciiBlock block, int i) {
    // Calculate the vertical alignment offset based on the VAlignment
    int diff = this.height() - block.height();
    switch (align) {
      case TOP: return 0;
      case CENTER: return diff / 2;
      case BOTTOM: return diff;
      default: return 0;
    } //switch
  } //calculateAlignmentOffset(AsciiBlock, int)

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
    return ((other instanceof HComp) && (this.eqv((HComp) other)));
  } // eqv(AsciiBlock)

  /**
   * Determine if two HComp blocks have the same contents.
   *
   * @param other
   *   The block to compare to this block.
   *
   * @return true if the two blocks are equivalent and false otherwise.
   */
  public boolean eqv(HComp other) {
    if ((this.height() == other.height())
          && (this.width() == other.width())
          && (this.align == other.align)) {
      for (int i = 0; i < this.blocks.length; i++) {
        if(!this.blocks[i].eqv(other.blocks[i])){
          return false;
        } //if
      } //for
      return true;
    } else {
      return false;
    } //endif
  } //eqv(HComp)
} // class HComp
