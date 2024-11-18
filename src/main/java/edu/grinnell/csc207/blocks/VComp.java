package edu.grinnell.csc207.blocks;

import java.util.Arrays;

/**
 * The vertical composition of blocks.
 *
 * @author Samuel A. Rebelsky
 * @author Alyssa Ryan
 * @author Slok Rajbhandari
 */
public class VComp implements AsciiBlock {
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
  HAlignment align;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a vertical composition of two blocks.
   *
   * @param alignment
   *   The way in which the blocks should be aligned.
   * @param topBlock
   *   The block on the top.
   * @param bottomBlock
   *   The block on the bottom.
   */
  public VComp(HAlignment alignment, AsciiBlock topBlock,
      AsciiBlock bottomBlock) {
    this.align = alignment;
    this.blocks = new AsciiBlock[] {topBlock, bottomBlock};
  } // VComp(HAlignment, AsciiBlock, AsciiBlock)

  /**
   * Build a vertical composition of multiple blocks.
   *
   * @param alignment
   *   The alignment of the blocks.
   * @param blocksToCompose
   *   The blocks we will be composing.
   */
  public VComp(HAlignment alignment, AsciiBlock[] blocksToCompose) {
    this.align = alignment;
    this.blocks = Arrays.copyOf(blocksToCompose, blocksToCompose.length);
  } // VComp(HAlignment, AsciiBLOCK[])

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
    int currentRow = 0;
    for (AsciiBlock block : blocks) {
      if (i < currentRow + block.height()) {
        // We are in the right block, so get the row from this block
        String blockRow = block.row(i - currentRow);
        // Handle alignment
        int widthDiff = this.width() - block.width();
        int leftPadding = 0;
        int rightPadding = 0;
        if (align == HAlignment.LEFT) {
          rightPadding = widthDiff; // Pad the right side
        } else if (align == HAlignment.RIGHT) {
          leftPadding = widthDiff; // Pad the left side
        } else if (align == HAlignment.CENTER) {
          leftPadding = widthDiff / 2;
          rightPadding = widthDiff - leftPadding;
        } //endif
        // Return the row with the appropriate padding
        return " ".repeat(leftPadding) + blockRow + " ".repeat(rightPadding);
      } else {
        // Move to the next block's rows
        currentRow += block.height();
      } //endif
    } //for
    // If the row is out of bounds
    throw new Exception("Row out of bounds");
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    int size = 0;
    for (int i = 0; i < blocks.length; i++) {
      size += blocks[i].height();
    } //for
    return size;
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    int size = 0;
    for (int i = 0; i < blocks.length; i++) {
      if (blocks[i].width() > size) {
        size = blocks[i].width();
      } //endif
    } //for
    return size;
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
    return ((other instanceof VComp) && (this.eqv((VComp) other)));
  } // eqv(AsciiBlock)

  /**
   * Determine if two VComps have the same contents.
   *
   * @param other
   *   The block to compare to this block.
   *
   * @return true if the two blocks are equivalent and false otherwise.
   */
  public boolean eqv(VComp other) {
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
  } //eqv(VComp)
} // class VComp
