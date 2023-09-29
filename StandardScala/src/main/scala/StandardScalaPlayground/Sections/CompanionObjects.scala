package StandardScalaPlayground.Sections


class CompanionObjPerson(id: String = "None") {
  var name: String = "DefaultValue"
  var age: Option[Int] = None
}


object CompanionObjPerson {
  // Constructor that uses name only:
  def apply(name: String): CompanionObjPerson = {
    val person1 = new CompanionObjPerson
    person1.name = name

    person1
  }

  // Constructor that uses name and age, and also specifies id:
  def apply(name: String, age: Int): CompanionObjPerson = {
    val person2 = new CompanionObjPerson("75139")
    person2.name = name
    person2.age = Some(age)

    person2
  }

}


object CompanionObjects extends App {

  /** Companion Object Basics */
    // Companion Objects are objects that instantiate an instance of a class with the same name:
    val justName = CompanionObjPerson("John")
    println(s"Companion object justName example ${justName.name}")

    // This pattern allows us to have multiple constructors, using multiple .apply( ) methods:
    val nameAndAge = CompanionObjPerson("John", 54)
    println(s"Companion object nameAndAge example name: ${nameAndAge.name} age: ${nameAndAge.age.get}")

}


object CompanionObjectsExercises extends App {
  /** Exercises */
  // 1. Create a companion object with two apply methods. Use them to instantiate a class with 3 parameters, 1 of which is optional.

}






/** Exercise Solutions */
// 1. class Dog(owner: String = "None", breed: String, age: Int)
//
//    object Dog {
//      def apply(breed: String, age: Int): Dog = {
//        new Dog(breed = breed, age = age)
//      }
//      def apply(owner: String, breed: String, age: Int): Dog = {
//        new Dog(owner, breed, age)
//      }
//    }