package StandardScalaPlayground.Sections

object FlatMap extends App {
  // flatMap is a combination of the .map and .flatten functions.

  /** .flatten( ) Primer */
    // .flatten( ) and .flatMap( ) are for nested collections
    // .flatten( ) will unwrap the first inner collection, and merge all resulting elements into a single collection:
    println(s"Flatten makes List(List(1, 2), List(3, 4)) into: ${List(List(1, 2), List(3, 4)).flatten}")
    // What happened above was:
      // 1. The outer List( ) is left alone -> List( )
      // 2. The first level down from the outer List is ungrouped, and merged under the top List( ) -> 1 2 3 4 -> List(1, 2, 3, 4)


    println(s"Flatten makes List(List(1, List(2)), List(List(3), 4)) into: ${List(List(1, List(2)), List(List(3), 4)).flatten}")
    // What happened above was:
      // 1. Put our outermost List( ) to the side -> List( )
      // 2. Ungroup the first level down, and merge under the top List( ) -> 1 List(2) List(3) 4 -> List(1, List(2), List(3), 4)


  /** .flatMap( ) Basics */
    // .flatMap( ) first applies a function to each element in a collection (map), then disintegrates the top level of its returned collection on (flatten)
    // The mapped function must return an iterable:
    val addOne = List(1, 2, 3).flatMap(x => List(x + 1))
    println(s"addOne was: $addOne")

    // What happened above was:
      // 1. .map( ) -> To each element, add one, and wrap the result in a List --> List(List(2), List(3), List(4))
      // 2. .flatten( ) -> Ungroup the first level down, and combine them under the top -> 2 3 4 -> List(2, 3, 4)
          // - This is why the function has to return an iterable (collection). We need something valid to flatten.
    // So, above was equivalent to:
    //  println(s"Equivalent was: ${List(1, 2, 3).map(x => List(x + 1)).flatten}")


  /** .flatMap( ) function example */
    def listOfElement(element: Any): List[Any] = {
      List(element, element, element)
    }

    val triplicate = List("one", "two", "three").flatMap(listOfElement)
    println(s"triplicate was $triplicate")
    val tritriplicate = List("a", "b", "c").flatMap(listOfElement).flatMap(listOfElement)
    println(s"triplicate triplicated was $tritriplicate")

    // Above, we applied the function again to the intermediate List("a", "a", "a", "b", "b", "b", "c", "c", "c"). Two cycles total of Map, Flatten


}


object FlatMapExercises extends App {
  /** Exercises */
  // 1. Replace each element with a boolean reflecting whether it is an even number or not in List(1, 2, 3) using .flatMap

  // 2. Turn the list List(List(1, 2), List(3, 4)) into a single List, multiplying each element by itself

  // 3. Translate List(1, 2, 3).map(elem => List(elem * 2)).flatten to a .flatMap

}






/** Exercise Solutions */
// 1. List(1, 2, 3).flatMap( element =>
//    List(if(element % 2 == 0) true else false)
//  )

// 2. List(List(1, 2), List(3, 4)).flatMap(innerList => innerList.map(element => element * element))

// 3. List(1, 2, 3).flatMap(elem => List(elem * 2))
