package ScalaWithCatsBook.MonoidsSemigroups

object ExerciseBooleanMonoids {

  // define some monoids for this type, starting with following definitions
  trait Semigroup[A] { def combine(x:A, y:A): A }
  trait Monoid[A] extends Semigroup[A] { def empty: A }
  object Monoid { def apply[A](implicit monoid: Monoid[A]) = monoid}



}
