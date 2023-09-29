package StandardScalaPlayground.Sections

object Ranges extends App {

  /** Range Basics */
    // Ranges are used to create a sequence, usually for use in a loop
    // They consist of three parts - a lower bound, an upper bound, and a step value - which specifies what to increment by:
    val someRange1 = Range(1, 10, 1)

    // Above represents List(1, 2, 3, 4, 5, 6, 7, 8, 9). It is read as "From 1 to 10, counting by 1, excluding 10"

    // In regard to that last piece - by default, we create an "exclusive" Range - a range that will exclude the upper bound that you specify
    // To include the upper bound, use Range.inclusive:
    val someRange2 = Range.inclusive(1, 10)

    // Above represents List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10). "From 1 to 10, counting by 1"
    // Notice above that we can leave the step value unspecified, and it will default to one.


  /** Creating Ranges with Keywords - to, until, by */
    // Using 'until' will create a range that excludes the upper bound that you specify:
    val oneUntilTen = 1 until 10
    println(s"Range 1 until 10 is List(1, 2, 3, 4, 5, 6, 7, 8, 9): ${oneUntilTen.toList == List(1, 2, 3, 4, 5, 6, 7, 8, 9)}")

    // Using 'to', you can create a range that includes the upper bound:
    val oneToTen = 1 to 10
    println(s"Range 1 to 10 is List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10): ${oneToTen.toList == List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)}")

    // 'by' allows you to specify a step:
    val twoSkipTen = 2 to 10 by 2
    println(s"Range 2 to 10 by 2 is List(2, 4, 6, 8, 10): ${twoSkipTen.toList == List(2, 4, 6, 8, 10)}")


  /** Iterating over a Range */
    // Using Range( ) gives you access to the standard iterable operations:
    Range(1, 2).map(elem => println(elem))

    // When using "to" or "until", you must use parenthesis:
    (1 until 5).map(elem => println(elem))

    // As already seen, you could also convert the Range to a List, and iterate over that:
    (1 to 5).toList


  /** Ranges are also used commonly in [[ForLoops]] to specify the number of iterations */
    for (x <- 1 to 3)
      print(x)

}


object RangesExercises extends App {
  /** Exercises */
  // 1. Create Range with all the odd numbers from 1 to 10, using Range( ), and again using Range( ).inclusive. Print each element

  // 2. Create Range with the numbers 1 through 10 using "to", and again using "until". Print each element

  // 3. Create a List with 28 values that are all the string "scala", using a Range

  // 4. Call a function that prints "Hello" 13 times using Range

}






/** Exercise Solutions */
// 1. Range(1, 11, 2).foreach(println)
//    Range.inclusive(1, 10, 2).foreach(println)

// 2. (1 to 10).foreach(println)
//    (1 until 11).foreach(println)

// 3. (1 to 28).map(_ => "scala").toList

// 4. def hello = println("hello")
//    for (x <- 1 to 13) {
//      hello
//    }