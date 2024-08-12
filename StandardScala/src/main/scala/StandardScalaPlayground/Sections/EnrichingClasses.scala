package StandardScalaPlayground.Sections

object EnrichingClasses extends App {

  /** Intro to Class Enrichment */
    // - Class enrichment, also known as the "pimp my library" pattern, refers to using implicit classes to add
    //   functionality to already-existent types

    // For example, the following will add functionality to the Int object, because the compiler will recognize that
    // there is an implicit class in scope, which takes an Int. Therefore, all of the implicit class's methods will be
    // available:
    implicit class RichInt(originalValue: Int) {  // Can only take one parameter
      def isEven: Boolean = originalValue %  2 == 0
      def sqrt: Double = Math.sqrt(originalValue)
    }

    println(s".isEven was: ${12.isEven}")
    println(s".sqrt was: ${12.sqrt}")

    // As noted above, the implicit class can only take one parameter, which is normal when dealing with implicits.
    // Also note that often, the enriching implicit class will be defined as follows, extending AnyVal:
    implicit class RichString(val originalValue: String) extends AnyVal {
     // ...
    }

    // The reason this is done is for optimization purposes. It makes the class a value class, which incurs no runtime
    // overhead.

}


object EnrichingClassesExercises extends App {
  /** Exercises */
  // 1. Enrich the Boolean class so that it has a method called flip!, which will turn true into false, and vice versa.
  //    Hint: Use backticks ` ` to accommodate an ! in your method name

}






/** Exercise Solutions */
// 1.
//    implicit class RichBoolean(originalValue: Boolean) {
//      def `flip!`: Boolean = {
//        !originalValue
//      }
//    }
//
//    Or:
//
//    implicit class RichBoolean(val originalValue: Boolean) extends AnyVal {
//      def `flip!`: Boolean = {
//        !originalValue
//      }
//    }
