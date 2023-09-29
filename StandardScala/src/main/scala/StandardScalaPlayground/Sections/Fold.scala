package StandardScalaPlayground.Sections

object Fold extends App {

  /** .fold( ) Basics */
    // Fold operations are for accumulating all elements in a collection
    // We specify two sets of parameters ->
    //  1. The value to start the accumulation with, in the first parenthesis - this should match the return type of the intended result
    //  2. The function to accumulate the items, in the second parenthesis
    val someCombo = List("sc", "al", "a").fold("")((accumulator, nextItem) => accumulator + nextItem)
    println(s"someCombo was $someCombo")

    // Above, we started with "", then gave two aliases -> one that represented all the values folded so far, and the other
    // to represent each element in the List. Here, "accumulator" starts at "", then becomes, "sc", then "scal".
    // Using no aliases, the above could be written as:
    val someCombo2 = List("sc", "al", "a").fold("")(_ + _)
    println(s"someCombo2 was the same: $someCombo2")

    // For complex return values with subtypes, you can specify the type of the empty starter value as follows:
    val types = List(Map("one" -> (2, 3))).fold(Map[String, (Int, Int)]())((accumulator, next) => next)
    // Above, we start with an empty Map of type Map[String, (Int, Int)]
    // You could also make a val with the type annotation and use that:
    val starter: Map[String, (Int, Int)] = Map.empty


  /** .foldLeft( ) and .foldRight( ) */
    // .foldLeft( ) is more common than .fold( ), because unlike .fold( ), .foldLeft( ) guarantees that the collection
    // will be processed from left to right:
    val leftFold = List("one", "two", "three").foldLeft("")((accumulator, next) => accumulator + next)
    println(s".foldLeft( ) gave us $leftFold")

    // .foldRight( ) processes the collection from right -> left.
    // Notice that we swap the accumulator's position to the right, because we are starting there:
    val rightFold = List("one", "two", "three").foldRight("")((next, accumulator) => accumulator + next)
    println(s".foldRight( ) gave us $rightFold")


}


object FoldExercises extends App {
  /** Exercises */
  // 1. Create a method that combines two strings. Use .foldLeft( ) to use the method to convert List("Al", "aba", "ma") into one value

  // 2. Using the same method from #1, use .foldRight to convert ("ma", "aba", "Al") into one value

}






/** Exercise Solutions */
// 1. def combine(str1: String, str2: String) = {
//      str1 + str2
//    }
//    List("Al", "aba", "ma").foldLeft("")((accum, next) => combine(accum, next))

// 2. List("ma", "aba", "Al") .foldRight("")((next, accum) => combine(accum, next))

