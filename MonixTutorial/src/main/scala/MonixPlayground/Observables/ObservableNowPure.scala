package MonixPlayground.Observables

import monix.execution.Scheduler.Implicits.global
import monix.reactive.Observable

object ObservableNowPure extends App {

  /** Observable.now( ) Basics */
    // - Observable.now( ) is identical to Observable.pure( )
    // - They are for lifting already-known values into an Observable context. If the value is already known, this is
    //   slightly more performant than Observable.eval( )
    // - If the value is not already known, this eagerly evaluates upon initiation, blocking your thread:

    val result = Observable.now {
        Thread.sleep(3000)
        println("FinishedExecuting")
    }
    println("Created!")

    // Above, we wait for three seconds, then print, all without consuming the Observable, due to the eager evaluation.

    // As a result, when we do consume the Observable, the result will be instantaneous:
    result.subscribe()


  /** For further reading on the Task equivalent, see [[MonixPlayground.Tasks.TaskNowPure]] */


}






object ObservableNowPureExercises extends App {
  import monix.execution.Scheduler.Implicits.global
  import scala.concurrent.duration._

  /** Exercises */
  // 1. Create an eagerly-evaluated Observable that prints 1 through 3, and execute it

  // 2. How long will the following operation take?
  //      Observable.now(Thread.sleep(2000)).delayExecution(3.seconds)
  //      println("Hello")

}






/** Exercise Solutions */
// 1.
//    Observable.now(List(1, 2, 3).map(println))

// Or:

//    val obs = Observable.now(List(1, 2, 3))
//    obs.foreach(x => x.foreach(println))
//  etc.

// 2. It will take 2 seconds, because the Thread.sleep is eagerly evaluated, but the Observable is never executed