package edu.grinnell.csc207.blocks;

/**
 * A block that arranges two blocks in a checkerboard pattern.
 */
public class CheckerboardBlock implements AsciiBlock {
    // Fields
    private AsciiBlock block1;
    private AsciiBlock block2;
    private int numRows;
    private int numCols;

    // Constructor
    public CheckerboardBlock(AsciiBlock block1, AsciiBlock block2, int numRows, int numCols) {
        this.block1 = block1;
        this.block2 = block2;
        this.numRows = numRows;
        this.numCols = numCols;
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

        int blockRowIndex = i % block1.height();
        String result = "";

        for (int j = 0; j < numCols; j++) {
            if ((i + j) % 2 == 0) {
                result += block1.row(blockRowIndex);
            } else {
                result += block2.row(blockRowIndex);
            } // end if-else
        } // end for

        return result;
    } // row(int)

    /**
     * Determine how many rows (height) are in the block.
     *
     * @return the total number of rows
     */
    @Override
    public int height() {
        return block1.height() * numRows;
    } // height()

    /**
     * Determine how many columns (width) are in the block.
     *
     * @return the total number of columns
     */
    @Override
    public int width() {
        return block1.width() * numCols;
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
        return this.numRows == otherBlock.numRows
                && this.numCols == otherBlock.numCols
                && this.block1.eqv(otherBlock.block1)
                && this.block2.eqv(otherBlock.block2);
    } // eqv(AsciiBlock)
} // class CheckerboardBlock
