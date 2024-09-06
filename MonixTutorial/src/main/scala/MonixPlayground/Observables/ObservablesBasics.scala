package MonixPlayground.Observables

import monix.eval.Task
import monix.execution.Scheduler
import monix.execution.Scheduler.Implicits.global
import monix.reactive.Observable

import scala.concurrent.duration._

object ObservablesBasics extends App {

  /** What are Observables */
    // - Observables are sources for events. They allow us to process the events reactively (as they come in) without
    //   blocking a thread to wait between their arrivals.

  /** Creating Observables */

    /** Observable( ), Observable.fromIterable( ) */
      // - These are identical. Using them, you can turn any collection (Map, Set, List, etc.) into an Observable:
      Observable(Map(1 -> 2, 2 -> 3))   // Observable(1 -> 2, 2 -> 3) also works
      Observable.fromIterable(List("a", "b", "c"))

    /** Observable.now( ), Observable.pure( ) - [[ObservableNowPure]] */
      // - These are identical. They are for lifting an already-known/evaluated value into the Observable context:
      Observable.now(List(1, 2, 3, 4))
      Observable.pure(List(1, 2, 3, 4))

    /** Observable.eval( ), Observable.delay( ) - [[ObservableEvalDelay]] */
      // - These are the same. These Observables are only evaluated upon subscription (lazily evaluated):
      Observable.eval(List(1, 2, 3, 4))
      Observable.delay(List(1, 2, 3, 4))

    /** Observables With Multi-line Operations */
      // - Any operation can be put into an Observable, and multi-line operations require { } instead of ( ):
      Observable {
        val hello = "Hello!"
        println(hello)
      }
      // Note that the print statement will be executed above, even though the Observable is never executed.
      // This means that Observable.fromIterable( )'s are eagerly evaluated

    /** Observable.range( ) */
      // - Using .range( ) will create an Observable of an iterable from the (lower bound) to the (upper bound - 1):
      Observable.range(1, 5)  // (1, 2, 3, 4)
      Observable.range(2, 10, 2)  // (2, 4, 6, 8)

    /** Observable.interval( ) */
      // - Creates an Observable which will emit an incremented whole number every <duration>.
      Observable.interval(1.second)   // 1... 2... 3... 4... etc.

    /** Observable.timerRepeated( ) */
      // - After a given initial delay, emit a value every given period, forever:
      Observable.timerRepeated(
        initialDelay = 0.seconds,
        period = 1.second,
        "Hello"
      )

    /** Observable.repeat( ) */
      // - .repeat( ), which can also be appended to the end of an Observable, will replay the value from the evaluation
      // forever:
      private def waitSec: Unit = Thread.sleep(1000)

      Observable.repeat {
        waitSec
        1
      }
      // Above, we will wait one second on the first evaluation, and thereafter replay just the return value, 1, forever.

    /** Observable.repeatEval( ) */
      // - .repeatEval( ) will repeat the entire evaluation of the Observable forever:
      Observable.repeatEval {
        waitSec
        1
      }
      // Above, unlike with .repeat, we will perform the sleep every single time, and then return 1

    /** Observable.evalOnce( ) */
      // .evalOnce( ) will, upon the first evaluation, "memoize" - cache, basically - the result for all future references:
      var count = 0
      val result = Observable.evalOnce {
        count += 1
        count
      }
      result.foreach(x => println(s"evalOnce once: $x"))
      result.foreach(x => println(s"evalOnce twice: $x"))

    /** Observable.defer( ), Observable.suspend( ) */
      // - These are identical ways of creating a lazily-evaluated Observable from a function that returns an Observable:
      var countDefer = 0

      def addTwo: Observable[Int] = {
        Thread.sleep(10000)
        countDefer += 2
        Observable(countDefer)
      }

      val defered1: Observable[Int] = Observable.defer(addTwo)
      val defered2: Observable[Int] = Observable.defer(addTwo)
      // The function must return an Observable. Note that if you used Observable.eval( ) to make your lazily-evaluated
      // Observable above, you would have a nested Observable[Observable[Int]], instead of just Observable[Int]

    /** Observable.merge */
      // - .merge combines one level of nested Observables into one Observable, similar to how .flatten combines one level
      //   of a nested collection in regular scala:
      val oneObservable: Observable[Int] = Observable.merge(
        Observable(1, 2, 3),
        Observable(4, 5, 6)
      )

