package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 * Test the Driver class
 */
class DriverTest {

  /**
   * Tests for correct output document with filename ordering flag
   */
  @Test
  public void testFileNameOrdering() {
    try {
      String mdRootDirectory = "testDirectory";
      String orderingFlag = "filename";
      String studyGuidePath = "outputStudyGuides/studyGuideFilenameOrder.md";
      String[] mainArray = {mdRootDirectory, orderingFlag, studyGuidePath};
      Driver.main(mainArray);
      String testStudyGuide = "src/test/resources/studyGuideFilename.md";

      String file1Content = readFileContent(testStudyGuide);
      String file2Content = readFileContent(studyGuidePath);
      assertEquals(file1Content, file2Content);
    } catch (IOException e) {
      System.err.println("Error detected in reading file");
      System.exit(1);
    }
  }

  //The following test works on a local device, but not on GitHub due to the fact that
  // values like creation and last modified time are automatically controlled and set by the system
  //
  //  /**
  //   * Tests for correct output document with creation ordering flag
  //   */
  //  @Test
  //  public void testCreationTimeOrdering() {
  //    try {
  //      String mdRootDirectory = "testDirectory";
  //      String orderingFlag = "created";
  //      String studyGuidePath = "outputStudyGuides/studyGuideCreatedOrder.md";
  //      String[] mainArray = {mdRootDirectory, orderingFlag, studyGuidePath};
  //      Driver.main(mainArray);
  //      String testStudyGuide = "src/test/resources/studyGuideCreated.md";
  //
  //      String file1Content = readFileContent(testStudyGuide);
  //      String file2Content = readFileContent(studyGuidePath);
  //      System.out.println(file1Content);
  //      System.out.println(file2Content);
  //      assertEquals(file1Content, file2Content);
  //    } catch (IOException e) {
  //      System.err.println("Error detected in reading file");
  //      System.exit(1);
  //    }
  // }

  /**
   * Tests for correct output document with creation ordering flag
   */
  @Test
  public void testCreationTimeOrdering() {
    try {

      Map<String, ArrayList<String>> importantInfo1 = new LinkedHashMap<>();
      ArrayList<String> alist1 = new ArrayList<>();
      alist1.add("ArrayList is a Java class implemented using the List interface");
      importantInfo1.put("# ArrayList in Java", alist1);
      ArrayList<String> alist2 = new ArrayList<>();
      alist2.add("AbstractList Class and implement only the get() and the size() methods");
      importantInfo1.put("## AbstractList", alist2);
      ArrayList<String> alist3 = new ArrayList<>();
      alist3.add("making a fresh copy of the list");
      importantInfo1.put("## CopyOnWriteArrayList", alist3);
      ArrayList<MdFile> mdFiles = new ArrayList<>();
      mdFiles.add(new MdFile(Path.of("testDirectory/ArrayList/ArrayList.md"), "ArrayList.md",
          importantInfo1,
          FileTime.from(Instant.parse("2023-05-13T20:08:12Z")),
          FileTime.from(Instant.parse("2023-05-13T20:08:12Z"))));

      Map<String, ArrayList<String>> importantInfo2 = new LinkedHashMap<>();
      ArrayList<String> blist1 = new ArrayList<>();
      blist1.add("An **array** is a collection of variables of the same type");
      importantInfo2.put("# Java Arrays", blist1);
      ArrayList<String> blist2 = new ArrayList<>();
      blist2.add("General Form: type[] arrayName;");
      blist2.add("only creates a reference");
      importantInfo2.put("## Declaring an Array", blist2);
      ArrayList<String> blist3 = new ArrayList<>();
      blist3.add("General form:  arrayName = new type[numberOfElements];");
      blist3.add("numberOfElements must be a positive Integer.");
      blist3.add("Gotcha: Array size is not modifiable once instantiated.");
      importantInfo2.put("## Creating an Array (Instantiation)", blist3);
      mdFiles.add(new MdFile(Path.of("testDirectory/Arrays/Arrays.md"), "Arrays.md", importantInfo2,
          FileTime.from(Instant.parse("2023-05-13T02:52:03Z")),
          FileTime.from(Instant.parse("2023-05-13T02:52:03Z"))));

      Map<String, ArrayList<String>> importantInfo3 = new LinkedHashMap<>();
      ArrayList<String> clist1 = new ArrayList<>();
      clist1.add("Vectors act like resizable arrays");
      importantInfo3.put("# Vectors", clist1);
      ArrayList<String> clist2 = new ArrayList<>();
      clist2.add("General Form: Vector<type> v = new Vector();");
      clist2.add("type needs to be a valid reference type");
      importantInfo3.put("## Declaring a vector", clist2);
      ArrayList<String> clist3 = new ArrayList<>();
      clist3.add("v.add(object of type);");
      importantInfo3.put("## Adding an element to a vector", clist3);
      mdFiles.add(
          new MdFile(Path.of("testDirectory/Vectors/Vectors.md"), "ArrayList.md", importantInfo3,
              FileTime.from(Instant.parse("2023-05-15T17:17:26Z")),
              FileTime.from(Instant.parse("2023-05-15T17:17:26Z"))));
      MdFileCollection mdFileCollection = new MdFileCollection(mdFiles);
      String orderingFlag = "created";
      String studyGuidePath = "outputStudyGuides/studyGuideCreatedOrder.md";
      String testStudyGuide = "src/test/resources/studyGuideCreated.md";
      mdFileCollection.sortMdFiles(orderingFlag);
      StudyGuideWriter studyGuide = new StudyGuideWriter(mdFileCollection);
      studyGuide.writeStudyGuide(new File(studyGuidePath));
      String file1Content = readFileContent(testStudyGuide);
      String file2Content = readFileContent(studyGuidePath);
      assertEquals(file1Content, file2Content);
    } catch (IOException e) {
      System.err.println("Error detected in reading file");
      System.exit(1);
    }
  }

