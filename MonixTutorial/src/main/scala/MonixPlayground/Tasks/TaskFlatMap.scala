package MonixPlayground.Tasks

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

object TaskFlatMap extends App {

  /** .flatMap( ) Basics */
    // .flatMap in Monix is for performing intermediate operations on the values contained in Tasks
    // The inside of a .flatMap must always return a Task:
    val task1 = Task(5).flatMap(x => Task.eval(x + 1))

    task1.foreach(x => println(s"Added one: $x"))

    // Above, the .flatMap allowed us to work with the value wrapped in the Task.eval( ). We had to explicitly wrap
    // the result in a Task.eval( ) in the .flatMap( ), because .flatMap( ) must return a Task.


  /** A note on .map( ) */
    // .map( ) in Monix is used to apply a function to the value of a Task, just as in regular Scala:
    def addOne(x: Int) = x + 1
    val answer = Task.eval(5).map(addOne)
    answer.foreach(x => println(s"Added one with .map( ): $x"))

    // Anything that can be done with .map( ) can be done with .flatMap( )
    // .map(f) simply provides a shorthand way to do .flatMap(x => Task.now(f(x)))


}






object TaskFlatMapExercises extends App {
  import monix.execution.Scheduler.Implicits.global

  /** Exercises */
  // 1. Using .flatMap( ), add 5 to Task(3), and print the result

  // 2. Using .flatMap( ), print each value in Task(Task(List(1, 2, 3)))

  // 3. Translate the following for-comprehension into a combination of .flatMap( ) and .map( )
  //  val comp = for {
  //    t1 <- Task(10)
  //    t2 <- Task(t1 * 2)
  //    t3 <- Task(println(t2))
  //  } yield t3
  //
  //  comp.runSyncUnsafe()

}






/** Exercise Solutions */
// 1.
//    Task(3).flatMap(x => Task(x + 5)).foreach(println)
//    Task(3).flatMap(x => Task(x + 5)).map(println).runSyncUnsafe()
//    Task(3).flatMap(x => Task(x + 5)).map(x => println(x)).runAsyncAndForget
//    etc.

// 2.
//    Task(Task(List(1, 2, 3))).flatMap(x => x.flatMap(y => Task(y.foreach(println)))).runSyncUnsafe()
//    Task(Task(List(1, 2, 3))).flatMap(x => x.flatMap(y => Task(y.foreach(println)))).runAsyncAndForget
//    etc.

// Or:

//    Task(Task(List(1, 2, 3))).flatMap(x => x.flatMap(y => Task(y.map(println)))).runSyncUnsafe()
//    Task(Task(List(1, 2, 3))).flatMap(x => x.flatMap(y => Task(y.map(z => println(z))))).runSyncUnsafe()
//    Task(Task(List(1, 2, 3))).flatMap(_.flatMap(x => Task(x.foreach(println)))).runSyncUnsafe()
//    Task(Task(List(1, 2, 3))).flatMap(_.flatMap(y => Task(y.map(println)))).runSyncUnsafe()
//    etc.

// 3.
//    Task(10).flatMap(t1 => Task(t1 * 2)).flatMap(t2 => Task(println(t2))).map(t3 => Task(t3)).runSyncUnsafe()
//    - Recall that yield( ) performs a .map( )