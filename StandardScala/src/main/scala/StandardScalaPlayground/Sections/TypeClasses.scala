package StandardScalaPlayground.Sections

object TypeClasses extends App {

  /** Intro to Generics (Parametric Polymorphism) */
    // - In scala, generic types are stand-ins for types which are specified only when the generic class is instantiated,
    //   or the generic method is used, etc.
    // - Almost always, generics will be represented by a single capital letter - A, B, I, etc. However, you can
    //   technically use whatever you want

    // Below is an example of annotating a simple class with generic typing:
    class Pair[A, B](val first: A, val second: B) {
      def swap: Pair[B, A] = {
        println("Swap!")
        new Pair[B, A](second, first)
      }
    }

    val intStringPair: Pair[Int, String] = new Pair[Int, String](100, "Hello world")
    println(s"First was ${intStringPair.first}, second was ${intStringPair.second}")

    val swappedPair: Pair[String, Int] = intStringPair.swap
    println(s"First was ${swappedPair.first}, second was ${swappedPair.second}")


  /** Type Classes */
    // - A type class allows us to use Traits implicitly, and define a function's behavior across multiple types

    // First, we make our generic trait:
    trait Printable[A] {
      def format(value: A): String
    }

    // Then, we create instances for each type that we want to define the type class for:
    object PrintableInstances {
      implicit val printableInt: Printable[Int] = new Printable[Int] {
        def format(value: Int): String = s"Int: $value"
      }

      implicit val printableString: Printable[String] = new Printable[String] {
        def format(value: String): String = s"String: $value"
      }

      implicit val printableBoolean: Printable[Boolean] = new Printable[Boolean] {
        def format(value: Boolean): String = s"Boolean: $value"
      }

      implicit val printableList: Printable[List[String]] = new Printable[List[String]] {
        def format(value: List[String]): String = s"List: $value"
      }

    }

    // Finally, instead of extending the Trait, as we would when defining a traditional interface, we use
    // implicit Printable[A] to tell the compiler to look for an implicit instance of whatever our type is to handle
    // the function call:
    object Printable {
      def format[A](value: A)(implicit p: Printable[A]): Unit = {
        println(p.format(value))
      }

    }

    // We also have to import the implicits defined above into our local context:
    import PrintableInstances._

    Printable.format(123)
    Printable.format("Hello")
    Printable.format(true)
    Printable.format(List("Hello world", "Hello universe"))


    // For another alternative to method overloading, see the Magnet Pattern


  /** Variance */
    /* - Variance in scala defines how parameterized types (like List[A], Option[A], or Function[A, B]) relate to their
         subtypes. It determines whether instances of generic types can substitute for one another
       - There are three kinds of variance:
         - Covariant (+) - A type with a subtype relationship maintains that relationship with its container type. For
           example, if Dog is a subtype of Animal, then List[Dog] is a subtype of List[Animal]
         - Contravariant (-) - A type with a subtype relationship reverses that relationship in its container. For
           example, if Animal is a supertype of Dog, then Printer[Animal] is a subtype of Printer[Dog]
         - Invariant (default, no symbol) - A parameterized type has no relationship with other types, even if their
           parameters do. For example, Container[Dog] and Container[Animal] are completely unrelated, regardless of the
           relationship between Dog and Animal
     */

    // Below are examples of each type of variance:
    class Animal
    class Dog extends Animal

    // Covariance - List is covariant in A (+A), allowing substitution of List[Dog] for List[Animal] (subtype for supertype)
      val dogs: List[Dog] = List(new Dog)
      val animals: List[Animal] = dogs // Works due to covariance

    // You'll recognize that many default types in scala are covariant - List, Option, Vector - all of these accept a
    // subtype in place of a supertype


    // Contravariance - Printer is contravariant in A (-A), meaning Printer[Animal] can print Dog (supertype for subtype)
      class Printer[-A] {
        def print(value: A): Unit = println(value)
      }

      val animalPrinter: Printer[Animal] = new Printer[Animal]
      val dogPrinter: Printer[Dog] = animalPrinter // Works due to contravariance


    // Invariance - Container is invariant in A (no +/-), no relationship between Container[Dog] and Container[Animal]
      class Container[A](value: A)
      val dogContainer: Container[Dog] = new Container(new Dog)
      // val animalContainer: Container[Animal] = dogContainer  // Error - Container is invariant, so it does not matter
      //                                                                   that Dog is a subtype of Animal

}


object TypeClassesExercises extends App {
  /** Exercises */
  // 1. Create a type class called Show that defines a method show, which takes a value of type A and returns a String
  //    representation of that value. Implement the type class for String, Int, and, if you want a challenge, Option[A].
  //    Then, use the type class in an object called Show, and test the object for each type that you defined
  //    Hint: Remember to import your implicits

}






/** Exercise Solutions */
// 1.
//    trait Show[A] {
//      def show(value: A): String
//    }
//
//    object ShowInstances {
//      // Note that you have liberty as to what your implementations look like, below is just one example
//      implicit val showInt: Show[Int] = new Show[Int] {
//        def show(value: Int): String = s"Integer: $value"
//      }
//
//      implicit val showString: Show[String] = new Show[String] {
//        def show(value: String): String = s"String: $value"
//      }
//
//      // Here, we are using the type class again, because we need it for the A inside the Option
//      implicit def showOption[A](implicit showA: Show[A]): Show[Option[A]] = new Show[Option[A]] {
//        def show(value: Option[A]): String = value match {
//          case Some(a) => s"Some(${showA.show(a)})"
//          case None    => "None"
//        }
//      }
//    }
//
//    object Show {
//      def printShow[A](value: A)(implicit showInstance: Show[A]): Unit = {
//        println(showInstance.show(value))
//      }
//    }
//
//    import ShowInstances._
//
//    Show.printShow(100)
//    Show.printShow("Hello world")
//    Show.printShow(None: Option[Int])
//    Show.printShow(Some(100): Option[Int])
//    Show.printShow(Some("Test"): Option[String])
