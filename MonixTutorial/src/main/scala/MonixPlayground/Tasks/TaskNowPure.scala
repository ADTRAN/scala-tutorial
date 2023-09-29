package MonixPlayground.Tasks

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.duration.DurationInt

object TaskNowPure extends App {

  /** Task.now( ) Basics */
    // - Task.now( ) is the same as Task.pure( )
    // - These are for creating a Task with a value that has already been computed.
    // - If you instead pass in a computation that needs to be evaluated, your scheduler will block in order to strictly
    // evaluate it as soon as it's created

    // Below is an example of this behavior:

    Task.now {
      Thread.sleep(2000)    // some thread-blocking operation
      println("Done")
    }

    // If the above is run, even without anything executing the Task, it will take 2 seconds, and print "Done". This is because
    // the Task's expression is evaluated as soon as it's created.


  /** A more complex example & comparison with Task.eval( ) */
    // Below we have:
      // 1. A function that waits for 10 milliseconds
      // 2. 1000 calls to this function in Task.eval( )'s, gathered up in a sequence
      // 3. 1000 calls to this function in Task.now( )'s, gathered up in a sequence

    def delayTen(source: String) = {
        Thread.sleep(10)    // some thread-blocking operation
        source
      }

    val evalTasks = Task.sequence {
      (1 to 500).map { _ =>
        Task.eval(delayTen("eval"))
      }.toList
    }
    println("done with eval creation")

    val nowTasks = Task.sequence {
      (1 to 500).map { _ =>
        Task.now(delayTen("now"))
      }.toList
    }
    println("done with now creation")

    // When running the above, the Task.eval( )'s finish instantly, since their evaluation is lazy. They will not evaluate
    // their calls to delayTen() until scheduled
    // This is because .sequence transforms the Seq(Task.eval( )) into Task.eval(Seq( )), and Task.eval( ) is lazy

    // The Task.now( )'s were all strictly evaluated (sequential) as soon as they were created, though, meaning it took at
    // least 10ms * 500 = 5 seconds to finish.
    // This is because .sequence transformed the Seq(Task.now( )) into Task.now(Seq( )), and Task.now( ) is strict - what it wraps
    // will be evaluated as soon as it's created

    // This is why Task.now( ) or Task.pure( ) should only be used when dealing with values that are already known - otherwise you'll block.
    // When used in this proper way, it is more efficient than Task.eval( ), because it does not have to deal with the overhead
    // of actually scheduling the Task on the scheduler, instead simply putting it "next in line", since its value is assumed to be known and ready.


    /** Scheduling the Tasks */
      // Now, when going about to actually schedule the Task.eval(Seq( )) and Task.now(Seq( )) created above, we get the opposite performance:
      nowTasks.runAsyncAndForget
      println("done with now execution")

      evalTasks.runAsyncAndForget
      println("done with eval execution")

      // The nowTasks finish instantly, but the evalTasks take 10 seconds
      // Again, this is because we already have all of our Task.now( ) values, because they were strictly evaluated upon creation
      // Our lazy values from Task.eval( ), however, were only evaluated once scheduled


}






object TaskNowPureExercises extends App {
  import monix.execution.Scheduler.Implicits.global

  /** Exercises */
  // 1. Initialize a Task using both aliases for a strictly-evaluated Task

  // 2. What value will be printed below?
  //    var x = 1
  //    val myTask = Task.eval(println(x))
  //    Task.now(x += 100)
  //    x += 1
  //
  //    myTask.runSyncUnsafe()

  // 3. Using Task.now, print the result of 3 + 2, after three seconds, without using any .run function


}






/** Exercise Solutions */
// 1.
//    val myTask1 = Task.now(1)
//    val myTask2 = Task.pure(1)

// 2. 102, because the incrementing with Task.now( ) will take place immediately, and the print statement will be
//    lazily evaluated after another + 1 incrementation

// 3.
//    Task.now {
//      val result = 3 + 2
//      Thread.sleep(3000)
//      println(result)
//    }

// Or:

//    def delayThree = {
//      val result = 3 + 2
//      Thread.sleep(3000)
//      println(result)
//    }
//
//    Task.now(delayThree)

