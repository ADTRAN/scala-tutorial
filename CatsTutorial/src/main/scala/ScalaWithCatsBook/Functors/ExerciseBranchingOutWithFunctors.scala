package ScalaWithCatsBook.Functors

object ExerciseBranchingOutWithFunctors {
  // write a functor for the binary tree data type
  sealed trait Tree[+A]
  final case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]
  final case class Leaf[A](value: A) extends Tree[A]

}
