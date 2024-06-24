package StandardScalaPlayground.Sections

object Functions extends App {

  /** Function/Method Basics */
    // Because everything in Scala is an object, you can call methods on anything with a .
    val myString = "Hello"
    println(s"Just using a dot: ${myString.size}")
    // Any time you wish to see a list of methods available to you, simply put a dot after an object,
    // and IntelliJ will give you a list


  /** Basic function example */
    // The following function takes a String and an Integer, and returns a String:
    def fun1(string1: String, int1: Int): String = {
      val result = s"$string1 is ${int1.toString} year(s) old"
      result
    }
    // Unless the 'return' keyword is used elsewhere - typically reserved for emergencies - the last value in the
    // function will be returned.
    // Notice above that if we didn't put a the 'result' value, we would return the "initialization of the val result", which
    // is nothing - Unit.

    // Calling the functionExample method above:
    val result = fun1("John", 23)
    println(s"First function result was $result")
    // Above, we didn't specify the parameter names when passing values into the function, because that's optional.
    // You could also do:
    fun1(string1 = "John", int1 = 23)

    // If your function consists of a single statement, the { } are optional, so sometimes you will see those omitted:
    def fun2 = println("A single statement function")
    fun2


  /** Default parameter values */
    // Giving your function parameters default values:
    def defaultVals(name: String = "John", age: Int = 26) = println(s"Default values function result was $name, $age years old")
    defaultVals()

    // You can pass arguments positionally without being explicit, omitting those with defaults:
    defaultVals("Mark")
    // If you wish to use a default value prior to specifying other arguments, you must be explicit:
    defaultVals(age = 34)
    // For this reason, defaults are usually specified at the end of function signatures.


  /** Chaining method calls */
    // Method calls are chained together by using multiple dots:
    val chain: String = "30"
    println(s"Chain a bunch of methods - ${chain.toInt.isWhole.isInstanceOf[Boolean].toString.size}") // size of "true" is 4


  /** Implicit Vals in functions */
    // Implicit vals allow a value to be passed around without explicitly passing it:
    implicit val justWorks: String = "Just Works"

    def implicitFunction()(implicit aString: String) = {
      println(s"My implicit value was - $aString")
    }

    // Calling the above function will automatically search for a fitting implicit in the scope - justWorks in this case
    implicitFunction()

    // Notice above that implicits are specified in a set of parameters in a separate set of ( ) after the normal arguments in the
    // function signature.
    // The compiler will look for an implicit value of the right type in your scope. If it can't find one, it will
    // not compile. If you have more than one implicit of the same type, you have to pass them explicitly, kind of defeating the point:
    implicit val oneImplicit: Int = 1
    implicit val twoImplicit: Int = 2

    def anotherImplicitFunction()(implicit int1: Int) = {
      println(s"My implicit value was - $int1")
    }

    anotherImplicitFunction()(twoImplicit)


  /** Infix Notation */
    // In scala, methods can be called without explicitly using a ".":
    List(1, 2, 3) map (x => x + 1) // (2, 3, 4)

    // One common use of this would be mathematical operators:
    println(s"1 + 2 = ${1 + 2} is the same as 1.+(2) = ${1.+(2)}")

    // In practice, it's normal to use the standard dot notation exclusively, except for special cases like math
    // operators, where everyone uses infix notation without thinking about it
}


object FunctionsExercises extends App {
  /** Exercises */
  // 1. Convert an Integer to a String using, a built-in method call

  // 2. Print whether #1 is a String or not, using a built-in method call

  // 3. Turn 3.3 into the string "false" using built-in method calls. Print it
        // Hint - since we are dealing with numbers, put ( ) around the 3.3 to have IntelliJ prompt you with methods after you put a .

  // 4. Create a method that takes two integers, and returns the sum. Set a variable equal to a call of the function, and print the result

  // 5. Same as #4, but this time do subtraction, and return the length of the result, converted to a String (using built-in methods)

  // 6. Create a method that takes a String and an Int, and returns a String.
  //    Give the string a default value. Set a variable equal to a call of the function that doesn't override the default value.

}






/** Exercise Solutions */
// 1. val someInt = 2.toString

// 2. println(someInt.isInstanceOf[String])

// 3. println(3.3.isWhole.toString)

// 4. def sum(a: Int, b: Int): Int = a + b
//    val result = sum(9, 10)
//    println(result)

// 5. def sub(a: Int, b: Int): Int = {
//      (a - b).toString.length
//    }
//
//    val result = sub(91231, 1024)
//    println(result)

// 6.
// def function1 (arg1: String = "Hello", arg2: Int): String = {
//    arg1
//  }
//  val defaultResult = function1(arg2 = 0)


