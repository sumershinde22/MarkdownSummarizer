package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Test the MdFileCollectionTest class
 */
public class MdFileCollectionTest {
  /**
   * Test for an invalid ordering flag as a command line parameter
   */
  @Test
  public void testInvalidOrderingFlag() {
    String mdRootDirectory =
        "/Users/sumershinde/Desktop/Sumer/College/Summer/CS3500/pa01-sumershinde22/testDirectory";
    String orderingFlag = "null";
    String studyGuidePath =
        "/Users/sumershinde/Desktop/Sumer/College/Summer/CS3500/pa01-sumershinde22/testDirectory/"
            + "studyGuide.md";
    String[] mainArray = {mdRootDirectory, orderingFlag, studyGuidePath};
    assertThrows(IllegalArgumentException.class, () -> Driver.main(mainArray));
  }
}
