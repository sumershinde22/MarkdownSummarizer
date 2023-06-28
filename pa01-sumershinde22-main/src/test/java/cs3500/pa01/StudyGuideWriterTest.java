package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import org.junit.jupiter.api.Test;

/**
 * Test the StudyGuideWriter class
 */
public class StudyGuideWriterTest {
  /**
   * Tests for correct response to an invalid output path
   */
  @Test
  public void testInvalidOutputPath() {
    String studyGuidePath1 =
        "testDirectory/studyGuide";
    StudyGuideWriter writer = new StudyGuideWriter(null);
    assertThrows(IllegalArgumentException.class, () -> writer.writeStudyGuide(
        new File(studyGuidePath1)));
    String studyGuidePath2 =
        "testDirectory/";
    assertThrows(IllegalArgumentException.class, () -> writer.writeStudyGuide(
        new File(studyGuidePath2)));
  }
}
