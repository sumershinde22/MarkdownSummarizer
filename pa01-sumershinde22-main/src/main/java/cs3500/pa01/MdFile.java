package cs3500.pa01;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Map;

/**
 * Represents a Markdown file with its associated metadata.
 */
public class MdFile {
  /**
   * The path of the Markdown file.
   */
  public Path filePath;
  /**
   * The name of the Markdown file.
   */
  public String fileName;
  /**
   * The important text of the Markdown file.
   */
  public Map<String, ArrayList<String>> importantText;
  /**
   * The creation time of the Markdown file.
   */
  public FileTime createdTime;
  /**
   * The modification time of the Markdown file.
   */
  public FileTime modifiedTime;

  /**
   * Constructs an instance of the MDFile class.
   *
   * @param filePath      the path of the Markdown file.
   * @param fileName      the name of the Markdown file.
   * @param importantText the important text of the Markdown file.
   * @param createdTime   the creation time of the Markdown file.
   * @param modifiedTime  the modification time of the Markdown file.
   */
  public MdFile(Path filePath, String fileName, Map<String, ArrayList<String>> importantText,
                FileTime createdTime, FileTime modifiedTime) {
    this.filePath = filePath;
    this.fileName = fileName;
    this.importantText = importantText;
    this.createdTime = createdTime;
    this.modifiedTime = modifiedTime;
  }
}