    /** Observable.fromTask( ) */
      // - Tasks can be lifted out of the Task context and into the Observable context with .fromTask( ):
      Observable.fromTask(Task(1, 2, 3))


  /** Schedulers */

    /** Global Scheduler */
      // - As with Tasks, Observables need a scheduler to manage async operations.
      // - For most Observables, simply importing monix.execution.Scheduler.Implicits.global will suffice

    /** Observable.executeOn( ) */
      // - You may define an alternate scheduler for the Observable to be executed on, which will use a separate thread pool
      // - This is explicitly recommended in cases involving blocking input/output:
      lazy val io = Scheduler.io(name = "io")
      Observable("Some I/O operation").executeOn(io)

    /** Observable.executeAsync */
      // - This will ensure the Observable is executed on its own thread. This allows for parallel execution, just as above
      Observable(1).executeAsync


  /** Executing an Observable */

    /** .subscribe( ) */
      // - Until .subscribe is called on an Observable, nothing happens, unless the Observable is created with .now( ) or .pure( ),
      //   which causes immediate evaluation, as mentioned above.
      // - .subscribe is typically called at the end of a program's logic, and it begins evaluating each element in the Observable:
      val stream = Observable(1, 2, 3).map(x => println(s"Running $x"))
      stream.subscribe()

    /** .foreach( ) */
      // .foreach( ) subscribes to the stream, along with applying the specified function to each element:
      val stream2 = Observable(1, 2, 3)
      stream2.foreach(x => println(s"Foreach says: ${x * 10}"))

    /** Observable.delayExecution( ), Observable.delayExecutionWith( ) */
      // .delayExecution( ) will delay a subscription for a finite duration:
      val delay1 = Observable("Delayed1").delayExecution(3.seconds)
      // .delayExecutionWith( ) will delay a subscription until the specified trigger Observable emits an event or completes:
      val delay2 = Observable("Delayed2").delayExecutionWith(delay1)
      delay1.foreach(println)
      delay2.foreach(println)


  /** Operating on Observables */

    /** .flatMap( ), .concatMap( ) - [[ObservableFlatMap]] */
      // - Like Tasks, the contents of an Observable can be operated on using .flatMap( ), as they are emitted.
      //   and will cause the evaluation of whatever is contained in the Observable
      val results: Observable[Int] = Observable(1, 2, 3).flatMap(emitted =>
        Observable(emitted * 2)
      )

      results.foreach(x => println(s"The .flatMap() yielded: $x"))

      // Notice above that just like with Tasks, we must always return an Observable within the .flatMap( )

    /** .filter( ) */
      // - Also available is .filter( ), which behaves just like regular scala .filter( ), returning an Observable:
      val filtered = Observable.range(1, 100).filter(elem => elem % 10 == 0)
      filtered.foreach(x => println(s"Filtered was $x"))

    /** .foldLeft( ) */
      // - As with standard scala .foldLeft( ), you can use .foldLeft( ) to accumulate all of the values in the Observable
      //   into a single value using some function:
      val folded = Observable.range(1, 10000).foldLeft(0L)((accumulator, next) => accumulator + next)
      folded.foreach(result => println(s"Folded was $result"))

    /** .collect{ } */
      // As with standard .collect{ }, you can use .collect{ } to specify a partial function to apply to elements in
      // the Observable:
      val collected = Observable(1, true, "string", List(1, 2)).collect {
        case num: Int => num * 2
        case bool: Boolean => false
        case str: String => str.concat("-string2")
      }
      collected.foreach(result => println(s"Collected was $result"))

    /** .take( ) Methods */
      // - There are a number of methods prefixed with ".take", which allow the closing of an Observable after
      //   certain conditions are met.
      // - Besides operating on regular Observables, this allows us to terminate infinite streams:
      val takeFive = Observable.interval(50.milliseconds).take(5)
      takeFive.foreach(x => println(s"TakeFive was $x"))
      // Above, we took 5 elements, then the Observable ended, and returned.

      // - .takeWhile( ) - take elements until a predicate is true:
      val takeWhile = Observable.interval(50.milliseconds).takeWhile(result => result < 3)
      takeWhile.foreach(x => println(s"TakeWhile was $x"))

      // Above, each "result" is being evaluated against the predicate as they are added to the Observable

