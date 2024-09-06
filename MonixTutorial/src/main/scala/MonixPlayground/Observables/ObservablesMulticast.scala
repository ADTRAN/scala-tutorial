package MonixPlayground.Observables

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global
import monix.reactive.{MulticastStrategy, Observable, Pipe}
import monix.reactive.observables.ConnectableObservable

import scala.concurrent.duration._

object ObservablesMulticast extends App {

  /** Multicasting Basics */
    // - By default, an Observable can only stream events to a single consumer
    // - Multicasting allows each element to be relayed to multiple consumers. This is called creating a "hot" Observable


  /** .replay */
    val generator = Observable.interval(1.second).take(3)

    // .replay transforms a standard "cold" Observable into a "hot" Observable:
    val hot: ConnectableObservable[Long] = generator.replay

    // .subscribe or .foreach will subscribe to the hot Observable, but not begin evaluation like it does with normal Observables:
    val sub1 = hot.foreach(x => println(s"subscriber-1: $x"))
    val sub2 = hot.foreach(x => println(s"subscriber-2: $x"))

    // .connect begins emitting elements to subscribers:
    hot.connect()

    Thread.sleep(3000)    // give example time to finish


  /** .multicast( ) */
    // - Like .replay, .multicast( ) creates an instance of ConnectableObservable
    // - .multicast( ) requires a Pipe, which specifies our MulticastStrategy, allowing us to tune our multicasting behavior

    /** .multicast( ) with Pipe.replay */
      // - Pipe.replay corresponds to MulticastStrategy.replay, and also mirrors the basic behavior of .replay above
      // - In each case, all of the events emitted by the Observable from the beginning are emitted to subscribers:

      val stream: ConnectableObservable[Long] = Observable.interval(100.milliseconds).multicast(Pipe.replay[Long])

      stream.connect()      // Begin emitting events
      Thread.sleep(2000)    // Sleep for 2 seconds before subscribing

      // Subscribe to the hot Observable:
      val subFirst = stream.take(3).foreach(x => println(s"Pipe.replay first sub: $x"))
      val subSecond = stream.take(3).foreach(x => println(s"Pipe.replay second sub: $x"))

      // Notice that even though we waited 2 seconds to subscribe, we still get the first events emitted by the Observable,
      // because we are using Pipe.replay

    /** .multicast( ) with Pipe.publish */
      // - Pipe.publish, corresponding to MulticastStrategy.publish, only relays events to subscribers which were emitted by
      //   the Observable *after* the subscription:

      val stream2: ConnectableObservable[Long] = Observable.interval(100.milliseconds).multicast(Pipe.publish[Long])

      stream2.connect()     // Begin emitting events
      Thread.sleep(2000)    // Sleep for 2 seconds before subscribing

      // Subscribe to the hot Observable:
      val subOne = stream2.take(3).foreach(x => println(s"Pipe.publish first sub: $x"))
      val subTwo = stream2.take(3).foreach(x => println(s"Pipe.publish second sub: $x"))

      // Notice that we only get the events emitted after we subscribe, *not* all of those emitted from beginning of .connect


  // Be aware that there are other options for Pipe beyond the two demonstrated above


  Task.never.runSyncUnsafe()   // Do not exit program, to give time for Observables to run

}






object ObservablesMulticastExercises extends App {
  import monix.execution.Scheduler.Implicits.global
  import scala.concurrent.duration._

  /** Exercises */
  // 1. Using .replay, create an Observable that outputs a series of five random numbers. Connect three subscribers to it,
  //    and evaluate the Observable, printing the results.

  // 2. Using .multicast and Pipe.publish, have one subscriber get the first 5 elements of Observable.interval(1.second),
  //    and have another subscriber get the next five


  Task.never.runSyncUnsafe()   // Do not exit program, to give time for Observables to run

}






/** Exercise Solutions */
// 1.
//    val random = new scala.util.Random
//    val multi = Observable(List(random.nextInt, random.nextInt, random.nextInt, random.nextInt, random.nextInt)).replay
//
//    val sub1 = multi.foreach(x => println(s"sub1: $x"))
//    val sub2 = multi.foreach(x => println(s"sub2: $x"))
//    val sub3 = multi.foreach(x => println(s"sub3: $x"))
//
//    multi.connect

// Or:

//    val random = new scala.util.Random
//    val multi = Observable {
//     (1 to 5).map(_ => random.nextInt)
//    }.replay
//
//    val sub1 = multi.foreach(x => println(s"sub1: $x"))
//    val sub2 = multi.foreach(x => println(s"sub2: $x"))
//    val sub3 = multi.foreach(x => println(s"sub3: $x"))
//
//    multi.connect

// etc.

// 2.
//    val multi = Observable.interval(1.second).multicast(Pipe.publish[Long])
//    multi.connect
//
//    val sub1 = multi.take(5).foreach(x => println(s"sub1: $x"))
//
//    Thread.sleep(5100)
//
//    val sub2 = multi.take(5).foreach(x => println(s"sub2: $x"))
