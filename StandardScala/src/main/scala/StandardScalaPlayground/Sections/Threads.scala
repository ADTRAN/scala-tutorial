package StandardScalaPlayground.Sections

import java.util.concurrent.{ExecutorService, Executors}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future, Promise}
import scala.util.{Failure, Success}

object Threads extends App {

  /** Threads Basics */
    // - Threads are a way to do concurrent programming in scala
    // - Defining a custom task in a Thread requires you to make a subclass extending the JVM's Thread class. This will
    //   include defining the .run() method for the class
    // - The Thread can then be started with .start, and you can await the execution's completion with .join

    // For example:
    // Define a class that extends Thread
    class MyThread extends Thread {
      override def run(): Unit = {
        for (i <- 1 to 3) {
          println(s"Thread ${Thread.currentThread().getName} says: $i")
          Thread.sleep(1000)
        }
      }
    }

    // Create instances of MyThread
    val thread1 = new MyThread
    val thread2 = new MyThread

    // Start the threads
    thread1.start()
    thread2.start()

    // Wait for the threads to finish
    thread1.join()
    thread2.join()

    println("Both threads have finished execution")


  /** Thread Pools */
    // - Creating Threads is expensive, so Thread Pools are often used to pre-allocate and reuse Threads
    // - Thread Pools accept tasks which extend the JVM's Runnable class, and once again, the .run() method must be
    //   defined

    // Create a fixed thread pool with 3 threads
    val threadPool: ExecutorService = Executors.newFixedThreadPool(3)

    // Define a simple task
    class SimpleTask(id: Int) extends Runnable {
      override def run(): Unit = {
        Thread.sleep(1000)  // Simulate some work
        println(s"Task $id completed on thread ${Thread.currentThread().getName}")
      }
    }

    // Submit tasks to the thread pool
    for (i <- 1 to 5) {
      threadPool.execute(new SimpleTask(i))
    }

    // Shut down the thread pool - no new tasks will be accepted, but previously submitted tasks will be executed
    threadPool.shutdown()
    println("Our ThreadPool has been shut down - before execution is complete!")


  // Todo: .wait( ) and .notify( ), .notifyAll( ) and synchronized
}


object ThreadsExercises extends App {
  /** Exercises */
  // 1. Using Threads, define a task to wait 5 seconds, then print "BIG!". Define another task to wait 1 second, and
  //    then print "small". Run 1 "big" task, and 5 "small" tasks, within 6 seconds

  // 2. Create a Thread Pool with 5 Threads. Submit 10 tasks to the Thread Pool which all wait for 2 seconds, before
  //    printing "Done!", along with the Thread's id

}






/** Exercise Solutions */
// 1.
//    class Big extends Thread {
//      override def run(): Unit = {
//        Thread.sleep(5000)
//        println("BIG!")
//      }
//    }
//
//    class Small extends Thread {
//      override def run(): Unit = {
//        Thread.sleep(1000)
//        println("small")
//      }
//    }
//
//    val bigTask = new Big
//    bigTask.start()
//
//    val smallTasks = for (i <- 1 to 5) yield new Small
//    smallTasks.foreach(task => task.start())
//
//    bigTask.join()
//    smallTasks.foreach(task => task.join())

// 2.
//    val threadPool: ExecutorService = Executors.newFixedThreadPool(5)
//
//    class SimpleTask extends Runnable {
//      override def run(): Unit = {
//        Thread.sleep(2000)
//        println(s"Done! ${Thread.currentThread().getName}")
//      }
//    }
//
//    for (_ <- 1 to 10) {
//      threadPool.execute(new SimpleTask)
//    }
//
//    threadPool.shutdown()