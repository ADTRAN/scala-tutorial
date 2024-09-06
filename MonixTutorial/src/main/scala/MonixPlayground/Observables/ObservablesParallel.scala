package MonixPlayground.Observables

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global
import monix.reactive.Observable

object ObservablesParallel extends App {
  // If these examples are stepping over each other when executing this program, feel free to comment any of them out -
  // they are independent.

  /** Parallelism Basics */
    // - Parallelism involves performing multiple simultaneous operations, each on their own CPU thread.
    // - This is distinct from asynchronous programming, which may involve only a single CPU thread bouncing back and forth
    //   between tasks as they signal that they are waiting.
    // - Monix offers multiple ways to process Observables in parallel


  /** .executeAsync */
    // - Constructing an Observable with .executeAsync will run it on its own CPU thread, and therefore allow us to do
    //   work in parallel:

    var count = 0

    val generator1 = Observable.repeatEval {
      Thread.sleep(1000)
      count += 1
      count
    }.take(5).executeAsync
    val generator2 = Observable.repeatEval {
      Thread.sleep(1000)
      count += 1
      count
    }.take(5).executeAsync

    generator1.foreach(x => println(s"first $x"))
    generator2.foreach(x => println(s"second $x"))

    // Above, we create an Observable with .repeatEval, which will continuously replay not just the value of the Observable,
    // but also the entire evaluation, which allows us to repeatedly sleep. We take the first five elements, and execute
    // them on their own CPU threads.

    // Try removing .executeAsync to observe the synchronous behavior we would get without it


  /** .mapParallelOrdered( ) */
    // - .mapParallelOrdered( ) allows you to specify a 'parallelism', which will be how many threads will be used to
    //   perform the parallel computation.
    // - It takes a function that must return a Task, and maps it to the elements emitted by the Observable.
    // - It also guarantees that results will be emitted in their original order

    Observable(1, 2).mapParallelOrdered(parallelism = 2) { elem =>
      Task {
        println(s"Running $elem")
        Thread.sleep(3000)
        elem
      }
    }.foreach(x => println(s"Done $x"))

    // Above, both elements finish simultaneously after 3 seconds. With synchronous programming, this would take 6 seconds.


  /** .mapParallelUnordered( ) */
    // - .mapParallelUnordered( ) is the same as above, but returns values in the order that they complete evaluation

    private def waiting(seed: (String, Int)): Task[Unit] = {
      Thread.sleep(seed._2 * 1000)
      Task(println(seed))
    }

    val generator = Observable(("first", 5), ("second", 1), ("third", 1), ("fourth", 1), ("fifth", 1))
      .mapParallelUnordered(parallelism = 2)(waiting)

    generator.subscribe()

    // Above, we map the "waiting" function to each element in the Observable, with a parallelism of 2.
    // If we were synchronously processing, we would have to wait 5 seconds before we were able to process
    // the other 4 elements in the Observable.

    // If your CPU has more than 2 cores, you can try raising the parallelism


  Task.never.runSyncUnsafe()   // Do not exit program, to give time for Observables to run

}






object ObservablesParallelExercises extends App {
  import monix.execution.Scheduler.Implicits.global
  import scala.concurrent.duration._

  /** Exercises */
  // 1. Using .executeAsync and the function below, print "Hello" 10 times in 5 seconds
      def hello: Unit = {
        Thread.sleep(1000)
        println("Hello")
      }


  // 2. Do the same with .mapParallelUnordered


  Task.never.runSyncUnsafe()   // Do not exit program, to give time for Observables to run

}






/** Exercise Solutions */
// 1.
//    val obs1 = Observable.repeatEval(hello).take(5).executeAsync
//    val obs2 = Observable.repeatEval(hello).take(5).executeAsync
//
//    obs1.subscribe
//    obs2.subscribe

// 2.
//    val obs = Observable.range(1, 11).mapParallelOrdered(parallelism = 2)(_ => Task(hello))
//    obs.subscribe

// Or:

//    val obs = Observable(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).mapParallelOrdered(parallelism = 2)(_ => Task(hello))
//    obs.subscribe

// etc.