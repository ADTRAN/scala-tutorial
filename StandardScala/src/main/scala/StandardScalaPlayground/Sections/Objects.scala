package StandardScalaPlayground.Sections

object SomeObject extends App {
  def apply(someString: String) = {
    println(someString)
  }

  val someMember = 10
  def utilityMethod(x1: Int, x2: Int): Int = {
    x1 + x2
  }

}



object Objects extends App {

  /** Object Basics */
    // In most languages, "object" refers to an instance of a class. In Scala, we have the concept of Singleton Objects
    // They are classes with a single instance, which is why you cannot specify parameters in the signature
    // Referencing an Object in multiple places is referencing the same thing multiple times. You are not creating unique instances.

    /** Objects are instantiated using the .apply( ) method, covered in [[Apply]], which acts as a "default method" of sorts: */
    val newObject = SomeObject("Hello")
    // Above is the same as:
    val newObjectVerbose = SomeObject.apply("Hello")


    /** .apply( ) is most commonly used to instantiate classes, in a pattern known as using a "Companion Object",
     * which can be seen here -> [[CompanionObjPerson]]
     */

    // Objects can be used as containers for utility functions or member values:
    val added = SomeObject.utilityMethod(1, 2)
    println(s"1 + 2 was $added")

    val memberVal = SomeObject.someMember
    println(s"The member value was $memberVal")


}


object ObjectsExercises extends App {
  /** Exercises */
  // 1. Create an object with a method that prints your name. Call the method from outside the object

  // 2. Create an object with 2 member values that are integers, and a method to add two integers.
  //    Add the values from outside the object, using the object's method

}






/** Exercise Solutions */
// 1. object PrintName {
//    def print = {
//      println("John Smith")
//    }
//  }
//  PrintName.print


// 2. object Adder {
//      val myVal1: Int = 9
//      val myVal2: Int = 10
//
//      def add(int1: Int, int2: Int) = {
//        int1 + int2
//      }
//    }
//
//   Adder.add(Adder.myVal1, Adder.myVal2)


