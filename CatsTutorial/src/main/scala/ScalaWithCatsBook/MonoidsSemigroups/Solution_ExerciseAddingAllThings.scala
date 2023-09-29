package ScalaWithCatsBook.MonoidsSemigroups

import cats._

object Solution_ExerciseAddingAllThings extends App{

  def add[A](items: List[A])(implicit combiner: Semigroup[A]): A = {
    items.reduce(combiner.combine(_, _))
  }

  assert (5 == add(List(1,2,2)))
  val nothin: Option[Int] = None
  assert (Some(5) == add(List(Option(1), Option(2), nothin, Option(2))))
}
