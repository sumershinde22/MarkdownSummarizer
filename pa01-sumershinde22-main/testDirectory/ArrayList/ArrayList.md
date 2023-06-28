# ArrayList in Java

- [[ArrayList is a Java class implemented using the List interface]]
- Java ArrayList, as the name suggests, provides the functionality of a dynamic array where the size is not fixed as an
  array

## AbstractList
- This class is used to implement an unmodifiable list, for which one needs to only extend this [[AbstractList Class and implement only the get() and the size() methods]].

## CopyOnWriteArrayList
- This class implements the list interface. It is an enhanced version of ArrayList in which all the modifications(add, set, remove, etc.) are implemented by [[making a fresh copy of the list]].