package cs3500.pa01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The FilesReader class reads and processes Markdown files to create instances of MDFile.
 */
public class FilesReader {
  /**
   * An ArrayList containing the paths of the Markdown files to be read and processed.
   */
  ArrayList<Path> mdFilePaths;

  /**
   * Constructs a FilesReader object with the specified list of Markdown file paths.
   *
   * @param mdFilePaths An ArrayList containing the paths of the Markdown files to be read and
   *                    processed.
   */
  public FilesReader(ArrayList<Path> mdFilePaths) {
    this.mdFilePaths = mdFilePaths;

  }

  private Map<String, ArrayList<String>> getImportantText(Path mdPath) {
    Map<String, ArrayList<String>> importantInfo = new LinkedHashMap<>();
    ArrayList<String> fileContent = readFileContent(mdPath);
    List<List<String>> splitArrayList = new ArrayList<>();
    List<String> currentSublist = new ArrayList<>();

    for (String item : fileContent) {
      if (item.contains("#")) {
        if (!currentSublist.isEmpty()) {
          splitArrayList.add(currentSublist);
          currentSublist = new ArrayList<>();
        }
      }
      currentSublist.add(item);
    }

    if (!currentSublist.isEmpty()) {
      splitArrayList.add(currentSublist);
    }

    for (List<String> list : splitArrayList) {
      String key = list.get(0);
      list.remove(0);
      StringBuilder sb = new StringBuilder();
      for (String item : list) {
        sb.append(item);
      }
      importantInfo.put(key, extractStrings(sb.toString()));
    }
    return importantInfo;
  }

  private static ArrayList<String> extractStrings(String input) {
    ArrayList<String> extractedStrings = new ArrayList<>();

    // Define the pattern to match the desired content within double square brackets
    Pattern pattern = Pattern.compile("\\[\\[(.*?)]]");
    Matcher matcher = pattern.matcher(input);

    // Find all matches and extract the captured groups
    while (matcher.find()) {
      String extractedString = matcher.group(1);
      extractedStrings.add(extractedString);
    }

    return extractedStrings;
  }

  private static ArrayList<String> readFileContent(Path filePath) {
    ArrayList<String> contents = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()))) {
      String line;
      while ((line = br.readLine()) != null) {
        contents.add(line);
      }
    } catch (IOException e) {
      System.err.println("Error reading file.");
    }
    return contents;
  }

  private FileTime getCreationTime(Path mdPath) throws IOException {
    BasicFileAttributes attributes = Files.readAttributes(mdPath, BasicFileAttributes.class);
    return attributes.creationTime();
  }

  private FileTime getModificationTime(Path mdPath) throws IOException {
    BasicFileAttributes attributes = Files.readAttributes(mdPath, BasicFileAttributes.class);
    return attributes.lastModifiedTime();
  }

  /**
   * Creates and returns an ArrayList of MDFile objects corresponding to the Markdown files
   * specified in the constructor.
   *
   * @return MDFile ArrayList objects corresponding to the Markdown files specified in constructor.
   * @throws IOException If an error occurs while reading any of the files.
   */
  public ArrayList<MdFile> createMdFiles() throws IOException {
    ArrayList<MdFile> mdFiles = new ArrayList<>();
    for (Path mdPath : mdFilePaths) {
      String fileName = mdPath.getFileName().toString();
      Map<String, ArrayList<String>> importantInfo = getImportantText(mdPath);
      FileTime creationTime = getCreationTime(mdPath);
      FileTime modificationTime = getModificationTime(mdPath);
      MdFile mdFile =
          new MdFile(mdPath, fileName, importantInfo, creationTime, modificationTime);
      mdFiles.add(mdFile);
    }
    return mdFiles;
  }
}
