package MonixPlayground.Observables

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global
import monix.reactive.Observable

import scala.concurrent.duration._


object ObservableForComprehension extends App {

  /** For-comprehension Basics */
    // - For-comprehensions chain .flatMap( ) and .map( ) statements in a way that's easier to read:

    val res = for {
      elem <- Observable(1, 2, 3, 4)
      add  <- Observable(elem + 1)
    } yield add

    res.foreach(println)

    // Above is equivalent to Observable(1, 2, 3, 4).flatMap(elem => Observable(elem + 1)).map(add => add)
    // Each <- in the body of the for-comprehension is a .flatMap, and a yield statement represents a .map

    /** - Often, a Monix-based application will have a main for-comprehension, in which all of the major controlling logic
         is located. See [[MonixPlayground.Tasks.TaskApps]] for an example
     */


}






object ObservableForComprehensionExercises extends App {
  import monix.execution.Scheduler.Implicits.global

  /** Exercises */
  // 1. Translate Observable.interval(1.second).flatMap(first => Observable(first * 10)).flatMap(second => Observable(second * 10)).map(third => println(third)) into a for-comprehension. Modify the statement so that you only print ten results.


  Task.never.runSyncUnsafe()   // Do not exit program, to give time for Observables to run

}






/** Exercise Solutions */
// 1.
//      val result = for {
//        src    <- Observable.interval(1.second).take(10)
//        first  <- Observable(src * 10)
//        second <- Observable(first * 10)
//      } yield println(second)
//
//      result.subscribe()