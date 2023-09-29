package StandardScalaPlayground.Sections

object MatchCase extends App {

  /** Match-Case (Pattern Matching) Basics */
    // Match-Case statements consist of a single value being pattern matched to a case:
    def matching(value: Any): Unit = {
      value match {
        case int: Int    => println(s"$value was $int, an Int")
        case str: String => println(s"$value was $str, a String")
        case _           => println("Anything else") // underscore here means "anything else"
      }
    }
    // Above, notice how you can access someAny, and its alias, int or str, in the case. They will not always be the same, but they are here.
    // This will become useful when dealing with more complex alias signatures and situations, as shown below


  /** Different Kinds of Matching */
    def matching2(input: Any): Unit = {
      case class MatchClass1(name123: String, age123: Int)
      case class MatchClass2(name: String, age: Int)

      // Below is a match-case with a lot of possible cases:
      val result = input match {
        case 0                        => "zero" // matches can be made by value
        case Boolean                  => "boolean type, no alias" // matches can be made by type without an alias
        case num: Int                 => s"num was $num" // or by type with an alias
        case num2: Int if (num2 > 20) => s"num2 was $num2" // or with an if condition added to the case. This won't ever execute, because the above case is more generic
        case MatchClass1(name, age)   => s"Name1 was $name, age1 was $age" // matching case's args can be given an alias
        case mc2: MatchClass2         => s"Name2 was ${mc2.name}, age2 was ${mc2.age}" // Or you can give the type an alias and access its members with .
        case str: String              => if (str.length > 2) true
                                          else false    // You can perform further operations and call functions in a match-case
        case _                        => "anything else"
      }
    }


  /** Match with Either and the @ sign */
    // - We can use the @ symbol will bind "all" to the entire Some(value). Therefore, we can return Some(value) instead of just value.
    def someMatch(seed: Option[Any]) = seed match {
      case Some(value: Int) => value      // return just value for Some(Integer)
      case all @ Some(_)    => all        // return entire Some(value) for any value
      case None             => None
    }

    println(s"someMatch(Some(15)) was ${someMatch(Some(15))}")
    println(s"someMatch(Some('Hello')) was ${someMatch(Some("Hello"))} - we grabbed the Some( )")
    println(s"someMatch(None) was ${someMatch(None)}")


  /** Match Function example */
    // Below is a list corresponding to each case in the pattern match:
    val mixedList = List(None, Some(123), Some("someString"), (None, Some(2)), (Some("someString in a tuple"), None), true)

    // Iterate over the list
    val result2 = mixedList.map(element =>
      element match {
        case None => "Type was Option[Any], val Null"
        case Some(value: Int) => s"Type was Option[Int], value was $value"
        case Some(value: String) => s"Type was Option[String], value was $value"
        case (nothing: Option[Any], Some(value: Int)) => s"Type was Tuple2[Option[Any], Option[Int]], values were ($nothing, $value)"
        case (Some(value: String), nothing: Option[Any]) => s"Type was Tuple2[Option[String], Option[String]], values were ($value, $nothing)"
        case _ => "Something else"
      }
    )

    result2.foreach(println)

}


object MatchCaseExercises extends App {
  /** Exercises */
  // 1. Write a function accepting one "argument: Any" with a match-case that returns any String passed to it, capitalized,
  // else return "Error"

  // 2. Write a function accepting one arg of Any type that uses match-case to return an Int, only if the Int is even. Else return 0
  // The match case should be able to handle Strings, Ints, and Booleans explicitly, and anything else should return "something else"

  // 3. Return the value 42 from List(12, (Some("six"), None), 42, "seven") using match-case

}






/** Exercise Solutions */
// 1. def caps(input: Any) = input match {
//      case str: String => str.toUpperCase
//      case _ => "Error"
//    }

// 2. def even(input: Any) = {
//      input match {
//        case int: Int =>
//          if(int % 2== 0) int
//          else 0
//        case str: String => 0
//        case bool: Boolean => 0
//        case _ => "something else"
//      }
//    }

// 3. List(12, (Some("six"), None), 42, "seven").filter(x =>
//      x match {
//        case 42 => true
//        case _ => false
//      }
//    ).head