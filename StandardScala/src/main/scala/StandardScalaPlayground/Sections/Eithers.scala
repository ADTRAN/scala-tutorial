package StandardScalaPlayground.Sections

object Eithers extends App {

  /** Either Basics */
    // Eithers have a value of Right(value) or Left(value)
    // The Right( ) signifies an expected, good result. Left( ) represents something has gone wrong. This is called being right-biased.
    // Unlike Option's Some(value) or None, having a value to work with on a Left (failure) aids in error handling

    // The type of an Either represents which type a Right and Left should be
    // Below expects a Left( ) to be an Int, and a Right( ) to be a String
    var someEither: Either[Int, String] = Right("string")
    // Reassigning to a Left( ):
    someEither = Left(1)


  /** Unwrapping Eithers */
    // The first way to unwrap Eithers is to use .getOrElse:
    val anotherEither: Either[Int, Int] = Right(12)
    val unwrapped1 = anotherEither.getOrElse("or else")
    println(s".getOrElse on a Right gave us: $unwrapped1")

    val yetAnotherEither: Either[Int, Int] = Left(12)
    val unwrapped2 = yetAnotherEither.getOrElse("or else")
    println(s".getOrElse on a Left gave us: $unwrapped2")

    // As you can see from above, .getOrElse will only unwrap the value for a Right( ). A Left( ) will trigger the orElse
    // case, because Left( )'s signify failure.

    // The other common way to unwrap Eithers is to use match-case:
    val listEithers: List[Either[Int, Int]] = List(Right(100), Left(200))
    val unwrappedList = listEithers.map {
      case Right(someVal) => someVal
      case Left(value) => value
    }

    println(s"The unwrapped List of Eithers was $unwrappedList")

    // For another example using a full match-case, see the "function example" below

    // If you already have isolated the Right( ) or Left( ), you can use .value:
    val unwrapRight = Right(12)
    println(s"Value of Right(12).value is ${unwrapRight.value}")


  /** A function example */
    def returnEither(value: Int): Either[String, String] = {
      if (value % 2 == 0) Right("Even")
      else Left("Odd")
    }

    val result = returnEither(25) match {
      case Right(value) => value      // If the number was even, get rid of the Right( ) wrapping
      case Left(aBadValue) => aBadValue   // Likewise for Left( )
    }

    // Above, if you wanted to keep the Left( ) or Right( ) wrapping, you would use 'someAlias @ Right(value) => someAlias',
    // since @ binds the alias to the whole value. You could also just do 'Right(value) => Right(value)'

    println(s"Result was $result")

}


object EithersExercises extends App {
  /** Exercises */
  // 1. Write a function that accepts one Either[Int, String]. Using match-case, return the entire Left(value) if Left(value),
  //    and return just the value if Right(value)

}






/** Exercise Solutions */
// 1. def wholeOrPart(input: Either[Int, String]) = input match {
//      case Right(value) => value
//      case whole @ Left(_) => whole
//    }



