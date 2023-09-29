package StandardScalaPlayground.Sections

object ForEach extends App {

  /** .foreach( ) Basics */
    // .foreach( ) applies a function to every element in a Collection
    // It only differs from .map( ) because it does not return a modified Collection. It is only for generating side-effects,
    // such as passing a value to another function, logging something, etc.

    val someList = List(1, 2, 3)
    someList.foreach(x => println(x))
    // Above, the alias "x" is given to each element in the list
    // This map is read as "For each element "x" in the list, apply println( )"

    // .foreach( ) returns a Unit, that is to say, nothing:
    val result = someList.foreach(x => x * 2)
    println(s"The returned value from .foreach was $result")

    // Just like .map( ), you can use the underscore to represent the element in the collection being operated on:
    someList.foreach(println(_))


  /** Multi-type and multi-line .foreach( ) statements */
    // Similar to .map( ) and other functions, use a { } when constructing multi-line .foreach( ) statements:
    println("Multi-line foreach:")
    List(1, 2).foreach { x =>
      println(x)
      println(x + 10)
    }

    // Same goes for handling types:
    println("Complex type foreach:")
    List(1, (2, 3), 4).foreach {
      case x: Int => println("Single")
      case (x: Int, y: Int) => println("Tuple")
    }


}


object ForEachExercises extends App {
  /** Exercises */
  // 1. Create a function that adds two elements of a Tuple2[Int, Int].
  // Use foreach to print this function applied to List((1, 10), (2, 20))


}






/** Exercise Solutions */
// 1. def sum(tuple: (Int, Int)) = {
//      tuple._1 + tuple._2
//    }
//    List((1, 10), (2, 20)).foreach(element =>
//      println(sum(element))
//    )
