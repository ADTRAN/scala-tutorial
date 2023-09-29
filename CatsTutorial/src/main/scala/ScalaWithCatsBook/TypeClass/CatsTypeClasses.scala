package ScalaWithCatsBook.TypeClass

import cats._
import cats.implicits._

object CatsTypeClasses extends App {
  //cats.Show is meant to be a better toString implementation
  val showInt: Show[Int] = Show.apply[Int]
  val showString: Show[String] = Show.apply[String]

  // expand on Cats implementations by implementing directly
  import java.util.Date
  implicit val dateShow: Show[Date] =
    new Show[Date] {
      def show(date: Date): String = s"${date.getTime} ms since epoch."
    }

  new Date().show

  // or using convenience methods
  def show[A](f: A => String): Show[A] = new Show[A] {
    def show(a: A): String = f(a)
  }
  def fromToString[A]: Show[A] = new Show[A] {
    def show(a: A): String = a.toString
  }

  // note we can't define two implicits of same types in same scope or we get an
  // ambiguous error, so we comment out the implicit marker so code compiles.
  /*implicit*/ val dateShow2: Show[Date] = Show.show(date => s"${date.getTime} ms since epoch.")
}
