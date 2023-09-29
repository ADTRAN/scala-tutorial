package StandardScalaPlayground.Sections

import com.github.nscala_time.time.Imports._

object WhileLoops extends App {

  /** While Loop Basics */
    // While loops in Scala, like most languages, allow us to repeatedly execute a code block until a condition is met:
    var x = 0
    while (x < 3) {
      println(x)
      x += 1
    }


  /** Infinite Loops and Break */
    // Infinite loops are possible with while(true)

    // Many "useful" infinite loops contain a break at some point. Break, unlike in other languages, is not very commonly
    // used in Scala - being part of the scala.util library, rather than standard:
    import scala.util.control._

    // Below is an example of an infinite loop that will break after 5 seconds.

    // The "weird" part of this is the Breaks. We have to initialize a Breaks object, wrap our infinite loop in it using .breakable,
    // and then call .break on it to exit the infinite loop:
    val loop = new Breaks
    val start = DateTime.now()
    loop.breakable {
      while (true) {
        if ((start to DateTime.now).millis > 5000) loop.break   // if 5 seconds have passed, break
      }
    }
    println("Break-ed!")

}


object WhileLoopsExercises extends App {
  /** Exercises */
  // 1. Print "Hello" 5 times using a while loop

}






/** Exercise Solutions */
// 1.
//   var x = 0
//   while(x < 5) {
//     println("Hello")
//     x += 1
//   }

