package StandardScalaPlayground.Sections

object Collect extends App {

  /** .collect{ } Basics */
    // .collect{ } is a way to transform a collection composed of various potential types, according to rules given
    // for each of the potential cases:

    val result = List((1, "1"), (2, "2"), List(1, 2, 3, 4), Set(1, 2, 3)).collect {
      case (num: Int, str: String) => num + str.toInt
      case list: List[Int] => list.sum
    }

    println(s"Result was $result")
    // Above, only the elements that matched one of the given cases had the respective function applied to them.
    // Elements not matching a case are ignored, and not added to the resulting collection

    // If you were to try the same with .map{ }, you would need not a partial function, but a function that can handle
    // each possible case. You would receive an error once it got to the Set( ), since there is no case defined for it


  /** .collect{ }.toMap */
    // Suppose we have a List of tuples, created from a previous operation:
    val listTuples: List[(String, List[Int])] = List(("first", List(1, 2, 3)), ("second", List(4, 5)))

    // We can use .collect{ } to create a Map, while also transforming the content:
    val newMap = listTuples.collect {
      case (key: String, value: List[Int]) => (key, value.map(_ * 2))
    }.toMap

    println(s"newMap was $newMap")


}


object CollectExercises extends App {
  /** Exercises */
  // 1. Use .collect{ } to transform List(1, 2, false, 3) into List(2, 4, 6)

  // 2. Use .collect{ } to transform List(("first", List(1, 2, 3), true), ("second", List(4, 5, 6), true), ("second", List(23, 1), true))
  //    into a collection, removing any elements with duplicate first values in the tuple

}






/** Exercise Solutions */
// 1. List(1, 2, false, 3).collect{
//      case int: Int => int * 2
//    }

// 2. List(("first", List(1, 2, 3), true), ("second", List(4, 5, 6), true), ("second", List(23, 1), true)).collect {
//      case (str: String, list: List[Int], _: Boolean) => (str, list)
//    }.toMap