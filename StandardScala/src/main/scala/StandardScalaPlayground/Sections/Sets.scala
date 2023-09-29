package StandardScalaPlayground.Sections

object Sets extends App {
  // Sets are essentially Lists with only unique elements

  /** Set Basics */
    // Sets will automatically drop duplicate elements
    val someSet = Set(1, 2, 3, 3)
    println(s"The set is $someSet")


  /** Getting Items from Sets */
    val getSet = Set("a", "b", "c")

    // Get first item, all but first item (tail):
    println(s"Head was ${getSet.head}, tail was ${getSet.tail}")

    // Unlike List, you cannot get an element at a particular index
    // Instead, below is equivalent to .contains:
    println(s"getSet('a') was ${getSet("a")}")


  /** Adding to/removing from Sets */
    // Add to a Set with +
    val addSet: Set[String] = Set("a", "b", "c") + "d"
    println(s"Added set is $addSet")

    // Remove from a Set with -
    val subtractSet = Set("a", "b", "c") - "c"
    println(s"Subtracted set is $subtractSet")


  /** Set Equality */
    // Unlike lists, Sets do not have to have elements in the same order to be equal:
    println(s"Order matters in Lists, so result was: ${List(1, 2) == List(2, 1)}")
    println(s"Order does not matter in Sets, so result was: ${Set(1, 2) == Set(2, 1)}")

    // Set contains much the same functionality as List, beyond the above examples
    println(s"Set contained 'a': ${subtractSet.contains("a")}")
    println(s"Set size was ${subtractSet.size}")
    // etc...

}


object SetsExercises extends App {
  /** Exercises */
  // 1. Write a type annotation for a Set of Tuples, where each Tuple has an Integer and a String

  // 2. Write a function to check if a Set[Int] contains an element: Int.
  // If it does, remove that item from the Set, and return the modified Set


}






/** Exercise Solutions */
// 1. val typing: Set[(Int, String)] = Set.empty

// 2. def cutOff(someSet: Set[Int], candidate: Int) = {
//      if (someSet.contains(candidate)) someSet - candidate
//      else someSet
//    }

