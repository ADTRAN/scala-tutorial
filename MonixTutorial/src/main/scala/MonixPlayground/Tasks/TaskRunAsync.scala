package MonixPlayground.Tasks

import monix.eval.Task
import monix.execution.Callback
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.duration._

object TaskRunAsync extends App {

  /** .runAsync Basics */
    // .runAsync will hand your Task to the scheduler, which will execute the Task at its best discretion (asynchronously)

    // .runAsync allows you to specify a callback function, which is a function that will run after the Task is finished executing
    // The returned value is an Either, so we use pattern matching to handle the result:
    Task.eval(100).runAsync { result =>
      result match {
        case Right(goodVal) => println(goodVal + 1)
        case Left(error) => throw new Exception(s"Error was $error")
      }
    }

    // If something goes wrong with the execution, .runAsync will return a Left.


  /** .runAsyncAndForget */
    // Callbacks are optional. If you want to hand your Task to the scheduler for execution, and don't care about specifying
    // a callback, you can use .runAsyncAndForget:
    Task.eval(100).runAsyncAndForget

    // Using the above, you lose access to any return value that a Task may have upon execution

    // Alternatively, you could just use an empty callback:
    Task.eval(100).runAsync(Callback.empty)


  /** Taking Advantage of Async Behavior */

    // Consider the example below:
    val first = Task{
      println("Hello 1")
    }.delayExecution(3.seconds).runAsyncAndForget

    val second = Task {
      println("Hello 2")
    }.runAsyncAndForget

    Thread.sleep(5000)   // Wait to exit the program before our Async operations are complete

    // Above, since the first Task had a delay (representing, for instance, a call over a network which takes some time to
    // return, etc.), the second task was executed immediately by the scheduler - no blocking took place. The other Task
    // returned when it was ready, asynchronously.


  /** Threads */

    // Consider the following example:
    val one = Task {
      Thread.sleep(2000)    // some thread-blocking operation
      println("Hello 100")
    }.runAsyncAndForget

    val two = Task {
      println("Hello 200")
    }.runAsyncAndForget

    // Even though we are using .runAsyncAndForget, we are still using the same thread for executing both Tasks.
    // Therefore, since one of them had a thread-blocking operation, which represents any calculation at all,
    // the other was delayed.

    // Asynchronous, then, does not necessarily mean parallel.

    /** To learn about parallel execution, see [[TasksParallel]] */

}






object TaskRunAsyncExercises extends App {
  import monix.execution.Scheduler.Implicits.global

  /** Exercises */
  // 1. Using .runAsync and the function below, print false
  def someBool(seed: Int): Boolean = {
    if (seed > 10) true else false
  }

}






/** Exercise Solutions */
// 1.
//    Task(someBool(4)).runAsync(result =>
//      result match {
//        case Right(value) => println(value)
//        case Left(value) => value
//      }
//    )

// Or:

//    Task(someBool(4)).runAsync { result =>
//      val myVal = result match {
//        case Right(value) => value
//        case Left(value) => value
//      }
//      println(myVal)
//    }
