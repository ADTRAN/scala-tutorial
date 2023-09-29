package StandardScalaPlayground.Sections


object IfElse extends App {

  /** Basic If-Else */
    // The if statement in Scala is composed of a predicate and an expression, with { } brackets optional on single expressions:
    if (true) {
      if (1 == 1) println("Just one")
    }

    // 'else if' allows you to add multiple if expressions to your match statement,
    // and 'else' is the default if no prior predicates were satisfied
    def branching(num: Int) = {
      if (num < 0) "negative"
      else if (num == 4) "four"
      else if (num % 2 == 0) "positive even"
      else "positive odd"
    }

    println(s"-3 was ${branching(-3)}, 4 was ${branching(4)}, 2 was ${branching(2)}, 5 was ${branching(3)}")
    // Above, notice that even though 4 satisfies two cases, it was picked up by the first matching case, in order from top to bottom.


  /** An If-Else Example with .foreach( ) */
    List(1, 2, 3).foreach(x =>
      if(x % 2 == 0) {
        println(s"$x was even")
      } else println(s"$x was odd")
    )

}



object IfElseExercises extends App {
  /** Exercises */
  // 1. Create a method that can take one number as input. Print "small" if input is a number 0-100, "big" if a number is 101-1000,
  // and "unknown" if anything else


  // 2. Use if else and .map( ) to turn List(1, 2, 3) into List("yes", "no", "yes")

}






/** Exercise Solutions */
// 1. def sorter(input: Int) = {
//      if (input >= 0 && input <= 100) {
//        println("small")
//      }
//      else if (input > 100 && input <= 1000) {
//        println("big")
//      }
//      else println("unknown")
//    }

// 2. List(1, 2, 3).map(x =>
//      if(x % 2 == 0) {
//        "no"
//      } else "yes"
//    )
