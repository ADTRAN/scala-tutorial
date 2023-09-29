package StandardScalaPlayground.Sections

object ForLoops extends App {

  /** For Loop Basics */
    // For loops allow you to do an operation an arbitrary number of times in Scala, like in most other languages
    // They iterate over each element to the right of the "generator" (<-), and each element is accessible in a temporary var:
    for (x <- List(1, 2, 3))
      print(x)

    // Above is equivalent to:
    List(1, 2, 3).foreach(x => print(x))


  /** For Loops with [[Ranges]] */
    // Ranges are most often used to specify the arbitrary number of executions in for loops:
    for (x <- 1 to 10) {
      print(s" $x ")
    }

    // Specifying multiple generators is possible via a semicolon. This will iterate through all combinations of the generators:
    for (x <- 1 to 2; y <- 1 to 2) {
      print(s" x:$x y:$y ")
    }


  /** For Loops with Collections */
    // The same can be done with collections like Lists, Sets, Maps, etc.
    // The number of elements in the collection determines the number of iterations:
    for (x <- Set("a", "b", "c")) {
      print(x)
    }
    // This approach is not as common as using Ranges, as it is just a strange way of doing a .map( ), with no real benefit


  /** Filtering elements */
    // You can specify if conditions to filter the generated Range or Collection:
    for (x <- List(1.1, 2, 3.3) if x.isWhole) {
      println("\n This should only run once")
    }

}


object ForLoopsExercises extends App {
  /** Exercises */
  // 1. Print "Hello" three times using a for loop, using a collection as a generator

  // 2. Print "a", "b", and "c" from List("a", "b", "c") using a for loop

  // 3. Print "Hello-{count}" 100 times using a for loop, using a range as a generator, where {count} increments from 2 to 200

  // 4. Print "Hello-{bool}" 50 times, using a for loop, using the range 1 to 100, where {bool} is whether the current element
  //    from the generator is divisible by 10

}






/** Exercise Solutions */
// 1. for (x <- List(1, 2, 3)) println("Hello")

// 2. for (x <- List("a", "b", "c")) println(x)

// 3. for (count <- (2 to 200 by 2)) print(s"Hello-$count ")
//    for (count <- (1 to 100)) print(s"Hello-${count * 2} ")

// 4. for (x <- (1 to 100) if x % 2 == 0) print(s"Hello-$x-${x % 10 == 0} ")