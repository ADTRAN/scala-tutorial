package MonixPlayground.Tasks

import cats.effect.ExitCode
import monix.eval.{Task, TaskApp}
import monix.execution.Scheduler.Implicits.global
import monix.reactive.Observable

import scala.util.Random

/** A Basic TaskApp */
object TaskApps extends TaskApp {
  /**
    - In production, you may see a TaskApp, which is a trait provided by Monix
    - The pattern it enforces has a single "run" method, which executes a Task, intended to be the main Task containing
      all of your program logic
    - In the example below, we have a "main" method, which contains a single for-comprehension
      - Let each line in the for-comprehension represent some arbitrary operation involving a Task
      - In the end, the results are combined into a single Task, and returned to "run", which will execute the Task
      - In reactive programming, our Tasks would be hooked into some event loop waiting for events, and so would
        keep processing forever, unlike the example below, which finishes its work almost immediately
  */
  override def run(args: List[String]): Task[ExitCode] = {
    main.flatMap(_ => Task.unit.loopForever.as(ExitCode.Success))
  }

  def main: Task[Unit] = {
    val result = for {
      one   <- val1
      two   <- val2
      three <- val3
    } yield println(one + two + three)
    result
  }

  def val1: Task[Int] = {
    Task.eval(1)
  }
  def val2: Task[Int] = {
    Task.eval(2)
  }
  def val3: Task[Int] = {
    Task.eval(3)
  }

}


/** A More Complex TaskApp, involving a Monix Observable -> [[MonixPlayground.Observables.ObservablesBasics]] */
object TaskApps2 extends TaskApp {
  /**
    - In the below example, let each source represent some genuine source of events that our microservice cares about
    - Then, instead of the process function merely printing, you can imagine we would be sending a result to somewhere
      else in our ecosystem, or to another object for further processing
    - Finally, see that the event loop is started by the automatic call to .run( ), but there is no expectation that
      processing will ever actually end. It's only serving as an entrypoint
   */
  override def run(args: List[String]): Task[ExitCode] = {
    val random = new Random()

    val source1 = Observable.repeatEval {
      Thread.sleep(1000)
      random.nextInt(1000000)
    }.executeAsync

    val source2 = Observable.repeatEval {
      Thread.sleep(1000)
      random.nextInt(1000000)
    }.executeAsync

    process(source1, source2)
  }


  def process(source1: Observable[Int], source2: Observable[Int]): Task[ExitCode] = {
    source1.foreach { rand =>
      if(rand % 2 == 0) println(s"$rand was even") else println(s"$rand was odd")
    }

    source2.foreach { rand =>
      if(rand % 2 == 0) println(s"$rand was even") else println(s"$rand was odd")
    }

    Task.unit.as(ExitCode.Success)
  }

}