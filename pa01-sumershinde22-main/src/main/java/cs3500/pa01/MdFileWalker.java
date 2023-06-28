package cs3500.pa01;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

/**
 * A file visitor implementation for visiting Markdown files (.md) and storing their paths in an
 * ArrayList.
 */
public class MdFileWalker implements FileVisitor<Path> {
  /**
   * An ArrayList to store all the paths of files with .md extension.
   */
  ArrayList<Path> mdFilePaths;

  /**
   * Constructs a new MdFileWalker object with an empty ArrayList.
   */
  public MdFileWalker() {
    this.mdFilePaths = new ArrayList<>();
  }

  /**
   * Called before visiting a directory. Logs the name of the directory being visited.
   *
   * @param dir   the path of the directory being visited
   * @param attrs the basic attributes of the directory
   * @return CONTINUE to visit the directory
   */
  @Override
  public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
    System.out.println("Starting Directory: " + dir.toString());
    return FileVisitResult.CONTINUE;
  }

  /**
   * Adds the path of a file to the ArrayList if the file has .md extension and is a regular file.
   *
   * @param file  the file to be visited
   * @param attrs the attributes of the file
   * @return CONTINUE
   */
  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
    if (file.toString().endsWith(".md") && attrs.isRegularFile()) {
      mdFilePaths.add(file);
    }
    System.out.println("Visited File at Path: " + file);
    return FileVisitResult.CONTINUE;
  }

  /**
   * Continues visiting other files if a file cannot be accessed or does not exist.
   *
   * @param file the file that failed to be visited
   * @param exc  the exception thrown
   * @return CONTINUE
   */
  @Override
  public FileVisitResult visitFileFailed(Path file, IOException exc) {
    return FileVisitResult.CONTINUE;
  }

  /**
   * Called after visiting a directory. Logs the name of the directory being visited.
   *
   * @param dir the path of the directory being visited
   * @param exc the exception thrown
   * @return CONTINUE to visit the directory
   */
  @Override
  public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
    System.out.println("Finished Directory: " + dir.toString());
    return FileVisitResult.CONTINUE;
  }
}
