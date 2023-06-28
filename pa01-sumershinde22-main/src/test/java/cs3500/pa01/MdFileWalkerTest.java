package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

/**
 * Tests the MdFileWalker class
 */
public class MdFileWalkerTest {
  /**
   * Tests for correct response to when visiting a file fails
   */
  @Test
  public void testVisitFileFailed() {
    MdFileWalker fileWalker = new MdFileWalker();
    Path dummyPath = Path.of("dummy");
    IOException ioException = new IOException();
    FileVisitResult result = fileWalker.visitFileFailed(dummyPath, ioException);
    assertEquals(FileVisitResult.CONTINUE, result);
  }
}
