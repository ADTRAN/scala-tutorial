package ScalaWithCatsBook.MonoidsSemigroups

object OverviewMonoidsSemigroups {

  // Overview:
  //     a Monoid is a Type that contains two things:
  //     1.)  an associative operation combine of type (A, A) => A
  //     2.)  an element empty of type A that is the identity element, ie 0 for +, 1 for *

  //     a Semigroup is just the combine portion of a monoid.



  // TOC:
  // A.) ExerciseBooleanMonoids -- Define some Boolean Monoids
  // B.) ExerciseSetMonoidsSemigroups -- What monoids and semigroups are there for sets
  // C.) MonoidsInCats -- we can use cats.Monoid type to get an already built monoid
  // D.) ExerciseAddingAllThings -- implement def add(items: List[Int]): Int

}
