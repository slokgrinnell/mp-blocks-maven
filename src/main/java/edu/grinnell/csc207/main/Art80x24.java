package edu.grinnell.csc207.main;

import edu.grinnell.csc207.blocks.VComp;
import edu.grinnell.csc207.blocks.HComp;
import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.Rect;
import edu.grinnell.csc207.blocks.VAlignment;
import edu.grinnell.csc207.blocks.HAlignment;

import java.io.PrintWriter;

/**
 * Create and print an amazing 80x24 ASCII artwork.
 *
 * @author Alyssa Ryan
 * @author Slok Rajbhandari
 */
public class Art80x24 {
  /**
   * Create the artwork.
   *
   * @param args
   *   Command-line arguments (currently ignored).
   *
   * @exception Exception
   *   If something goes wrong with one of the underlying classes.
   */
  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);
    AsciiBlock bigEar = new Rect('*', 2, 2);
    AsciiBlock smallEar = new Rect('#', 2, 1);
    AsciiBlock head = new Rect('*', 15, 4);
    AsciiBlock snout = new Rect('*', 7, 2);
    AsciiBlock tail = new Rect('*', 3, 10);
    AsciiBlock neck = new Rect('*', 6, 10);
    AsciiBlock body1 = new Rect('*', 28, 7);
    AsciiBlock body2 = new Rect('*', 18, 1);
    AsciiBlock body3 = new Rect('*', 19, 1);
    AsciiBlock leg1 = new Rect('*', 5, 4);
    AsciiBlock leg2 = new Rect('#', 4, 3);
    AsciiBlock leg3 = new Rect('*', 5, 4);
    AsciiBlock leg4 = new Rect('#', 4, 3);

    AsciiBlock ears = new HComp(VAlignment.BOTTOM, bigEar, smallEar);
    AsciiBlock head2 = new VComp(HAlignment.LEFT, ears, new HComp(VAlignment.BOTTOM, head, snout));
    AsciiBlock headAndNeck = new VComp(HAlignment.LEFT, head2, neck);
    AsciiBlock[] pieces = {tail, body1, headAndNeck};
    AsciiBlock topHalf = new HComp(VAlignment.BOTTOM, pieces);
    AsciiBlock legPair1 = new HComp(VAlignment.TOP, leg1, leg2);
    AsciiBlock leftQuarter = new VComp(HAlignment.LEFT, body2, legPair1);
    AsciiBlock legPair2 = new HComp(VAlignment.TOP, leg3, leg4);
    AsciiBlock rightQuarter = new VComp(HAlignment.RIGHT, body3, legPair2);
    AsciiBlock bottomHalf = new HComp(VAlignment.TOP, leftQuarter, rightQuarter);
    AsciiBlock art = new VComp(HAlignment.LEFT, topHalf, bottomHalf);

    AsciiBlock.print(pen, art);
    pen.close();
  } // main(String[])
} // class Art80x24
