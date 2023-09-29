package MonixPlayground.Tasks

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

object TaskEvalDelay extends App {

  /** Task.eval( ) Basics */
    // - Task( ) is the same as Task.eval( ) and Task.delay( )
    // - These are for instantiating a Task that will "lazily" evaluate -> its expression will only be evaluated when run by the scheduler.

    // Below is an example of this behavior:

    val evalTask = Task.eval {
      Thread.sleep(5000)    // some thread-blocking operation
      println("Execution complete")
    }

    println("Task.eval( ) creation complete, running...")
    evalTask.runSyncUnsafe()

    // In the above example, the initialization was instant - no evaluation took place. The 5 second wait only occurred
    // when we scheduled the Task.eval( ), since it is lazy

}






object TaskEvalDelayExercises extends App {
  import monix.execution.Scheduler.Implicits.global

  /** Exercises */
  // 1. Initialize a Task using all three aliases for a lazily-evaluated Task

  // 2. What value will be printed below?
  //    var x = 1
  //    val myTask = Task.eval(println(x))
  //    x += 1
  //
  //    myTask.runSyncUnsafe()

}






/** Exercise Solutions */
// 1.
//    val myTask1 = Task(1)
//    val myTask2 = Task.eval(1)
//    val myTask3 = Task.delay(1)

// 2. Two, because the incrementation occurs before the lazy valuation of the Task