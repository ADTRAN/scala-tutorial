package MonixPlayground.Tasks

import monix.eval.Task
import monix.execution.{Callback, Scheduler}

import scala.concurrent.Await
import scala.concurrent.duration._


object TasksBasics extends App {

  /** What are Tasks */
    // Tasks are an abstraction that makes asynchronous programming easier and more efficient in Scala.


  /** Creating Tasks */

    /** Task( ), Task.eval( ) and Task.delay( ) - [[TaskEvalDelay]] */
      // - These are identical - they create a Task whose value will be lazily evaluated each time it is run - like a function call
        // - Expressions that are "lazily evaluated" are only evaluated at the point in time when their values are
        //   needed by something else currently being executed - in  Monix, that means when they are executed by the Scheduler
      Task(1)
      Task.eval(1)

    /** Task.now( ) and Task.pure( ) - [[TaskNowPure]] */
      // - These are identical - they create a Task whose value is strictly evaluated.
        // - Strict (also know as "eager") evaluation is the opposite of lazy evaluation - the expression is evaluated
        //   as soon as it's bound to a variable.
      Task.now(1)
      Task.pure(1)

    /** Using >> or *> to chain Tasks */
      // - If we don't care about the return value from a Task, and would like to run one Task immediately after another,
      //   we can use >>, or *>
      val chained: Task[Unit] = Task(print("Run first... ")) >> Task(println("Run next"))

      // The above chain will return the value of the second Task.

    /** Task.evalOnce( ) */
      // - This Task will be lazily evaluated once, and reuse that first result in all subsequent runs of the Task
      Task.evalOnce("An expression with a re-usable result")

    /** Task.unit */
      // - The following are identical ways of representing Tasks that do nothing, commonly used to satisfy type
      //   requirements when performing intermediate operations:
      val myTaskUnit1: Task[Any] = Task.unit
      val myTaskUnit2: Task[Unit] = Task.now(())

    /** Task.never */
      // - Task.never creates a Task which will never complete, usually used to suspend program termination:
      Task.never
      // Below will both suspend program termination:
        // Task.unit.loopForever.runSyncUnsafe()
        // Task.never.runSyncUnsafe()

  /** Schedulers */

    /** Global Scheduler */
      // - By default, all Tasks need to be run on a scheduler, which organizes their asynchronous execution
      // - For most Tasks, simply importing monix.execution.Scheduler.Implicits.global will suffice:
      import monix.execution.Scheduler.Implicits.global

    /** Task.executeOn( ) - [[TasksParallel]] */
      // - You may define an alternate scheduler for the Task to be executed on, which will use a separate thread pool
      // - This is explicitly recommended in cases involving blocking I/O
      lazy val io = Scheduler.io(name = "io")
      Task.eval("Some blocking I/O Task").executeOn(io)

    /** Task.executeAsync - [[TasksParallel]] */
      // - This will ensure the Task is executed on its own thread. This allows for parallel execution, just as above
      Task.eval("Some blocking I/O Task").executeAsync


  /** Running & Evaluating Tasks */
  // - Building a Task does not execute that Task - it has to be picked up and put on the scheduler to be executed
  val helloTask = Task(println("Hello"))

    /** .runToFuture( ) - [[TaskRunToFuture]] */
      // - This triggers the asynchronous evaluation of the Task, and returns a future that can be awaited or cancelled:
      val myFuture = helloTask.runToFuture
      myFuture.cancel()

      // Note with Crtl + hovering over .runToFuture that the implicit scheduler is being used.
      // When to use .runToFuture -> If you'd like to be able to cancel the Task before completion, or want to
      // integrate with some interface using standard Scala futures (scala.concurrent.future)

    /** .runAsync( ) - [[TaskRunAsync]] */
      // - This will trigger the asynchronous execution of the Task at the discretion of the Scheduler
      // - .runAsync takes a callback function that will execute upon completion of the Task, which will return an Either:
      Task(1).runAsync(result => println(s"Do something with the returned Either: ${result.getOrElse("Nothing")}"))

