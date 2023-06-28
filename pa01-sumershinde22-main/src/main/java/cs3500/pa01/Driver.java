package cs3500.pa01;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * This is the main driver of this project.
 */
public class Driver {

  private static final String ARGUMENT_EXCEPTION_MESSAGE =
      "Please include all command arguments in the order of root of the notes, ordering flag,"
          + " and output path for the study guide.";

  /**
   * Project entry point
   *
   * @param args - no command line args required
   */
  public static void main(String[] args) throws IOException {
    // Check if 3 arguments are present
    if (args.length != 3) {
      throw new IllegalArgumentException(ARGUMENT_EXCEPTION_MESSAGE);
    }

    String notesRoot = args[0];
    String orderingFlag = args[1];
    String outputPath = args[2];
    Path notesRootPath = Path.of(notesRoot);
    MdFileWalker mdFileWalker = new MdFileWalker();
    Files.walkFileTree(notesRootPath, mdFileWalker);

    FilesReader fileReader = new FilesReader(mdFileWalker.mdFilePaths);
    ArrayList<MdFile> mdFiles = fileReader.createMdFiles();
    MdFileCollection mdFileCollection = new MdFileCollection(mdFiles);
    mdFileCollection.sortMdFiles(orderingFlag);

    StudyGuideWriter studyGuide = new StudyGuideWriter(mdFileCollection);
    studyGuide.writeStudyGuide(new File(outputPath));
  }
}
