package StandardScalaPlayground.Sections

object ForComprehensions extends App {
  // for-comprehensions are syntactic sugar for other operations, meaning anything that can be done
  // with a for-combination can be done using combinations of .foreach, .flatMap, .map, and .filter
  // The primary purpose of for-comprehensions is to make nested operations easier to read. You will see them often

  /** For-comprehension basics */
    for {
      x <- List(1, 2)   // x is the sequence 1, 2 -> this type of operation called a "generator" in for-comprehensions
      y <- List(3, 4)   // y is the sequence 3, 4
    } yield ()  // yield nothing. More on yield later
    // It can be useful to think of our generators x and y as "for each element in the iterable..."
    // The return type of the yield will be the return type of the first expression


  /** No yield - .foreach( ) */
    // Not using yield with a for-comprehension is akin to nesting .foreach( ) operations
    println("Each element in each list appended to each other:")
    for {
      numGenerator <- List(1, 2)
      strGenerator <- List("aa", "bb", "cc")
    } println(numGenerator + strGenerator)
    // Above is equivalent to:
    // List(1, 2).foreach(first => List("aa", "bb", "cc").foreach(second => println(first + second)))


  /** Yield - .map( ) and .flatMap( ) */
    // Using yield will string your expressions together in a series of flatMaps terminating in a .map:
    val combo = for {
      numGenerator <- List(1, 2)
      charGenerator <- List('a', 'b', 'c')
      strGenerator <- List("-red", "-blue", "-green")
    } yield (numGenerator.toString + charGenerator + strGenerator)

    println(s"The combo was $combo")
    // Above is equivalent to:
    // List(1, 2).flatMap(first => List('a', 'b', 'c').flatMap(second => List("-red", "-blue",  "-green").map(third => first.toString + second + third)))


  /** if( ) - .filter( ) */
    // .filter( ) can be applied to your for-comprehension using if( ):
    val filterThis = for {
      someString <- List("red", "blue", "yellow")
      if(someString.length > 4)
    } yield someString

    println(s"Filter kicks out red and blue: $filterThis")


  /** Definitions */
    // You can use = in for-comprehensions to define temporary variables for use in the comprehension:
    val define = for {
      generator <- List(("first", "second"), ("third", "fourth"))
      first = generator._1
      second = generator._2
    } yield second

    // Above - only take the second element in our generator
    // Above is equivalent to:
    // List(("first", "second"), ("third", "fourth")).map { case (first, second) => second }
    println(s"Second elements were: $define")

}


object ForComprehensionExercises extends App {
  /** Exercises */
  // 1. Turn List(1, 2).flatMap(first => List("a", "b", "c").map(second => first + second)) into a for-comprehension

  // 2. For the lists List("b", "g", "q", "e", "o") and List("a", "x", "o", "g", "m", "z", "r", "t"), use a for-comprehension to
  // filter out any elements in one list that aren't in the other. If an element passes the filter, return "match"

}






/** Exercise Solutions */
// 1. for {
//    gen1 <- List(1, 2)
//    gen2 <- List("a", "b", "c")
//  } yield gen1 + gen2

// 2. for {
//    e1 <- List("b", "g", "q", "e", "o")
//    e2 <- List("a", "x", "o", "g", "m", "z", "r", "t")
//    if e1 == e2
//  } yield s"match - $e1"
// Above is equivalent to:
// List("b", "g", "q", "e", "o").flatMap(first => List("a", "x", "o", "g", "m", "z", "r", "t").filter(second => first == second).map(_ => s"match - $first"))
