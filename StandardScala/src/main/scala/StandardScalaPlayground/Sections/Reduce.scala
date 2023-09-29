package StandardScalaPlayground.Sections

object Reduce extends App {

  /** .reduce( ) Basics */
    // .reduce( ) reduces a collection into a single value using a binary function:
    val oneString = List("a", "ab", "abc").reduce((x, y) => x + y)
    println(s"Combined string was: $oneString")
    // Above, we combined the List's elements, sort of like a .fold( ). -> ((a + ab) + abc)
    // x started as "a", then became "aab", then "aababc" -> x is the accumulator, y is the "next element"

    // Reduces can be written without aliases:
    println(s"No aliases ${List(1, 2, 3).reduce(_ + 1 + _)}")
    // Above, the first _ represents the accumulator, the second represents the new element


  /** Using .reduce( ) to Filter */
    def maximum (x: Int, y: Int) = {
      println(s"x was $x, y was $y")
      if(x > y) x else y
    }

    val max = List(8, 24, 19, 1).reduce((x, y) =>
      maximum(x, y) // return the max
    )

    println(s"Max was $max")


  /** .reduceRight( ) and .reduceLeft( ) */
    // .reduceLeft( ), unlike .reduce( ), guarantees that the collection will be processed from left -> right:
    val reduceLeft = List(1, 2, 3).reduceLeft(_ - _)
    println(s".reduceLeft( ) gave us: $reduceLeft")
    // Above gives us ((1 - 2) - 3) = -4

    // .reduceRight( ) will process the collection from right -> left:
    val reduceRight = List(1, 2, 3).reduceRight(_ - _)
    println(s".reduceRight( ) gave us: $reduceRight")
    // Above gives us (1 - (2 - 3)) = 2



  /** Use cases */
  // When and why use .reduce( ) over .fold( )?
  // -> .reduce( ) is less generic than .fold( ). A reduce will return the same type as (or a supertype of) as the collection
  // being reduced, whereas a fold can return anything.

  // Opinion:
  // If you want to keep a single element from the collection, it's easier to think in terms of .reduce( )
  // If you want to use all the elements to make a new element, it's easier to think in terms of .fold( )


}


object ReduceExercises extends App {
  /** Exercises */
  // 1. Create a method that returns the longer of two Strings.
  // Use .reduce( ) and that method to get the longest item in List("Long", "Longest", "Longer")

}






/** Exercise Solutions */
// 1. def longer(str1: String, str2: String) = {
//      if (str1.length > str2.length) str1 else str2
//    }
//
//    List("Long", "Longest", "Longer").reduce((x, y) => longer(x, y))
