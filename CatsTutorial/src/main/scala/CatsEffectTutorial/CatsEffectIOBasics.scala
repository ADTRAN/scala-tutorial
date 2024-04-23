package CatsEffectTutorial

import cats.effect.IO
import cats.effect.unsafe.implicits.global

import java.util.concurrent.Executors
import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

object CatsEffectIOBasics extends App {

  /** Creating IO */

    /** IO( ), IO.apply( ), IO.delay( ) */
      // - These are identical - they lift any operation into the IO context, which will be lazily evaluated upon
      //   execution.
      IO.delay("Some operation")
      IO("Some operation")
      IO.apply("Some operation")

      // Multi-line operations require IO{ } rather than IO( ):
      IO.delay {
        Thread.sleep(10000)
        "return val"
      }

    /** IO.pure( ) */
      // - IO.pure is for lifting already-known values, which do not require evaluation, into the IO context.
      // - When used on already-known values, it is more performant than IO.delay( ).
      // - Be aware that the operation will be eagerly evaluated, meaning it will block to evaluate if the value is not
      //   already known, as soon as it is initialized, even before it is executed.
      IO.pure("Already-known value")

    /** IO.blocking( ) */
      // - IO.blocking( ) is for operations that are expected to block. The evaluation will be sent to a thread pool
      //   separate from the main execution context
      IO.blocking(Thread.sleep(5000))

    /** Chaining IO */
      // - If we don't care about the return value from an IO, and would like to evaluate one IO immediately after another,
      //   we can use >> or *>
      val chained1: IO[String] = IO("first") >> IO("second") >> IO("third")
      val chained2: IO[String] = IO("first") *> IO("second")

      // Above in chained1, we ignore the returned value from the first two IO, and only return the third value.

    /** IO.print( ), IO.println( ) */
      // - These are simple ways to lift a print statement into the IO context:
      IO.print("Hello world")
      IO.println(List(1, 2, 3))

    /** IO.none, IO.some( ) */
      // - These are both for creating Options, lifted into the IO context:
      val none: IO[Option[String]] = IO.none[String]
      val some: IO[Option[String]] = IO.some("some value")

    /** IO.sleep( ) */
      // - IO.sleep( ) is an asynchronous wait in the IO context, and so only halts the CPU thread
      //   when scheduled and executed:
      IO.sleep(1.second)

    /** IO.realtime */
      // - This lifts the current epoch time into IO context:
      IO.realTime

    /** IO.unit */
      // A Unit lifted into the IO context:
      IO.unit

    /** IO.never */
      // Creates an IO which will never complete evaluation:
      IO.never

  /** Schedulers */

    /** Global Scheduler */
      // - By default, all IO need to be run on a scheduler, which organizes their asynchronous execution
      // - For most IO, simply importing cats.effect.unsafe.implicits.global will suffice:

     // import cats.effect.unsafe.implicits.global

    /** IO.evalOn( ) */
      // - You may define an alternate ExecutionContext from import scala.concurrent.ExecutionContext for the IO to be
      //   executed on, which will use a separate thread pool
      // - This also requires defining an Executor from java.util.concurrent.Executors
      val executor = Executors.newCachedThreadPool()
      val context1 = ExecutionContext.fromExecutor(executor)
      IO("Some blocking IO").evalOn(context1)


  /** Executing IO */

    /** .unsafeRunAsync( ), .unsafeRunAndForget( ) */
      // - This will trigger the asynchronous execution of the IO at the discretion of the Scheduler
      // - .unsafeRunAsync takes a callback function that will execute upon completion of the IO, which will return an Either:
      IO("Something Async").unsafeRunAsync { result =>
        println(result.getOrElse(""))
      }

      // - If you don't want to specify a callback because you don't care about the return value, you can use:
      IO("Something AsyncAndForgotten").unsafeRunAndForget()

    /** .unsafeRunSync() */
      // - This will trigger the synchronous execution of the Task, and will block its thread while waiting for a result
      // - For this reason, this is intended to be used either at the "end of the world" in your program logic, or
      //   for already-known values
      IO("An already-known value").unsafeRunSync()


  /** Operating on IO */

    /** Using .flatMap( ) */
      // - .flatMap( ) allows you to lift a value from an IO context, operate on it, and then return an IO:
      IO(1).flatMap(x => IO.println(x + 1))   // would print 2 when executed

    /** Using .map( ) */
      // - Similar to .flatMap( ), .map( ) allows you to operate on a value bound in an IO context, and also always returns
      //   an IO instance, though you don't have to explicitly wrap the result in an IO( ) with .map( ) - it's done implicitly:
      val result: IO[Int] = IO(1).map(x => x + 1)

    /** Using for-comprehensions */
      // - Because for comprehensions are syntactic sugar for .flatMap( ) and .map( ) operations, they can be similarly used
      //   to operate on IO instances:
      val res: IO[Int] = for {
        one <- IO(1)
        two <- IO(one + 1)
      } yield two

      // Above yields IO(2), and is equivalent to the statement:
      //  IO(1).flatMap(one => IO(one + 1).map(two => two))
      // Each <- in the comprehension represents a .flatMap( ), and the yield statement represents a .map( )


}
