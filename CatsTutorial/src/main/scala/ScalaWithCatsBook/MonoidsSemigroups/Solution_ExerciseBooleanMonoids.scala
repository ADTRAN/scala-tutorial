package ScalaWithCatsBook.MonoidsSemigroups

class Solution_ExerciseBooleanMonoids {
  import ExerciseBooleanMonoids._ // for starting definitions

  val and = Monoid(
    new Monoid[Boolean] {
      def combine(x: Boolean, y: Boolean) = x && y
      def empty = {true}
    }
  )

  val or = Monoid(
    new Monoid[Boolean] {
      def combine(x: Boolean, y: Boolean): Boolean = x || y
      def empty: Boolean = {false}
    }
  )

}
