package StandardScalaPlayground.Sections

object Apply {
  val y = 5
  def apply(x: Int): Int = {
    x + y
  }

  def apply(someString: String) = {
    println(s"String apply says $someString")
  }
}

class ClassApply(x: Int) {
  def apply(y: Int): Int = {
    x + y
  }
}


object RunApply extends App {

  /** Apply Basics */
    //  "apply" is method name with some magic in Scala. It is essentially a default constructor method.
    //  ObjectWithApply(x) is the same as calling ObjectWithApply.apply(x)

    println(s"Use the integer .apply ${Apply(5)}")

    // You can have multiple .apply methods. This targets the other .apply:
    Apply("Hello")

    // Classes can also use an .apply( ) method, separate from their signatures:
    val classApply = new ClassApply(5)
    println(s"Class apply example ${classApply(10)}")

    // Classes can be initialized and their .apply can be accessed simultaneously, using the
    // format 'new className(constructor)(apply)':
    val classInitApply = new ClassApply(50)(100)
    println(s"Class initialize and apply example $classInitApply")

    /** Case classes have a built-in apply, and so do not need you to specify one, or use 'new'. See [[CaseClasses]] */

}


object ApplyExercises extends App {
  /** Exercises */
  // 1. Create a class with two .apply( ) methods, and use both of them

}






/** Exercise Solutions */
// 1. class TwoApplies{
//     def apply(x: Int): Unit = {
//       println(s"Hello $x")
//     }
//
//     def apply(someString: String): Unit = {
//       println(s"String apply says $someString")
//     }
//   }
//
//   val myInstance = new TwoApplies
//   myInstance.apply(12)
//   myInstance.apply("Hello")