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
    val optionInt: Option[Int] = Some(1)


  /** Types in Methods */
    // The return type of a function is placed after the arguments in the signature
    // Take one Int, return a String:
    def someFunction(arg1: Int): String = {
      arg1.toString
    }

    // When a function only performs side effects without returning a value, it will return the Unit value:
    def someFunction2(arg1: Int): Unit = {
      println("Side effects like printing return nothing - Unit")
    }


  /** Is Specifying Types Optional? */
    // The compiler is smart enough to infer types in many cases:
    val str = "Hello"

    // However, there are cases where this will not be possible. Leaving off types also makes code harder to comprehend.
    // Therefore, it's good practice to explicitly specify your types, especially in production code that others will need
    // to understand later.


  /** Generic Types */
    // Specifying a generic type - usually represented by a capital letter, but this is arbitrary - allows functions or
    // classes to have types specified on a per-instance (class) or per-call (function) basis:
    def function1[A](value: A) = {
      println(value)
    }
    function1[Int](123)
    function1[String]("234")
    function1(false)  // Notice that the compiler can infer A, and doesn't need the full function1[Boolean](false). It's good to be explicit though

    def function2[A, B](val1: A, val2: B) = {
      // Do something with your custom/variable-typed values
    }
    function2[Int, String](123, "234")


  /** Supertypes and Subtypes */
    // The most common method for making a basic type to group subclasses would be declaring a new class or trait,
    // and extending the superclass using the 'extends' keyword:
    class Animal
    class Dog extends Animal
    class Cat extends Animal
    class Rat extends Animal

    trait Building
    class House extends Building
    class School extends Building
    class Skyscraper extends Building

    // Subtypes satisfy a function or class's requirement for their supertype, for instance:
    def adopt(animal: Animal) = {}
    adopt(new Cat)

    /** [[Classes]] and [[Traits]] are covered in their own sections later, but for now, just know that you will see
        both used to group subtypes
     */

}


object TypesExercises extends App {
  /** Exercises */
  // 1. Initialize 3 vals, one of type String, Int, and Boolean

  // 2. Initialize a List of type Boolean

  // 3. Initialize a function that takes a String, a Boolean, and an Int, and returns an Int

  // 4. Initialize a List of Tuples made of Int, List of Ints, String

  // 5. Create a function which takes a generic value, and prints it

}






/** Exercise Solutions */
// 1. val one: String = ""
//    val two: Int  = 0
//    val three: Boolean = true

// 2. val bool: List[Boolean] = List.empty

// 3. def function(str: String, bool: Boolean, int: Int): Int = {
//      0
//    }

// 4. val someList: List[(Int, List[Int], String)] = List.empty
//    Or
//    val someList: List[Tuple3[Int, List[Int], String]]
//    Or
//    val someList: List[Tuple3[Int, List[Int], String]] = List((1, List(1), "1"))
//    etc.

// 5. def generic[I](value: I) = println(value)
