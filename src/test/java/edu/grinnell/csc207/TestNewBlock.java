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

/**
 * Tests of the new block.
 */
public class TestNewBlock {
  // +-------+-------------------------------------------------------
  // | Tests |
  // +-------+

  // Test 1: Basic Functionality Test for CheckerboardBlock
  @Test
  public void testCheckerboardBasic() throws Exception {
    AsciiBlock block1 = new Line("A");
    AsciiBlock block2 = new Line("B");
    CheckerboardBlock checkerboard = new CheckerboardBlock(block1, block2, 3, 3);

    assertEquals(3, checkerboard.height(), "Height should match the number of rows.");
    assertEquals(3, checkerboard.width(), "Width should match the number of columns.");
    assertEquals("ABA", checkerboard.row(0), "Row 0 should alternate A and B.");
    assertEquals("BAB", checkerboard.row(1), "Row 1 should alternate B and A.");
    assertEquals("ABA", checkerboard.row(2), "Row 2 should alternate A and B.");
  } // testCheckerboardBasic()

  // Test 2: Empty Blocks
  @Test
  public void testCheckerboardWithEmptyBlock() throws Exception {
    AsciiBlock block1 = new Line("");
    AsciiBlock block2 = new Line("");
    CheckerboardBlock checkerboard = new CheckerboardBlock(block1, block2, 2, 2);

    assertEquals(0, checkerboard.height(), "Height should be 0 for empty blocks.");
    assertEquals(0, checkerboard.width(), "Width should be 0 for empty blocks.");

    Exception exception = assertThrows(Exception.class, () -> checkerboard.row(0));
    assertEquals("Invalid row 0", exception.getMessage(), "Exception message should be 'Invalid row 0'.");
  } // testCheckerboardWithEmptyBlock()

  // Test 3: Single Character Blocks
  @Test
  public void testCheckerboardSingleCharacter() throws Exception {
    AsciiBlock block1 = new Line("X");
    AsciiBlock block2 = new Line("O");
    CheckerboardBlock checkerboard = new CheckerboardBlock(block1, block2, 2, 3);

    assertEquals("XOXOXO", checkerboard.row(0), "First row should alternate X and O.");
    assertEquals("OXOXOX", checkerboard.row(1), "Second row should alternate O and X.");
  } // testCheckerboardSingleCharacter()

  // Test 4: Larger Blocks
  @Test
  public void testCheckerboardLargerBlocks() throws Exception {
    AsciiBlock block1 = new Line("ABC");
    AsciiBlock block2 = new Line("123");
    CheckerboardBlock checkerboard = new CheckerboardBlock(block1, block2, 2, 2);

    assertEquals("ABC123", checkerboard.row(0), "First row should alternate ABC and 123.");
    assertEquals("123ABC", checkerboard.row(1), "Second row should alternate 123 and ABC.");
  } // testCheckerboardLargerBlocks()

  // Test 5: Combining Checkerboard with HComp
  @Test
  public void testCheckerboardWithHComp() throws Exception {
    AsciiBlock block1 = new Line("X");
    AsciiBlock block2 = new Line("O");
    CheckerboardBlock checkerboard = new CheckerboardBlock(block1, block2, 2, 2);

    AsciiBlock hComp = new HComp(VAlignment.TOP, new AsciiBlock[] {checkerboard, new Line("===")});

    assertEquals("XOXO===", hComp.row(0), "First row should be 'XOXO==='.");
    assertEquals("OXOX   ", hComp.row(1), "Second row should be 'OXOX   '.");
  } // testCheckerboardWithHComp()

  // Test 6: Combining Checkerboard with VComp
  @Test
  public void testCheckerboardWithVComp() throws Exception {
    AsciiBlock block1 = new Line("X");
    AsciiBlock block2 = new Line("O");
    CheckerboardBlock checkerboard = new CheckerboardBlock(block1, block2, 2, 2);

    AsciiBlock vComp = new VComp(HAlignment.LEFT, new AsciiBlock[] {checkerboard, new Line("===")});

    assertEquals("XOXO", vComp.row(0), "First row should be 'XOXO'.");
    assertEquals("OXOX", vComp.row(1), "Second row should be 'OXOX'.");
    assertEquals("===", vComp.row(2), "Third row should be '==='.");
  } // testCheckerboardWithVComp()

} // class TestNewBlock