      /* Many such methods exist, and can be chained together:
         - .takeUntil( ) - take elements until another "trigger" Observable begins to emit elements, or completes:
            - Observable.interval(50.milliseconds).takeUntil(Observable.unit.delayExecution(1.second))
               .foreach(x => println(s"TakeUntil was $x"))

         - .takeEveryNth( ) - take only every nth element into the Observable, dropping the rest:
            - Observable.interval(50.milliseconds).takeEveryNth(5).take(10)
             .foreach(x => println(s"TakeEveryNth was $x"))

         - .takeByTimespan( ) - take elements only until a given time limit is exceeded:
            - Observable.interval(50.milliseconds).takeByTimespan(1.second)
              .foreach(x => println(s"TakeByTimespan was $x"))
      */

    /** .drop( ) Methods */
      // - Corresponding to .take( ) methods, we have .drop methods, which can be used to ignore events:
      val dropTwoTakeOne = Observable.interval(50.milliseconds).drop(2).take(1)   // drop 0 and 1 from the series
      dropTwoTakeOne.foreach(x => println(s"DropTwoTakeOne was $x"))

      // As with .take, there is .drop, .dropLast, .dropWhile, .dropUntil, .dropByTimespan, etc.

    /** .mapEval( ) */
      // - .mapEval( ) will apply a Task to each element emitted by the Observable.
      // - For each element, the Task will be evaluated, without the need to use any explicit Task execution methods, i.e. .runAsync( ):
      val me = Observable(1, 2, 3).mapEval { x =>
        Task(println(s"MapEval says: ${x * 2}"))
      }
      me.subscribe()


  /** Turning Finite Observables Directly Into Tasks */
    // - Observables have various "L functions". These convert Observables into Monix Tasks

    /** Observable.toListL */
    // You can use .toListL to put all items into a Task[List[]]:
    val listTask: Task[List[Int]] = Observable(1, 2, 3).toListL
    listTask.foreach(list => println(s"The Task[List] was: $list"))

    /* Likewise, other collection-based methods that have standard Observable support are also available for Task conversion:
       - .foreachL, .foldLeftL
       - .firstL, .headL, .lastL
       - .countL, .sumL, .maxL, .minL
       - .existsL, .isEmptyL, .nonEmptyL

       If your input stream is infinite, these methods will never complete, and will eventually throw an error.
    */



    Task.never.runSyncUnsafe()   // Do not exit program, to give time for Observables to run
}






object ObservablesBasicsExercises extends App {
  import monix.execution.Scheduler.Implicits.global
  import scala.concurrent.duration._

  /** Exercises */
    // 1. Create an Observable with the values from Map(1 -> "one", 2 -> "two"), and print the values from the Map

    // 2. Create an Observable that adds 1 + 2, prints the sum, and then returns the sum. Subscribe to the Observable

    // 3. Starting with Observable.interval(1.second), print 5, 10, 15
    //    hint: use .drop and .take

    // 4. Add 1 + 2 and 3 + 4 in two separate Observables, then merge them into one. Then, convert the Observable to a Task[List],
    //    and print the sum of the List



  Task.never.runSyncUnsafe()   // Do not exit program, to give time for Observables to run

}






/** Exercise Solutions */
// 1.
//    val obs = Observable(1 -> "one", 2 -> "two") // or Observable.fromIterable(Map(1 -> "one", 2 -> "two"))
//    obs.foreach(elem => println(elem._2))

// Or:

//    val obs = Observable(1 -> "one", 2 -> "two").map(elem => println(elem._2))
//    obs.subscribe
// etc.

// 2.
//    val obs = Observable {
//      val sum = 1 + 2
//      println(s"Sum was $sum")
//      sum
//    }
//    obs.subscribe

// 3.
//    val obs = Observable.interval(1.second).drop(1).take(3).map(x => println(x * 5))
//    obs.subscribe

// Or:

//    val obs = Observable.interval(1.second).drop(1).take(3)
//    obs.foreach(x => println(x * 5))

// Or:

//    val obs = Observable.interval(1.second).drop(1).takeEveryNth(5).take(3)
//    obs.foreach(println)
// etc.

// 4.
//    val obs1 = Observable(1 + 2)
//    val obs2 = Observable(3 + 4)
//    val merged: Task[List[Int]] = Observable(obs1, obs2).merge.toListL
//    merged.foreach(x => println(x.sum))
