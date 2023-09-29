package ScalaWithCatsBook.TypeClass

import cats._
import cats.implicits._

object Solution_ExerciseCatsPrintableLibrary {

  object Printable {
    def format[A](a: A)(implicit formatter: Show[A]): String = formatter.show(a)
    def print[A](a: A)(implicit formatter: Show[A]): Unit = println(formatter.show(a))
  }

  // uses cats.implicits Show[Int] implicit definition
  Printable.print[Int](5)
}
