package ScalaWithCatsBook.Functors

import cats.Functor

object _Functors extends App {
  // A cats Functor is simply a wrapper around something that can be mapped over - like a List( ), Set( ), Option( ), etc.
  // Any type which has one "hole" is a functor, where holes (*) represent another type -> Option[*], List[*]
  // Types like Tuple# have two or more "holes" -> Tuple2[*, *], Tuple3[*, *, *], etc.
  // in cats functors look like:
  /*
  trait Functor[F[_]] {
    def map[A, B](fa: F[A])(f: A => B): F[B]
  }*/

  /** Functor basics */
  // Functors are guaranteed to have a single operation, .map( ):
  val aFunctor = List(1, 2, 3).map(_ + 1)

  /** cats Functor */
  // Where T is a valid functor type, Functor[T].map( ) takes two sets of arguments
  // - The first parenthesis is the functor Object of type T
  // - The second parenthesis is the function to apply to the functor
  val catsListFunctor = Functor[List].map(List(1, 2, 3))(element => element + 1)

  // suppose we want to provide functional safety for adding 1 to ints
  val functionForIntegerAddition = (x: Int) => x + 1
  val liftedFunction = Functor[Option].lift(functionForIntegerAddition)


}
