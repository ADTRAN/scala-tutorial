package ScalaWithCatsBook.MonoidsSemigroups

object MonoidsInCats {
  import cats._
  import cats.implicits._

  val three = Monoid[Option[Int]].combine(Option(3), none).get
  assert (3 == three)
}
