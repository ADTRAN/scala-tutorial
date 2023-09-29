package ScalaWithCatsBook.TypeClass

object Solution_ExercisePrintableLibrary {

  trait Printable[A] {
    def format(a: A): String
  }

  object PrintableInstances {
    implicit val printableString = new Printable[String] {
      def format(s: String): String = s
    }
    implicit val printableInt = new Printable[Int] {
      def format(i: Int): String = i.toString
    }
  }

  object Printable {
    def format[A](a: A)(implicit formatter: Printable[A]): String = formatter.format(a)
    def print[A](a: A)(implicit formatter: Printable[A]): Unit = println(formatter.format(a))
  }

}
