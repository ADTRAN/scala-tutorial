package StandardScalaPlayground.Sections

import scala.util.{Failure, Success, Try}

object TryCatch extends App {

  /** Try-Catch Basics */
    // - A try-catch block is useful in cases where an operation may result in an exception being thrown.
    // - It allows us to specify what will happen if an exception is encountered, depending on what the exception is:
    val result = try {
      "myString".toInt
    } catch {
      case e: Exception => println(s"Couldn't convert, got exception: $e")
    }


  /** Multiple Exception Types */
    // An example with custom exception types:
    class TooSmall(message: String) extends Exception(message)
    class TooLarge(message: String) extends Exception(message)

    def sizeOf(num: Int) = {
      if (num < 10)       throw new TooSmall("The number was too small")
      else if (num > 10)  throw new TooLarge("The number was too large")
      else                "The number was just right"
    }

    val result2 = try {
      sizeOf(10)      // Try changing this value...
    } catch {
      case s: TooSmall  => s.getMessage
      case l: TooLarge  => l.getMessage
      case _: Exception => "Some other exception encountered"
    }

    println(s"Result was: $result2")

    // Above, we define two custom Exceptions, extending the Java Exception class. Then, we can handle them in
    // separate cases in our try-catch statement.


  /** The Try object */
    // - Scala also has a Try( ) object, which will return a Success( ) or Failure( ) once it evaluates:
    val result3: Try[String] = Try(sizeOf(90))
    result3 match {
      case Success(str) => println(str)
      case Failure(ex: TooSmall) => println("The number was too small")
      case Failure(ex: TooLarge) => println("The number was too large")
      case Failure(ex) => println(s"Some other exception: $ex")
    }

    // - Using .getOrElse on the Try object will get you the result if no exception is thrown, orElse if one is thrown,
    //   return the specified value:
    println(result3.getOrElse("OrElse"))

    // - Also, similar to the match case example above, you can use .recover to specify ways to handle any exceptions
    //   that are encountered, without bothering with the Success( ) or Failure( ) wrappers:
    result3.recover {
      case ex: TooLarge => println(s"Handling the exception $ex")
    }

}



object TryCatchExercises extends App {
  /** Exercises */
  // 1. Create a function that accepts a number, and divides 10 by that number. Protect against a divide-by-zero with a
  //    try-catch block. Return 0 in such a case

  // 2. Using try-catch, create a List[Int] from List("1", "none", "3", "4", "none"), substituting 0 when no direct
  //    conversion to an Integer can be made

}






/** Exercise Solutions */
// 1.
//    def divide(num: Int): Int = {
//      val result = try 10 / num
//      catch {
//        case e: Exception => println(s"Exception was: $e")
//          0
//      }
//
//      result
//    }
//
//    println(divide(0))

// Or:

//    def divide(num: Int): Int = {
//      val result = try 10 / num
//      catch {
//        case e: java.lang.ArithmeticException => println(s"Exception was: $e")
//          0
//      }
//
//      result
//    }


// 2.
//    val result = List("1", "none", "3", "4", "none").map { elem =>
//      try elem.toInt
//      catch {
//        case _: Exception => 0
//      }
//    }
//    println(result)