  //The following test works on a local device, but not on GitHub due to the fact that
  // values like creation and last modified time are automatically controlled and set by system
  //
  //  /**
  //   * Tests for correct output document with modified ordering flag
  //   */
  //  @Test
  //  public void testModificationTimeOrdering() {
  //    try {
  //      String mdRootDirectory = "testDirectory";
  //      String orderingFlag = "modified";
  //      String studyGuidePath = "outputStudyGuides/studyGuideModifiedOrder.md";
  //      String[] mainArray = {mdRootDirectory, orderingFlag, studyGuidePath};
  //      Driver.main(mainArray);
  //      String testStudyGuide = "src/test/resources/studyGuideModified.md";
  //
  //      String file1Content = readFileContent(testStudyGuide);
  //      String file2Content = readFileContent(studyGuidePath);
  //      assertEquals(file1Content, file2Content);
  //    } catch (IOException e) {
  //      System.err.println("Error detected in reading file");
  //      System.exit(1);
  //    }
  //  }

  /**
   * Tests for correct output document with modified ordering flag
   */
  @Test
  public void testModificationTimeOrdering() {
    try {
      Map<String, ArrayList<String>> importantInfo1 = new LinkedHashMap<>();
      ArrayList<String> alist1 = new ArrayList<>();
      alist1.add("ArrayList is a Java class implemented using the List interface");
      importantInfo1.put("# ArrayList in Java", alist1);
      ArrayList<String> alist2 = new ArrayList<>();
      alist2.add("AbstractList Class and implement only the get() and the size() methods");
      importantInfo1.put("## AbstractList", alist2);
      ArrayList<String> alist3 = new ArrayList<>();
      alist3.add("making a fresh copy of the list");
      importantInfo1.put("## CopyOnWriteArrayList", alist3);
      ArrayList<MdFile> mdFiles = new ArrayList<>();
      mdFiles.add(new MdFile(Path.of("testDirectory/ArrayList/ArrayList.md"), "ArrayList.md",
          importantInfo1,
          FileTime.from(Instant.parse("2023-05-13T20:08:12Z")),
          FileTime.from(Instant.parse("2023-05-13T20:16:10.966753036Z"))));

      Map<String, ArrayList<String>> importantInfo2 = new LinkedHashMap<>();
      ArrayList<String> blist1 = new ArrayList<>();
      blist1.add("An **array** is a collection of variables of the same type");
      importantInfo2.put("# Java Arrays", blist1);
      ArrayList<String> blist2 = new ArrayList<>();
      blist2.add("General Form: type[] arrayName;");
      blist2.add("only creates a reference");
      importantInfo2.put("## Declaring an Array", blist2);
      ArrayList<String> blist3 = new ArrayList<>();
      blist3.add("General form:  arrayName = new type[numberOfElements];");
      blist3.add("numberOfElements must be a positive Integer.");
      blist3.add("Gotcha: Array size is not modifiable once instantiated.");
      importantInfo2.put("## Creating an Array (Instantiation)", blist3);
      mdFiles.add(new MdFile(Path.of("testDirectory/Arrays/Arrays.md"), "Arrays.md", importantInfo2,
          FileTime.from(Instant.parse("2023-05-13T02:52:03Z")),
          FileTime.from(Instant.parse("2023-05-13T04:01:10.23886289Z"))));

      Map<String, ArrayList<String>> importantInfo3 = new LinkedHashMap<>();
      ArrayList<String> clist1 = new ArrayList<>();
      clist1.add("Vectors act like resizable arrays");
      importantInfo3.put("# Vectors", clist1);
      ArrayList<String> clist2 = new ArrayList<>();
      clist2.add("General Form: Vector<type> v = new Vector();");
      clist2.add("type needs to be a valid reference type");
      importantInfo3.put("## Declaring a vector", clist2);
      ArrayList<String> clist3 = new ArrayList<>();
      clist3.add("v.add(object of type);");
      importantInfo3.put("## Adding an element to a vector", clist3);
      mdFiles.add(
          new MdFile(Path.of("testDirectory/Vectors/Vectors.md"), "ArrayList.md", importantInfo3,
              FileTime.from(Instant.parse("2023-05-15T17:17:26Z")),
              FileTime.from(Instant.parse("2023-05-15T17:17:29.036147479Z"))));
      MdFileCollection mdFileCollection = new MdFileCollection(mdFiles);
      String orderingFlag = "modified";
      String studyGuidePath = "outputStudyGuides/studyGuideModifiedOrder.md";
      String testStudyGuide = "src/test/resources/studyGuideModified.md";
      mdFileCollection.sortMdFiles(orderingFlag);
      StudyGuideWriter studyGuide = new StudyGuideWriter(mdFileCollection);
      studyGuide.writeStudyGuide(new File(studyGuidePath));
      String file1Content = readFileContent(testStudyGuide);
      String file2Content = readFileContent(studyGuidePath);
      assertEquals(file1Content, file2Content);
    } catch (IOException e) {
      System.err.println("Error detected in reading file");
      System.exit(1);
    }
  }

  private static String readFileContent(String filePath) {
    StringBuilder sb = new StringBuilder();
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        sb.append(line).append(System.lineSeparator());
      }
    } catch (IOException e) {
      System.err.println("Error reading file.");
    }
    return sb.toString();
  }

  /**
   * Tests for correct response of not 3 arguments
   */
  @Test
  public void testArguments() {
    String[] args2 = {"notes/root", "1"};
    assertThrows(IllegalArgumentException.class, () -> Driver.main(args2));
  }
}