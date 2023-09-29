package StandardScalaPlayground.Sections



class Person(name: String, age: Int) {
  val someMember = "A member val's value"

  def sayHello = {
    println(s"Hi, my name is $name, I am $age years old")
  }

}



object Classes extends App {

  /** Class Basics */
    // New class instances require the 'new' keyword
    val person = new Person("John", 23)

    // Member functions can be accessed with Class.method
    person.sayHello

    // Member values can be accessed as well
    println(s"Params were: ${person.someMember}")


  /** Inheritance & Overrides */
    // Subclasses can inherit only one parent class (superclass)
    object AnotherPerson extends Person("John", 23) {}
    class AThirdPerson extends Person("John", 23) {}

    // Extending multiple parent objects is done via Traits in Scala.

    // If you'd like to override the implementation of a method or val in the superclass, use the 'override' keyword:
    class LetsOverride extends Person("John", 23) {
      override val someMember: String = "A new someMemberVal"
      override def sayHello: Unit = println("Goodbye")

    }


  /** Subtyping */
    // Anything that extends a class will pass a type check for that superclass. The same is true of traits and case classes:
    class RequiredSuper
    object Subtype extends RequiredSuper
    println(s"Subtype is an instance of its supertype: ${Subtype.isInstanceOf[RequiredSuper]}")


  /** Abstract classes */
    // Abstract classes are essentially templates that give a list of methods/vals that must be implemented by subclasses:
    abstract class AbstractClass {
      val abstractMember: Int
      def sayGreen(): Unit
    }

    class SomeSubclass extends AbstractClass {
      override val abstractMember: Int = 42
      override def sayGreen(): Unit = println("Green!")
    }
    // As you can see above, we also use the 'override' keyword to give the abstract methods and vals an implementation


}


object ClassesExercises extends App {
  /** Exercises */
  // 1. Create a class that takes one String to initialize. Initialize an instance of that class, and print the String from
  //    outside of the class

  // 2. Create a class with two methods. Create an object that extends the class, and overrides one of the methods


}






/** Exercise Solutions */
// 1. class Hello(str: String) {
//    val fromSig: String = str
//  }
//
//  val someHello = new Hello("Hello")
//  println(someHello.fromSig)

// 2. class TwoMethods {
//    def meth1(str: String): String = str
//    def meth2(int: Int): Int = int
//  }

//  object SubClass extends TwoMethods {
//    override def meth1(str: String): String = str + "Adding to the implementation"
//  }
//  println(SubClass.meth1("SomeString"))

