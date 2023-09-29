package ScalaWithCatsBook.Functors

object ContravariantAndInvariantFunctors {
  // two type classes similar to Functor are
  // 1.) contravariant functor -- has operation called contramap
  //     contramap prepends an operation to a chain.

  /* for Printable */
  import ScalaWithCatsBook.TypeClass.Solution_ExercisePrintableLibrary._

  // here we create a new Printable[B]
  trait ContraPrintable[A] extends Printable[A] {
    self => // use self inorder to get a handle to outer interface format.

    def contramap[B] (func: B => A): Printable[B] = new Printable[B] {
      def format(b: B): String = {
        self.format(func(b))
      }
    }
  }


}
