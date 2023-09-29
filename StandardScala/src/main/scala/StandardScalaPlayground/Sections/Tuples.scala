package StandardScalaPlayground.Sections

object Tuples extends App {

  /** Tuple Basics */
    // Tuples in Scala can consist of 1-22 elements
    val tuple2: (Int, Int) = (1, 2)
    // The above type annotation is syntactic sugar for Tuple2[Int, Int]

    val mixedTuple: (Int, String, Boolean) = (1, "string", false)

    // Tuple elements are accessed with ._1, ._2 ... ._n
    val tup = (1, 2, 3)
    println(s"First was ${tup._1}, second was ${tup._2}, third was ${tup._3}")


}


object TuplesExercises extends App {
  /** Exercises */
  // 1. Get the first value from ("one", "two")

  // 2. Add the third and fourth values from (10, 20, 30, 40)

  // 3. Write the type signature for: val mixed = (true, 3, "house"), using Tuple3[ ]

}






/** Exercise Solutions */
// 1. ("one", "two")._1

// 2. val four = (10, 20, 30, 40)
//   four._3 + four._4

// 3. val mixed: Tuple3[Boolean, Int, String] = (true, 3, "house")