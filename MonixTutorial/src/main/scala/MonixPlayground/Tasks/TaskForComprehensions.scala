package MonixPlayground.Tasks

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

object TaskForComprehensions extends App {

  /** For Comprehension Basics */
    // - for-comprehensions in Monix are for performing intermediate operations on the values contained in Tasks
    // - They allow us to "unwrap" the value from a Task, operate on it, and return a Task with a new value
    // - Under the hood, for-comprehensions chain .flatMap statements, terminating in a .map in the yield statement
    // - for-comprehensions, like .flatMap( ) in Monix, must return a Task.

    val firstTask = Task.eval(5)

    val lastTask = for {
      step1   <- firstTask                // step1 = 5
      step2   <- Task.eval(step1 + 5)     // step2 = Task(5 + 5)
    } yield step2

    // Above is the same as Task.eval(5).flatMap(step1 => Task.eval(step1 + 5)).map(step2 => step2)

    lastTask.foreach(x => println(s"Result was $x"))


  /** Not Using Yield */
    // Not using the "yield" keyword changes the terminating step from .map( ) to .foreach( )

    val some = for {
          step1   <- Task.eval(1)                // step1 = 5
          step2   <- Task.eval(step1 + 5)     // step2 = Task(5 + 5)
        } println(step2 + 5)

    // Above is equivalent to Task.eval(1).flatMap(step1 => Task.eval(step1 + 5)).foreach(step2 => println(step2 + 5))

}






object TaskForComprehensionExercises extends App {
  import monix.execution.Scheduler.Implicits.global

  /** Exercises */
  // 1. Translate Task(1).flatMap(x => Task(x + 2)).flatMap(y => Task(y + 3)).map(z => println(z)).runSyncUnsafe()
  //    into a for-comprehension

  // 2. Using a for-comprehension, print the values in the List in Task(Task(Task(List(1, 2, 3))))

}






/** Exercise Solutions */
// 1.
//    val myTask = for {
//      x <- Task(1)
//      y <- Task(x + 2)
//      z <- Task(y + 3)
//    } yield println(z)
//
//    myTask.runSyncUnsafe()

// 2.
//    val myTask1 = Task(Task(Task(List(1, 2, 3))))
//    val result = for {
//      unwrap1 <- myTask1
//      unwrap2 <- unwrap1
//      unwrap3 <- unwrap2
//    } yield unwrap3.foreach(println)      // could also be .map(println), .map(x => println(x)), etc.
//
//    result.runSyncUnsafe()

// Or:

//    val myTask1 = Task(Task(Task(List(1, 2, 3))))
//    for {
//      unwrap1 <- myTask1
//      unwrap2 <- unwrap1
//      unwrap3 <- unwrap2
//    } unwrap3.foreach(println)

//    val myTask1 = Task(Task(Task(List(1, 2, 3))))
//      for {
//        unwrap1 <- myTask1
//        unwrap2 <- unwrap1
//        unwrap3 <- unwrap2
//      } unwrap3.map(println)