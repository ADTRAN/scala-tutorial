package StandardScalaPlayground.Sections

object Options extends App {

  /** Option Basics */
    // Options wrap other types, and are for when a value may or may not be there
    // Options have only two values - Some(value) or None
    val couldBe: Option[String] = Some("Yes")

    // To get the value from a Some( ), use .get or .getOrElse:
    println(s"Our Option[String] couldBe was ${couldBe.get}")


  /** .getOrElse */
    // .getOrElse( ) allows you to provide a default value if you encounter a None:
    val no: Option[String] = None
    println(s"Our empty Option was ${no.getOrElse("An orElse value")}")


  /** .map( ) & Other Iterable Operations */
    // .map( ) allows you to modify the Option[ ] before .getOrElse:
    def some(int: Int) = {
      if (int > 5) None else Some(int)
    }

    val check = some(23).map(x => x + 5).getOrElse("Nothing")
    val check2 = some(2).map(x => x + 5).getOrElse("Nothing")


}


object OptionsExercises extends App {
  /** Exercises */
  // 1. Write a function that accepts one argument of Option[String] type.
  // Use match-case to return the value inside Some( ), or if it's a None, return "Error"

  // 2. Write a function that accepts one Option[Int]. In one line, return the value if it's Some(value) and "No" if None

}






/** Exercise Solutions */
// 1. def unwrap(input: Option[String]) = input match {
//      case Some(value) => value
//      case None => "Error"
//    }

// 2. def oneLine(input: Option[Int]) = {
//      input.getOrElse("No")
//    }
