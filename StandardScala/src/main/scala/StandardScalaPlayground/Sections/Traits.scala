package StandardScalaPlayground.Sections



trait FirstTrait {
  val aMember: String
}

trait SecondTrait {
  def tellMember(): Unit
}




object Traits extends App {

  /** Trait Basics */
    // Traits are most commonly used to provide abstract methods and vals to be implemented in their subclasses
    // Unlike classes, a subclass can extend multiple traits, separated by the 'with' keyword:
    object SubClass extends FirstTrait with SecondTrait {
      override val aMember: String = "John"
      override def tellMember(): Unit = println(s"Hello $aMember")
    }

    // Above, we used the 'override' keyword to give our traits' abstract method and val an implementation
    SubClass.tellMember()


}


object TraitsExercises extends App {
  /** Exercises */
  // 1. Create a trait that has an unimplemented method that takes 1 String and returns a String. Extend it in two separate objects,
  //    each with different implementations of the method

  // 2. Create two traits with one method each. Create a class to extend them both. Then, create an object to extend the class. Call
  //    each method using the object

}






/** Exercise Solutions */
// 1. trait SomeTrait {
//      def returnString(str: String): String
//    }
//
//    object Extend1 extends SomeTrait {
//      override def returnString(str: String): String = {
//        str
//      }
//    }
//    object Extend2 extends SomeTrait {
//      override def returnString(str: String): String = {
//        str + str
//      }
//    }

// 2.
//    trait Trait1 {
//      def stringOp1(str: String): String
//    }
//    trait Trait2 {
//      def stringOp2(str: String): String
//    }
//    class Class1 extends Trait1 with Trait2 {
//      override def stringOp1(str: String): String = {
//        str.reverse
//      }
//
//      override def stringOp2(str: String): String = {
//        str.toUpperCase()
//      }
//    }
//
//    object SubSubClass extends Class1
//    println(SubSubClass.stringOp1("Reverse"))
//    println(SubSubClass.stringOp2("capital"))
