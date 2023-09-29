package StandardScalaPlayground.Sections

object Types extends App {

  /** Type Basics */
    // Types follow a variable name, with a colon in between:
    val anInt: Int = 0
    val aString: String = "abc"
    val aChar: Char = 'a'
    val aBoolean: Boolean = true

    // Types that have a subtype - a List of Strings, for instance - place their subtype(s) in [ ]
    val listOfStrings: List[String] = List.empty
    val listOfListsOfStrings: List[List[String]] = List.empty
    val tupleOfIntString: Tuple2[Int, String] = (1, "val")


  /** Types in Methods */
    // The return type of a function is placed after the arguments in the signature
    // Take one Int, return a String:
    def someFunction (arg1: Int): String = {
      arg1.toString
    }


  /** Is Specifying Types Optional? */
    // The compiler is smart enough to infer types in many cases:
    val str = "Hello"

    // However, there are cases where this will not be possible. Leaving off types also makes code harder to comprehend.
    // Therefore, it's good practice to explicitly specify your types, especially in production code that others will need
    // to understand later.

}


object TypesExercises extends App {
  /** Exercises */
  // 1. Initialize 3 vals, one of type String, Int, and Boolean

  // 2. Initialize a List of type Boolean

  // 3. Initialize a function that takes a String, a Boolean, and an Int, and returns an Int

  // 4. Initialize a List of Tuples made of Int, List of Ints, String

}






/** Exercise Solutions */
// 1. val one: String = ""
//    val two: Int  = 0
//    val three: Boolean = true

// 2. val bool: List[Boolean] = List.empty

// 3. def function(str: String, bool: Boolean, int: Int): Int = {
//      0
//    }

//4.  val someList: List[(Int, List[Int], String)] = List.empty
//    Or
//    val someList: List[Tuple3[Int, List[Int], String]]
//    Or
//    val someList: List[Tuple3[Int, List[Int], String]] = List((1, List(1), "1"))
//    etc.