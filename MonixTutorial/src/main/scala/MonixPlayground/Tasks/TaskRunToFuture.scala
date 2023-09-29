package MonixPlayground.Tasks

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.Await
import scala.concurrent.duration._

object TaskRunToFuture extends App {

  /** .runToFuture( ) Basics */
    // .runToFuture returns a cancellable future. We can either await this future for a final result, or cancel it early.
    // Using .runToFuture will evaluate the Task:
        Task.eval(println(5)).runToFuture

    // If we only care about side effects, as above, we don't need to bother with the return value.


  /** Await */
    // As mentioned above, calling .runToFuture on a Task evaluates it
    // In the following case, that means waiting two seconds until we get our final "1" value:

    val future1 =  Task.eval(1).delayExecution(1.second).runToFuture

    println(s"Future1 before being awaited: $future1")

    // However, as you can see in the print statement above, we do not have a value at this point.
    // We have a cancellable future, which needs to  be awaited:
    println(s"Future1 after being awaited: ${Await.result(future1, 5.seconds)}")

    // Await is part of the standard Scala concurrent library. We specify a max wait time for the future's final evaluation to
    // complete, in this case, 5 seconds.

    // Await does not evaluate. It simply allows us to get the result of our future, and set an upper time bound on how
    // long we want to wait for the result to evaluate from .runToFuture, once we call Await.

    // To demonstrate when the timeout for Await matters, consider the following example:

    val future2 = Task.sleep(2.seconds).executeAsync.runToFuture

    Thread.sleep(2000)
    Await.result(future2, 1.seconds)
    println("Beat the timeout")

    // We only Await for 1 second max, but the Task takes 2 seconds. We don't fail, because by the time we entered the Await,
    // we had already waited on the thread for 2 seconds, and the .runToFuture had already finished evaluating, because we
    // executed it on its own thread with .executeAsync


  /** Cancellation */
    // .runToFuture allows us to cancel our futures before completion

    // For example, below we have a future that will wait for 20 seconds if we don't cancel it. But we'll cancel it after
    // only three seconds:
    val future3 = Task.eval(Task.sleep(20.seconds)).runToFuture
    Thread.sleep(3000)
    future3.cancel()

    println("Future3 cancelled")


    // Because we used Task.sleep( ) in the example above, we did not block the current thread when evaluating our Task.
    // When dealing with real operations that block threads with their execution, in order to cancel, the .cancel would
    // need to occur on a separate thread from the future evaluation.

    // Putting the Future onto its own thread can be accomplished by using .executeAsync:
    def waitTwenty = {
      Thread.sleep(20000)   // thread-blocking, unlike Task.sleep( )
    }

    val future4 = Task.eval(waitTwenty).executeAsync.runToFuture
    Thread.sleep(3000)
    future4.cancel()

    println("Future4 cancelled")

    // If you were to remove the .executeAsync above, the future would complete after 20 seconds, then the .cancel would be called
    // after a further 3 seconds (23 total), on the same thread, becoming pointless.

}






object TaskRunToFutureExercises extends App {
  import monix.execution.Scheduler.Implicits.global

  /** Exercises */
  // 1. Using .runToFuture, print the value of:
  //    Task {
  //      val one = 1
  //      val two = 2
  //      one + two
  //    }

  // 2. Using .runToFuture, free the value from Task(15) entirely, and put it into a new val

}






/** Exercise Solutions */
// 1.
//    val myFuture = Task {
//       val one = 1
//       val two = 2
//       one + two
//     }.runToFuture
//   println(Await.result(myFuture, 1.second))

// 2.
//    val myVal = Await.result(Task(15).runToFuture, 1.second)
//    println(myVal)

// Or:

//   val future = Task(15).runToFuture
//   val breakout = Await.result(future, 1.second)
//   println(breakout)
