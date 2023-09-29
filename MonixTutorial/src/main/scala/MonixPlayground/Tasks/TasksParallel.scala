package MonixPlayground.Tasks

import monix.eval.Task
import monix.execution.Scheduler
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.duration._

object TasksParallel extends App {

  /** Parallelism & Threading Basics */
    // - Within a single execution thread, only one action is ever being done at any given time.
    //   Asynchronous simply means that we can jump to another job when the one being executed signals that is is waiting.
    //   In other words, single-threaded async programming means that we are non-blocking, but not necessarily doing multiple
    //   calculations at once.

    // - Parallelism, on the other hand, means that multiple computing threads are running at the same time, and multiple
    //   actions are being performed simultaneously
    // - In order to work with parallelism in Monix, then, we have to involve multiple threads


  /** Multi-Threading */

    /** .executeAsync */
      // The first way achieve parallelism is to specify .executeAsync when creating the Task.
      // Each Task with .executeAsync will have its own thread:
        Task.eval(println("Done 1")).delayExecution(3.seconds).executeAsync.runAsyncAndForget
        Task.eval(println("Done 2")).delayExecution(3.seconds).executeAsync.runAsyncAndForget

        Thread.sleep(3100)  // Delay program exiting for Task execution to complete

      // Above, both Tasks were done in 3 seconds, because they executed on separate threads.


    /** .executeOn( ) */
      // It is also possible to be explicit about which Scheduler to run a Task on, allowing us to define and designate
      // Schedulers for specific tasks:
        val io = Scheduler.io(name = "input-output")
        Task.eval("Some Task involving input/output over a network").executeOn(io)


    /** Task.parSequence( ) */
      // Another way to achieve parallelism is to gather up Tasks in a collection, and execute them with Task.parSequence( ):
        val first = Task.eval(println("Done 3")).delayExecution(3.seconds)
        val second = Task.eval(println("Done 4")).delayExecution(3.seconds)

        Task.parSequence(List(first, second)).runAsyncAndForget

        Thread.sleep(3100)  // Delay program exiting for Task execution to complete

      // Above takes a List[Task] and turns it into a single Task[List]

      // Task.parSequence() will maintain the original order of the Tasks when returning results. To disregard order for
      // both effects and results, you can use Task.parSequenceUnordered( )

}






object TasksParallelExercises extends App {
  import monix.execution.Scheduler.Implicits.global

  /** Exercises */
  // 1. Using Task.executeAsync, count to 3 on two threads in parallel, waiting 1 second between each increment.
  //    Print "done" when finished


  // 2. Using Task.parSequence, execute 3 Tasks in parallel which all increment a var called counter, taking 5 seconds each to complete.
  //    Then, using >>, print the counter's value only after it has been incremented.


  Task.never.runSyncUnsafe() // don't exit program until Tasks finish

}






/** Exercise Solutions */
// 1.
//    Task(1).flatMap(x => Task(x + 1)).delayExecution(1.second)
//      .flatMap(x => Task(x + 1)).delayExecution(1.second)
//      .flatMap(_ => Task(println("done 1")))
//      .executeAsync.runAsyncAndForget
//
//    Task(1).flatMap(x => Task(x + 1)).delayExecution(1.second)
//      .flatMap(x => Task(x + 1)).delayExecution(1.second)
//      .flatMap(_ => Task(println("done 2")))
//      .executeAsync.runAsyncAndForget

// Or, using for-comprehensions:

//    val myTask = for {
//      t1 <- Task(1)
//      t2 <- Task(t1 + 1).delayExecution(1.second)
//      t3 <- Task(t2 + 1).delayExecution(1.second)
//      t4 <- Task(println("done 3"))
//    } yield t4
//    myTask.executeAsync.runAsyncAndForget
//
//    val myTask2 = for {
//      t1 <- Task(1)
//      t2 <- Task(t1 + 1).delayExecution(1.second)
//      t3 <- Task(t2 + 1).delayExecution(1.second)
//      t4 <- Task(println("done 4"))
//    } yield t4
//    myTask2.executeAsync.runAsyncAndForget

// 2.
//   var counter = 0
//   val myList = List(Task(counter += 1).delayExecution(5.seconds),
//   Task(counter += 1).delayExecution(5.seconds),
//   Task(counter += 1).delayExecution(5.seconds))
//
//   (Task.parSequence(myList) >> Task(print(counter))).runSyncUnsafe()