      // A callback is a function that is passed to another function, and called when that other function is finished executing
      // In this case, we are passing it into .runAsync, and it is executed once the Either is returned from the evaluation
      // When to use .runAsync( ) -> When you'd like to like to specify a callback

      // Optionally, if you don't care about doing anything with the result, you can omit the callback using either of the following:
      helloTask.runAsyncAndForget
      helloTask.runAsync(Callback.empty)
      // When to use .runAsyncAndForget -> When you don't care about having a cancellable future or a callback

    /** .runSyncUnsafe( ) - [[TaskRunSyncUnsafe]] */
      // - This will trigger the synchronous execution of the Task, and will block its thread while waiting for a result
      helloTask.runSyncUnsafe()
      // When to use .runSyncUnsafe( ) -> When you absolutely need synchronous execution. Try to avoid this as a general rule


    /** Using .foreach( ) - [[TaskForEach]] */
      // - This evaluates a Task, and strictly applies a function to the result:
      val numberCountTask = Task.eval("Some val accessed by foreach")
      numberCountTask.foreach(x => println(x))


    /** Using .flatMap( ) - [[TaskFlatMap]] */
      // - .flatMap creates a new Task by applying a function to the successful result of the source Task, and returns a
      //   Task equivalent to the result of the function.
      // - In other words, it allows us to evaluate Tasks, perform operations on their values, and then continue dealing with Tasks
      //   in our program after an intermediate calculation:
      def odd(n: Int): Task[Boolean] =
        Task.eval(n % 2 == 1).flatMap { value =>
          value match {
            case false => Task.eval(false)
            case true => Task.eval(true)
          }
        }

      // Above - in the function, we define a Task that will check if a value is even. We then used .flatMap because it allows
      // us to evaluate the Task and check its value. This map function must return a Task, because .flatMap( ) ensures a new Task
      // to replace the evaluated one.

      odd(13).runAsync(result => println(s"The .flatMap operation gave us: ${result.getOrElse("Error")}"))


    /** Using for comprehensions - [[TaskForComprehensions]] */
      // - Because for comprehensions use .flatMap under the hood, they are integrated with Monix to also execute Tasks.
      // - Remember that for comprehensions chain .flatMaps, terminating in a .map in the yield statement:
      val result = for {
        one <- Task("Line one ")
        two <- Task(one + " Line two")
      } yield println(two)

      result.runAsyncAndForget

      // Above is equivalent to:
      // Task("Line one ").flatMap(first => Task(first + " Line two").map(second => println(second))).runAsyncAndForget
      // Notice that for comprehensions are much more readable, their primary benefit

}


object TasksBasicsExercises extends App {
  import monix.execution.Scheduler.Implicits.global

  /** Exercises */
  // 1. Starting with Task(5), print 6

  // 2. Create a Task that adds 2 + 3, prints the sum, and then returns the sum. Execute the Task

  // 3. From Task(Task(List(1, 2, 3))), print the total sum of all elements

}






/** Exercise Solutions */
// 1.
//    Task(5).flatMap(x => Task(x + 1)).foreach(println)
//    Task(5).foreach(x => println(x + 1))
//    Task(5).flatMap(x => Task(println(x + 1))).runAsyncAndForget
//    Task(5).map(x => println(x + 1)).runAsyncAndForget
//  etc.

// 2.
//    Task {
//      val sum = 2 + 3
//      println(sum)
//      sum
//    }.runAsyncAndForget

// 3.
//    Task(Task(List(1, 2, 3))).flatMap { nestedTask =>
//      nestedTask.flatMap(list =>
//        Task(println(list.sum))
//      )
//    }.runAsyncAndForget

// Or:

//    Task(Task(List(1, 2, 3))).flatMap { nestedTask =>
//      nestedTask.map { list =>
//        println(list.sum)
//      }
//    }.runAsyncAndForget

// Or:

//    val result: Task[Unit] = for {
//      nestedTask <- Task(Task(List(1, 2, 3)))
//      sum <- nestedTask.flatMap(list =>
//        Task(println(list.sum))
//      )
//    } yield sum
//
//    result.runAsyncAndForget

// etc.