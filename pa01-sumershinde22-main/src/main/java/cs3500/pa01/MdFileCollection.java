package cs3500.pa01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

/**
 * Represents a collection of MDFiles.
 */
public class MdFileCollection {
  /**
   * The list of MDFiles in the collection.
   */
  ArrayList<MdFile> mdFiles;

  /**
   * Constructs an instance of the MDFileCollection class.
   *
   * @param mdFileCollection the list of MDFiles in the collection.
   */
  public MdFileCollection(ArrayList<MdFile> mdFileCollection) {
    this.mdFiles = mdFileCollection;
  }

  /**
   * Sorts the MDFiles in the collection based on the provided ordering flag.
   *
   * @param orderingFlag the ordering flag, which can be "filename", "created", or "modified".
   * @throws IllegalArgumentException if the ordering flag is not one of "filename", "created", or
   *                                  "modified".
   */
  public void sortMdFiles(String orderingFlag) {
    if (Objects.equals(orderingFlag, "filename")) {
      mdFiles.sort(Comparator.comparing(file -> file.fileName));
    } else if (Objects.equals(orderingFlag, "created")) {
      mdFiles.sort(Comparator.comparing(file -> file.createdTime));

    } else if (Objects.equals(orderingFlag, "modified")) {
      mdFiles.sort(Comparator.comparing(file -> file.modifiedTime));
    } else {
      throw new IllegalArgumentException(
          "The second argument must be filename, created, or modified");
    }
  }

}
