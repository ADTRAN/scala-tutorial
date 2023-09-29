package StandardScalaPlayground.Sections

object MapOperations extends App {

  /** .map( ) Basics */
    // First note that .map( ) has nothing to do with the collection of the same name, Map( ), so don't get confused

    // .map( ) operations apply a function to each member of a collection, and return the new modified collection:
    val strList = List("1", "2", "3")
    val intList = strList.map(someNum => someNum.toInt)
    // Above, the alias "someNum" is given to each element in the list
    // This map is read as "For each element "someNum" in the list, apply .toInt"

    println(s"The intList is $intList")

    // Notice that we had to create a new List[Int] to store the result of .map( )
    // .map( ) does not modify the original List. If you don't create a new val to store the result, only the side effects will occur

    // We already saw how to give each element an alias with the =>
    // You can also use the _ to signify each element in the collection, without needing to alias:
    val noAlias = List("1", "2", "3").map(_.toInt)
    println(s"No alias was $noAlias")

    // If you are nesting other operations inside the .map( ), using _ can get confusing, so giving an alias is recommended


  /** Mapping collections with complex types & multi-line functions */
    // A .map( ) function on a List of Tuples:
    val tupleList: List[(Int, Int)] = List((1, 1), (2, 2), (3, 3))
    val tupleResult = tupleList.map {
      case (x, y) => x + y
    }
    println(s"The modified tuple List was $tupleResult")
    // Notice above that because our alias needed to be a case ( ) to accommodate the tuple, we also had to use map{ } instead of map( )

    // We also have to use the { } any time our map function is multiple lines:
    println("Result of multiple lines example:")
    val multiLine = List(1, 3).map { x =>
      println(x)
      println(x * 2)
    }

    // The same is true when working with .flatMap, .filter, .foreach, .foldLeft, .foldRight, etc.


  /** A basic .map( ) transformation function -> see [[MatchCase]] for more details*/
    // Below we use .map( ) in a function similar to a match-case statement. If the element is a Tuple of type (String, Int), we will make a String
    // If the element is any other type, we will replace it with the string "some other type"
    def mixedMap(list: List[Any]): List[String] = {
      list.map {
        case (a: String, b: Int) => a + b.toString
        case _ => "some other type"   // Here the _ means "in the case of any other type"
      }
    }

    println(s"MixedMap first result was ${mixedMap(List(("a", 1), ("b", 2), ("c", 3)))}")
    println(s"MixedMap second result was ${mixedMap(List(1))}")


  /** Nested .map( ) example */
    println("Nested example:")
    List(List(1, 2), List(10, 20), List(100, 200)).map(nestedList => // For each nested List
      nestedList.map( element =>  // For each element in the nested List
        println(element)
      )
    )

}


object MapExercises extends App {
  /** Exercises */
  // 1. Turn List(1, 2, 3) into List(2, 4, 6). Use an alias for each element, then do the same without an alias

  // 2. Turn List(1, 2, 3) into a List of Booleans

  // 3. Turn each element of List("1", "2", "3") into an integer, then in a separate statement in the same map, print whether each element is even or not

  // 4. Add up all the elements in List(List(1, 2, 3), List(4, 5, 6)). Only use one alias.
}






/** Exercise Solutions */
// 1a. List(1, 2, 3).map(x => x * 2)
// 1b. List(1, 2, 3).map(_ * 2)

// 2. List(1, 2, 3).map(x => x.isInstanceOf[Int]) or x.isWhole
// Or, simply:
//   List(1, 2, 3).map(x => true)
// Could do something like:
//  val boolean = true
//  List(1, 2, 3).map(x => boolean)
// Or boolean could be:
//  def boolean = true    --> small function that just returns true
// Easter egg - see what happens when you define boolean as a val *after* your .map( ) (order declaration matters)

// 3. List("1", "2", "3").map { x =>
//      val intermediate = x.toInt
//      val isEven = intermediate % 2 == 0
//      println(isEven)
//    }

// 4. var count = 0
//  List(List(1, 2, 3), List(4, 5, 6)).map( list =>
//    list.map(
//      count += _
//    )
//  )
//
//  Or, could do:
//  List(List(1, 2, 3), List(4, 5, 6)).map( list =>
//    list.sum
//  ).sum
// We .sum each list, then we have a list of ints, so we can .sum that now too


