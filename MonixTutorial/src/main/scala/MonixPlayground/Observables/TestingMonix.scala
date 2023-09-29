package MonixPlayground.Observables

import monix.eval.{Fiber, Task}
import monix.reactive.{Observable, OverflowStrategy}
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.duration.DurationInt
import scala.util.Random

object TestingMonix extends App {
  val one = Task {
    println("Test")
    throw new Exception("Error")
  }.delayExecution(1.second).onErrorRestart(10)

  one.runSyncUnsafe()

}
