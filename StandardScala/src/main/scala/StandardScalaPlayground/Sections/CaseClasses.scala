package StandardScalaPlayground.Sections

object CaseClasses extends App {

  /** Case Class Basics */
    // Case classes are usually used for modelling data:
    case class House(floors: Int, squareFeet: Int, address: String)

    val someHouse = House(2, 1500, "123 Main St.")
    // Notice above that unlike classes, we don't use "new" when instantiating an instance

    // Also unlike classes, parameters in the signature can be accessed with the .
    println(s"someHouse had ${someHouse.floors} floors")


  /** Copy & Comparison */
    // Case classes have a .copy( ) method that allows unmodified and modified copying:
    val case1 = House(1, 1, "1")
    val case2 = case1.copy()  // unmodified -> same as case1
    val case3 = case1.copy(floors = 2)  // modified first field -> still inherits last 2 fields

    println(s"case3's floors field was ${case3.floors}, but squareFeet was still ${case3.squareFeet}")

    // Case classes with the same content are regarded as identical:
    println(s"case1 and case2 are the same: ${case1 == case2}")


}


object CaseClassesExercises extends App {
  /** Exercises */
  // 1. Create a case class with 4 fields that are all Ints. Create a variable that initializes an instance.
  //    Print the sum of all the parameters

  // 2. Create a case class that takes a firstName string in its signature, and has one method that takes a lastName.
  //    Print the full name in the method


}






/** Exercise Solutions */
// 1. case class Operands(i1: Int, i2: Int, i3: Int, i4: Int)
//    val add = Operands(1, 10, 100, 1000)
//    println(add.i1 + add.i2 + add.i3 + add.i4)

// 2. case class FirstLast (first: String) {
//     def greeting(last: String): Unit = {
//      println(s"My name is $first $last")
//     }
//   }
//   FirstLast("myFirst").greeting("myLast")
//   Or, could call like:
//   val first = FirstLast("myFirst")
//   first.greetings("myLast")


