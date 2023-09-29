package MonixPlayground.Tasks

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global


object TaskForEach extends App {

  /** .foreach( ) Basics */
    // - In Monix, .foreach is used to strictly evaluate a Task, and run a function on the result
    // - In practice, .foreach is rarely used outside of debug or test situations, having the sole
    //   use of triggering side effects

    Task.eval(500).foreach(println)

    // Note that while .foreach( ), like .map( ) and .flatMap( ), allows you to operate on the value wrapped in a Task,
    // the return value of .foreach( ) will always be Unit, whereas for .map( ) & .flatMap( ), we always return a Task:
    def less(integer: Int) = {
      val myVal = integer - 1
      println(myVal)
    }

    val result = Task.eval(5).foreach(less)
    println(s"The result was $result")    // note that the result is () -> the Unit value

}






object TaskForEachExercises extends App {
  import monix.execution.Scheduler.Implicits.global

  /** Exercises */
  // 1. Using .foreach( ), print each element in the List inside Task(Task(List(1, 2, 3)))

  // 2. Using .foreach( ), print the sum of the elements in the List inside Task(List(1, 2, 3))

}






/** Exercise Solutions */
// 1.
//    Task(Task(List(1, 2, 3))).foreach(nestedTask =>
//      nestedTask.foreach(list =>
//        list.foreach(elem =>
//          println(elem)
//        )
//      )
//    )

// 2.
//   Task(List(1, 2, 3)).foreach(list => println(list.sum))

// Or:

//   Task(List(1, 2, 3)).foreach(list => println(list.foldLeft(0)((a, b) => a + b)))
//   etc.