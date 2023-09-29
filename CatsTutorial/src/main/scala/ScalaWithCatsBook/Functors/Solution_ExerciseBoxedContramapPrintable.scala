package ScalaWithCatsBook.Functors

object Solution_ExerciseBoxedContramapPrintable {

  import ExerciseBoxedContramapPrintable.Box
  import ContravariantAndInvariantFunctors.ContraPrintable
  import ScalaWithCatsBook.TypeClass.Solution_ExercisePrintableLibrary.Printable

  implicit def printableBox[A](implicit p: ContraPrintable[A]): Printable[Box[A]]= {
    val func: Box[A] => A = b => b.value
    p.contramap[Box[A]](func)
    // can also be written like:
    // p.contramap[Box[A]](_.value)
  }


}
