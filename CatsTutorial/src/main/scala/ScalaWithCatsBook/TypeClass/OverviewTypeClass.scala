package ScalaWithCatsBook.TypeClass

object OverviewTypeClass {

  // Overview:
  //     TypeClasses allow for ad-hoc polymorphism using generics and are implemented using scala implicits

  // TOC:
  // A.) TypeClass                   -- overview of TypeClasses and implicit scope with type enhancement
  // B.) RecursiveImplicitResolution -- Type class composition with example

  /* C.) ExercisePrintableLibrary:
     1.) define a type class Printable[A] containing a single method "format".
         def format[A](a: A): String

     2.) create an object PrintableInstances containing instances of Printable
         for String and Int.

     3.) define an object Printable with two generic interface methods:
         a.) format accepts a value of type A and a Printable of the corresponding type.
             It uses the relevant Printable to convert the A to a String.
         b.) print accepts the same parameters as format and returns Unit (prints to console)
   */

  // C.) CatsTypeClasses -- cats has some predefined type classes and some convenience around expanding
  // D.) Exercise re-implement Cat application using Show instead of printable
  // E.) CatsEquality -- cats provides some safe equality checks using type classes

  // F.) VariandAndTypeClasses --
  /*     a.) covariance     -- means that type F[B] is a subtype of F[A] if B is a subtype of A
                               trait F[+A] (used for modeling output types i.e. List[+A]

         b.) contravariance -- means that type F[B] is a subtype of F[A] if A is a subtype of B
                               trait F[-A] (used for modeling input types i.e. JsonWriter[-A]
   */

}
