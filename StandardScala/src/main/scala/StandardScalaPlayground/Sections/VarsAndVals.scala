package StandardScalaPlayground.Sections

object VarsAndVals extends App {

  /** Vars and Vals Basics */
    // vals are immutable - meaning they cannot be changed once initialized.
    // Operations "altering" vals actually replace the val with a new instance
    // In Scala, it's considered good practice to deal with immutable data types:
    val name = "John"
    println(s"Name was $name") // placing an 's' before a string allows for variable insertion (interpolation)

    // "Updating" a val entails creating another val:
    val newName = name + " Smith"

    // vars, on the other hand, are mutable:
    var count = 0
    count += 1
    println(s"Count is now: $count")

    // In Scala, the convention is to use camelCase for names of vars, vals, and methods.


  /** Multi-line Vars and Vals */
    // In Scala, { } are optional for single-line statements, but must be used to create vars and vals with multi-statement values:
    val one = { 1 }
    val three = {
      val two = 2
      one + two
    }
    println(s"One plus two equals: $three")


  /** Lazy vals */
    // Lazy vals are only evaluated the first time they are referenced
    // This is useful if an operation takes a lot of time, but may not be used in all cases
    lazy val someLongOperation = Thread.sleep(1000000)


  /** Implicit vals are another type of val, and will be covered in the [[Functions]] section */

}


object VarsAndValsExercises extends App {
  /** Exercises */
  // 1. Initialize an immutable value to your name

  // 2. Print your name from #1

  // 3. Print "Hello (yourName)" using your name from #1 for (yourName)

  // 4. Initialize a mutable value to 23, add to it twice, and print it
}






/** Exercise Solutions */
// 1. val myName = "Your Name"

// 2. println(myName)
//    print(myName) --> no newline

// 3. println(s"Hello $myName")

// 4. var mutable = 23
//    mutable = mutable + 1
//    mutable += 1
//    println(mutable)