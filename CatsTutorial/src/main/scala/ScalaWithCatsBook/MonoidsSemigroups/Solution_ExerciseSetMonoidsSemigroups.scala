package ScalaWithCatsBook.MonoidsSemigroups

// note below are just a couple of the monoids avail for sets.
class Solution_ExerciseSetMonoidsSemigroups {
  import ExerciseBooleanMonoids._

  implicit def setUnion[A]: Monoid[Set[A]] = new Monoid[Set[A]] {
    def empty: Set[A] = Set.empty
    def combine(x: Set[A], y: Set[A]): Set[A] = x.union(y)
  }

  implicit def setIntersection[A]: Semigroup[Set[A]] = new Semigroup[Set[A]] {
    def combine(x: Set[A], y: Set[A]): Set[A] = x.intersect(y)
  }

}
