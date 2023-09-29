package StandardScalaPlayground.Sections

object Filter extends App {

  /** .filter( ) Basics */
    // .filter( ) applies a condition to each element in a collection, and returns a new
    // collection of the elements that satisfied the condition:
    val someList = List(1, 2, 3, 4).filter(x => x % 2 == 0)
    // Above, if the number is even, the condition will evaluate to true, and the number will be kept

    println(s"Filtered list was $someList")

    // Filter all the tuples with None out:
    val trimmed = List((14, None), ("x", "y"), ("x", None)).filter {
      case(x, y) => y != None
    }
    println(s"The filter removed the Nones: $trimmed")

    // Underscores, as always, can represent an element:
    val underscore = List(1, 2, 3).filter(_ < 3)
    println(s"Underscore was $underscore")


  /** Example with a function */
    // Filter out everything less than 3
    def lessThree(input: Int): Boolean = {
      if(input < 3) true
      else false
    }

    val result = List(0, 1, 2, 3, 4).filter(lessThree)
    println(s"Result of lessThree was $result")


  /** Multi-line .filter( ) */
    // Keep only the evens. Notice we use .filter{ } instead of .filter( ) for multi-line expressions
    val evens = List("1", "2", "3").filter { x =>
      val intermediate = x.toInt
      intermediate % 2 == 0
    }
    println(s"Evens were $evens")


  /** .filterNot( ) */
    // Depending on whether it makes your code easier to read, you can use .filterNot to only keep
    // elements NOT satisfying the given predicate:
    val filterNot = List(1, 2, 3).filterNot( _ % 2 == 0)    // if even, true, so discard
    println(s"Only kept the odds: $filterNot")


}


object FilterExercises extends App {
  /** Exercises */
  // 1. Filter out everything except for the booleans in List(true, "string", false, 3). Do not use an alias

  // 2. Create a function that returns true if a string with 5 or more characters is passed to it.
  // Then, use that function to filter List("test", "test1", "test", "test2"), and print each element as you filter.

  // 3. Filter List(List(1, 2, 3),  List(4, 5, 6)) so that the top List only contains Lists with 2 or more even elements
}






/** Exercise Solutions */
// 1. List(true, "string", false, 3).filter(
//    _.isInstanceOf[Boolean]
//  )
//Or, could have:
//  List(true, "string", false, 3).filter( element =>
//    element == true || element == false
//  )

// 2. def fiveOrMore(str: String): Boolean = str.size >= 5
//  List("test", "test1", "test", "test2").filter { element =>
//    println(s"Testing $element")
//    fiveOrMore(element)
//  }

// 3. List(List(1, 2, 3),  List(4, 5, 6)).filter { innerList =>
//    innerList.filter(element =>
//      element % 2 == 0
//    ).length >= 2
//  }


