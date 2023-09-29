package MonixPlayground.Observables

import monix.execution.Scheduler.Implicits.global
import monix.reactive.Observable

object ObservableEvalDelay extends App {

  /** Observable.eval( ) Basics */
    // - Observable.eval( ) is identical to Observable.delay( )
    // - These will initialize an Observable that will be lazily evaluated - meaning it will not evaluate until it is
    //   consumed

    val result = Observable.delay {
      Thread.sleep(3000)
      println("Finished executing")
    }
    println("Created!")

    // Above, we print immediately, because no evaluation took place.

    // We instead lazily evaluate when the Observable is consumed:
    result.subscribe


  /** For further reading on the Task equivalent, see [[MonixPlayground.Tasks.TaskEvalDelay]] */


}






object ObservableEvalDelayExercises extends App {
  import monix.execution.Scheduler.Implicits.global
  import scala.concurrent.duration._

  /** Exercises */
  // 1. Create a lazily-evaluated Observable that prints the numbers 1 through 3, and run it

  // 2. How long will the following operation take?
  //      Observable.eval(Thread.sleep(2000)).delayExecution(3.seconds)
  //      println("Hello")

}






/** Exercise Solutions */
// 1.
//    val obs = Observable.eval (
//      List(1, 2, 3).map(println)
//    )
//    obs.subscribe()

// Or:

//    val obs = Observable.eval (List(1, 2, 3))
//    obs.foreach(x => x.foreach(println))
// etc.

// 2. It will be instant, because the Observable is lazily evaluated, and is never executed