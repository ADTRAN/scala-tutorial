package MonixPlayground.Tasks

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.duration._

object TaskSequence extends App {

  /** Task.sequence( ) Basics */
    // Task.sequence( ) is a way to gather up Tasks into a single Task, and run them together
    // In terms of types, it is a way to turn a List[Task] into a Task[List] -> though any Collection can be used, not just List

    val myTasks = List(Task(println(1)), Task(println(2)), Task(println(3)))

    Task.sequence(myTasks).runAsyncAndForget

    // .sequence( ) causes the Tasks to be executed one by one, in order, which means that both effects and results will be ordered


  /** Task.parSequence( ) */
    // If, however, we want to run Tasks in parallel, we can use Task.parSequence( ):
    val firstTask = Task {
      Thread.sleep(2000)
      println("First executed")
      1
    }
    val secondTask = Task {
      Thread.sleep(1000)
      println("Second executed")
      2
    }

    val parallel = Task.parSequence(List(firstTask, secondTask)).runSyncUnsafe()
    println(s"Return vals for Task.parSequence( ) were $parallel")

    // The effects of the Tasks in a Task.parSequence( ) are not guaranteed to be in order, but the results are returned in order
    // That's why above, the second message printed before the first, yet we still get List(1, 2) as a result.


  /** Task.parSequenceUnordered( ) */
    // Similarly, you can use Task.parSequenceUnordered( ), which will execute the collection of Tasks in parallel, and
    // will prepend the results to a return List as they complete. In other words, results are ordered from most recently completed to first completed:

    val taskOne = Task {
      Thread.sleep(4000)
      println("First executed")
      1
    }
    val taskTwo = Task {
      Thread.sleep(2000)
      println("Second executed")
      2
    }
    val taskThree = Task {
      Thread.sleep(1000)
      println("Third executed")
      3
    }
    val taskFour = Task {
      Thread.sleep(5000)
      println("Fourth executed")
      4
    }

    val parallelUnordered = Task.parSequenceUnordered(List(taskOne, taskTwo, taskThree, taskFour)).runSyncUnsafe()
    println(s"Return vals for Task.parSequenceUnordered( ) were $parallelUnordered")

    // Therefore, return value order is not guaranteed, but if you know how long each Task will take, technically you
    // could deduce the return value.


   /** For more examples, see [[TasksParallel]] */

}






object TaskSequenceExercises extends App {
  import monix.execution.Scheduler.Implicits.global

  /** Exercises */
  // 1. Using a sequence method, and the Tasks below, print "Second" before "First"
  val task1 = Task(println("First")).delayExecution(3.seconds)
  val task2 = Task(println("Second")).delayExecution(2.seconds)


  // 2. Using a method to generate Tasks, gather 100 Tasks that each wait for 2 seconds and then print "Hello", and run them
  //    in parallel

}






/** Exercise Solutions */
// 1.
//    Task.sequence(List(task2, task1)).runSyncUnsafe()
//    Task.parSequence(List(task1, task2)).runSyncUnsafe()
//    Task.parSequenceUnordered(List(task1, task2)).runSyncUnsafe()
//    etc.

// 2.
//    def generate(num: Int): List[Task[Unit]] = {
//      val listTasks = (1 to num).map { x =>
//        Task {
//          Thread.sleep(2000)
//          println(s"Hello $x")
//        }
//      }.toList
//
//      listTasks
//    }
//
//    Task.parSequence(generate(100)).runSyncUnsafe()