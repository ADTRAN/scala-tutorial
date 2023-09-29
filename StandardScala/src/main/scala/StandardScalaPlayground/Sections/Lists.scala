package StandardScalaPlayground.Sections

import scala.collection.mutable.ListBuffer

object Lists extends App {
  /** List basics */
    // Creating a new List:
    val someList = List("elem1, elem2, elem3")

    // Creating an empty List:
    val emptyList = List.empty
    val anotherEmptyList = List()

    // Giving your List a type:
    val aStringList: List[String] = List("elem1, elem2, elem3")
    val anIntList: List[Int] = List(1, 2, 3)
    val mixedList: List[Any] = List("a string", 22, true)


  /** Adding to Lists */
    // Combining two lists:
    val combo1 = List(1, 2, 3) ::: List(4, 5, 6)
    println(s"The combined list is $combo1")
    // Note that above, order matters, because order matters in Lists. If we switched the order of the Lists, we would have different results.
    // The same goes for all the operations below

    // Could also combine two Lists with:
    val combo2 = List(1, 2, 3) ++ List(1, 2, 3)
    println(s"The second combined list is $combo2")

    val combo3 = List(10, 20, 30).concat(List(40, 50, 60))
    println(s"The third combined list is $combo3")

    val combo4 = List(100, 200, 300).appendedAll(List(400, 500, 600))
    println(s"The fourth combined list is $combo4")

    // Prepend to a List - note again that argument order matters - the List must go second:
    val prependedList = 1 +: List(2, 3)
    val prependedList2 = 1 :: List(2, 3)
    println(s"The prepended list is $prependedList")

    // Appending to a List:
    val appendedList = List(1, 2, 3) :+ 4
    val appendedList2 = List(1, 2, 3) ::: List(4)
    val appendedList3 = List(1, 2, 3).appendedAll(List(4))
    println(s"The appended list is $appendedList")

  /** Removing from a List is primarily done via [[Filter]] */


  /** Accessing a List's elements */
    val accessList = List("element1", "element2", "element3")

    // Get the first item:
    println(s"First item was ${accessList(0)}")
    println(s"First item again was ${accessList.head}")

    // Get the last item:
    println(s"Last item was ${accessList.last}")

    // Get a specific element - Lists are zero-indexed:
    println(s"Index 0: ${accessList(0)}, Index 1: ${accessList(1)}, Index 2: ${accessList(2)}")

    // Check if an element is in a List
    val check = List(1, 2, 3).contains(3)
    println(s"$check - List contained the element")


  /** ListBuffers */
    // If you need a truly mutable List, use a ListBuffer:
    var mutableList = new ListBuffer[String]()
    mutableList += "2"
    mutableList.addOne("3")
    mutableList.prepend("1")
    mutableList.append("4")
    println(s"Our mutable list is $mutableList")

    // Removing from a ListBuffer:
    mutableList -= "2"
    println(s"Our mutable list, after a removal, is $mutableList")
    mutableList.remove(2)
    println(s"Our mutable list, after another removal, is $mutableList")


  /** Other operations with Lists */
    val operationList = List(1, 2, 3, 4, 5)

    // Get size/length of List
    println(s".size of operations list was ${operationList.size}, which is the same as .length - ${operationList.length}")

    // Get new List with .head (first element) removed
    println(s"Tail of operations list was ${operationList.tail}")

    // Some basic math operations like:
    println(s"The min was ${operationList.min}, the max was ${operationList.max}, the sum was ${operationList.sum}")

    // As always, making a list and then putting a dot . afterwards will have IntelliJ prompt you with all the operations available to you.

}


object ListsExercises extends App {
  /** Exercises */
  // 1. Get the first value from List(1, 2, 3) using 2 different methods

  // 2. Create a recursive function to print one element of a List at a time.
        // Hint - use .head, .tail

  // 3. Starting with List(2, 3), prepend 1, and append 4, on one line

  // 4. Give the type annotation of a List that can hold a List of Booleans or Integers


}






/** Exercise Solutions */
// 1. List(1, 2, 3).head
//    List(1, 2, 3)(0)

// 2.  def recurse(list: List[Int]): Unit = {
//       if(list.size > 0) {
//         println(list.head)
//         recurse(list.tail)
//       }
//    }
//
//    recurse(List(1, 2, 3))

// 3. val result = 1 +: List(2, 3) :+ 4
//    println(result)

// 4. val mixed: List[List[Any]] = List.empty

