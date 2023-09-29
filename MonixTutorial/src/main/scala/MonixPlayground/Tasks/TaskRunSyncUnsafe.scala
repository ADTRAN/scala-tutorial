package MonixPlayground.Tasks

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.duration._

object TaskRunSyncUnsafe extends App {

  /** .runSyncUnsafe( ) Basics */
    // - .runSyncUnsafe( ) evaluates the Task immediately (synchronously), and blocks until the evaluation is complete.
    // - For these reasons, it's intended to be used in dire situations only, when there are no other options to do
    //   what needs to be done, or at the "end of the world" -> where the program's main Task is being executed

    val sync = Task.sleep(2.seconds) >> Task.eval {
      println(2)
    }
    sync.runSyncUnsafe()

    // Notice the parenthesis after .runSyncUnsafe( ). You can specify a timeout for how long to wait for a completed result,
    // the default being to wait forever -> Duration.Inf:

    Task.unit.runSyncUnsafe(2.seconds)

    // Below will throw a Timeout exception if uncommented:
    // Task.eval(println("Delay..."))
    //   .delayExecution(3.seconds)
    //   .runSyncUnsafe(2.seconds)


  /** Freeing Values from a Task */
    // Like .runToFuture, .runSyncUnsafe( ) makes it possible to free the value from a Task entirely:
    val myVal2 = Task("Free!").runSyncUnsafe()
    println(myVal2)

}






object TaskRunSyncUnsafeExercises extends App {
  import monix.execution.Scheduler.Implicits.global

  /** Exercises */
  // 1. Using .runSyncUnsafe( ) & .flatMap( ), print the sum of Task(3) + Task(4)

  // 2. Use .runSyncUnsafe( ) to free the value entirely from Task(123), and put it into a new val

}






/** Exercise Solutions */
// 1.
//    Task(3).flatMap(x =>
//      Task(4).flatMap(y =>
//        Task(println(x + y))
//      )
//    ).runSyncUnsafe()

// 2. val val1 = Task(123).runSyncUnsafe()