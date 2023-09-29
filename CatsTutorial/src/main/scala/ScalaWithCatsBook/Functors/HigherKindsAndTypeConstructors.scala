package ScalaWithCatsBook.Functors

import cats.Functor

object HigherKindsAndTypeConstructors {

  // Kinds are like types for types ~ to # of holes exist for a type
  // List is a type constructor with one hole, i.e. List[Int], List[A]
  //   note List is a type constructor, List[A] is a type.

  // type constructors use underscore(s) to specify hole(s)
  def myMethod[F[_]](implicit f: Functor[F]) = {
    //f.map// whatever
  }

}
