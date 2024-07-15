package StandardScalaPlayground.Sections

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future, Promise}
import scala.util.{Failure, Success}

object Futures extends App {

  /** Futures Basics */
    // - Futures allow us to perform parallel execution. We can perform one operation per cpu core we have, simultaneously.
    // - Futures must be scheduled on an execution context. Notice the global implicit execution context imported at the top
    //   of this file. This global execution context should be sufficient for most basic use cases.
    // - Futures are not lazy by default. They begin to execute as soon as they are created. There is a lazyFuture as well,
    //   which will delay execution of the Future until it is awaited, but that is beyond the scope of this tutorial.

    // - Let's put this all together in a basic example below:

    val f1: Future[Unit] = Future {
      Thread.sleep(2000)
      println("Hello 1")
    }

    val f2: Future[Unit] = Future {
      Thread.sleep(2000)
      println("Hello 2")
    }

    val bundledFutures: Future[List[Unit]] = Future.sequence(List(f1, f2))

    Await.result(bundledFutures, 3.seconds)

    // - Above, we define two Futures. Each will wait for 2 seconds, then print something.
    // - We bundle them together so that we can await their result as a single operation, with Future.sequence.
    //   This is not required, but is a common workflow when dealing with Futures.
    // - Finally, we block our thread, to await the result of our our bundled Future, with Await.result.
    //   Our timeout is only 3 seconds, which would be surpassed if both Futures ran sequentially, since they both wait
    //   for 2 seconds. We don't reach the timeout, because they both run at the same time.
    // - Even though the Futures begin to execute as soon as they are created, when we reach an Await.result, we can set
    //   a hard time limit on the completion of the Future.


  /** .onComplete( ) */
    // - In the above example, we only produced some side effects (print statements). If our Futures return a result
    //   that we care about, we can involve a callback, which will be a function called after the Future completes:
    val f3: Future[Int] = Future {
      Thread.sleep(2000)
      3
    }
    f3.onComplete {
      case Success(result) => println(s"Success! Result: $result")
      case Failure(ex) => println(s"Failure! Exception: $ex")
    }

    // - Notice that we don't Await.result (blocking the current thread) when specifying a callback. This is because by
    //   specifying a callback, you are specifying a method to be asynchronously executed when the Future completes. If
    //   you block the current thread using Await.result, you could end up waiting for the Future to complete, while
    //   the Future itself is waiting for the current thread to be available to execute the foreach callback.
    //   This can lead to a deadlock.

    /** The function given to .onComplete has to handle a Try object, which is a type covered in [[TryCatch]] */


  /** .foreach( ) */
    // - You can specify a callback in a .foreach( ) as well, which will only be executed on a Success:
    val f4: Future[Int] = Future {
      Thread.sleep(2000)
      4
    }
    f4.foreach { result =>
      println(s"Assuming a success in my .foreach callback: $result")
    }


  /** .flatMap( ) */
    // - .flatMap( ) allows you to take the value contained in the Future, and operate on it.
    // - The function given to .flatMap( ) must also return a Future:
    val f5: Future[Int] = Future(1)
    val f6: Future[Unit] = f5.flatMap(value => Future(println(value + 1)))
    Await.result(f6, Duration.Inf)


  /** .map( ) */
    // - .map( ) similarly allows you to operate on the completed value of a Future. However, unlike .flatMap( ), it
    //   returns a Future without needing to explicitly wrap the function in a Future:
    val f7: Future[Int] = Future(2)
    val f8: Future[Unit] = f7.map(value => println(value + 2))
    Await.result(f8, Duration.Inf)


  /** for-comprehensions */
    // - for-comprehensions are a way to chain .flatMap( ) calls, terminating in a .map( ) in the yield statement:
    for {
      l1 <- Future(List(1, 2, 3))
      l2 <- Future(l1.map(x => x * 10))
    } yield println(l2)

    // Above is equivalent to Future(List(1, 2, 3)).flatMap(l1 => Future(l1.map(x => x * 10))).map(l2 => println(l2))


  /** Promises */
    // - Promises are basically a way to have a manually-completable Future. They have an associated Future, which
    //   can then be manually completed via the Promise's .success( ), .failure( ), etc. methods, either with a value
    //   or an Exception:
    val promise1: Promise[String] = Promise[String]()   // create a new Promise
    val future1: Future[String] = promise1.future       // get the Promise's Future, which it will be able to complete

    // Start some arbitrary Future, which will call .success( ) after 5 seconds. This will run asynchronously, and
    // right away, because Futures are not lazy:
    Future {
      Thread.sleep(3000)
      promise1.success("Completed the Promise's Future!")
    }

    // Define what happens when the Promise's Future completes, as you would for any normal Future:
    future1.onComplete {
      case Success(value) => println(s"Promise completed with value: $value")
      case Failure(exception) => println(s"Promise failed with exception: $exception")
    }

    // Wait until the Promise's Future completes
    Thread.sleep(4000)

}


object FuturesExercises extends App {
  /** Exercises */
  // 1. Using Futures, print "First" after waiting 2 seconds, and "Second" after waiting 3 seconds, both completing within
  //    4 seconds total.

  // 2. Using Futures, count to 3 on two threads in parallel, waiting one second between each increment.
  //    Print "done" when finished

    Thread.sleep(4000)  // do not exit program before Futures complete

}






/** Exercise Solutions */
// 1.
//    val f1: Future[Unit] = Future {
//      Thread.sleep(2000)
//      println("First")
//    }
//
//    val f2: Future[Unit] = Future {
//      Thread.sleep(3000)
//      println("Second")
//    }
//
//    val bundledFutures: Future[List[Unit]] = Future.sequence(List(f1, f2))
//
//    Await.result(bundledFutures, 4.seconds)

// 2.
//    val f1 = Future(0).map { x =>
//      Thread.sleep(1000)
//      x + 1
//    }.map { x =>
//      Thread.sleep(1000)
//      x + 1
//    }.map { x =>
//      Thread.sleep(1000)
//      x + 1
//    }
//
//    val f2 = Future(0).flatMap { x =>
//      Thread.sleep(1000)
//      Future(x + 1)
//    }.flatMap { x =>
//      Thread.sleep(1000)
//      Future(x + 1)
//    }.flatMap { x =>
//      Thread.sleep(1000)
//      Future(x + 1)
//    }
//
//    val combo = Future.sequence(List(f1, f2)).foreach(_ => println("Done"))