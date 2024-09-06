package MonixPlayground.Observables

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global
import monix.reactive.Observable

import scala.concurrent.duration._


object ObservableFlatMap extends App {

  /** Observable.flatMap( ), .concatMap( ) Basics */
    // - .flatMap( ) is an alias for .concatMap( ), but since .flatMap( ) is more familiar, this section will use it.
    // - .flatMap( ) applies a function to each element in the Observable, requiring an Observable value to be returned:

    val flatMapped: Observable[Int] = Observable(1, 2, 3).flatMap(x => Observable(x * 2))
    flatMapped.foreach(println)


  /** A more complex example */
    val obs: Observable[Long] = Observable.interval(50.milliseconds).drop(1).takeEveryNth(10).take(3)
    obs.flatMap { elem =>
      val oper = println(elem * 2)
      Observable(oper)
    }.toListL.runAsyncAndForget

    /*
      Above, we:
        - Create an Observable emitting an event every 50 ms
        - We drop the first element, which is going to be 0 (.interval starts emitting integers at 0)
        - We take every tenth element from there on out -> 10, 20, 30
        - We take three elements before closing the Observable

      Then:
        - Use .flatMap( ) to operate on each element in the Observable
        - Turn the result into a Task[List] with .toListL
        - Execute the Task with .runAsyncAndForget
     */


  /** A note on .map( ) */
    // - .map( ) allows you to apply a function to each elements in the Observable without needing to explicitly wrap
    //   the return value of the function in Observable( ). It still returns an Observable:

    val mapped: Observable[Unit] = Observable(1, 2, 3).map(x => println(x * 100))
      .delayExecution(3.seconds)   // delay execution so examples don't step over each other
    mapped.subscribe()



  Task.never.runSyncUnsafe()   // Do not exit program, to give time for Observables to run

}






object ObservableFlatMapExercises extends App {
  import monix.execution.Scheduler.Implicits.global

  /** Exercises */
  // 1. Using .flatMap, double all the values in Observable(Observable(1, 2, 3), Observable(4, 5, 6)). Then, print them.

  // 2. Using .flatMap and a function returning an Observable, turn Observable(Observable(1, 2, 3), Observable(4, 5, 6))
  //    into Observable(false, true, false, true, false, true). Print the values

}






/** Exercise Solutions */
// 1.
//     val obs = Observable(Observable(1, 2, 3), Observable(4, 5, 6))
//      .flatMap(nestedObs =>
//        nestedObs.flatMap(elem =>
//          Observable(elem * 2)
//        )
//      )
//    obs.foreach(println)

// Or:

//    val obs = Observable(Observable(1, 2, 3), Observable(4, 5, 6))
//      .flatMap(nestedObs =>
//        Observable {
//          nestedObs.flatMap(elem =>
//            Observable(elem * 2)
//          )
//        }
//      )
//    obs.foreach(nestedObs => nestedObs.foreach(println))

// etc.

// 2.
//    def evenOdd(obs: Observable[Int]): Observable[Boolean] = {
//     obs.flatMap { elem =>
//       if (elem % 2 == 0) Observable(true)
//       else Observable(false)
//     }
//    }
//
//    val result = Observable(Observable(1, 2, 3), Observable(4, 5, 6)).flatMap(nestedObs =>
//     evenOdd(nestedObs)
//    ).map(println)
//
//    result.subscribe

// Or:

//    val result = Observable(Observable(1, 2, 3), Observable(4, 5, 6)).flatMap(nestedObs =>
//    evenOdd(nestedObs)
//    )
//
//    result.foreach(println)

// etc.