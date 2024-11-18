package edu.grinnell.csc207;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.CheckerboardBlock;
import edu.grinnell.csc207.blocks.HComp;
import edu.grinnell.csc207.blocks.Line;
import edu.grinnell.csc207.blocks.VAlignment;
import edu.grinnell.csc207.blocks.VComp;
import edu.grinnell.csc207.blocks.HAlignment;
import edu.grinnell.csc207.blocks.Empty;

/**
 * Tests of the new block.
 * @author Alyssa Ryan
 * @author Slok Rajbhandari
 */
public class TestNewBlock {
  // +-------+-------------------------------------------------------
  // | Tests |
  // +-------+

  /**
   * Test 1: Basic Functionality Test for CheckerboardBlock
   */
  @Test
  public void testCheckerboardBasic() throws Exception {
    AsciiBlock block1 = new Line("A");
    AsciiBlock block2 = new Line("B");
    CheckerboardBlock checkerboard = new CheckerboardBlock(block1, block2, 3, 3);

    assertEquals(3, checkerboard.height(), "Height should match the number of rows.");
    assertEquals(6, checkerboard.width(), "Width should match the number of columns.");
    assertEquals("ABABAB", checkerboard.row(0), "Row 0 should alternate A and B.");
    assertEquals("BABABA", checkerboard.row(1), "Row 1 should alternate B and A.");
    assertEquals("ABABAB", checkerboard.row(2), "Row 2 should alternate A and B.");
  } // testCheckerboardBasic()

  /**
   * Empty Blocks
   */
  @Test
  public void testCheckerboardWithEmptyBlock() throws Exception {
    AsciiBlock block1 = new Empty();
    AsciiBlock block2 = new Empty();
    CheckerboardBlock checkerboard = new CheckerboardBlock(block1, block2, 2, 2);

    assertEquals(0, checkerboard.height(), "Height should be 0 for empty blocks.");
    assertEquals(0, checkerboard.width(), "Width should be 0 for empty blocks.");

    Exception exception = assertThrows(Exception.class, () -> checkerboard.row(0));
    assertEquals("Invalid row 0", exception.getMessage(), "Exception message should be 'Invalid row 0'.");
  } // testCheckerboardWithEmptyBlock()

  /**
   * Single Character Blocks
   */
  @Test
  public void testCheckerboardSingleCharacter() throws Exception {
    AsciiBlock block1 = new Line("X");
    AsciiBlock block2 = new Line("O");
    CheckerboardBlock checkerboard = new CheckerboardBlock(block1, block2, 2, 3);

    assertEquals("XOXOXO", checkerboard.row(0), "First row should alternate X and O.");
    assertEquals("OXOXOX", checkerboard.row(1), "Second row should alternate O and X.");
  } // testCheckerboardSingleCharacter()

  /**
   * Larger Blocks
   */
  @Test
  public void testCheckerboardLargerBlocks() throws Exception {
    AsciiBlock block1 = new Line("ABC");
    AsciiBlock block2 = new Line("123");
    CheckerboardBlock checkerboard = new CheckerboardBlock(block1, block2, 2, 2);

    assertEquals(12, checkerboard.width(), "Width of the checkerboard should be 12");
    assertEquals(2, checkerboard.height(), "Height should be 2");
    assertEquals("ABC123ABC123", checkerboard.row(0), "First row should alternate ABC and 123.");
    assertEquals("123ABC123ABC", checkerboard.row(1), "Second row should alternate 123 and ABC.");
  } // testCheckerboardLargerBlocks()

  /**
   * Combining Checkerboard with HComp
   */
  @Test
  public void testCheckerboardWithHComp() throws Exception {
    AsciiBlock block1 = new Line("X");
    AsciiBlock block2 = new Line("O");
    CheckerboardBlock checkerboard = new CheckerboardBlock(block1, block2, 2, 2);

    AsciiBlock hComp = new HComp(VAlignment.TOP, new AsciiBlock[] {checkerboard, new Line("===")});

    assertEquals("XOXO===", hComp.row(0), "First row should be 'XOXO==='.");
    assertEquals("OXOX   ", hComp.row(1), "Second row should be 'OXOX   '.");
  } // testCheckerboardWithHComp()

  /**
   * Combining Checkerboard with VComp
   */
  @Test
  public void testCheckerboardWithVComp() throws Exception {
    AsciiBlock block1 = new Line("X");
    AsciiBlock block2 = new Line("O");
    CheckerboardBlock checkerboard = new CheckerboardBlock(block1, block2, 2, 2);
    assertEquals(2, checkerboard.height(), "Height should be 2");
  
    AsciiBlock vComp = new VComp(HAlignment.LEFT, new AsciiBlock[] {checkerboard, new Line("====")});

    assertEquals(3, vComp.height(), "Height should be 3");
    assertEquals("XOXO", vComp.row(0), "First row should be 'XOXO'.");
    assertEquals("OXOX", vComp.row(1), "Second row should be 'OXOX'.");
    assertEquals("====", vComp.row(2), "Third row should be '===='.");
  } // testCheckerboardWithVComp()

  /**
   * Ensures we make a proper checkerboard when the blocks have a height more than 1
   */
  @Test
  public void testCheckerboardMultiLineBlock() throws Exception {
    AsciiBlock block1 = new VComp(HAlignment.LEFT, new Line("AA"), new Line("BB"));
    AsciiBlock block2 = new VComp(HAlignment.LEFT, new Line("CC"), new Line("DD"));

    CheckerboardBlock board = new CheckerboardBlock(block1, block2, 2, 2);

    assertEquals(4, board.height(), "Height should be 4");
    assertEquals(8, board.width(), "Width should be 8");

    assertEquals("AACCAACC", board.row(0), "First row should be AACC");
    assertEquals("DDBBDDBB", board.row(1), "Second row should be BBDD");
    assertEquals("CCAACCAA", board.row(2), "Third row should be CCAA");
    assertEquals("BBDDBBDD", board.row(3), "Fourth row should be DDBB");
  } //testCheckerboardMultiLineBlock()
} // class TestNewBlock
