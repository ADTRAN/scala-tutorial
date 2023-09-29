package ScalaWithCatsBook.TypeClass

object ExercisePrintableLibrary {

  trait Printable[A] {
    def format(a: A): String
  }

  object PrintableInstances {
    /*
      define a string Printable[String] and a Printable[Int] here
     */
  }

  object Printable {
    // implement stubbed signatures below, note arg not present
    def format[A] = ???
    def print[A] = ???
  }

}
